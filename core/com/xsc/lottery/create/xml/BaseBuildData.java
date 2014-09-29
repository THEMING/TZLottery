package com.xsc.lottery.create.xml;

import javax.annotation.PostConstruct;

import org.htmlparser.parserapplications.StringExtractor;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.enumerate.LotteryType;

@SuppressWarnings("unused")
public abstract class  BaseBuildData 
{
	//遗漏路径
	protected final String StaticMiss = "staticMiss";
	
	//开奖结果漏路径
	protected final String StaticOpenResult = "staticOpenResult";
	
	@PostConstruct
	private void registerBuild(){
		BuildDataFactory.getInstance().registerBuildClass(this);
	}
	protected String readNetResout(String url) throws Exception{
		StringExtractor se = new StringExtractor(url);
		String htmltextsource = se.extractStrings(false);
		return htmltextsource;
	}
	
	//
	protected  LotteryTerm currTerm;
	
	
	
	//生成数据
	public abstract void startBuildStaticData() throws Exception;
	
	
	
	public abstract LotteryType getType();
	
	public void setCurrTerm(LotteryTerm currTerm) {
		this.currTerm = currTerm;
	}
	
	
	
}
