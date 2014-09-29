package com.xsc.lottery.entity.enumerate;

/** 活动 */
public enum PloyType
{
    常规("all"), 套餐活动("meal");

    private String name_EN;

    private PloyType(String name_en)
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

    static public PloyType enToType(String en)
    {
        for (PloyType type : PloyType.values()) {
            if (type.getName_EN().equals(en)) {
                return type;
            }
        }
        return null;
    }
}
