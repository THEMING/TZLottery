package com.xsc.lottery.web.action.json;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonMsgBean
{
    public static String RESULT_STATUS_STR = "status";
    public static String RESULT_MSG_STR = "message";

    public static String getResultStatusJsonStrByType(
            AjaxResultStatusType statusType)
    {
        JSONObject objec = new JSONObject();
        objec.put(RESULT_STATUS_STR, statusType.name());
        return objec.toString();
    }
    
    public static String getResultStatusJsonStrByTypeAndBalance(
            AjaxResultStatusType statusType, BigDecimal balance)
    {
        JSONObject objec = new JSONObject();
        objec.put(RESULT_STATUS_STR, statusType.name());
        objec.put("balance", balance);
        return objec.toString();
    }

    public static String getResultStatusJsonStrByTypeAndMsg(
            AjaxResultStatusType statusType, String msg)
    {
        JSONObject object = new JSONObject();
        object.put(RESULT_STATUS_STR, statusType.name());
        object.put(RESULT_MSG_STR, msg);
        return object.toString();
    }

    @SuppressWarnings("unchecked")
    public static String MapToJsonString(Map map)
    {
        return JSONObject.fromObject(map).toString();
    }

    @SuppressWarnings("unchecked")
    public static String ListToJsonString(List list)
    {
        return JSONArray.fromObject(list).toString();
    }

    @SuppressWarnings("static-access")
    public static String getResultStatusJsonStrByTypeAndOjbe(
            AjaxResultStatusType statusType, Object... objs)
    {
        JSONObject object = new JSONObject();
        object.put(RESULT_STATUS_STR, statusType.name());
        object.put(RESULT_MSG_STR, object.fromObject(objs).toString());
        return object.toString();
    }
}
