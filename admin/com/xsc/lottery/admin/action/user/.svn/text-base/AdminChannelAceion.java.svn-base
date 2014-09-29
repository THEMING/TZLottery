package com.xsc.lottery.admin.action.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.admin.AdminChannel;
import com.xsc.lottery.entity.admin.AdminRoleFunction;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.service.admin.AdminUserService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("admChannel")
public class AdminChannelAceion extends AdminBaseAction
{
    private Long depth;// 节点ID

    @Autowired
    private AdminUserService userService;

    private List<AdminChannel> parentChannelList;

    private List<AdminChannel> depthList;

    private List<AdminChannel> chnTreeList;

    private AdminChannel channel;

    private Long chnId;

    public List<AdminChannel> getChnTreeList()
    {
        return chnTreeList;
    }

    public String index()
    {
        return SUCCESS;
    }

    // 模版页面左则部
    public String left()
    {

        parentChannelList = new ArrayList<AdminChannel>();
        AdminUser adminUser = (AdminUser) getSession().get(
                SESSION_ADMINUSER_KEY);
        List<AdminRoleFunction> roleFunction = new ArrayList<AdminRoleFunction>();
        if (depth == null) {
            roleFunction = userService.getRoleFunctionList(adminUser.getRole()
                    .getId());
        }
        else {
            roleFunction = userService.getRoleFunctionList(adminUser.getRole()
                    .getId(), depth);
        }
        for (AdminRoleFunction rf : roleFunction) {
            AdminChannel ac = new AdminChannel();
            ac = rf.getChannel();
            if (ac.getChannelName().getBytes().length > 3) {
            	parentChannelList.add(ac);
			}
        }
        return "left";
    }

    // 模版页面头部
    public String head()
    {
        depthList = new ArrayList<AdminChannel>();
        AdminUser adminUser = (AdminUser) getSession().get(
                SESSION_ADMINUSER_KEY);
        List<AdminRoleFunction> roleFunction = userService.getRoleFunctionList(
                adminUser.getRole().getId(), 0l);
        for (AdminRoleFunction rf : roleFunction) {
            AdminChannel ac = new AdminChannel();
            ac = rf.getChannel();
            if (ac.getChannelName().getBytes().length > 3) {
            	depthList.add(ac);
			}
        }
        return "head";
    }

    public List<AdminChannel> getParentChannelList()
    {
        return parentChannelList;
    }

    public Long getDepth()
    {
        return depth;
    }

    public void setDepth(Long depth)
    {
        this.depth = depth;
    }

    public List<AdminChannel> getDepthList()
    {
        return depthList;
    }

    public AdminChannel getChannel()
    {
        return channel;
    }

    public void setChannel(AdminChannel channel)
    {
        this.channel = channel;
    }

    public Long getChnId()
    {
        return chnId;
    }

    public void setChnId(Long chnId)
    {
        this.chnId = chnId;
    }

    public void setUserService(AdminUserService userService)
    {
        this.userService = userService;
    }

}
