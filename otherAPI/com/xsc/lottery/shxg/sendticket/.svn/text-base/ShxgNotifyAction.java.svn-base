package com.xsc.lottery.shxg.sendticket;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xg.client.comm.ParamVO;
import com.xg.client.comm.XXmlUtil;
import com.xg.client.crypter.Base64;
import com.xg.client.crypter.CompressBytes;
import com.xg.client.crypter.CreateDesKey;
import com.xg.client.crypter.CryptorHMAC;
import com.xg.client.crypter.CryptorXDES;
import com.xg.client.crypter.CryptorXRSA;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

/**新冠通知 */


@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Shxg.notify")
public class ShxgNotifyAction extends LotteryClientBaseAction
{
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static String wAgent = Configuration.getInstance().getValue("shxg.agent");
	private final static String shxgKey = Configuration.getInstance().getValue("shxg.md5key");
	
	private final CryptorXRSA rsacryptor = new CryptorXRSA();
	private final CreateDesKey createkey = new CreateDesKey();
	
	private String passwds;
	private String message;
	private String checkor;
	
    /**
     * 新冠票通知
     */
    public String index() throws Exception
    {
    	ParamVO invo = null;
    	if ((passwds.length()>=1)&&(checkor.length()>=1)&&(message.length()>=1))
		{
			String _inpasswds = new String(rsacryptor.decryptE(Base64.decode(passwds)));
			String recvKey = _inpasswds.substring(0, 8);
			CryptorHMAC hmac = new CryptorHMAC();				
			String newCheckor = new String(Base64.encode(hmac.digestXMAC(shxgKey, message + recvKey)));
			CryptorXDES des = new CryptorXDES();
			des.setkey(recvKey);		
			byte[] src1 = des.decrypt(Base64.decode(message));	
			byte[] zsrc1 = HttpUtils.decompressBytes(src1);
			String inxmldata = new String(zsrc1).trim();
			logger.info("新冠通知内容："+inxmldata);
			if (checkor.equals(newCheckor)){
				//正常
				invo = new ParamVO(XXmlUtil.string2Xml(inxmldata));
			}else{
				System.out.println("消息检查错误.");
			}
		}
    	PrintWriter out = getResponse().getWriter();
    	ParamVO msgVo = new ParamVO("MSG");
    	msgVo.setStringValue("@errcode","0000");
    	msgVo.setStringValue("@errdesc","接受正常");
    	ParamVO returnVo = new ParamVO("Resp");
    	returnVo.setStringValue("@errcode","0000");
    	returnVo.setStringValue("@errdesc","接受正常");
    	returnVo.setStringValue("@funcid",invo.getStringValue("@funcid"));
    	returnVo.setStringValue("@agent",wAgent);
    	returnVo.setStringValue("@memo",invo.getStringValue("@memo"));
    	returnVo.setStringValue("@lottid",invo.getStringValue("@lottid"));
    	returnVo.setStringValue("@period",invo.getStringValue("@period"));
    	returnVo.setStringValue("@hmid",invo.getStringValue("@hmid"));
    	String outxmldata = XXmlUtil.xml2String(returnVo.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim();
		logger.info("响应新冠通知内容："+outxmldata);
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
		msgVo.setStringValue("@passwds", outpasswds);
		msgVo.setStringValue("@checkor", outcheckor);
		msgVo.getXmlRoot().setText(outmessage);
    	out.write(XXmlUtil.xml2String(msgVo.getXmlRoot(), XXmlUtil.getMultilineXmlFormat()).trim());
    	return null;
    }
    
    public String responseForXG()
    {
    	return null;
    }

	public String getPasswds() {
		return passwds;
	}

	public void setPasswds(String passwds) {
		this.passwds = passwds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCheckor() {
		return checkor;
	}

	public void setCheckor(String checkor) {
		this.checkor = checkor;
	}
}
