package com.xsc.lottery.javatest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xsc.lottery.entity.business.LotteryTerm;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.LotteryType;
import com.xsc.lottery.handle.LotteryHandleFactory;
import com.xsc.lottery.javatest.tools.Launcher;
import com.xsc.lottery.service.admin.AdminUserService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.LotteryOrderService;
import com.xsc.lottery.service.business.LotteryPlanService;
import com.xsc.lottery.service.business.LotteryTermService;

@SuppressWarnings( { "unchecked", "unused" })
public class TermTaskTest
{
    public static Map lotterMap = new HashMap();
    
    static {
        lotterMap.put("排列三", "33");
        lotterMap.put("排列五", "35");
        lotterMap.put("大乐透", "23529");
        lotterMap.put("福彩3d", "52");
        lotterMap.put("双色球", "51");
        lotterMap.put("七星彩", "10022");
        lotterMap.put("七乐彩", "23528");
        lotterMap.put("足彩胜负彩", "11");
        lotterMap.put("足彩任九场", "19");
        lotterMap.put("足彩进球彩", "18");
        lotterMap.put("足彩半全场", "16");
        lotterMap.put("北京单场足彩", "41");
        lotterMap.put("竞彩足彩", "42");
    }

    public static void run()
    {
        // LotteryTerm nextTerm = new LotteryTerm();
        // nextTerm.setCurrent(true);
        // nextTerm.setTermNo("201001");
        // nextTerm.setType(LotteryType.大乐透);
        //		
        // Calendar now = DateUtil.parse("2010-08-20 16:10:00",
        // "yyyy-MM-dd HH:mm:ss");
        // //Calendar now = DateUtil.now();
        // Calendar StopTogether = DateUtil.now();
        // Calendar StopSale = DateUtil.now();
        // Calendar OpenPrize = DateUtil.now();
        // Calendar SendPrize = DateUtil.now();
        //		
        // nextTerm.setStartSaleTime(now);
        //
        // StopTogether.setTime(now.getTime());
        // StopTogether.add(Calendar.MINUTE, 1);
        // nextTerm.setStopTogetherSaleTime(StopTogether);
        //		
        // StopSale.setTime(StopTogether.getTime());
        // StopSale.add(Calendar.MINUTE, 1);
        // nextTerm.setStopSaleTime(StopSale);
        //		
        // OpenPrize.setTime(StopSale.getTime());
        // OpenPrize.add(Calendar.MINUTE, 1);
        // nextTerm.setOpenPrizeTime(OpenPrize);
        //		
        // SendPrize.setTime(OpenPrize.getTime());
        // SendPrize.add(Calendar.MINUTE, 1);
        // nextTerm.setSendPrizeTime(SendPrize);
        // nextTerm.setTicketPlat(SendTicketPlat.博众);

        // nex
        LotteryTermService termservice = (LotteryTermService) Launcher
                .getBean("lotteryTermService");
        LotteryTerm lotteryTerm = termservice.getByTypeAndTermNo("10109",
                LotteryType.valueOf("大乐透"));
        System.out.println(lotteryTerm.getId() + lotteryTerm.getTermNo());
        // termservice.stopSale(nextTerm);
        // LotteryTermTaskFactory factory = (LotteryTermTaskFactory)
        // Launcher.getBean("lotteryTermTaskFactory");
        // factory.startAllLottery();
        // System.out.println(factory.getDelayedQueueTaskerMap().get(LotteryType.jx_11x5.ordinal()).getTaskQuantity());
    }

    public static void test()
    {
        AdminUserService aservice = (AdminUserService) Launcher
                .getBean("adminUserService");
        CustomerService cusService = (CustomerService) Launcher
                .getBean("customerService");
        LotteryOrderService lotteryOrderService = (LotteryOrderService) Launcher
                .getBean("lotteryOrderService");
        LotteryPlanService planService = (LotteryPlanService) Launcher
                .getBean("lotteryPlanService");
        LotteryTermService lotteryTermService = (LotteryTermService) Launcher
                .getBean("lotteryTermService");
        LotteryHandleFactory factory = (LotteryHandleFactory) Launcher
                .getBean("lotteryHandleFactory");

        List<WinDescribeTicket> list = new ArrayList<WinDescribeTicket>();
        // Ticket ticket=lotteryOrderService.findByIdTicket(3l);
        LotteryTerm term = lotteryTermService.findById(5l);
        // List<PrizeLevel>
        // prizeLevels=lotteryTermService.getTermByPrizeLevel(term);
        // list=factory.getLotteryHandleFactory(LotteryType.大乐透).checkAllOrderWin(ticket,
        // prizeLevels);
        lotteryTermService.openPrize(term);
        System.out.println();
        // Order order=new Order();
        //		
        // order.setAmount(BigDecimal.valueOf(100l));
        // order.setBuildTime(Calendar.getInstance());
        // order.setCustomer(cusService.findById(1l));
        // order.setFailCause("失败原因");
        // order.setMultiple(1);
        // order.setOrderResult(OrderResult.未兑奖);
        // order.setOutAmount(BigDecimal.valueOf(100l));
        // order.setPayStatus(PayStatus.已付款);
        // order.setPlan(planService.findById(1l));
        // order.setPloyNumber(0);
        // order.setPloyType(PloyType.常规);
        // order.setStatus(OrderStatus.出票成功);
        // order.setTerm(lotteryTermService.findById(19l));
        // order.setTicketThirdName("第三方票务接口名字");
        // order.setType(LotteryType.大乐透);
        // order.setWinMoney(BigDecimal.valueOf(100l));
        // order.setWinTaxMoney(BigDecimal.valueOf(1000l));
        // lotteryOrderService.save(order);
        // Customer cu=new Customer();
        // cu.setAllWinMoney(BigDecimal.ONE);
        // cu.setCredentNo("123");
        // cu.setCredentType(CredentType.身份证);
        // cu.setEmail("aaaa");
        // cu.setLastLoginTime(Calendar.getInstance());
        // cu.setMobileNo("12312312");
        // cu.setNickName("中奖");
        // cu.setPassword("aaa");
        // cu.setPloyAccur(100l);
        // cu.setPloyConsumed(1000l);
        // cu.setPloymMobileCode("11111111");
        // cu.setRealName("中");
        // cu.setRegisterTime(Calendar.getInstance());
        // cu.setWallet(cusService.getWallet(1l));
        // Customer c=cusService.save(cu);
        // System.out.println(c.getId());

        // aservice.delFunctionList(7l);
        // List<AdminRoleFunction> a=aservice.getFunctionList(5l);
        // AdminUser au=aservice.getAdminUser("admin");
        // System.out.println(aservice.getFunctionList(3l, 0l));
        // System.out.println(au.getAdminName());
        // String str=aservice.getPermissionsList(3l, 13l);
        // System.out.println(str);
    }

    public static void main(String[] args)
    {
        // run();
        // System.out.println();
        test();
        // getnextterm();
        /*
         * ScheduledExecutorService newses =
         * Executors.newSingleThreadScheduledExecutor();
         * Set<ScheduledExecutorService> SingleThreadExecutorSet = new
         * HashSet<ScheduledExecutorService>();
         * SingleThreadExecutorSet.add(newses);
         */
        /*
         * System.out.println(NANOSECONDS.convert(DateUtil.now().getTimeInMillis(
         * ), MILLISECONDS)); System.out.println(System.nanoTime());
         * System.out.println(DateUtil.now().getTimeInMillis());
         */
        /*
         * Calendar now = DateUtil.now(); Calendar now1 = DateUtil.now();
         * now1.add(Calendar.MINUTE, 1);
         */
        /*
         * Calendar now1 = DateUtil.now(); Calendar now = DateUtil.now();
         * now1.add(Calendar.MINUTE, -1);
         * System.out.println(TimeUnit.SECONDS.convert
         * (now1.getTimeInMillis()-now
         * .getTimeInMillis(),TimeUnit.MILLISECONDS));
         */
        // System.exit(0);
    }
}
