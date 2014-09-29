package com.xsc.lottery.web.action;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Community;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.MatchArrange;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryTermService;
import com.xsc.lottery.service.business.MatchArrangeService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("groupbuy.indexAct")
public class GroupBuyIndexAction extends LotteryClientBaseAction
{
    @Autowired
    private CommunityService communityService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LotteryTermService termService;

    @Autowired
    private MatchArrangeService matchArrangeService;
    
    
    private Page<Community> page;
    
    private List<MatchArrange> list;

    private String lotteryType;

    private Long communityId;

    private int pageNo = 1;

    private int pageSize = 15;

    private int totalPages;

    private CommunityType communityType;

    private CommunityType[] types = CommunityType.values();

    private String name;

    private boolean isself = false;
    
    private String termNo;
    
    private LotteryType type;
    
    private Calendar stopTogetherSaleTime;
    
    private Calendar stopSaleTime;
    
    private String englishType;
    
    
    
    public String help()
    {
    	return SUCCESS;
    }
    
    public String index()
    {
    	Customer customer = null;
        if(name != null) {
        	customer = customerService.getCustomerOrName(name);
        }
        
        if((LotteryType.enToType(lotteryType)!=LotteryType.四场进球)
        		&& (LotteryType.enToType(lotteryType)!=LotteryType.竞彩足球)
        		&& (LotteryType.enToType(lotteryType)!=LotteryType.竞彩篮球)
        		&&(LotteryType.enToType(lotteryType)!=LotteryType.足彩14场)
        		&&(LotteryType.enToType(lotteryType)!=LotteryType.足彩6场半)
        		&&(LotteryType.enToType(lotteryType)!=LotteryType.足彩任9))
        {
        	page = new Page<Community>();
        	page.setPageNo(pageNo);
        	page.setPageSize(pageSize);
        	page.setAutoCount(true);
        	page.setOrder("desc");
        	page = communityService.getCommunityPage(page, LotteryType
                .enToType(lotteryType), communityType, customer, termService
                .getCurrentTerm(LotteryType.enToType(lotteryType)));
        } else
        {
        	page = new Page<Community>();
        	page.setPageNo(pageNo);
        	page.setPageSize(pageSize);
        	page.setAutoCount(true);
        	page.setOrder("desc");
        	page = communityService.getCommunityPage(page, LotteryType
                .enToType(lotteryType), communityType, customer, null);
        }
        if((lotteryType== null)||(LotteryType.enToType(lotteryType)==LotteryType.竞彩足球))
        {
        	list = matchArrangeService.getCurrentMatch();
        }
        if(lotteryType != null)
        {
        	LotteryTerm term = termService.getCurrentTerm(LotteryType.enToType(lotteryType));
        	if(term == null)
        	{
        		return SUCCESS;
        	}
            type = term.getType();
            englishType = type.getName_EN();
            termNo = term.getTermNo();
            stopTogetherSaleTime = term.getStopTogetherSaleTime();
            stopSaleTime = term.getStopSaleTime();
        }       
        return SUCCESS;
    }

    public String listAllGroupBuy()
    {
        page = new Page<Community>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        page.setAutoCount(true);
        page.setOrder("desc");
        page = communityService.getCommunityPage(page, null, null, null, null);
        
        return SUCCESS;
    }

    public String searchGroupBuy()
    {
        page = new Page<Community>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        page.setAutoCount(true);
        page.setOrder("desc");
        page = communityService.getCommunityPage(page, null, CommunityType.未满员, null, null);
        
        return "rmhm";
    }

    public String ListGroupBuyByCust()
    {
        Customer customer = null;
        if(isself) {
            customer = getCurCustomer();
            if(null == customer) {
                return LOGIN;
            }
        }

        page = new Page<Community>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        page.setAutoCount(true);
        page.setOrder("desc");
        page = communityService.getTogetherSaleAndJoin(page, customer,
                LotteryType.enToType(lotteryType), null, communityType, name);
        
        return SUCCESS;
    }
    public String listCurrentMatch()
    {
    	  Customer customer = null;
	      if(name != null) {
	      	customer = customerService.getCustomerOrName(name);
	      }
	      page = new Page<Community>();
	      page.setPageNo(pageNo);
	      page.setPageSize(pageSize);
	      page.setAutoCount(true);
	      page.setOrder("desc");
	      page = communityService.getCommunityPage(page, LotteryType
	              .enToType(lotteryType), communityType, customer, termService
	              .getCurrentTerm(LotteryType.enToType(lotteryType)));
	      list = matchArrangeService.getCurrentMatch();
    	  return SUCCESS;
    }
    public Page<Community> getPage()
    {
        return page;
    }

    public void setPage(Page<Community> page)
    {
        this.page = page;
    }

    public Long getCommunityId()
    {
        return communityId;
    }

    public void setCommunityId(Long communityId)
    {
        this.communityId = communityId;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public CommunityType getCommunityType()
    {
        return communityType;
    }

    public void setCommunityType(CommunityType communityType)
    {
        this.communityType = communityType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCommunityService(CommunityService communityService)
    {
        this.communityService = communityService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public CommunityType[] getTypes()
    {
        return types;
    }

    public String getLotteryType()
    {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType)
    {
        this.lotteryType = lotteryType;
    }

    public boolean isIsself()
    {
        return isself;
    }

    public void setIsself(boolean isself)
    {
        this.isself = isself;
    }

	public List<MatchArrange> getList() {
		return list;
	}

	public void setList(List<MatchArrange> list) {
		this.list = list;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public Calendar getStopTogetherSaleTime() {
		return stopTogetherSaleTime;
	}

	public void setStopTogetherSaleTime(Calendar stopTogetherSaleTime) {
		this.stopTogetherSaleTime = stopTogetherSaleTime;
	}

	public Calendar getStopSaleTime() {
		return stopSaleTime;
	}

	public void setStopSaleTime(Calendar stopSaleTime) {
		this.stopSaleTime = stopSaleTime;
	}

	public String getEnglishType() {
		return englishType;
	}

	public void setEnglishType(String englishType) {
		this.englishType = englishType;
	}
}
