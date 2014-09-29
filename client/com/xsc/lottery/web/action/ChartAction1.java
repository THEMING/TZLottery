package com.xsc.lottery.web.action;

import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.LotteryTermService;

@Scope("prototype")
@Controller("chartaction1")
@SuppressWarnings( { "unused", "serial" })
public class ChartAction1 extends LotteryClientBaseAction{
	private List<LotteryTerm> lotteryResult;
	private int num = 10;
	private String type;
	private String tt="js";
	private String hongqiu="";
	private String lanqiu="";
	private String lotteryType;
	private String ball;
	private String labelX="";
	private String nlabelX="";

	private String gap="5";
	private String chartType;
	private String title;
	private String shengq="";
	private String pinq="";
	private String fuq="";
	private String football="";
	@Autowired
	private LotteryTermService lotteryTermService;
	
	//频率图
	public String getPinlvResult(String lottotype, String tjtype, int balltype,String charttype)
	{
		int []hongnum=new int[33];
		int []lannum=new int[16];
		int tjlist[]=new int[101],tjnum = 0;
		int ballbegin = 0,ballend = 0;
		int zsno=0;
		int sheng[]=new int[140];
		int ping[]=new int[140];
		int fu[]=new int[140];
		lotteryResult = lotteryTermService.getTermByZSMap(LotteryType
				.enToType(lottotype), num);
		if("zst".equals(charttype))
		{
			Collections.reverse(lotteryResult);
			if(tjtype.equals("haoma"))
			{
				tjtype="js";
			}
		}

		
		if("ssq".equals(lottotype))
		{
			if(balltype==1) 	
			{
				ballbegin=0;ballend=6;
			}
			if(balltype==2) 	
			{
				ballbegin=6;ballend=7;
			}
		}
		
		if("dlt".equals(lottotype))
		{
			if(balltype==1) 	
			{
				ballbegin=0;ballend=5;
			}
			if(balltype==2) 	
			{
				ballbegin=5;ballend=7;
			}
		}
		if(("3d".equals(lottotype))||"pls".equals(lottotype))
		{
			ballbegin=0;ballend=3;
		}
		if("plw".equals(lottotype))
		{
			ballbegin=0;ballend=5;
		}
		if("qxc".equals(lottotype))
		{
			ballbegin=0;ballend=7;
		}
		if("qlc".equals(lottotype))
		{
			if(balltype==1) 	
			{
				ballbegin=0;ballend=7;
			}
			if(balltype==2) 	
			{
				ballbegin=7;ballend=8;
			}
		}
		String previous[];
		String hh[];
		String termNo[]=new String[num];
		String cc[]=new String[14];
		for(int i=0;i<lotteryResult.size();i++){
			zsno=0;
			hh=lotteryResult.get(i).getResult().split("\\||\\,");	
			if(hh[0].equals("")) continue;
			
			if("cc".equals(tjtype)){
				title="场次";
				if("plt".equals(charttype))
				{
				   sheng[cc(hh,3)]++;
				   ping[cc(hh,1)]++;
				   fu[cc(hh,0)]++;
				}
				if("zst".equals(charttype)){
			       sheng[i]=cc(hh,3);	
			       ping[i]=cc(hh,1);
			       fu[i]=cc(hh,0);
				}
					
			}
			
			if("lianxue".equals(tjtype)){
				title="连续";
				if("plt".equals(charttype))
				{
				   sheng[lianxue(hh,3)]++;
				   ping[lianxue(hh,1)]++;
				   fu[lianxue(hh,0)]++;
				}
				if("zst".equals(charttype)){
			       sheng[i]=lianxue(hh,3);	
			       ping[i]=lianxue(hh,1);
			       fu[i]=lianxue(hh,0);
				}
					
			}
			if("jifen".equals(tjtype)){
				title="积分";
				if("zst".equals(charttype)){  
					tjnum=jifen(hh);
				}
					
			}
			if("duandian".equals(tjtype)){
				title="断点";
				if("plt".equals(charttype)){  
					tjnum=duandian(hh);
					tjlist[tjnum]++;
				}
				if("zst".equals(charttype)){  
					tjnum=duandian(hh);
				}
					
			}
			if("haoma".equals(tjtype))
			{
				for(int j=ballbegin;j<ballend;j++)
				{
					int tmpint;
					tmpint=Integer.parseInt(hh[j]);
					tjlist[tmpint]++;
				}
	
				
				int max=0;
				for(int k=0;k<tjlist.length;k++)
				{
					if(max<tjlist[k])
						max=tjlist[k];
				}
				if(1==balltype)
					ServletActionContext.getRequest().setAttribute("hmax", max+5);
				if(2==balltype)
					ServletActionContext.getRequest().setAttribute("nmax", max+5);

				title="号码";
				continue;//直接返回
			}
			
			if("yanxu".equals(tjtype))
			{
				title="延续";
				if(i==0) 
				{
					tjnum=0;
					
				} else
				{
					previous=lotteryResult.get(i-1).getResult().split("\\||\\,");;
					tjnum=yanXuNum(hh,previous,ballbegin,ballend);
				}
			}
			if("js".equals(tjtype))
			{
				title="奇数";
				
				tjnum=jishunum(hh,ballbegin,ballend);
			}
			if("qj1".equals(tjtype))
			{
				title="区间一";
				tjnum=quJian1(hh,ballbegin,ballend);
			}
			if("qj2".equals(tjtype))
			{
				title="区间二";
				tjnum=quJian2(hh,ballbegin,ballend);
			}
			if("qj3".equals(tjtype))
			{
				title="区间三";
				tjnum=quJian3(hh,ballbegin,ballend);
			}
			if("qj4".equals(tjtype))
			{
				title="区间四";
				tjnum=quJian4(hh,ballbegin,ballend);
			}
			if("lh".equals(tjtype))
			{
				title="连号";
				tjnum=lianHao(hh,ballbegin,ballend);
			}
			if("zds".equals(tjtype))
			{
				title="最大数";
				tjnum=zuiDaNum(hh,ballbegin,ballend);
			}
			if("zxs".equals(tjtype))
			{
				title="最小数";
				tjnum=zuiXiaoNum(hh,ballbegin, ballend);
			}
			if("dd".equals(tjtype))
			{
				title="断点";
				tjnum=duanDian(hh,ballbegin, ballend);
			}
			
			if("zst".equals(charttype))
			{
				tjlist[i]=tjnum;
				zsno++;
				String s=lotteryResult.get(i).getTermNo();
				termNo[i]="&nbsp;&nbsp;"+s.substring(4,7);
			}else
			{
			tjlist[tjnum]++;
			}
		}
		int max=0;
		if("football".equals(football))
		{	
			if("jifen".equals(tjtype)||"duandian".equals(tjtype))
			{
				 max=max(tjlist);
				  ServletActionContext.getRequest().setAttribute("hmax", max+5);
			}
			else{
			int maxs[]=new int[3];
			 maxs[0]=max(sheng);
		     maxs[1]=max(ping);
		     maxs[2]=max(fu);
		     max=max(maxs);
		    ServletActionContext.getRequest().setAttribute("hmax", max+5);}
		}
		else{
		    max=max(tjlist);
		    }
		if(1==balltype)
			ServletActionContext.getRequest().setAttribute("hmax", max+5);
		if(2==balltype)
			ServletActionContext.getRequest().setAttribute("nmax", max+5);
		lotteryType=LotteryType.enToType(type).name()+title;
		ball=LotteryType.enToType(type).name();
		int length=0;
		
		if("js".equals(tjtype)||"qj1".equals(tjtype)||"qj2".equals(tjtype)||"qj3".equals(tjtype)||"qj4".equals(tjtype))
			length=ballend-ballbegin+1;
		if("yanxu".equals(tjtype)||"lh".equals(tjtype))
		{
			if("ssq".equals(lottotype)||"dlt".equals(lottotype)||"qxc".equals(lottotype))
				length=8;
			if("3d".equals(lottotype)||"pls".equals(lottotype))
				length=4;
			if("plw".equals(lottotype))
				length=6;
			if("qlc".equals(lottotype))
				length=9;
		}
		
		if("zds".equals(tjtype)||"zxs".equals(tjtype)||"haoma".equals(tjtype))
		{
			
			if("ssq".equals(lottotype)&&(balltype==1))
					length=34;
			if("ssq".equals(lottotype)&&(balltype==2))
					length=17;
			if("dlt".equals(lottotype)&&(balltype==1))
				length=36;
			if("dlt".equals(lottotype)&&(balltype==2))
				length=13;
			if("3d".equals(lottotype))
				length=10;
			if("pls".equals(lottotype))
				length=10;
			if("plw".equals(lottotype))
				length=10;
			if("qxc".equals(lottotype))
				length=10;
			if("qlc".equals(lottotype))
				length=30;
			
			
		}
		
		if("zst".equals(charttype))
		{
			length=zsno;
		}
		if("football".equals(football)){
			if("14sfc".equals(lottotype)||"r9".equals(lottotype))
				length=14;
			if("4cjq".equals(lottotype))
				length=8;
			if("6cb".equals(lottotype))
				length=12;
			if("jifen".equals(tjtype))
				length=num;
			if("duandian".equals(tjtype)||"zst".equals(charttype))
				length=num;
			
		}
		if("football".equals(football))
		{
			if("jifen".equals(tjtype)||"duandian".equals(tjtype)){
				hongqiu=gettjstr(lottotype,tjtype,length,tjlist);
				if("zst".equals(charttype)){
					labelX=getlablezst(lottotype,termNo,length);
				}
				if("plt".equals(charttype)){
					labelX=getlable(lottotype,tjtype,length,tjlist);
				}
			}else{
			shengq=gettjstr(lottotype,tjtype,length,sheng);
			pinq=gettjstr(lottotype,tjtype,length,ping);
			fuq=gettjstr(lottotype,tjtype,length,fu);
			labelX=getlable(lottotype,tjtype,length,tjlist);}
		}
		if(balltype==1)
		{
			
			hongqiu=gettjstr(lottotype,tjtype,length,tjlist);
			labelX=getlable(lottotype,tjtype,length,tjlist);
			if("zst".equals(charttype))
			{
				
					labelX=getlablezst(lottotype,termNo,length);
					return hongqiu;
				
			}
			return hongqiu;
		} else
		{	lanqiu=gettjstr(lottotype,tjtype,length,tjlist);
			nlabelX=getlable(lottotype,tjtype,length,tjlist);
			if("zst".equals(charttype))
			{
					nlabelX=getlablezst(lottotype,termNo,length);
					return lanqiu;
				
			}
			return lanqiu;
		}
	
		
		
	}
	
	public String gettjstr(String type, String tt,int length,int tjlist[])
	{
		String tmplable="";
		int n=0;
		if("zst".equals(chartType)){
			n=num;
		}
		else n=length;
		for(int i=0;i<n;i++)
		{
			tmplable=tmplable+"\""+tjlist[i]+"\""+",";
		}
		tmplable=tmplable.substring(1, tmplable.length()-2);
		return tmplable;
		
	}
	
	public String getlablezst(String type, String tt[],int length)
	{
		labelX="";
		for(int i=0;i<tt.length;i++)
			labelX=labelX+"\""+tt[i]+"\""+",";
		labelX=labelX.substring(1, labelX.length()-2);
		return labelX;
	}
	
	public String getlable(String type, String tt,int length,int tjlist[])
	{
		String tmplable="";
		int n=length;
		
		for(int i=0;i<n;i++)
		{
		
			if("football".equals(chartType))
			tmplable=tmplable+"\""+(i+1)+"\""+",";
			else
			tmplable=tmplable+"\""+i+"\""+",";
		}
		tmplable=tmplable.substring(1, tmplable.length()-2);
		return tmplable;
	}
	 int zuiDaNum(String result[],int begin, int end)
		{
		    int max;
			max=Integer.parseInt(result[begin]);
			for (int i=begin+1;i<end;i++)
			{
				if (Integer.parseInt(result[i])>max)
				{
					max=Integer.parseInt(result[i]);
				}
			}
		    return max;
		}
	int  jishunum(String hh[],int ballbegin,int ballend){
		int num=0;
		for(int i=ballbegin;i<ballend;i++)
		{
			if(Integer.parseInt(hh[i])%2!=0){
				num++;
			}
		}
		return num;
	}


	public String plt()
	{
		if("jifen".equals(tt))
			tt="cc";
		int ballbegin,ballend;
		chartType="plt";
		
		if(type.equals("14sfc")||type.equals("4cjq")||type.equals("6cb"))
		{
			football="football";
		}
		
		if("football".equals(football))
		{
			if("duandian".equals(tt)){
				hongqiu=getPinlvResult(type,tt,5,chartType);
				return "footballq";
			}
			hongqiu=getPinlvResult(type,tt,5,chartType);
			return "football";
		}
		if("ssq".equals(type)||"dlt".equals(type)||"qlc".equals(type))
		{
			hongqiu=getPinlvResult(type,tt,1,chartType);
			lanqiu=getPinlvResult(type,tt,2,chartType);
			return "chart";
		} else
		{
			hongqiu=getPinlvResult(type,tt,1,chartType);
			return SUCCESS;
		}
		
	}
	
	public String zst()
	{ 
		int ballbegin,ballend;
		chartType="zst";
		if("haoma".equals(tt))
			tt="js";
		
		if(type.equals("14sfc")||type.equals("4cjq")||type.equals("6cb"))
		{
			football="football";
		}
		if("football".equals(football))
		{
			if("jifen".equals(tt)||"duandian".equals(tt)){
				hongqiu=getPinlvResult(type,tt,5,chartType);
				return "footballq";
			}else{
			hongqiu=getPinlvResult(type,tt,5,chartType);
			return "football";}
		}
	
	if("zds".equals(tt)||"hs".equals(tt)||"zxs".equals(tt))
		{if("ssq".equals(type)||"dlt".equals(type)||"qlc".equals(type))
			{hongqiu=getPinlvResult(type,tt,1,chartType);
		    lanqiu=getPinlvResult(type,tt,2,chartType);
		    return "chartc";
			}
		else
			hongqiu=getPinlvResult(type,tt,1,chartType);
		    return SUCCESS;
		}
	if("ssq".equals(type)||"dlt".equals(type)||"qlc".equals(type))
	{
		hongqiu=getPinlvResult(type,tt,1,chartType);
		lanqiu=getPinlvResult(type,tt,2,chartType);
		return "chart";
	} else
	{
		hongqiu=getPinlvResult(type,tt,1,chartType);
		return SUCCESS;
	}
	}
	public String fbt()
	{
		chartType="fbt";
		lotteryResult = lotteryTermService.getTermByZSMap(LotteryType
				.enToType(type), num);
		Collections.reverse(lotteryResult);
		return type;
		
	}
	
	                  /////////////断点
		int duanDian(String result[],int begin, int end)
		{
		    int  dd=0;
		    for(int i=begin;i<end;i++)
			{
				if  (Integer.parseInt(result[i])!=Integer.parseInt(result[i+1]))
					dd++;
			}
		    return  dd;
		}
		
		
		int lianHao(String result[],int begin, int end)
		{
			int max=1,lh=1;
			for(int i=begin;i<end-1;i++)
			{
				if ((Integer.parseInt(result[i])+1)==Integer.parseInt(result[i+1]))
				{
					lh++;
				}
				else
				{
					if(lh>max) max=lh;
				}
			}
			if(lh>max) max=lh;
			return max;
		}
	                  //////////////最小数
		int zuiXiaoNum(String result[],int begin, int end)
		{
			int min;
			min=Integer.parseInt(result[begin]);
			for(int i=begin+1;i<end;i++)
			{
				if (Integer.parseInt(result[i])<min)
				{
					min=Integer.parseInt(result[i]);
				}
			}
			return  min;
		}
		
		int yanXuNum(String current[], String previous[],int begin, int end)
		{
			int equalNum;
		    equalNum=0;
			for (int i=begin;i<end;i++)
			for(int j=begin;j<end;j++)
			{
				if (Integer.parseInt(current[i])==Integer.parseInt(previous[j]))
				{
					equalNum++;
				}
			}
			return equalNum;
		}
		int quJian1(String result[],int begin, int end)
		{
			int qj=0;
			for(int i=begin;i<end;i++)
			{if (Integer.parseInt(result[i])>=0&&Integer.parseInt(result[i])<=10)
				qj++;
			}
			return qj;	
		}
		int quJian2(String result[],int begin, int end)
		{
			int qj=0;
			for(int i=begin;i<end;i++)
			{if (Integer.parseInt(result[i])>=11&&Integer.parseInt(result[i])<=20)
				qj++;
			}
			return qj;	
		}
		int quJian3(String result[],int begin, int end)
		{
			int qj=0;
			for(int i=begin;i<end;i++)
			{if (Integer.parseInt(result[i])>=21&&Integer.parseInt(result[i])<=30)
				qj++;
			}
			return qj;	
		}
		int quJian4(String result[],int begin, int end)
		{
			int qj=0;
			for(int i=begin;i<end;i++)
			{if (Integer.parseInt(result[i])>=31&&Integer.parseInt(result[i])<=40)
				qj++;
			}
			return qj;	
		}
		//场次
		int cc(String result[],int type)
		{	
			int shenq=0;
			for(int k=0;k<result.length;k++)
			{
				if(Integer.parseInt(result[k])==type)
					{
					shenq++;
					}
			}
			return shenq;
		}
		int duandian(String result[])
		{	
			int shenq=1;
			for(int k=0;k<result.length-1;k++)
			{
				if(Integer.parseInt(result[k])==Integer.parseInt(result[k+1]))
					{
					shenq++;
					}
			}
			return shenq;
		}
		int max(int tjlist[]){
			int max=0;
			for(int k=0;k<tjlist.length;k++)
			{
				if(max<tjlist[k])
					max=tjlist[k];
			}
			return max;
		}
		int lianxue(String result[],int type){
			int lh=1;
			for(int i=0;i<result.length-1;i++)
			{
				if (Integer.parseInt(result[i])==type&&Integer.parseInt(result[i])==Integer.parseInt(result[i+1]))
			    lh++;
			}
			return lh;	
		}
		int jifen(String result[]){
			int jf=0;
			for(int k=0;k<result.length;k++){
				jf=jf+Integer.parseInt(result[k]);
			}
			return jf;	
		}
	public List<LotteryTerm> getLotteryResult() {
		return lotteryResult;
	}
	public void setLotteryResult(List<LotteryTerm> lotteryResult) {
		this.lotteryResult = lotteryResult;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTt() {
		return tt;
	}
	public void setTt(String tt) {
		this.tt = tt;
	}
	public LotteryTermService getLotteryTermService() {
		return lotteryTermService;
	}
	public void setLotteryTermService(LotteryTermService lotteryTermService) {
		this.lotteryTermService = lotteryTermService;
	}
	public String gethongqiu() {
		return hongqiu;
	}
	public void sethongqiu(String hongqiu) {
		this.hongqiu = hongqiu;
	}
	public String getHongqiu() {
		return hongqiu;
	}
	public void setHongqiu(String hongqiu) {
		this.hongqiu = hongqiu;
	}
	public String getLanqiu() {
		return lanqiu;
	}
	public void setLanqiu(String lanqiu) {
		this.lanqiu = lanqiu;
	}
	public String getLotteryType() {
		return lotteryType;
	}
	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}
	public String getLabelX() {
		return labelX;
	}
	public void setLabelX(String labelX) {
		this.labelX = labelX;
	}
	
	public String getGap() {
		return gap;
	}
	public void setGap(String gap) {
		this.gap = gap;
	}
	public String getNlabelX() {
		return nlabelX;
	}
	public void setNlabelX(String nlabelX) {
		this.nlabelX = nlabelX;
	}

	public String getBall() {
		return ball;
	}

	public void setBall(String ball) {
		this.ball = ball;
	}

	public String getChartType() {
		return chartType;
	}

	public void setCharttype(String chartType) {
		this.chartType = chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShengq() {
		return shengq;
	}

	public void setShengq(String shengq) {
		this.shengq = shengq;
	}

	public String getPinq() {
		return pinq;
	}

	public void setPinq(String pinq) {
		this.pinq = pinq;
	}

	public String getFuq() {
		return fuq;
	}

	public void setFuq(String fuq) {
		this.fuq = fuq;
	}

	public String getFootball() {
		return football;
	}

	public void setFootball(String football) {
		this.football = football;
	}


}
