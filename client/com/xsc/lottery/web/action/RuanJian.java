package com.xsc.lottery.web.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Filedownlod;
import com.xsc.lottery.service.business.FiledownlodService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("RuanJian.XZ")
public class RuanJian extends AdminBaseAction {
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FiledownlodService filedownlodService;


	private List<Filedownlod> list;


	private int pageNo = 1;
    private int pageSize = 10;
    
    
	@SuppressWarnings("unchecked")
	public String index()
	{

		list = filedownlodService.getAllFiledownlod();
		
		return SUCCESS;
	}


	public List<Filedownlod> getList() {
		return list;
	}


	public void setList(List<Filedownlod> list) {
		this.list = list;
	}

	
}