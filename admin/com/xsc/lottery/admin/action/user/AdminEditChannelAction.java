package com.xsc.lottery.admin.action.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.admin.AdminChannel;
import com.xsc.lottery.entity.admin.AdminChannelMode;
import com.xsc.lottery.service.admin.AdminChannelService;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("adminEditTunnel")
public class AdminEditChannelAction extends AdminBaseAction
{
    private Long depth;// 节点ID

    @Autowired
    private AdminChannelService channelService;

    private List<AdminChannel> parentChannelList;

    private List<AdminChannel> depthList;

    private List<AdminChannel> chnTreeList;

    private List<AdminChannelMode> modeList;

    private AdminChannel channel;

    private Long chnId;

    private String cname;

    private String ename;

    private String cid;

    private AdminChannelMode channelmode;

    private Long modeId;

    // 模版页面主体
    public String index()
    {
        List<AdminChannel> list = new ArrayList<AdminChannel>();
        List<AdminChannel> depth = channelService.getAdminChannelDepthAsc(0l);
        for (AdminChannel chn : depth) {
            list.add(chn);

            List<AdminChannel> parent = channelService
                    .getAdminChannelDepthAsc(chn.getId());
            if (parent != null) {
                for (AdminChannel chnParent : parent) {
                    list.add(chnParent);
                }
            }
        }
        chnTreeList = list;
        return "chnlist";
    }

    // 模块编辑
    public String edit()
    {
        depthList = channelService.getAdminChannelDepthAsc(0l);
        if (chnId != null) {
            channel = channelService.findById(chnId);
        }
        return "edit";
    }

    // 模块保存
    public String sav()
    {
        channel.setParentPath(channel.getParentId().toString());
        if (channel.getParentId() > 0) {
            channel.setDepth(1);
        }
        else {
            channel.setDepth(0);
        }
        channelService.save(channel);
        return index();
    }

    public String modeview()
    {
        modeList = channelService.getModeList(chnId);
        return "vmode";
    }

    public String editmode()
    {
        if (modeId != null) {
            channelmode = channelService.getMode(modeId);
            cname = channelmode.getCname();
            ename = channelmode.getEname();
            modeId = channelmode.getId();
            chnId = channelmode.getChId();
        }
        return "emode";
    }

    public String savemode()
    {
        channelmode = new AdminChannelMode();
        channelmode.setChId(chnId);
        channelmode.setCname(cname);
        channelmode.setEname(ename);
        channelService.saveMode(channelmode);
        return modeview();
    }

    public String delmode()
    {
        AdminChannelMode admode = channelService.getMode(modeId);
        channelService.delMode(admode);
        return modeview();
    }

    // 模块删除
    public String del()
    {

        return index();
    }

    public Long getDepth()
    {
        return depth;
    }

    public void setDepth(Long depth)
    {
        this.depth = depth;
    }

    public Long getChnId()
    {
        return chnId;
    }

    public void setChnId(Long chnId)
    {
        this.chnId = chnId;
    }

    public List<AdminChannel> getParentChannelList()
    {
        return parentChannelList;
    }

    public List<AdminChannel> getDepthList()
    {
        return depthList;
    }

    public List<AdminChannel> getChnTreeList()
    {
        return chnTreeList;
    }

    public AdminChannel getChannel()
    {
        return channel;
    }

    public void setChannel(AdminChannel channel)
    {
        this.channel = channel;
    }

    public void setChannelService(AdminChannelService channelService)
    {
        this.channelService = channelService;
    }

    public AdminChannelMode getChannelMode()
    {
        return channelmode;
    }

    public void setChannelMode(AdminChannelMode channelmode)
    {
        this.channelmode = channelmode;
    }

    public Long getModeId()
    {
        return modeId;
    }

    public void setModeId(Long modeId)
    {
        this.modeId = modeId;
    }

    public List<AdminChannelMode> getModeList()
    {
        return modeList;
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getEname()
    {
        return ename;
    }

    public void setEname(String ename)
    {
        this.ename = ename;
    }

    public String getCid()
    {
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }

}
