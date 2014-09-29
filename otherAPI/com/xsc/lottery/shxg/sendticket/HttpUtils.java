package com.xsc.lottery.shxg.sendticket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Inflater;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xg.client.comm.ParamVO;
import com.xg.client.comm.XXmlUtil;
import com.xg.client.crypter.Base64;
import com.xg.client.crypter.CompressBytes;
import com.xg.client.crypter.CreateDesKey;
import com.xg.client.crypter.CryptorHMAC;
import com.xg.client.crypter.CryptorXDES;
import com.xg.client.crypter.CryptorXRSA;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.NetWorkUtil;

public class HttpUtils
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static String shxgKey = Configuration.getInstance().getValue("shxg.md5key");
	
	private final CryptorXRSA rsacryptor = new CryptorXRSA();
	private final CreateDesKey createkey = new CreateDesKey();
	
	public static byte[] decompressBytes(byte input[]) {
		int cachesize = 1024;
		Inflater decompresser = new Inflater();
		byte output[] = new byte[0];
		decompresser.reset();
		decompresser.setInput(input);
		ByteArrayOutputStream o = new ByteArrayOutputStream(input.length);
		try {
			byte[] buf = new byte[cachesize];
			int got;
			while (!decompresser.finished()) {
				got = decompresser.inflate(buf);
				o.write(buf, 0, got);
			}
			output = o.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output;
	}
 
 public ParamVO httpXGRequest(ParamVO inparamVO){
	 ParamVO returnPV = null;
	 String outxmldata = XXmlUtil.xml2String(inparamVO.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim();
	 logger.info("向新冠发送请求内容："+outxmldata);
	 String _outpasswds = createkey.createDesKey();
	 String outpasswds = Base64.encodeS(rsacryptor.encryptE(_outpasswds.getBytes()));
		
	 outpasswds = outpasswds.replaceAll("\\+","%2B");
		
	CryptorXDES desEx = new CryptorXDES();
	desEx.setkey(_outpasswds.substring(0, 8));
		
	byte[] src = outxmldata.getBytes();
	byte[] zsrc =  CompressBytes.compressBytes(src);
	
	String outmessage = new String(Base64.encode(desEx.encrypt(zsrc)));
			
	CryptorHMAC hmacEx = new CryptorHMAC();
		
	String outcheckor = Base64.encodeS(hmacEx.digestXMAC(shxgKey, outmessage + _outpasswds.substring(0, 8)));		
	outmessage = outmessage.replaceAll("\\+","%2B");
	outcheckor = outcheckor.replaceAll("\\+","%2B");
	String shxgUrl = Configuration.getInstance().getValue("shxg.url");	
	if (shxgUrl != null) {
		try{
			shxgUrl = shxgUrl+"?passwds="+outpasswds+"&message="+outmessage+"&checkor="+outcheckor;
			logger.info("向新冠发送请求信息："+shxgUrl);
			String result = NetWorkUtil.sendRequest(shxgUrl, null);
			logger.info("新冠响应信息："+result);
			if (!StringUtils.isEmpty(result)) {				
				ParamVO invo = new ParamVO(XXmlUtil.string2Xml(result));						
				String inpasswds = invo.getStringValue("@passwds");
				String incheckor = invo.getStringValue("@checkor");
				String inmessage = invo.getXmlRoot().getValue();
				if ((inpasswds.length()>=1)&&(incheckor.length()>=1)&&(inmessage.length()>=1))
				{
					String _inpasswds = new String(rsacryptor.decryptE(Base64.decode(inpasswds)));
					String recvKey = _inpasswds.substring(0, 8);
					CryptorHMAC hmac = new CryptorHMAC();				
					String newCheckor = new String(Base64.encode(hmac.digestXMAC(shxgKey, inmessage + recvKey)));
					CryptorXDES des = new CryptorXDES();
					des.setkey(recvKey);		
					byte[] src1 = des.decrypt(Base64.decode(inmessage));	
					byte[] zsrc1 = decompressBytes(src1);
					String inxmldata = new String(zsrc1).trim();
					logger.info("新冠响应内容："+inxmldata);
					if (incheckor.equals(newCheckor)){
						//正常
						invo = new ParamVO(XXmlUtil.string2Xml(inxmldata));
						returnPV = invo;
					}else{
						System.out.println("消息检查错误.");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	return returnPV;
}

}
