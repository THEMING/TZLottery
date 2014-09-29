package com.xsc.lottery.service.business;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.entity.business.Filedownlod;
import com.xsc.lottery.service.LotteryBaseService;

public interface FiledownlodService extends LotteryBaseService<Filedownlod>
{
	public List<Filedownlod> getAllFiledownlod();
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Filedownlod> getFiledownlodByName(String filename);
	
}
