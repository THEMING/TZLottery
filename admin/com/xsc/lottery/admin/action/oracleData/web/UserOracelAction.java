package com.xsc.lottery.admin.action.oracleData.web;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.admin.action.oracleData.base.Page;
import com.xsc.lottery.admin.action.oracleData.dao.model.UserDetailOrcl;
import com.xsc.lottery.admin.action.oracleData.service.UserService;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Wallet;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.CredentType;
import com.xsc.lottery.entity.enumerate.UserType;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.DateUtil;

@SuppressWarnings({ "serial", "unused" })
@Scope("prototype")
@Controller("Admin.userOracle")
public class UserOracelAction extends AdminBaseAction
{
    private UserService userService = new UserService();

    @Autowired
    private CustomerService customerService;

    private Page<UserDetailOrcl> page;

    private UserDetailOrcl user;

    private Integer[] sId;

    private int uid;

    private int pageNo = 1;

    private int pageSize = 15;

    public String index()
    {
        page = new Page<UserDetailOrcl>();
        page.setPageSize(pageSize);
        page.setPageNo(pageNo);
        page = userService.getUserDetail(page);
        return SUCCESS;
    }

    public String view()
    {
        user = userService.getUserOrcl(uid);
        return "view";
    }

    public String add()
    {
        if (sId != null && sId.length > 0) {
            for (int id : sId) {
                UserDetailOrcl user = new UserDetailOrcl();
                user = userService.getUserOrcl(id);
                Customer customer = customerService.getCustomerOrName(user
                        .getNameNick());

                if (customer == null
                        || StringUtils.isEmpty(customer.getNickName())) {
                    customer = new Customer();
                    customer.setNickName(user.getNameNick());// 用户名
                    customer.setPassword(user.getPassword());
                    if (!StringUtils.isEmpty(user.getNameReal()))
                        customer.setRealName(user.getNameReal());// 真实姓名
                    else {
                        customer.setRealName(" ");// 真实姓名
                    }
                    customer.setCredentType(CredentType.IDCard);// 证件类型：身份证
                    if (!StringUtils.isEmpty(user.getCard()))
                        customer.setCredentNo(user.getCard());// 身份证号
                    else {
                        customer.setCredentNo(" ");
                    }
                    if (user.getUsr_type() == 0)
                        customer.setEmail(user.getEmil());// 邮件
                    if (user.getUsr_type() == 1 || user.getUsr_type() == 2)
                        customer.setUser3_id(user.getEmil());// 第三方用户ID
                    customer.setRegisterTime(DateUtil.parseTimeStamp(user
                            .getRegDate().substring(0, 15)));// 注册时间
                    customer.setLastLoginTime(DateUtil.parseTimeStamp(user
                            .getLastDate().substring(0, 15)));// 最后登陆时间
                    customer.setMobileNo(user.getPhone());// 手机号
                    customer.setLoginNum(user.getLogin_times());// 登陆次数
                    
                    if (!StringUtils.isEmpty(user.getNameBank())) {
                        customer.setBank(Bank.valueOf(user.getNameBank()));// 银行名字
                    }
                    
                    customer.setSubbranch(user.getBankName());// 银行支行
                    customer.setBankNumber(user.getBankNumber());// 银行卡号
                    customer.setCity(user.getCity()); // 城市
                    customer.setProvince(user.getAddress());// 省份
                    customer.setAllWinMoney(new BigDecimal(user.getZj() / 100l));// 中奖金额
                    
                    if (user.getUsr_type() == 0) {
                        customer.setUsrType(UserType.本地注册用户);
                    }
                    else if (user.getUsr_type() == 1) {
                        customer.setUsrType(UserType.支付宝用户);
                    }
                    else if(user.getUsr_type() == 2) {
                        customer.setUsrType(UserType.财付通用户);
                    }

                    double money = user.getBlacne() + user.getCoupon()
                            + user.getCash_coupon();
                    Wallet wallet = new Wallet();
                    wallet.setCustomer(customer);
                    if (user.getStatu() == 0) {
                        wallet.setStatus(1);
                    }
                    else {
                        wallet.setStatus(0);
                    }
                    wallet.setBalance(new BigDecimal(money / 100l));// 余额
                    customer.setWallet(wallet);
                    customer = customerService.save(customer);
                    customerService.saveWalletSummary(customer.getWallet());
                }
            }
        }

        return index();
    }

    public String alladd()
    {
        List<UserDetailOrcl> ulist = userService.getUserOrclList();
        for (UserDetailOrcl user : ulist) {
            Customer customer = customerService.getCustomerOrName(user
                    .getNameNick());
            if (customer == null || StringUtils.isEmpty(customer.getNickName())) {
                customer = new Customer();
                if (!StringUtils.isEmpty(user.getNameNick())) {
                    customer.setNickName(user.getNameNick());// 用户名
                    if (!StringUtils.isEmpty(user.getPassword())) {
                        customer.setPassword(user.getPassword());
                    }
                    else {
                        customer.setPassword(" ");
                    }
                    if (!StringUtils.isEmpty(user.getNameReal()))
                        customer.setRealName(user.getNameReal());// 真实姓名
                    else {
                        customer.setRealName(" ");// 真实姓名
                    }
                    customer.setCredentType(CredentType.IDCard);// 证件类型：身份证
                    if (!StringUtils.isEmpty(user.getCard())) {
                        customer.setCredentNo(user.getCard());// 身份证号
                    }
                    else {
                        customer.setCredentNo(" ");
                    }
                    if (!StringUtils.isEmpty(user.getEmil())) {
                        customer.setOld(1);
                        customer.setEmail(user.getEmil());// 邮件
                    }
                    else {
                        customer.setEmail(" ");// 邮件
                    }
                    customer.setRegisterTime(DateUtil.parseTimeStamp(user
                            .getRegDate().substring(0, 15)));// 注册时间
                    if (user.getLastDate() != null) {
                        customer.setLastLoginTime(DateUtil.parseTimeStamp(user
                                .getLastDate().substring(0, 15)));// 最后登陆时间
                    }
                    customer.setMobileNo(user.getPhone());// 手机号
                    customer.setLoginNum(user.getLogin_times());// 登陆次数
                    if (!StringUtils.isEmpty(user.getNameBank())) {
                        customer.setBank(Bank.valueOf(user.getNameBank()));// 银行名字
                    }
                    customer.setSubbranch(user.getBankName());// 银行支行
                    customer.setBankNumber(user.getBankNumber());// 银行卡号
                    customer.setCity(user.getCity()); // 城市
                    customer.setProvince(user.getAddress());// 省份
                    System.out.println(user.getZj());
                    customer.setAllWinMoney(new BigDecimal(user.getZj() / 100l));// 中奖金额
                    
                    if (user.getUsr_type() == 0) {
                        customer.setUsrType(UserType.本地注册用户);
                    }
                    else if (user.getUsr_type() == 1) {
                        customer.setUsrType(UserType.支付宝用户);
                    }
                    else if (user.getUsr_type() == 2) {
                        customer.setUsrType(UserType.财付通用户);
                    }

                    double money = user.getBlacne() + user.getCoupon() + user.getCash_coupon();
                    Wallet wallet = new Wallet();
                    wallet.setCustomer(customer);
                    if (user.getStatu() == 0) {
                        wallet.setStatus(1);
                    }
                    else {
                        wallet.setStatus(0);
                    }
                    wallet.setBalance(new BigDecimal(money / 100l));// 余额
                    customer.setWallet(wallet);
                    customer = customerService.save(customer);
                    customerService.saveWalletSummary(customer.getWallet());
                }
            }
        }
        return index();
    }

    public Page<UserDetailOrcl> getPage()
    {
        return page;
    }

    public void setPage(Page<UserDetailOrcl> page)
    {
        this.page = page;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer[] getsId()
    {
        return sId;
    }

    public void setsId(Integer[] sId)
    {
        this.sId = sId;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public UserDetailOrcl getUser()
    {
        return user;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public void setUser(UserDetailOrcl user)
    {
        this.user = user;
    }

    public static void main(String[] args)
    {
        System.out.println(new BigDecimal(Double.parseDouble("9592") / 100l)
                .toString());
    }
}
