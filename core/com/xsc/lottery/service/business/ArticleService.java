package com.xsc.lottery.service.business;
import java.util.Calendar;
import java.util.List;

import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.entity.admin.WinPrize;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.business.ArticleCategory;
import com.xsc.lottery.entity.business.ArticleInLink;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.entity.partner.Partner;
import com.xsc.lottery.service.LotteryBaseService;

public interface ArticleService extends LotteryBaseService<Article>
{
	public List<Article> getAllArticles();
	public Page<Article> getArticlePage(Page<Article> page, Calendar startTime,
			Calendar endTime, String articleType, LotteryType lotteryType,ArticleCategory category,String title);
	public Page<Article> getPicturePage(Page<Article> page, Calendar startTime,
			Calendar endTime, String articleType, LotteryType lotteryType,ArticleCategory category,String title,Partner partner);
	public List<Article> getArticlesByTypeAndNum(String type, int num);
	public List<Article> getArticlesByLotteryTypeAndNum(LotteryType type, int num);
	public List<Article> getArticlesByAuthorAndNum(String author, int num);
	public List<Article> getxgzxArticlesByLotteryTypeAndNum(LotteryType type, int num);
	public List<Article> getExportArticlesByLotteryTypeAndNum(LotteryType type);
	public List<Article> getArticlesBy2TypeAndNum(String ntype, LotteryType type, int num);
	public List<Article> getArticlesByAuthorTypeAndNum(String author, LotteryType type, int num);
	public WinPrize updateWinPrize(WinPrize winPrize);
	public WinPrize saveWinPrize(WinPrize winPrize);
	public Page<WinPrize> find(Page<WinPrize> page,Long id,Calendar time,String type,String rankingType);
	public WinPrize getWinPrize(Long id);
	public void deleteWinPrize(Long id);
	
	/**��ݲ��֣��淨���ͣ���4���article*/
	public List<Article> getArticlesByTypeLotteryTypeAndNum(String type, LotteryType lotteryType, int num);
	
    /**为手机资讯做的分页  begin从1开始*/
	public List<Article> getArticlesByTypeAndNumForPhone(String type, int num, int begin);
	
	
	public ArticleCategory updateCategory(ArticleCategory articleCategory);
	public ArticleCategory saveCategory(ArticleCategory articleCategory);
	public List<ArticleCategory> findAllCategory();
	public ArticleCategory getCategory(Long id);
	public void deleteCategory(Long id);
	
	public ArticleInLink updateInLink(ArticleInLink articleInLink);
	public ArticleInLink saveInLink(ArticleInLink articleInLink);
	public List<ArticleInLink> findAllInLink();
	public ArticleInLink getInLink(Long id);
	public void deleteInLink(Long id);
	
	
	
}
