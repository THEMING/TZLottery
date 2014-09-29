package com.xsc.lottery.web.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.Filedownlod;
import com.xsc.lottery.service.business.FiledownlodService;
import com.xsc.lottery.util.Configuration;

@Scope("prototype")
@Controller("customer.download")
@SuppressWarnings("serial")
public class FileDownloadAction extends LotteryBaseAction
{
    @Autowired
	private FiledownlodService filedownlodService;
    
	private Filedownlod filedownlod;
	
	private String path = Configuration.getInstance().getValue("download.rootdir");
	
	private String fileName;// 要下载的文件名
	
	public String index() 
	{
		if (filedownlodService.getFiledownlodByName(fileName).isEmpty()) 
		{
			filedownlod = new Filedownlod();
			filedownlod.setCount(1);
			filedownlod.setFileName(fileName);
			filedownlodService.save(filedownlod);
		} 
		else 
		{
			filedownlod = filedownlodService.getFiledownlodByName(fileName).get(0);
			filedownlod.setCount(filedownlod.getCount() + 1);
			filedownlodService.update(filedownlod);
		}
		return SUCCESS;
	}
	
	// 下载文件
	public InputStream getDownloadFile() 
	{
		InputStream istream = null;
		try {
			istream = new FileInputStream(path + getFileName());
		}
		catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("file not found");
		}
		return istream;
	}
	
	public void setFileName(String fileName) 
	{
		try {
			// 解决中文文件名问题
			this.fileName = new String(fileName.getBytes("ISO-8859-1"), "GBK");
		} 
		catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
	}
	
	public String getFileName() 
	{
		String name = "";
		try {
			// 解决中文文件名问题
			name = new String(fileName.getBytes("GBK"), "ISO8859-1");
		}
		catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return name;
	}

	public FiledownlodService getFiledownlodService() {
		return filedownlodService;
	}

	public void setFiledownlodService(FiledownlodService filedownlodService) {
		this.filedownlodService = filedownlodService;
	}

	public Filedownlod getFiledownlod() {
		return filedownlod;
	}

	public void setFiledownlod(Filedownlod filedownlod) {
		this.filedownlod = filedownlod;
	}
	
	
}
