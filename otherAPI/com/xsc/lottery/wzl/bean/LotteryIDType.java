package com.xsc.lottery.wzl.bean;

public class LotteryIDType {
	//篮球让分胜负过关
	public static String LQ_RFSFGG = "306";
	//篮球让分胜负单关
	public static String LQ_RFSFDG = "316";
	//篮球胜负过关
	public static String LQ_SFGG = "307";
	//篮球胜负单关
	public static String LQ_SFDG = "317";
	//篮球胜分差过关
	public static String LQ_SFCGG = "308";
	//篮球胜分差单关
	public static String LQ_SFCDG = "318";
	//篮球大小分过关
	public static String LQ_DXFGG = "309";
	//篮球大小分单关
	public static String LQ_DXFDG = "319";
	/**值转换成相应的我中了的接口的值*/
	
    public static String typeToWZLType(String type, int isDanguan)
    {
    	if(type.equals("SF"))
    	{
    		if(isDanguan == 1)
    		{
    			return LQ_SFDG;
    		}
    		else
    		{
    			return LQ_SFGG;
    		}
    	}
    	else if(type.equals("RFSF"))
    	{
    		if(isDanguan == 1)
    		{
    			return LQ_RFSFDG;
    		}
    		else
    		{
    			return LQ_RFSFGG;
    		}
    	}
    	else if(type.equals("SFC"))
    	{
    		if(isDanguan == 1)
    		{
    			return LQ_SFCDG;
    		}
    		else
    		{
    			return LQ_SFCGG;
    		}
    	}
    	else if(type.equals("DXF"))
    	{
    		if(isDanguan == 1)
    		{
    			return LQ_DXFDG;
    		}
    		else
    		{
    			return LQ_DXFGG;
    		}
    	}
    	else
    	{
    		throw new RuntimeException("彩票类型错误");
    	}
    }
    
    public static String GetClusterType(String szCluster)
    {
    	if(szCluster.equals("1*1"))
    	{
    		return "01";		//单关
    	}
    	else if(szCluster.equals("2*1"))
    	{
    		return "02";		//2串1
    	}
    	else if(szCluster.equals("3*1"))
    	{
    		return "03";		//3串1
    	}
    	else if(szCluster.equals("4*1"))
    	{
    		return "06";		//4串1
    	}
    	else if(szCluster.equals("5*1"))
    	{
    		return "11";		//5串1
    	}
    	else if(szCluster.equals("6*1"))
    	{
    		return "18";		//6串1
    	}
    	else if(szCluster.equals("7*1"))
    	{
    		return "28";		//7串1
    	}
    	else if(szCluster.equals("8*1"))
    	{
    		return "34";		//8串1
    	}
    	else
    	{
    		throw new RuntimeException("不支持的串类型");
    	}
    }
}
