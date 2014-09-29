package com.xsc.lottery.util;

import com.xsc.lottery.entity.enumerate.LotteryType;

public class LotteryTypeUtil {
    public static int changeLotteryTypeToNum(LotteryType type)
    {
    	if (type == null) {
			return 0;
		}
    	else if (type.equals(LotteryType.全部)) {
			return 0;
		}
    	else if (type.equals(LotteryType.七乐彩)) {
			return 9;
		}
    	else if (type.equals(LotteryType.七星彩)) {
			return 8;
		}
    	else if (type.equals(LotteryType.上海时时乐)) {
			return 7;
		}
    	else if (type.equals(LotteryType.双色球)) {
			return 1;
		}
    	else if (type.equals(LotteryType.四场进球)) {
			return 13;
		}
    	else if (type.equals(LotteryType.大乐透)) {
			return 2;
		}
    	else if (type.equals(LotteryType.排列三)) {
			return 4;
		}
    	else if (type.equals(LotteryType.排列五)) {
			return 5;
		}
    	else if (type.equals(LotteryType.福彩3d)) {
			return 3;
		}
    	else if (type.equals(LotteryType.竞彩篮球)) {
			return 15;
		}
		else if (type.equals(LotteryType.竞彩足球)) {
			return 12;
		}
		else if (type.equals(LotteryType.足彩14场)) {
			return 10;
		}
		else if (type.equals(LotteryType.足彩6场半)) {
			return 14;
		}
		else if (type.equals(LotteryType.足彩任9)) {
			return 11;
		}
		else if (type.equals(LotteryType.重庆时时彩)) {
			return 6;
		}
    	else if (type.equals(LotteryType.老11选5)) {
			return 16;
		}
    	else if (type.equals(LotteryType.快乐扑克3)) {
			return 17;
		}
    	else if (type.equals(LotteryType.广西快3)) {
			return 18;
		}
    	else if (type.equals(LotteryType.上海11选5)) {
			return 19;
		}
    	else if (type.equals(LotteryType.十一运夺金)) {
			return 20;
		}
    	else {
			throw new RuntimeException("对不起，没有这个彩种");
		}
    }
    
    public static int changeSupplierStatusToNum(String status)
    {
    	if (status.equals("无效")) {
			return 0;
		}
    	else if (status.equals("可以正常出票")) {
			return 1;
		}
    	else if (status.equals("不能出票")) {
			return 2;
		}
    	else {
			throw new RuntimeException("对不起，没有这个状态");
		}
    }
    
    public static int changeSupplierPayStatusToNum(String status)
    {
    	if (status.equals("申请")) {
			return 0;
		}
    	else if (status.equals("正在审批")) {
			return 1;
		}
    	else if (status.equals("审批完成")) {
			return 2;
		}
    	else if (status.equals("付款完成")) {
			return 3;
		}
    	else {
			throw new RuntimeException("对不起，没有这个状态");
		}
    }
    
    public static String changeLotteryTypeToName(Integer num)
    {
    	if (num == null) {
			return "全部";
		}
    	else if (num == 0) {
    		return "全部";
		}
    	else if (num == 9) {
    		return "七乐彩";
		}
    	else if (num == 8) {
    		return "七星彩";
		}
    	else if (num == 7) {
    		return "上海时时乐";
		}
    	else if (num == 1) {
    		return "双色球";
		}
    	else if (num == 13) {
    		return "四场进球";
		}
    	else if (num == 2) {
    		return "大乐透";
		}
    	else if (num == 4) {
    		return "排列三";
		}
    	else if (num == 5) {
    		return "排列五";
		}
    	else if (num == 3) {
    		return "福彩3d";
		}
    	else if (num == 15) {
    		return "竞彩篮球";
		}
		else if (num == 12) {
    		return "竞彩足球";
		}
		else if (num == 10) {
    		return "足彩14场";
		}
		else if (num == 14) {
    		return "足彩6场半";
		}
		else if (num == 11) {
    		return "足彩任9";
		}
		else if (num == 6) {
    		return "重庆时时彩";
		}
		else if (num == 16) {
			return "老11选5";
		}
		else if (num == 17) {
			return "快乐扑克3";
		}
		else if (num == 18) {
			return "广西快3";
		}
		else if (num == 19) {
			return "上海11选5";
		}
		else if (num == 20) {
			return "十一运夺金";
		}
    	else {
			throw new RuntimeException("对不起，没有这个彩种");
		}
    }
}
