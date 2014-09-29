package com.xsc.lottery.admin.action.customer;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.datasource.JDBCLaShou;
import com.xsc.lottery.admin.action.AdminBaseAction;


@SuppressWarnings("serial")
@Scope("prototype")
@Controller("lashou.fanli")
public class LaShouFanLiAction extends AdminBaseAction{

	private String stime;
	
	private String etime;

	private List<Object[]> list;
	
	private int size;
	
	private String money;
	
	private String u_money;
	
	private String c_money;
	
    public String index() {
    	
    	JDBCLaShou jdbcLaShou = new JDBCLaShou();
    	
    	if ("".equals(stime)) {
    		stime = null;
    	}
    	
    	if ("".equals(etime)) {
    		etime = null;
    	}

    	list = jdbcLaShou.getData(stime, etime);
    	Double m = 0.00, u = 0.00, c = 0.00;
    	for (Object[] objects : list) {
			m += Double.valueOf(objects[0].toString());
			u += Double.valueOf(objects[1].toString());
			c += Double.valueOf(objects[2].toString());
		}
		NumberFormat nf = NumberFormat.getNumberInstance();
    	nf.setMaximumFractionDigits(2);
    	size = list.size();
    	money = nf.format(m);
    	u_money = nf.format(u);
    	c_money = nf.format(c);
		return SUCCESS;
	}
	
    public String list() {
    	return SUCCESS;
    }

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public List<Object[]> getList() {
		return list;
	}

	public void setList(List<Object[]> list) {
		this.list = list;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getU_money() {
		return u_money;
	}

	public void setU_money(String u_money) {
		this.u_money = u_money;
	}

	public String getC_money() {
		return c_money;
	}

	public void setC_money(String c_money) {
		this.c_money = c_money;
	}

}
