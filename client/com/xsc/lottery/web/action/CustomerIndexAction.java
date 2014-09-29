package com.xsc.lottery.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.admin.WinPrize;
import com.xsc.lottery.service.business.ArticleService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.indexAct")
public class CustomerIndexAction extends LotteryClientBaseAction
{
	private int mainIndex;
	
	private int subIndex;
	
	@Autowired
	  private ArticleService articleService;
	
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
        
       
		return SUCCESS;
    }
	
    public int getMainIndex() {
		return mainIndex;
	}

	public void setMainIndex(int mainIndex) {
		this.mainIndex = mainIndex;
	}

	public int getSubIndex() {
		return subIndex;
	}

	public void setSubIndex(int subIndex) {
		this.subIndex = subIndex;
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
