package com.xsc.lottery.admin.action.term;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.TermTypeConfig;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.SendTicketPlat;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.TermConfigService;
import com.xsc.lottery.task.term.LotteryTermTaskFactory;

/** 彩期采集后台管理action */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Admin.termConfig")
public class AdminTermConfig extends AdminBaseAction
{
    @Autowired
    private TermConfigService termConfigService;
    @Autowired
    private LotteryTermService termService;
    @Autowired
    private LotteryTermTaskFactory lotteryTermTaskFactory;

    private LotteryType type;
    private LotteryType[] typeList = LotteryType.values();
    private TermTypeConfig termConfig;
    private SendTicketPlat[] sendTicketPlats = SendTicketPlat.values();
    private LotteryTerm currentTerm;
    private List<LotteryTerm> currentTermList = null;
    
    private String termNo;
    private String addNewTerm;

	public String index()
    {
        if(null != type) {
            termConfig = termConfigService.getConfigByType(type);
            if(lotteryTermTaskFactory.canStartMultiTaskOneType(type)) {
            	currentTermList = termService.getCurrentTermArray(type);
            	if(currentTermList.size() > 0) {
            		currentTerm = currentTermList.get(0);
            	}
            }
            else {
            	currentTerm = termService.getCurrentTerm(type);
            }
        }
        return SUCCESS;
    }
    
    public String saveConfig()
    {
    	if(termConfig != null) {
    		termConfig.setType(type);
    		termConfigService.save(termConfig);
    	}
    	return SUCCESS;
    }
    
    public String saveTerm()
    {
    	saveConfig();
    
    	//if(currentTerm == null) {
    	//	currentTerm = new LotteryTerm();
    	//}
    	
    	if(termConfig.isStop()) {
    		addActionMessage(type.name() + " 当前期开奖后,不再生成新彩期!");
    		return index();
    	}
    	
    	if(addNewTerm != null && addNewTerm.equals("true")) {
    		logger.info("add new current term!");   		
    		LotteryTerm newTerm = new LotteryTerm();
    		newTerm.setTermNo(termNo);
    		newTerm.setStartSaleTime(currentTerm.getStartSaleTime());
    		newTerm.setStopSaleTime(currentTerm.getStopSaleTime());
    		newTerm.setStopTogetherSaleTime(currentTerm.getStopTogetherSaleTime());
    		newTerm.setOpenPrizeTime(currentTerm.getOpenPrizeTime());
    		newTerm.setSendPrizeTime(currentTerm.getSendPrizeTime());
    		currentTerm = newTerm;
    	}
    	
    	currentTerm.setType(type);
    	currentTerm.setAutoCheckWin(termConfig.isAutoCheckWin());
        currentTerm.setAutoOpen(termConfig.isAutoOpen());
        currentTerm.setCurrent(true);
        currentTerm.setOutPoint(termConfig.getOutPoint());
       
        //彩期线程池中是否有彩期
    	if (lotteryTermTaskFactory.hasTask(currentTerm)) {
        	addActionMessage(currentTerm + " 已经在销售队列，修改无效。");
        	return index();
        }
    	
    	termService.save(currentTerm);
    	
    	if(lotteryTermTaskFactory.canStartMultiTaskOneType(type)) {
        	lotteryTermTaskFactory.shutdownByTypeAndTerm(type, currentTerm, true);
        	lotteryTermTaskFactory.startLotteryTermByTypeAndTerm(type, currentTerm);
        }
        else {
        	lotteryTermTaskFactory.shutdownByType(type, true);
        	lotteryTermTaskFactory.startLotteryTermByType(type);
        }
        
        addActionMessage(currentTerm + " 开始销售!");
    	return index();
    }
    
    public String findByTerm()
    {
    	if(null != type) {
            currentTermList = termService.getCurrentTermArray(type);
            for(int i = 0; i < currentTermList.size(); i++) {
            	if(currentTermList.get(i).getTermNo().equals(currentTerm.getTermNo())) {
            		currentTerm = currentTermList.get(i);
            	}
            }
    	}
    	
    	logger.info("select " + currentTerm.getTermNo());
    	return SUCCESS;
    }
    
    protected Calendar string2Calendar(String str)
    {
    	if(str == null || str.equals("")) {
    		return null;
    	}
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Date date = null;
		try {
			date = sdf.parse(str);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		return calendar;
    }
    
    public void setType(LotteryType type)
    {
        this.type = type;
    }

    public TermTypeConfig getTermConfig()
    {
        return termConfig;
    }

    public void setTermConfig(TermTypeConfig termConfig)
    {
        this.termConfig = termConfig;
    }

    public SendTicketPlat[] getSendTicketPlats()
    {
        return sendTicketPlats;
    }

    public LotteryTerm getCurrentTerm()
    {
        return currentTerm;
    }

    public void setCurrentTerm(LotteryTerm currentTerm)
    {
        this.currentTerm = currentTerm;
    }

    public LotteryType getType()
    {
        return type;
    }

    public LotteryType[] getTypeList()
    {
        return typeList;
    }

	public List<LotteryTerm> getCurrentTermList() {
		return currentTermList;
	}

	public void setAddNewTerm(String addNewTerm) {
		this.addNewTerm = addNewTerm;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	
	public void setStartSaleTime(String startSaleTime) {
		//this.startSaleTime = startSaleTime;
		if(currentTerm != null) {
			currentTerm.setStartSaleTime(string2Calendar(startSaleTime));
		}
	}

	public void setStopSaleTime(String stopSaleTime) {
		//this.stopSaleTime = stopSaleTime;
		if(currentTerm != null) {
			currentTerm.setStopSaleTime(string2Calendar(stopSaleTime));
		}
	}

	public void setStopTogetherSaleTime(String stopTogetherSaleTime) {
		//this.stopTogetherSaleTime = stopTogetherSaleTime;
		if(currentTerm != null) {
			currentTerm.setStopTogetherSaleTime(string2Calendar(stopTogetherSaleTime));
		}
	}

	public void setOpenPrizeTime(String openPrizeTime) {
		//this.openPrizeTime = openPrizeTime;
		if(currentTerm != null) {
			currentTerm.setOpenPrizeTime(string2Calendar(openPrizeTime));
		}
	}

	public void setSendPrizeTime(String sendPrizeTime) {
		//this.sendPrizeTime = sendPrizeTime;
		if(currentTerm != null) {
			currentTerm.setSendPrizeTime(string2Calendar(sendPrizeTime));
		}
	}
}
