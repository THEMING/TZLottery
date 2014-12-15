package com.xsc.lottery.admin.action.article;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.util.Configuration;
  
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("image.upload")
public class UploadImageAction extends AdminBaseAction{  
  
      
    public String upload() throws FileUploadException, IOException{  
          
        HttpServletRequest request = ServletActionContext.getRequest();  
        HttpServletResponse response = ServletActionContext.getResponse();  
        PrintWriter out = response.getWriter();  
          
        String savePath = request.getRealPath("/")+"attached/";  
        File test = new File(savePath);  
        if(!test.exists()){  
            test.mkdirs();  
        }  
        //文件保存目录URL  
        String saveUrl  = request.getContextPath() + "/attached/";  
  
        //定义允许上传的文件扩展名  
        HashMap<String, String> extMap = new HashMap<String, String>();  
        extMap.put("image", "gif,jpg,jpeg,png,bmp");  
//      extMap.put("flash", "swf,flv");  
//      extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");  
//      extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");  
  
        //最大文件大小  
        long maxSize = 1000000;  
  
        response.setContentType("text/html; charset=UTF-8");  
  
        if(!ServletFileUpload.isMultipartContent(request)){  
            out.print(getError("请选择文件。"));  
            return "err";  
        }  
        //检查目录  
        File uploadDir = new File(savePath);  
        if(!uploadDir.isDirectory()){  
            out.print(getError("上传目录不存在。"));  
            return "err";  
        }  
        //检查目录写权限  
        if(!uploadDir.canWrite()){  
            out.print(getError("上传目录没有写权限。"));  
            return "err";  
        }  
  
        String dirName = request.getParameter("dir");  
        if (dirName == null) {  
            dirName = "image";  
        }  
        if(!extMap.containsKey(dirName)){  
            out.print(getError("目录名不正确。"));  
            return "err";  
        }  
        //创建文件夹  
        savePath += dirName + "/";  
        saveUrl += dirName + "/";  
        File saveDirFile = new File(savePath);  
        if (!saveDirFile.exists()) {  
            saveDirFile.mkdirs();  
        }  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
        String ymd = sdf.format(new Date());  
        savePath += ymd + "/";  
        saveUrl += ymd + "/";  
        File dirFile = new File(savePath);  
        if (!dirFile.exists()) {  
            dirFile.mkdirs();  
        }  
          
          
         MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper)request;    
            String fileName = wrapper.getFileNames("imgFile")[0];    
            File file = wrapper.getFiles("imgFile")[0];    
              
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
            if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){  
                out.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));  
                  
            }  
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;  
          
            saveUrl += newFileName;    
        
            FileOutputStream fos = new FileOutputStream(savePath + newFileName);    
            byte[] buffer = new byte[1024];    
            InputStream in = new FileInputStream(file);    
               
            try {    
                int num = 0;    
                while ((num = in.read(buffer)) > 0) {    
                    fos.write(buffer, 0, num);    
                }    
            } catch (Exception e) {    
                e.printStackTrace(System.err);    
            } finally {    
                try{    
                    if(in != null)    
                        in.close();    
                    if(fos != null)    
                        fos.close();    
                }catch(IOException e){}    
            }    
        
            JSONObject obj = new JSONObject();    
            obj.put("error", 0);    
            obj.put("url", Configuration.getInstance().getValue("image.pre")+saveUrl);    
            System.out.println(saveUrl);  
           out.print(obj.toJSONString());    
              
          
        return null;  
    }  
      
    private String getError(String message) {  
        JSONObject obj = new JSONObject();  
        obj.put("error", 1);  
        obj.put("message", message);  
        return obj.toJSONString();  
    }  
      
}  