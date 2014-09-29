package com.xsc.lottery.service.business;

import java.util.List;

import com.xsc.lottery.entity.business.AdminMobile;
import com.xsc.lottery.service.LotteryBaseService;

public interface AdminMobileService extends LotteryBaseService<AdminMobile> {
	public List<AdminMobile> getAllAdminMobile();
	public List<AdminMobile> getAllActiveAdminMobile();
	public int AddMobileNotify(String szContent, int nType);
}
