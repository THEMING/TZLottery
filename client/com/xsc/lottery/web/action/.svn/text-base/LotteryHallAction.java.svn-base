package com.xsc.lottery.web.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.admin.WinPrize;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.service.business.ArticleService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryTermService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("lotteryHallAction")
public class LotteryHallAction extends LotteryClientBaseAction{
	  @Autowired
	  private LotteryOrderService orderService;
	  @Autowired
	  private LotteryTermService lotteryTermService;
	  
	  @Autowired
	  private ArticleService articleService;
	 
	private Customer customer;
	  private String tt;
	  
	  private LotteryTerm ssq;
	  
	  private LotteryTerm ssqe;
	  
	  private LotteryTerm dlt;
	  
	  private LotteryTerm dlte;
	  
	  private String ssqdate;
	  
	  private String dltdate;
	  
	  private String ssqpool;
	  
	  private String dltpool;
	
	  private List winprize;
	  
	 

	private Page<WinPrize> page;
	 
	  private int pageNo=1;
	  
	  private int pageSize=10;
	  
	  

	public String index()
	    {
		  page = new Page<WinPrize>();
          page.setPageNo(pageNo);
          page.setPageSize(pageSize);
          page.setAutoCount(true);
          articleService.find(page, null, null, null,"近期中奖");
		  Customer customer = getCurCustomer();
          winprize=orderService.getWinPrize();
		  ssq=lotteryTermService.getCurrentTerm(LotteryType.双色球);
		  ssqe=lotteryTermService.getLastOpenPrizeResult(LotteryType.双色球);
		  dlt=lotteryTermService.getCurrentTerm(LotteryType.大乐透);
		  dlte=lotteryTermService.getLastOpenPrizeResult(LotteryType.大乐透);
		  ssqdate=date(ssq.getOpenPrizeTime());
		  dltdate=date(dlt.getOpenPrizeTime());
	      String s1=ssqe.getPrizePool().toString().split("\\.")[0];
	      ssqpool=poolsize(s1);
	      String s2=dlte.getPrizePool().toString().split("\\.")[0];
	      dltpool=poolsize(s2);
		  if(null!=customer){
			   Set set=new HashSet();
			   List<Order> list=orderService.getOrder(customer, LotteryType.全部);
			   for(int i=0;i<list.size();i++)
			   {
				   set.add(list.get(i).getType()+","+list.get(i).getType().getName_EN());
			   }
			   ServletActionContext.getRequest().setAttribute("ll", set);
		  }
		  return SUCCESS;
	    }
	
	  public String date(Calendar calendar){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		
		String date=sd.format(calendar.getTime());
		return date;
	}
	
	  public  String poolsize(String sq){
		 String ss="";
		 String []m={"元","万","亿"};
		 int l=sq.length();
		 for(int i=0;i<l/4+1;i++){
			 if(l%4==0&&i==l/4)
				 break;
			 if(i==l/4){
			ss=sq.substring(0, l-i*4)+m[i]+ss;}
			 else{
			 ss=sq.substring(l-(i+1)*4, l-i*4)+m[i]+ss;}
		 }
		return ss;
	}
	
	  public LotteryOrderService getOrderService() {
		return orderService;
	}
	  public List getWinprize() {
			return winprize;
		}

		public void setWinprize(List winprize) {
			this.winprize = winprize;
		}
	  public void setOrderService(LotteryOrderService orderService) {
		this.orderService = orderService;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	  public String getTt() {
			return tt;
		}
		public void setTt(String tt) {
			this.tt = tt;
		}
		public LotteryTermService getLotteryTermService() {
			return lotteryTermService;
		}
		public void setLotteryTermService(LotteryTermService lotteryTermService) {
			this.lotteryTermService = lotteryTermService;
		}
		public LotteryTerm getSsq() {
			return ssq;
		}
		public void setSsq(LotteryTerm ssq) {
			this.ssq = ssq;
		}
		public LotteryTerm getDlt() {
			return dlt;
		}
		public void setDlt(LotteryTerm dlt) {
			this.dlt = dlt;
		}
		public LotteryTerm getSsqe() {
			return ssqe;
		}
		public void setSsqe(LotteryTerm ssqe) {
			this.ssqe = ssqe;
		}
		public LotteryTerm getDlte() {
			return dlte;
		}
		public void setDlte(LotteryTerm dlte) {
			this.dlte = dlte;
		}
		public String getSsqdate() {
			return ssqdate;
		}
		public void setSsqdate(String ssqdate) {
			this.ssqdate = ssqdate;
		}
		public String getDltdate() {
			return dltdate;
		}
		public void setDltdate(String dltdate) {
			this.dltdate = dltdate;
		}
		public String getSsqpool() {
			return ssqpool;
		}
		public void setSsqpool(String ssqpool) {
			this.ssqpool = ssqpool;
		}
		public String getDltpool() {
			return dltpool;
		}
		public void setDltpool(String dltpool) {
			this.dltpool = dltpool;
		}
		 public ArticleService getArticleService() {
				return articleService;
			}

			public void setArticleService(ArticleService articleService) {
				this.articleService = articleService;
			}

			public Page<WinPrize> getPage() {
				return page;
			}

			public void setPage(Page<WinPrize> page) {
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
	
}
