package com.xsc.lottery.service.admin;

import java.util.List;

import com.xsc.lottery.entity.admin.AdminChannel;
import com.xsc.lottery.entity.admin.AdminChannelMode;
import com.xsc.lottery.service.LotteryBaseService;

public interface AdminChannelService extends LotteryBaseService<AdminChannel>
{
	public List<AdminChannel> getAdminChannelDepthDesc(Long parentId);
	
	public List<AdminChannel> getAdminChannelDepthAsc(Long parentId);

	public List<AdminChannelMode> getModeList(Long chnId);
	
	public void saveMode(AdminChannelMode mode);

	public void delMode(AdminChannelMode entity);
	
	public AdminChannelMode getMode(Long modeId);
}
