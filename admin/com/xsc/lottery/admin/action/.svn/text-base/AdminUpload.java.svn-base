package com.xsc.lottery.admin.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.util.Configuration;
import com.xsc.lottery.util.FileUtils;

@Scope("prototype")
@SuppressWarnings("serial")
@Controller("admin.upload")
public class AdminUpload extends AdminClienBaseAction
{
	@Autowired
    private LotteryHandleFactory handleFactory;
	
	private String path = Configuration.getInstance().getValue("download.rootdir");
	
	private File upload;
	
	private String uploadFileName;
	
	private String type;
	
	private static final int BUFFER_SIZE = 4096 ;
	
	public String index()
    {
		try {
			if(null != upload) {
			    logger.info("Uploading file " + uploadFileName + "...");
				File saveFile = new File(path + uploadFileName);
				copy(upload, saveFile);
				parse(upload);
			}
			else {
				throw new RuntimeException("上传空间的name必须为upload");
			}
		}
		catch (Exception e) {
			setJsonString("<span style=\"color:red\">" + e.getMessage() + "</span>");
			return AJAXJSON;
		}
		setJsonString("<span style=\"color:green\">上传成功!</span>");
		return AJAXJSON;
    }
	
	private void copy(File src, File dst) 
	{
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = in.read(buffer)) > 0) {
					out.write(buffer, 0, count);
				}
			} 
			finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} 
		catch (Exception e) {
			//e.printStackTrace();
		}
	} 
	
	@SuppressWarnings("unchecked")
	public void parse(File file) throws Exception
    {
		String xml = FileUtils.readFile0(file, "UTF-8");
		xml = xml.substring(xml.indexOf("<?xml"));
        try {
        	Document document = DocumentHelper.parseText(xml);
        	Element root = document.getRootElement();
        	//getElementList(root);
        	checkHead(getElement(root, "head"));
        	LotteryType lotteryType;
        	if(type == null)
        	{
        		lotteryType = LotteryType.enToType("jczq");
        	} else
        	{
        		lotteryType = LotteryType.enToType(type);
        	}
        	
    		handleFactory.getLotteryHandleFactory(lotteryType).
    			parseXML(root);
        }
        catch (Exception e) {
            e.printStackTrace();
        	throw new Exception("解析错误: " + e.getMessage());
        }
    }
	
	@SuppressWarnings("unchecked")
	public Element getElement(Element root, String name)
	{
		List elements = root.elements(); 
		for (Iterator it = elements.iterator(); it.hasNext();) {
			Element elem = (Element) it.next(); 
			if(elem.getName().equals(name)) {
				return elem;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkHead(Element head) 
	{ 
        if(head == null) {
        	return false;
        }
        Map<String, String> map = new HashMap<String, String>();
        List elements = head.elements(); 
        for (Iterator it = elements.iterator(); it.hasNext();) {
            Element elem = (Element) it.next(); 
            if (elem.elements().size() == 0) {
                String name = elem.getName(); 
                String value = elem.getTextTrim(); 
                map.put(name, value);
            } 
        }
        
        type = map.get("type");
        System.out.println(map);
        return true;
    }
	
	//递归遍历
	@SuppressWarnings("unchecked")
	public void getElementList(Element element)
	{ 
        List elements = element.elements(); 
        if (elements.size() == 0) { 
            String xpath = element.getPath(); 
            String value = element.getTextTrim(); 
            System.out.println(xpath + "   " + value);
        }
        else {
            for (Iterator it = elements.iterator(); it.hasNext();) { 
                Element elem = (Element) it.next();
                getElementList(elem); 
            } 
        } 
    }
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
}
