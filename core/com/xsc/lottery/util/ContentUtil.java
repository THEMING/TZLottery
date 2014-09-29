package com.xsc.lottery.util;

public class ContentUtil
{

    private static final String webRoot = "webRealpath";

    public static String getRealpath()
    {
        return Configuration.getInstance().getValue(webRoot);
    }
}
