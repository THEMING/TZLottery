package com.xsc.lottery.admin.action.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.SpreadChannel;
import com.xsc.lottery.entity.business.SystemParam;
import com.xsc.lottery.service.business.SysParamService;
import com.xsc.lottery.web.action.json.JsonMsgBean;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("systemParamManager")
public class SystemParamManagerAction extends AdminBaseAction
{

	public SimpleHibernateTemplate<SpreadChannel, Long> spreadChannelDao;

	@Autowired
	public void setSessionFactory(@Qualifier("sessionFactory")
	SessionFactory sessionfactory)
	{
		this.spreadChannelDao = new SimpleHibernateTemplate<SpreadChannel, Long>(sessionfactory, SpreadChannel.class);
	}

	
	
	
	
	private List<SystemParam> systemParamList;
	
	private Long paramId;
	
	private String name;
	
	private String value;
	
	private String description;
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Long getParamId()
	{
		return paramId;
	}

	public void setParamId(Long paramId)
	{
		this.paramId = paramId;
	}

	@Autowired
	private SysParamService systemParamService;

	public List<SystemParam> getSystemParamList()
	{
		return systemParamList;
	}

	public void setSystemParamList(List<SystemParam> systemParamList)
	{
		this.systemParamList = systemParamList;
	}

	/**
	 * 默认页
	 * @return
	 */
	public String index()
	{		
		systemParamList = systemParamService.findAll();
		
		return "list";
	}
	
	public String change(){
		
		Map map = new HashMap();
		
		SystemParam sp = systemParamService.findById(paramId);
		sp.setName(name);
		sp.setValue(value);
		sp.setDescription(description);
		systemParamService.save(sp);
		
		map.put("message", "修改成功");
		setJsonString(JsonMsgBean.MapToJsonString(map));
		
		
		return AJAXJSON;
	}
	
	public String addParam()
	{
        return "addParam";
    }
	
	public String addParam2(){
		SystemParam sp = new SystemParam();
		sp.setDescription(description);
		sp.setName(name);
		sp.setValue(value);
		systemParamService.save(sp);
		return index();
	}

}
