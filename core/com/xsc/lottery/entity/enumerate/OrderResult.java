package com.xsc.lottery.entity.enumerate;

public enum OrderResult {

	未开奖,作废,未中奖,已中奖,已兑奖;
	
	static public OrderResult enToType(String en)
    {
    	for (OrderResult type : OrderResult.values()) {
            if (type.name().equals(en))
                return type;
        }
        return null;
    }
}
