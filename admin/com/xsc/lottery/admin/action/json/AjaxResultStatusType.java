package com.xsc.lottery.admin.action.json;

public enum AjaxResultStatusType
{
    // 基本操作状态
    _0000("用户或密码错误"), _0001("请登陆"), ;
    private String value;

    private AjaxResultStatusType(String val)
    {
        this.value = val;
    }

    public String getValue()
    {
        return value;
    }

}
