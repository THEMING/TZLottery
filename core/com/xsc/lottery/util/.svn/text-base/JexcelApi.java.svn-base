package com.xsc.lottery.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@SuppressWarnings("unused")
public class JexcelApi
{
    @SuppressWarnings( { "unchecked", "deprecation" })
    public static String createList1(Map map, String file, String currdate)
    {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(); // 创建新的Excel工作薄
            HSSFSheet sheet = workbook.createSheet("统计"); // 在Excel工作薄中建工作表，名为缺省
            HSSFRow row = sheet.createRow(0); // 在索引0的位置建行（最顶端的行）
            HSSFCell cell = row.createCell((short) 0); // 在索引0的位置建单元格
            cell.setCellValue("月份"); // 在单元格输入一些内容
            cell = row.createCell((short) 1);
			cell.setCellValue("充值款项来源"); // 在单元格输入一些内容
            cell = row.createCell((short) 2);
            cell.setCellValue("充值金额"); // 在单元格输入一些内容

            int i = 1;
            for (Object m : map.keySet()) {
                row = sheet.createRow((short) (i)); // 在索引1的位置创建行（最顶端的行）
                cell = row.createCell((short) 0);
                cell.setCellValue(currdate); // 在单元格输入一些内容
                cell = row.createCell((short) 1);
                cell.setCellValue((String) m);
                cell = row.createCell((short) 2);
                cell.setCellValue("￥" + map.get(m).toString());
                i++;
            }

            FileOutputStream fOut = new FileOutputStream("/var/unzipddd/"
                    + "冲值统计.xls"); // 新建输出文件流
            workbook.write(fOut); // 把相应的Excel工作薄存盘
            fOut.flush();
            fOut.close(); // 操作结束，关闭文件

        }
        catch (IOException e) {
            return "file not change";
        }
        return "success";
    }

    @SuppressWarnings( { "unchecked", "deprecation" })
    public static String createList2(Map map, String file, String currdate)
    {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(); // 创建新的Excel工作薄
            HSSFSheet sheet = workbook.createSheet("统计"); // 在Excel工作薄中建工作表，名为缺省
            HSSFRow row = sheet.createRow(0); // 在索引0的位置建行（最顶端的行）
            HSSFCell cell = row.createCell((short) 0); // 在索引0的位置建单元格
            cell.setCellValue("月份"); // 在单元格输入一些内容
            cell = row.createCell((short) 1);
            cell.setCellValue("销售彩种"); // 在单元格输入一些内容
            cell = row.createCell((short) 2);
            cell.setCellValue("供应商"); // 在单元格输入一些内容
            cell = row.createCell((short) 3);
            cell.setCellValue("销售金额"); // 在单元格输入一些内容
            cell = row.createCell((short) 4);
            cell.setCellValue("中奖金额"); // 在单元格输入一些内容
            cell = row.createCell((short) 5);
            cell.setCellValue("已付金额"); // 在单元格输入一些内容
            cell = row.createCell((short) 6);
            cell.setCellValue("未付金额"); // 在单元格输入一些内容

            int i = 1;
            for (Object m : map.keySet()) {
                String[] str = map.get(m).toString().split("-");
                row = sheet.createRow((short) (i)); // 在索引1的位置创建行（最顶端的行）
                cell = row.createCell((short) 0);
                cell.setCellValue(currdate); // 在单元格输入一些内容
                cell = row.createCell((short) 1);
                cell.setCellValue((String) m);
                cell = row.createCell((short) 2);
                cell.setCellValue(str[0]);
                cell = row.createCell((short) 3);
                cell.setCellValue("￥" + str[1]);
                cell = row.createCell((short) 4);
                cell.setCellValue("￥" + str[2]);
                cell = row.createCell((short) 5);
                cell.setCellValue("￥" + str[3]);
                cell = row.createCell((short) 6);
                cell.setCellValue("￥" + str[4]);
                i++;
            }

            FileOutputStream fOut = new FileOutputStream("/var/unzipddd/"
                    + "销售统计.xls"); // 新建输出文件流
            workbook.write(fOut); // 把相应的Excel工作薄存盘
            fOut.flush();
            fOut.close(); // 操作结束，关闭文件
        } catch (IOException e) {
            return "file not change";
        }
        return "success";
    }

    public static void downLoad(HttpServletResponse response) throws Exception
    {
        File f = new File("/var/unzipddd/冲值统计.xls");
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要(), "iso-8859-1"));

        response.setHeader("Content-Disposition", "attachment; filename="
                + new String("冲值统计.xls".getBytes(), "utf-8"));
        response.setContentType("application/x-msdownload");
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.flush();
        out.close();

    }

    public static void downLoad1(HttpServletResponse response) throws Exception
    {
        File f = new File("/var/unzipddd/销售统计.xls");
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要(), "iso-8859-1"));

        response.setHeader("Content-Disposition", "attachment; filename="
                + new String("销售统计.xls".getBytes(), "utf-8"));
        response.setContentType("application/x-msdownload");
        
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        br.close();
        out.flush();
        out.close();
    }
}
