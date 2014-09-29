package com.xsc.lottery.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils
{

    protected static transient final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    // 从指定路径中读取
    public static String readFile(String path)
    {
        return readFile(path, "UTF-8");
    }

    public static String readFile(String path, String charsetName)
    {
        StringBuilder readStr = new StringBuilder();
        try {
            FileInputStream fileRead = new FileInputStream(new File(path));
            BufferedReader bufread = new BufferedReader(new InputStreamReader(
                    fileRead, charsetName));
            String read;
            while ((read = bufread.readLine()) != null) {
                readStr = readStr.append(read).append("\n");
            }
            bufread.close();
            fileRead.close();
        } 
        catch (IOException e) {
            logger.error("从指定路径【" + path + "】读取文本时出错:", e);
        }
        return readStr.toString();
    }

    public static String readFile(File file)
    {
        String readStr = "";
        try {
            FileInputStream fileRead = new FileInputStream(file);
            BufferedReader bufread = new BufferedReader(new InputStreamReader(
                    fileRead));
            String read;
            while ((read = bufread.readLine()) != null) {
                readStr += read + "\n";
            }
            if (readStr.toString().lastIndexOf("\n") > 0)
                readStr = readStr.substring(0, readStr.toString().lastIndexOf(
                        "\n"));
            bufread.close();
            fileRead.close();
        } 
        catch (IOException e) {
            logger.error("从指定路径【" + file.getPath() + "】读取文本时出错:", e);
        }
        return readStr;
    }
    
    public static String readFile0(File file, String charsetName)
    {
        String readStr = "";
        try {
            FileInputStream fileRead = new FileInputStream(file);
            BufferedReader bufread = new BufferedReader(new InputStreamReader(
                    fileRead, charsetName));
            String read;
            while ((read = bufread.readLine()) != null) {
                readStr += read;
            }
            bufread.close();
            fileRead.close();
        } 
        catch (IOException e) {
            logger.error("从指定路径【" + file.getPath() + "】读取文本时出错:", e);
        }
        return readStr;
    }

    public static void writeFile(String path, String content) throws Exception
    {
        FileOutputStream fileout = new FileOutputStream(path);
        fileout.write(content.getBytes("utf-8"));
        fileout.close();
    }
}
