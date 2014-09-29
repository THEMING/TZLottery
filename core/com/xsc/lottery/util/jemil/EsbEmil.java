package com.xsc.lottery.util.jemil;

import java.io.File;
import java.io.FileInputStream;

import org.apache.axis.client.Service;

import sun.misc.BASE64Encoder;

import com.hnas.esb.entity.GenerateReqMsg;
import com.hnas.esb.entity.MessageHeader;
import com.hnas.esb.entity.MessageRequest;
import com.hnas.esb.entity.Parameter;
import com.hnas.event.ESBServiceSoapStub;

public class EsbEmil
{
    private static String ESBEmailInvoke() throws Exception
    {
        //以下参数测试与正式不一样，全部需配置化以便更改
        //ESB接口地址
        String soapUrl = "http://10.2.57.133:8888/webservice/Projects_HnaESBService_initial_ESBService?wsdl";

        //路由编号
        String messageType = "EAI_EmailInformService";

        //用户名
        String userId = "TCL";

        //用户密码
        String userPwd = "123456";

        //用户私钥文件路径
        String privateKeyPath = "E:\\HNAS\\TCL_PrivateKey.dat";

        //业务接口参数,接口没有参数时设置为null
        Parameter[] parameter = new Parameter[9];

        //具体参数数组的长度根据《EAI邮件接口入口参数要求.pdf》设定，根据查询条件实例化参数个数

        //参数实例化的构造函数为：Parameter(参数名, 参数值)

        //此处假设公共邮箱地址为xxx@hnair.com，则用户名为xxx

        Parameter parameter0 = new Parameter("From", "wl-wang@hnair.com");
        Parameter parameter1 = new Parameter("To", "970956819@qq.com");
        Parameter parameter2 = new Parameter("UserName", "wl-wang");
        Parameter parameter3 = new Parameter("UserPwd", GetBase64Str("qwe123"));
        Parameter parameter4 = new Parameter("Cc", "970956819@qq.com");
        Parameter parameter5 = new Parameter("Bcc", "kf@369cai.com");
        Parameter parameter6 = new Parameter("Subject", "java邮件测试");
        Parameter parameter7 = new Parameter("Body",
                GetBase64Str("邮件发送内容测试<br />换行测试<br />测试完毕"));

        // String attachments =
        // "<Attachment><FileName>Zapotec.bmp</FileName><Content>" +
        // readFile("C:\\WINDOWS\\Zapotec.bmp") + "</Content></Attachment>";
        //
        // attachments = attachments.replaceAll("\r\n", "");//替换换行

        Parameter parameter8 = new Parameter("Attachments", null);

        parameter[0] = parameter0;
        parameter[1] = parameter1;
        parameter[2] = parameter2;
        parameter[3] = parameter3;
        parameter[4] = parameter4;
        parameter[5] = parameter5;
        parameter[6] = parameter6;
        parameter[7] = parameter7;
        parameter[8] = parameter8;

        //使用EAI的JAR生成Webservice请求消息的消息头对象
        MessageHeader messageHeader = new MessageHeader(messageType, userId, userPwd);

        //使用EAI的JAR生成Webservice请求消息对象
        MessageRequest messageRequest = new MessageRequest(messageHeader, parameter);

        try {
            // 使用EAI的JAR生成Webservice请求消息
            String message = GenerateReqMsg.generate(messageRequest, privateKeyPath);
            System.out.println(message);

            try {
                //定义axis客户端调用服务
                Service service = new Service();

                //实例化ESB服务,ESBServiceSoapStub为接口代理类名
                ESBServiceSoapStub saopObject = new ESBServiceSoapStub(
                        new java.net.URL(soapUrl), service);

                //调用ESB接口
                String response = saopObject.getDataFromInnerESB(message);

                return response; //response就是接口调用的返回值，再根据返回值格式及具体业务数据进行数据解析及业务操作

            }
            catch (Exception ex) {
                throw ex;
            }

        } 
        catch (Exception ex) {
            throw ex;
        }
    }

    /*
     * 获取Base64
     */
    private static String GetBase64Str(String source) throws Exception
    {
        //Base64编码对象
        BASE64Encoder base64 = new BASE64Encoder();
        String ret = base64.encode(source.getBytes("UTF-8"));
        ret = ret.replaceAll("\r\n", ""); // 替换去除换行
        return ret;
    }

    /*
     * 读取文件base64内容
     */
    public static String readFile(String fileName) throws Exception
    {
        File f = new File(fileName); // 创建文件对象
        FileInputStream fm = new FileInputStream(f); // 创建文件输入流
        int len = fm.available(); // 计算内容长度
        byte[] bytes = new byte[len];
        fm.read(bytes); // 读取文件内容
        fm.close(); // 关闭输入流
        String fileContent = (new BASE64Encoder()).encode(bytes); // 字节数组转换为字符串

        //返回文件内容
        return fileContent;
    }

    public static void s() throws Exception
    {
        ESBEmailInvoke();
    }
}
