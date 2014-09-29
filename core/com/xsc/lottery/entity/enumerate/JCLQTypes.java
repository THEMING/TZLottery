package com.xsc.lottery.entity.enumerate;

public enum JCLQTypes
{
	    单关胜负("SGCSF"), 单关让分胜负("SGRFSF"), 单关胜分差("SGSFC"), 单关大小分("SGDXF"), 
	    多关胜负("MGCSF"), 多关让分胜负("MGRFSF"), 多关胜分差("MGSFC"), 多关大小分("MGDXF");
	    private String name_EN;

	    private JCLQTypes(String name_en)
	    {
	        this.name_EN = name_en;
	    }

	    public String getName_EN()
	    {
	        return name_EN;
	    }

	    public void setName_EN(String name_EN)
	    {
	        this.name_EN = name_EN;
	    }

	    static public JCLQTypes enToType(String en)
	    {
	        for (JCLQTypes type : JCLQTypes.values()) {
	            if (type.getName_EN().equals(en))
	                return type;
	        }
	        return null;
	    }
	    
	    static public String typeToEn(JCLQTypes nType)
	    {
	        for (JCLQTypes type : JCLQTypes.values()) {
	            if (type == nType)
	                return type.getName_EN();
	        }
	        return null;
	    }    
	    
	    static public String getJCLQTypeString(String type, String guanshu)
	    {
	    	if(type.equals("SF"))
	    	{
	    		if(guanshu.equals("1"))
	    		{
	    			return "SGCSF";
	    		}
	    		else
	    		{
	    			return "MGCSF";
	    		}
	    	}
	    	else if(type.equals("RFSF"))
	    	{
	    		if(guanshu.equals("1"))
	    		{
	    			return "SGRFSF";
	    		}
	    		else
	    		{
	    			return "MGRFSF";
	    		}
	    	}
	    	else if(type.equals("SFC"))
	    	{
	    		if(guanshu.equals("1"))
	    		{
	    			return "SGSFC";
	    		}
	    		else
	    		{
	    			return "MGSFC";
	    		}
	    	}
	    	else if(type.equals("DXF"))
	    	{
	    		if(guanshu.equals("1"))
	    		{
	    			return "SGDXF";
	    		}
	    		else
	    		{
	    			return "MGDXF";
	    		}
	    	}
	    	else
	    	{
	    		throw new RuntimeException("彩票类型错误");
	    	}
	    }
}