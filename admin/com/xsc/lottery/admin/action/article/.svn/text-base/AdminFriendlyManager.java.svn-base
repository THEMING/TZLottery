package com.xsc.lottery.admin.action.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.FriendlyLink;
import com.xsc.lottery.service.business.ArticleService;

@Scope("prototype")
@Controller("Admin.FriendlyLinkManagement")
public class AdminFriendlyManager extends AdminBaseAction
{
  private static final long serialVersionUID = 8284681980990251689L;
  
  @Autowired
  private ArticleService articleService;
  private Long id;
  private String name;
  private String url;
  private Integer type;
  private Integer sort;
  private FriendlyLink friendlyLink;
  private List<FriendlyLink> friendlyLinkList;
  private String message;

  public String index()
  {
     this.friendlyLinkList = this.articleService.findAllFriendlyLink();
     return "success";
  }

  public String save()
  {
    try {
       if (this.friendlyLink == null)
      {
         this.friendlyLink = new FriendlyLink();
      }
       this.friendlyLink.setName(this.name);
       this.friendlyLink.setUrl(this.url);
       this.friendlyLink.setType(type);
       this.friendlyLink.setSort(this.sort);
       this.articleService.saveFriendlyLink(this.friendlyLink);
       addActionMessage("保存成功!");
    }
    catch (Exception e) {
       addActionMessage("保存失败!");
    }
     return index();
  }

  public String edit()
  {
     if (this.id != null)
       this.friendlyLink = this.articleService.getFriendlyLink(this.id);
     return "edit";
  }

  public String delete() {
    try {
       this.articleService.deleteFriendlyLink(this.id);
       addActionMessage("删除成功!");
    }
    catch (Exception e) {
       addActionMessage("删除失败!");
    }
     return index();
  }

  public FriendlyLink getFriendlyLink() {
     return this.friendlyLink;
  }

  public void setFriendlyLink(FriendlyLink friendlyLink) {
     this.friendlyLink = friendlyLink;
  }

  public Long getId() {
     return this.id;
  }

  public void setId(Long id) {
     this.id = id;
  }

  public String getName() {
     return this.name;
  }

  public void setName(String name) {
     this.name = name;
  }

  public String getUrl() {
     return this.url;
  }

  public void setUrl(String url) {
     this.url = url;
  }

  public Integer getSort() {
     return this.sort;
  }

  public void setSort(Integer sort) {
     this.sort = sort;
  }

  public List<FriendlyLink> getFriendlyLinkList() {
     return this.friendlyLinkList;
  }

  public String getMessage() {
     return this.message;
  }

public Integer getType()
{
	return type;
}

public void setType(Integer type)
{
	this.type = type;
}
}

