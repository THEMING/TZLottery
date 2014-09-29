package com.xsc.lottery.entity.enumerate;

import java.util.ArrayList;
import java.util.List;

public enum WalletLogType {
	代购费用,合买发起费用,合买参与费用,追号冻结,追号费用,合买保底冻结,合买保底费用,
	提款冻结,提款扣款,手续费冻结,
	手续费扣款,账户充值,账户返奖,直接充值,代购退款,合买退款,追号退款,合买撤单,合买保底解冻,合买保底部分解冻,代购部分退款,
	合买部分退款,活动赠送,提款失败返款,合买提成,系统扣款,套餐消费,拉手余额充值,为朋友充值,被朋友充值,佣金充值;
	
	public static List<WalletLogType> LOTTERY_IN = new ArrayList<WalletLogType>();
    public static List<WalletLogType> LOTTERY_OUT = new ArrayList<WalletLogType>();
    
    static {
    	LOTTERY_IN.add(WalletLogType.代购费用);
    	LOTTERY_IN.add(WalletLogType.合买保底费用);
    	LOTTERY_IN.add(WalletLogType.合买参与费用);
    	LOTTERY_IN.add(WalletLogType.合买发起费用);
    	LOTTERY_IN.add(WalletLogType.追号费用);
    	
    	LOTTERY_OUT.add(WalletLogType.代购退款);
    	LOTTERY_OUT.add(WalletLogType.代购部分退款);
    	LOTTERY_OUT.add(WalletLogType.合买退款);
    	LOTTERY_OUT.add(WalletLogType.合买部分退款);
    	LOTTERY_OUT.add(WalletLogType.追号退款);
    }
}