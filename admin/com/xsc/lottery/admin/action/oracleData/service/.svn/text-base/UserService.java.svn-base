package com.xsc.lottery.admin.action.oracleData.service;

import java.util.List;

import com.xsc.lottery.admin.action.oracleData.base.DataSourceFactory;
import com.xsc.lottery.admin.action.oracleData.base.Page;
import com.xsc.lottery.admin.action.oracleData.dao.UserDao;
import com.xsc.lottery.admin.action.oracleData.dao.imp.UserDaoImpl;
import com.xsc.lottery.admin.action.oracleData.dao.model.UserDetailOrcl;

public class UserService
{
    UserDao userDao = new UserDaoImpl(DataSourceFactory.getDataSource());

    public Page<UserDetailOrcl> getUserDetail(Page<UserDetailOrcl> page)
    {
        return userDao.getUserDetail(page);
    }

    public UserDetailOrcl getUserOrcl(int uid)
    {
        return userDao.getUserOrcl(uid);
    }

    public void saveUserOrcl(UserDetailOrcl user)
    {
        userDao.saveUserOrcl(user);
    }

    public List<UserDetailOrcl> getUserOrclList()
    {
        return userDao.getUserOrclList();
    }

}
