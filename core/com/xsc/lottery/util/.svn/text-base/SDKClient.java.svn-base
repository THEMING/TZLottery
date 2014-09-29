package com.xsc.lottery.util;


import cn.emay.sdk.client.api.Client;


/**
 * 创建与SP短信平台的连接
 * @author pengfangliang
 * @version 1.0 2014-06-18 21:26
 */

public class SDKClient {
	
	private static Client client=null;

	public SDKClient() {
	}
	
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	
	public synchronized static Client getClient(){
		if(client==null){
			try {
				
				client=new Client(Configuration.getInstance().getValue("softwareSerialNo"),Configuration.getInstance().getValue("key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
}
