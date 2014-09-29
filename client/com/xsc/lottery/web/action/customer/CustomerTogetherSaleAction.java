package com.xsc.lottery.web.action.customer;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.business.Community;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.business.Plan;
import com.xsc.lottery.entity.business.PlanItem;
import com.xsc.lottery.entity.enumerate.CommunityType;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.SecretType;
import com.xsc.lottery.service.business.CommunityService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryPlanService;
import com.xsc.lottery.web.action.LotteryClientBaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.customerTogetherSale")
public class CustomerTogetherSaleAction extends LotteryClientBaseAction {
	@Autowired
	private CommunityService communityService;

	@Autowired
	private LotteryOrderService lotteryOrderService;

	@Autowired
	private LotteryPlanService planService;

	private Page<Community> page;

	private Community community;
	
	private Long id;

	private String numberNo;

	private int pageNo = 1;

	private int pageSize = 10;

	private int totalPages;

	private LotteryType type;

	private LotteryType[] typeList = LotteryType.values();

	private OrderStatus[] statusList = OrderStatus.values();

	private Calendar beginTime;

	private Calendar endTime;

	private String status;

	private List<Part> list;

	private List<PlanItem> itemlist;

	private String message;

	private int tagIndex;
	
	private Customer customer;

	// 我的合买查询
	public String index() 
	{
		Customer customer = this.getCurCustomer();
		page = new Page<Community>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setAutoCount(true);
		page = communityService.getTogetherSale(page, type, beginTime, endTime,
				customer);
		for (Community c : page.getResult()) {
			Order order = lotteryOrderService.getOrderByCommunity(c);
			if (order.getWinTaxMoney().toString().equals("0")) {
				order.setWinTaxMoney(new BigDecimal("0.00"));
			}
			c.setOrder(order);
		}
		return SUCCESS;
	}

	public String backout() {
		community = communityService.findById(id);
		if (community.getOkPart() / community.getTotalPart() >= 0.5) {
			message = "不能撤单，有一半或以上分数参与!";
			return index();
		}

		if (!community.getCommunityType().equals(CommunityType.未满员)) {
			message = "不能撤单，已经撤单或已流产!";
			return index();
		}
		communityService.backoutCommunity(community);
		return index();
	}

	public String detail()
	{
		Plan plan = planService.getPlanBynumberNo(numberNo);
		community = communityService.getCommunityByPlan(plan);
		Order order = lotteryOrderService.getOrderByCommunity(community);
		community.setOrder(order);
		list = communityService.getPartByCommunity(community);
		Customer customer = this.getCurCustomer();

		if (customer != null) {
			if (community.getCustomer().getId().equals(customer.getId())) {
				tagIndex = 1;
			}
			else if (community.getSecretType().equals(SecretType.公开)) {
				tagIndex = 1;
			}
			else if (community.getSecretType().equals(SecretType.只对)) {
				List<Part> templist = communityService.getPartList(community,
						customer);
				if (templist.size() > 0) {
					tagIndex = 1;
				} 
				else {
					tagIndex = 0;
					message = "跟单即可见";
				}
			}
			else if (community.getSecretType().equals(SecretType.开奖后公开)) {
				LotteryTerm term = community.getTerm();
				if (term.getOpenPrizeTime().before(Calendar.getInstance())) {
					tagIndex = 1;
				} 
				else {
					tagIndex = 0;
					message = "开奖后公开";
				}
			} 
			else if (community.getSecretType().equals(SecretType.保密)) {

				tagIndex = 0;
				message = "保密";
			} 
			else {
				tagIndex = 0;
				message = "登录后可见";
			}
		}
		// 没有登录
		else if (customer == null) {
			if (community.getSecretType().equals(SecretType.公开)) {
				tagIndex = 1;
			}
			// 只对参与者公开
			else if (community.getSecretType().equals(SecretType.只对)) {

				tagIndex = 0;
				message = "跟单即可见";
			}
			// 开奖后公开
			else if (community.getSecretType().equals(SecretType.开奖后公开)) {
				LotteryTerm term = community.getTerm();
				if (term.getOpenPrizeTime().before(Calendar.getInstance())) {
					tagIndex = 1;
				} else {
					tagIndex = 0;
					message = "开奖后公开";
				}
			} else if (community.getSecretType().equals(SecretType.保密)) {
				tagIndex = 0;
				message = "保密";
			} else {
				tagIndex = 0;
				message = "登录后可见";
			}
		}
		return "info";

	}

	// 我的合买查询详情
	public String info() 
	{
		community = communityService.findById(id);
		list = communityService.getPartByCommunity(community);
		Order order = lotteryOrderService.getOrderByCommunity(community);
		community.setOrder(order);
		customer = this.getCurCustomer();
		message = "nothing";

		if (customer != null) {
			if (community.getCustomer().getId().equals(customer.getId())) {
				tagIndex = 1;
			}
			else if (community.getSecretType().equals(SecretType.公开)) {
				tagIndex = 1;
			}
			// 只对参与者公开
			else if (community.getSecretType().equals(SecretType.只对)) {
				List<Part> templist = communityService.getPartList(community,
						customer);
				if (templist.size() > 0) {
					tagIndex = 1;
				} else {
					tagIndex = 0;
					message = "跟单即可见";
				}
			}
			// 开奖后公开
			else if (community.getSecretType().equals(SecretType.开奖后公开)) {
				LotteryTerm term = community.getTerm();
				if (term.getOpenPrizeTime().before(Calendar.getInstance())) {
					tagIndex = 1;
				} else {
					tagIndex = 0;
					message = "开奖后公开";
				}
			} else if (community.getSecretType().equals(SecretType.保密)) {

				tagIndex = 0;
				message = "保密";
			} else {
				tagIndex = 0;
				message = "登录后可见";
			}
		}
		// 没有登录
		else if (customer == null) {
			if (community.getSecretType().equals(SecretType.公开)) {
				tagIndex = 1;
			}
			// 只对参与者公开
			else if (community.getSecretType().equals(SecretType.只对)) {

				tagIndex = 0;
				message = "跟单即可见";
			}
			// 开奖后公开
			else if (community.getSecretType().equals(SecretType.开奖后公开)) {
				LotteryTerm term = community.getTerm();
				if (term.getOpenPrizeTime().before(Calendar.getInstance())) {
					tagIndex = 1;
				}
				else {
					tagIndex = 0;
					message = "开奖后公开";
				}
			}
			else if (community.getSecretType().equals(SecretType.保密)) {
				tagIndex = 0;
				message = "保密";
			} 
			else {
				tagIndex = 0;
				message = "登录后可见";
			}
		}
		System.out.println(community.getSecretType().toString());
		return "info";
	}

	public void setLotteryOrderService(LotteryOrderService lotteryOrderService) {
		this.lotteryOrderService = lotteryOrderService;
	}

	public void setPlanService(LotteryPlanService planService) {
		this.planService = planService;
	}

	public void setNumberNo(String numberNo) {
		this.numberNo = numberNo;
	}

	public Page<Community> getPage() {
		return page;
	}

	public void setPage(Page<Community> page) {
		this.page = page;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public LotteryType getType() {
		return type;
	}

	public void setType(LotteryType type) {
		this.type = type;
	}

	public Calendar getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Calendar beginTime) {
		this.beginTime = beginTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LotteryType[] getTypeList() {
		return typeList;
	}

	public OrderStatus[] getStatusList() {
		return statusList;
	}

	public void setCommunityService(CommunityService communityService) {
		this.communityService = communityService;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<PlanItem> getItemlist() {
		return itemlist;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Community getCommunity() {
		return community;
	}

	public List<Part> getList() {
		return list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTagIndex() {
		return tagIndex;
	}

	public void setTagIndex(int tagIndex) {
		this.tagIndex = tagIndex;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
