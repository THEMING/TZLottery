package com.xsc.lottery.service.business;


import java.util.List;

import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.business.YouHuiMa;
import com.xsc.lottery.entity.business.YouHuiMa.YouHuiMaType;
import com.xsc.lottery.service.LotteryBaseService;

public interface YouHuiMaService extends LotteryBaseService<YouHuiMa> {

	public List<YouHuiMa> getYouHuiMaByNumber(String number);
	
	public List<YouHuiMa> getYouHuiMaAll();
	
	public List<YouHuiMa> getYouHuiMaByType(YouHuiMaType type);
	
	public List<YouHuiMa> getYouHuiMaByFanWei(String zxz, String zdz);
	
	public List<YouHuiMa> findYouHuiMa(YouHuiMaType type,Activity activity);
}
