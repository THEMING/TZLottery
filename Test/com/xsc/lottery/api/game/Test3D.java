package com.xsc.lottery.api.game;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.zip.Inflater;

import com.xg.client.comm.ParamVO;
import com.xg.client.comm.XXmlUtil;
import com.xg.client.crypter.Base64;
import com.xg.client.crypter.CompressBytes;
import com.xg.client.crypter.CreateDesKey;
import com.xg.client.crypter.CryptorHMAC;
import com.xg.client.crypter.CryptorXDES;
import com.xg.client.crypter.CryptorXRSA;

public class Test3D {

	/**
	 * @param args
	 */
	
	private static final long serialVersionUID = 1L;
	
	private final CryptorXRSA rsacryptor = new CryptorXRSA();
	private final CreateDesKey createkey = new CreateDesKey();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			Random random = new Random();
			ParamVO inparamVO = new ParamVO("Req");
			inparamVO.setStringValue("@funcid", "8882");
			inparamVO.setStringValue("@agent","8066");
			inparamVO.setStringValue("@memo","text");
			inparamVO.setStringValue("@lottid","18"); //05 3D；09 快乐十分
			inparamVO.setStringValue("@period", "140410017");
			inparamVO.setStringValue("@hmid","K3_33143"+random.nextInt(100000));
			inparamVO.setStringValue("@idcard", "343525197604231219");
			inparamVO.setStringValue("@realname","aaa");
			inparamVO.setStringValue("@useraddress","bbb@sohu.com");
			inparamVO.setStringValue("@hmcount","1");
			
			ParamVO tick = inparamVO.addXmlNode("t");
			String ticket = "5";
			tick.addStringValue("@c", "1_"+ticket+":1:1:1");
			
//			int aa = 0;
//			while (aa<3){
//				int t = random.nextInt(20);
//				if (t==0) t = 20;
//				String tt = t+"";
//				if (tt.length()<2) tt = "0"+tt;
//				if (ticket.indexOf(tt)>-1){
//					continue;
//				}else{
//					ticket= ticket+tt+",";
//					aa++;
//				}
//			}
//			ticket = ticket.substring(0,ticket.length()-1);
//			tick.addStringValue("@c", "1_"+ticket+":1:1:3");
//			
//			ticket = "";
//			aa = 0;
//			while (aa<5){
//				int t = random.nextInt(20);
//				if (t==0) t = 20;
//				String tt = t+"";
//				if (tt.length()<2) tt = "0"+tt;
//				if (ticket.indexOf(tt)>-1){
//					continue;
//				}else{
//					ticket= ticket+tt+",";
//					aa++;
//				}
//			}
//			
//			tick = inparamVO.addXmlNode("t");	
//			ticket = ticket.substring(0,ticket.length()-1);
//	        tick.addStringValue("@c", "2_"+ticket+":1:2:2");
			
	        
			inparamVO.addStringValue("@funcid","8882");
			inparamVO.addLongValue("@timestamp", System.currentTimeMillis());
		
			String outxmldata = XXmlUtil.xml2String(inparamVO.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim();
			System.out.println(outxmldata);
			
			Test3D test = new Test3D();
			
			String _outpasswds = test.createkey.createDesKey();
			String outpasswds = Base64.encodeS(test.rsacryptor.encryptE(_outpasswds.getBytes()));
			
			outpasswds = outpasswds.replaceAll("\\+","%2B");
			
			CryptorXDES desEx = new CryptorXDES();
			desEx.setkey(_outpasswds.substring(0, 8));
			
			byte[] src = outxmldata.getBytes();
			byte[] zsrc =  CompressBytes.compressBytes(src);
		
			String outmessage = new String(Base64.encode(desEx.encrypt(zsrc)));
				
			CryptorHMAC hmacEx = new CryptorHMAC();
			String md5Key = "1q2w3e4r56y";		
			
			String outcheckor = Base64.encodeS(hmacEx.digestXMAC(md5Key, outmessage + _outpasswds.substring(0, 8)));		
			outmessage = outmessage.replaceAll("\\+","%2B");
			outcheckor = outcheckor.replaceAll("\\+","%2B");
			
			String callurl = "http://180.168.215.243:8080/gxcams/cpt.svc";
			
			if (callurl != null) {
				try{
					callurl = callurl+"?passwds="+outpasswds+"&message="+outmessage+"&checkor="+outcheckor;
					URL url = new URL( callurl);
				    System.out.println(callurl);
					HttpURLConnection connection = null;
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setDoOutput(true);
					
					int rc = connection.getResponseCode();						
					if (rc == HttpURLConnection.HTTP_OK) {				
						InputStream in = null;
						in = connection.getInputStream();
						BufferedReader data = new BufferedReader(new InputStreamReader(in));
						String temp;String result = "";
						while ((temp = data.readLine()) != null) {
							result += temp;
							temp = null;
						}
						System.out.println(result);
						
						
						ParamVO invo = new ParamVO(XXmlUtil.string2Xml(result));						
						String inpasswds = invo.getStringValue("@passwds");
						String incheckor = invo.getStringValue("@checkor");
						String inmessage = invo.getXmlRoot().getValue();
						if ((inpasswds.length()<1)||(incheckor.length()<1)||(inmessage.length()<1))
							return;			
						
						String _inpasswds = new String(test.rsacryptor.decryptE(Base64.decode(inpasswds)));
						String recvKey = _inpasswds.substring(0, 8);
						CryptorHMAC hmac = new CryptorHMAC();				
						String newCheckor = new String(Base64.encode(hmac.digestXMAC(md5Key, inmessage + recvKey)));
						CryptorXDES des = new CryptorXDES();
						des.setkey(recvKey);		
						byte[] src1 = des.decrypt(Base64.decode(inmessage));	
						byte[] zsrc1 = decompressBytes(src1);
						String inxmldata = new String(zsrc1).trim();
						System.out.println(inxmldata);
						if (incheckor.equals(newCheckor)){
							//正常
							invo = new ParamVO(XXmlUtil.string2Xml(inxmldata));
							String  errcode = invo.getStringValue("@errcode");
							String  errdesc = invo.getStringValue("@errdesc");
							System.out.println("返回结果："+errcode);
							System.out.println("返回描述："+errdesc);
							
						}else{
							System.out.println("消息检查错误.");
							
						}
						data.close();
						in.close();				
					}
					System.out.println("aaaaaaaaa");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
	
	}

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
}
