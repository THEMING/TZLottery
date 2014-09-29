package com.xsc.lottery.web.action;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xsc.lottery.web.action.json.JsonMsgBean;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.suosui")
public class suosui extends LotteryClientBaseAction {
	final CharSequence[] items = { "中6保5", "中6保4", "中5保5", "中5保4", "中4保4" };

	private int adrzhushu[][];

	private int adrdatabase[][];

	private List<String> tempList = new ArrayList<String>();

	private List<String> list1 = new ArrayList<String>();

	private int adrtotal;

	private String code;

	private int totalnum;

	private String haoma;

	private int num;

	private String mode1;

	public String index() {
		return SUCCESS;
	}
	
	public String tcpsuoshui() {
		Map<String, Object> map=new HashMap<String, Object>();
		suosui1();
		map.put("num", num);
		map.put("totalnum", totalnum);
		map.put("list1", list1);
		map.put("mode1", mode1);
		map.put("haoma", haoma);
        setJsonString(JsonMsgBean.MapToJsonString(map));
		return AJAXJSON;
	}
		
	public String ss() {
		suosui1();
		return "ss";
	}

	public String suosui() {
		suosui1();
		return "suosui";
	}

	public CharSequence[] getItems() {
		return items;
	}

	public void loaddatabase() {
		ActionContext actionContext = ActionContext.getContext();
		Map map = actionContext.getApplication();
		try {
			if (map.get("adrdatabase") == null) {
				DataInputStream din;
				InputStream in = suosui.class
						.getResourceAsStream("/adrdatabase.dat");
				int length = in.available();
				din = new DataInputStream(in);
				adrzhushu = new int[34][5];
				adrdatabase = new int[3500][6];
				adrtotal = din.readInt();
				for (int i = 0; i < 34; i++) {
					for (int j = 0; j < 5; j++) {
						adrzhushu[i][j] = din.readInt();
					}
				}

				byte[] tmpbytes = new byte[7];
				for (int i = 0; i < adrtotal; i++) {
					in.read(tmpbytes);
					for (int j = 0; j < 6; j++) {
						adrdatabase[i][j] = (tmpbytes[j]);
					}
					map.put("adrdatabase", adrdatabase);
					map.put("adrzhushu", adrzhushu);
					map.put("adrtotal", adrtotal);
				}
			} else {
				adrdatabase = (int[][]) map.get("adrdatabase");
				adrzhushu = (int[][]) map.get("adrzhushu");
				adrtotal = (Integer) map.get("adrtotal");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int prenumadr(int s1, int s2) {
		int i, j, k, l, num = 0;
		for (i = 0; i < 34; i++) {
			for (j = 0; j < 5; j++) {
				if (i > s1)
					continue;
				if ((i == s1) && (j >= s2))
					continue;

				num += adrzhushu[i][j];

			}
		}
		return num;
	}

	public void suosui1() {
		loaddatabase();
//		tcp();
		String str = code.split("\\|")[0];
		haoma = str;
		String mode = code.split("\\|")[1];
		mode1 = (String) items[Integer.parseInt(mode) - 1];
		num = str.split(",").length;
		int num1;
		num1 = prenumadr(num, Integer.parseInt(mode) - 1);
		totalnum = adrzhushu[num][Integer.parseInt(mode) - 1];
		if (totalnum != 0) {
			for (int i = num1; i < (num1 + totalnum); i++) {
				String tempString = new String();
				for (int j = 0; j < 6; j++) {
					int temp = adrdatabase[i][j] - 1;
					if (j != 5) {
						tempString += str.split(",")[temp] + ",";
					} else {
						tempString += str.split(",")[temp];
					}
				}
				list1.add(tempString);
			}
		}
		getRequest().setAttribute("list", list1);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public String getHaoma() {
		return haoma;
	}

	public void setHaoma(String haoma) {
		this.haoma = haoma;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMode1() {
		return mode1;
	}

	public void setMode1(String mode1) {
		this.mode1 = mode1;
	}

	public List<String> getList1() {
		return list1;
	}

	public void setList1(List<String> list1) {
		this.list1 = list1;
	}
	
//	public void tcp() {
//		try {
//			FileWriter fileWriter=new FileWriter("c:\\adrzhushu.js");
//			fileWriter.write("var adrzhushu = [];");
//			int temp = 0;
//			String tempString = "";
//			for (int i = 0; i < 34; i++) {
//				for (int j = 0; j < 5; j++) {
//					temp = adrzhushu[i][j];
//					System.out.println(adrzhushu[i][j]);
//					tempString = "adrzhushu[" + i + "][" + j + "] = " + temp + ";";
//					fileWriter.write(tempString);
//				}
//			}
//			fileWriter.close();
//		} 
//		catch (IOException e) 
//		{            
//			e.printStackTrace();        
//		} 
//	}
}
