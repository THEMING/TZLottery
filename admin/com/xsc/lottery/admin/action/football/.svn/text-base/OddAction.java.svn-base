package com.xsc.lottery.admin.action.football;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.Odd;
import com.xsc.lottery.service.business.MatchArrangeService;

@Scope("prototype")
@Controller("oddaction")
@SuppressWarnings( { "unused", "serial" })
public class OddAction extends AdminBaseAction{

	
    private Page<Odd> page;
	
    private int pageNo=1;

    private int pageSize=10;
    
    private Odd odd;
    
    private String matchId;
    
    private String pankouType;
    
    private String pankouId;
    
    private String sheng;
    
    private String ping;
    
    private String fu;
    
    private String companyId;
    
    private List<Odd> list;
    
	@Autowired
	MatchArrangeService MatchArrangeService;
	public String index(){
		page = new Page<Odd>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
		MatchArrangeService.findOdd(page);
		return SUCCESS;
	}
	public String odd(){
		MatchArrangeService.fetchpeilv();
		return index();
	}
	public String findOdd(){
		list=MatchArrangeService.getOdd(matchId);
		return SUCCESS;
	}
	public MatchArrangeService getMatchArrangeService() {
		return MatchArrangeService;
	}
	public void setMatchArrangeService(MatchArrangeService matchArrangeService) {
		MatchArrangeService = matchArrangeService;
	}
	public Page<Odd> getPage() {
		return page;
	}
	public void setPage(Page<Odd> page) {
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
	public Odd getOdd() {
		return odd;
	}
	public void setOdd(Odd odd) {
		this.odd = odd;
	}
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public String getPankouType() {
		return pankouType;
	}
	public void setPankouType(String pankouType) {
		this.pankouType = pankouType;
	}
	public String getPankouId() {
		return pankouId;
	}
	public void setPankouId(String pankouId) {
		this.pankouId = pankouId;
	}
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getPing() {
		return ping;
	}
	public void setPing(String ping) {
		this.ping = ping;
	}
	public String getFu() {
		return fu;
	}
	public void setFu(String fu) {
		this.fu = fu;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public List<Odd> getList() {
		return list;
	}
	public void setList(List<Odd> list) {
		this.list = list;
	}
}
