package com.xsc.lottery.util;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

public class MapUtil
{
	public static Boolean checkKey(Map map,String key){
		if(map==null){
			return false;
		}
		if(map.containsKey(key)&&MapUtils.getObject(map, key)!=null){
			return true;
		}
		return false;
	}

}
