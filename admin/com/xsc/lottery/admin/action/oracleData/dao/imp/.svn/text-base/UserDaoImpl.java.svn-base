package com.xsc.lottery.admin.action.oracleData.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xsc.lottery.admin.action.oracleData.base.Page;
import com.xsc.lottery.admin.action.oracleData.dao.UserDao;
import com.xsc.lottery.admin.action.oracleData.dao.model.UserDetailOrcl;

public class UserDaoImpl implements UserDao
{
    private DataSource dataSource;

    public UserDaoImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    public Page<UserDetailOrcl> getUserDetail(Page<UserDetailOrcl> page)
    {
        QueryRunner run = new QueryRunner(dataSource);
        String sql1 = "select * from (select tt.*,rownum rn from (select u.id,b.user_id,u.USR_NICK as nameNick,u.realname as nameReal,u.USR_EMAIL as emil,u.REG_TIME as regDate,u.last_login_time as lastDate,u.MOBILE as phone,u.iactive as statu,b.bankname as nameBank,b.bank_name as bankName,b.ub_name as bnameBank,b.ub_idnumber as card,b.ub_address as address,b.bank_number as bankNumber,b.ub_city as city,t.user_account as blacne,t.user_coupon as coupon,t.user_cash_coupon as cash_coupon,v.price as zj,u.usr_type as usr_type from LOTTERY.WEB_USERS u left join (select bt.*,bf.bank_name as bankname from USER_BANK bt left join bankinfo bf on bt.bank_id=bf.bank_id) b on u.id=b.user_id inner join user_trade t on u.id=t.user_id left join (select parti_nick,sum(taxbonus) as price from view_prize_1to4 t where prizestatus=3 group by parti_nick) v on u.usr_nick=v.parti_nick where (u.iactive=1 or (u.iactive=0 and t.user_account>0)) and u.istatus=1 order by v.price desc,u.iactive desc) tt where  rownum<=?) where rn>=?";
        String sql2 = "select count(*) from LOTTERY.WEB_USERS u left join (select bt.*,bf.bank_name as bankname from USER_BANK bt left join bankinfo bf on bt.bank_id=bf.bank_id) b on u.id=b.user_id inner join user_trade t on u.id=t.user_id left join (select parti_nick,sum(taxbonus) as price from view_prize_1to4 t where prizestatus=3 group by parti_nick) v on u.usr_nick=v.parti_nick where (u.iactive=1 or (u.iactive=0 and t.user_account>0)) and u.istatus=1 order by v.price desc,u.iactive desc";
        try {
            ResultSetHandler h = new BeanListHandler(UserDetailOrcl.class);
            Object[] params1 = new Object[] { page.getPageEndRow(),
                    page.getPageStartRow() };
            page.setResult((List<UserDetailOrcl>) run.query(sql1, params1, h));
            h = new ScalarHandler();
            Object[] params2 = new Object[] {};
            page.setTotalCount(Integer.parseInt(run.query(sql2, params2, h)
                    .toString()));

        }
        catch (SQLException e) {

        }
        return page;
    }

    @SuppressWarnings("unchecked")
    public UserDetailOrcl getUserOrcl(int uid)
    {
        List<UserDetailOrcl> list = new ArrayList<UserDetailOrcl>();
        QueryRunner run = new QueryRunner(dataSource);
        String sql1 = "select u.id,b.user_id,u.USR_NICK as nameNick,u.login_pwd as password,u.realname as realname,u.USR_EMAIL as emil,u.REG_TIME as regDate,u.last_login_time as lastDate,u.MOBILE as phone,u.iactive as statu,b.bankname as nameBank,b.bank_name as bankName,b.ub_name as bnameBank,b.ub_idnumber as card,b.ub_address as address,b.bank_number as bankNumber,b.ub_city as city,t.user_account as blacne,t.user_coupon as coupon,t.user_cash_coupon as cash_coupon,v.price as zj,u.usr_type as usr_type from LOTTERY.WEB_USERS u left join (select bt.*,bf.bank_name as bankname from USER_BANK bt left join bankinfo bf on bt.bank_id=bf.bank_id) b on u.id=b.user_id inner join user_trade t on u.id=t.user_id left join (select parti_nick,sum(taxbonus) as price from view_prize_1to4 t where prizestatus=3 group by parti_nick) v on u.usr_nick=v.parti_nick where u.id=? order by v.price desc,u.iactive desc";
        try {
            ResultSetHandler h = new BeanListHandler(UserDetailOrcl.class);
            Object[] params1 = new Object[] { uid };
            list = (List<UserDetailOrcl>) run.query(sql1, params1, h);
        }
        catch (SQLException e) {

        }
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return new UserDetailOrcl();
    }

    public void saveUserOrcl(UserDetailOrcl user)
    {
    }

    @SuppressWarnings("unchecked")
    public List<UserDetailOrcl> getUserOrclList()
    {
        List<UserDetailOrcl> list = new ArrayList<UserDetailOrcl>();
        QueryRunner run = new QueryRunner(dataSource);
        String sql1 = "select u.id,b.user_id,u.USR_NICK as nameNick,u.login_pwd as password,u.realname as nameReal,u.USR_EMAIL as emil,u.REG_TIME as regDate,u.last_login_time as lastDate,u.MOBILE as phone,u.iactive as statu,b.bankname as nameBank,b.bank_name as bankName,b.ub_name as bnameBank,b.ub_idnumber as card,b.ub_address as address,b.bank_number as bankNumber,b.ub_city as city,t.user_account as blacne,t.user_coupon as coupon,t.user_cash_coupon as cash_coupon,v.price as zj,u.usr_type as usr_type from LOTTERY.WEB_USERS u left join (select bt.*,bf.bank_name as bankname from USER_BANK bt left join bankinfo bf on bt.bank_id=bf.bank_id) b on u.id=b.user_id inner join user_trade t on u.id=t.user_id left join (select parti_nick,sum(taxbonus) as price from view_prize_1to4 t where prizestatus=3 group by parti_nick) v on u.usr_nick=v.parti_nick where (u.iactive=1 or (u.iactive=0 and t.user_account>0)) and u.istatus=1 order by v.price desc,u.iactive desc";
        try {
            ResultSetHandler h = new BeanListHandler(UserDetailOrcl.class);
            list = (List<UserDetailOrcl>) run.query(sql1, h);
        }
        catch (SQLException e) {

        }
        return list;
    }
}
