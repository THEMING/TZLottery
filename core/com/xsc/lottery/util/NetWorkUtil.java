package com.xsc.lottery.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetWorkUtil
{
    private static Logger logger = LoggerFactory.getLogger(NetWorkUtil.class);

    /**
     * @param url
     * @return
     * @throws Exception
     */
    public static String requestNet(String url) throws Exception
    {
        return requestNet(url, "utf-8", 0, 15);
    }

    /**
     * @param url
     * @param maxWait
     *            最长等待秒
     * @return
     * @throws Exception
     */
    public static String requestNet(String url, int maxWait) throws Exception
    {
        return requestNet(url, "utf-8", 0, maxWait);
    }

    /**
     * @param url
     * @param code
     *            编码
     * @param maxWait
     *            最长等待秒
     * @return
     * @throws Exception
     */
    public static String requestNet(String url, String code, int maxWait)
            throws Exception
    {
        return requestNet(url, code, 0, maxWait);
    }

    public static void main(String[] args)
    {
        try {
            String str = NetWorkUtil.requestNet(
            		"http://www.500.com/static/info/kaijiang/shtml/qxc/12039.shtml", "gbk", 5);
            System.out.println(str);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param url
     * @param code
     *            编码
     * @param retryCount
     *            重试次数
     * @param maxWait
     *            最长等待秒数
     * @return
     * @throws Exception
     */
    public static String requestNet(String url, String code, int retryCount,
            int maxWait) throws Exception
    {
        HttpClientParams clientParams = new HttpClientParams();
        clientParams.setParameter(HttpClientParams.HTTP_CONTENT_CHARSET, code);
        clientParams.setSoTimeout(maxWait * 1000);
        HttpClient client = new HttpClient(clientParams);
        GetMethod getMethod = new GetMethod(url);
        String data = null;
        try {
            client.executeMethod(getMethod);
            data = getMethod.getResponseBodyAsString();
        } catch (Exception e) {
            if (retryCount > 0) {
                retryCount--;
                logger.info("访问" + url + "失败!2秒钟后重次,还" + retryCount + "有次重次机会");
                Thread.sleep(2000);
                return requestNet(url, code, retryCount, maxWait);
            }
            throw e;
        } finally {
            getMethod.releaseConnection();
        }
        return data;
    }

    /**
     *@param 携带数据脚本资源的字符串表示
     *@param 解析数据脚本资源的字符串表示
     *@param 解析数据脚本资源的java类
     * 
     *            对网络上的脚本资源进行解析
     */
    public static void ParseJsToJava(String data, String parsejs,
            Object parseClass) throws ScriptException
    {

        ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
        ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");

        jsEngine.put("result", parseClass);
        Compilable compilable = (Compilable) jsEngine;
        CompiledScript compiledScript = compilable.compile(data);
        compiledScript.eval();

        jsEngine.eval(parsejs);
    }
    
    /**
     * URL访问网络资源
     */
    static public String getHttpUrl(String urlString, String param,
            String charCode,String paramCharCode)
    {
        charCode = StringUtils.isBlank(charCode) ? "UTF-8" : charCode;
        URL url = null;
        HttpURLConnection con = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setConnectTimeout(180000);
            con.setRequestMethod("POST");
            byte[] b = param.getBytes(StringUtils.isBlank(paramCharCode) ? "UTF-8" : paramCharCode);
            con.getOutputStream().write(b, 0, b.length);
            con.getOutputStream().flush();
            con.getOutputStream().close();
            in = new BufferedReader(new InputStreamReader(con.getInputStream(),
                    charCode));
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                } else {
                    result.append(line);
                }
            }
        }
        catch (IOException e) {
        	logger.info("errorurl");
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (con != null) {
                    con.disconnect();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * URL访问网络资源
     */
    static public String getHttpUrl(String urlString, String param,
            String charCode)
    {
        charCode = StringUtils.isBlank(charCode) ? "UTF-8" : charCode;
        URL url = null;
        HttpURLConnection con = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setConnectTimeout(180000);
            con.setRequestMethod("POST");
            byte[] b = param.getBytes();
            con.getOutputStream().write(b, 0, b.length);
            con.getOutputStream().flush();
            con.getOutputStream().close();
            in = new BufferedReader(new InputStreamReader(con.getInputStream(),
                    charCode));
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                } else {
                    result.append(line);
                }
            }
        }
        catch (IOException e) {
        	logger.info("errorurl");
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (con != null) {
                    con.disconnect();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
    
    public static String getHttpUrlByGetMethod(String url, String param)
	{
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				String s=new String(line.getBytes("GBK"),"utf-8");
				result += "/n" + s;
				//result += "/n" + line;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
    public static String getHttpUrlByPostMethod(String url, String param)
	{
    	HttpURLConnection conn =null;
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url;
			URL realUrl = new URL(urlName);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.getOutputStream().write(param.getBytes());
			conn.connect();
			int i=conn.getResponseCode();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				 conn.disconnect();
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
    
    public static String sendRequest(String url, String method)
	{
    	if(StringUtil.isEmpty(method)) method = "GET";
    	HttpURLConnection connection =null;
		String result = "";
		BufferedReader data = null;
		try {
			String urlName = url;
			URL realUrl = new URL(urlName);
			connection = (HttpURLConnection) realUrl.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			int i=connection.getResponseCode();
			if(i == HttpURLConnection.HTTP_OK)
			{
				InputStream in = null;
				in = connection.getInputStream();
				data = new BufferedReader(new InputStreamReader(in,"GBK"));
				String temp;
				while ((temp = data.readLine()) != null) {
					result += temp;
					temp = null;
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
			if(data!=null){
				try {
					data.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
   
}
