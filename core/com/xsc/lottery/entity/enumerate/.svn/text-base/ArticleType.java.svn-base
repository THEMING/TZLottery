package com.xsc.lottery.entity.enumerate;

public enum ArticleType
{
    彩票新闻("cpxw"),
    专家推荐("zjtj"),
    网站公告("wzgg"),
    缩水讲堂("ssjt"),
    投注策略("tzcn"),
    相关资讯("xgzx"),
    主题新闻("ztxw"),
    彩市新闻("csxw"),
    高频新闻("gpxw");
    
    private String name_EN;
    
    private ArticleType(String name_en)
    {
        this.name_EN = name_en;
    }

    static public ArticleType enToType(String en)
    {
    	for (ArticleType type : ArticleType.values()) {
            if (type.getName_EN().equals(en))
                return type;
        }
        return null;
    }
    
    public String getName_EN()
    {
        return name_EN;
    }
    
    public void setName_EN(String name_EN)
    {
        this.name_EN = name_EN;
    }
}