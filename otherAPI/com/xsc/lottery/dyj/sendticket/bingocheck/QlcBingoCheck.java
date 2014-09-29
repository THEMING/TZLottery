package com.xsc.lottery.dyj.sendticket.bingocheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.handle.BingoCheck;
import com.xsc.lottery.util.MathUtil;

public class QlcBingoCheck implements BingoCheck
{
    public Ticket ticket;
    public StringBuffer bingoContent;
    private HashMap<String, BigDecimal> bingoHashMap;
    private List<WinDescribeTicket> winDescribeTicket;
    private boolean isBingo;

    private int[] zhudan = { 0, 0, 0, 0, 0, 0 ,0};//七个奖项

    public void addCheck(BingoCheck otherCheck)
    {
        if (!otherCheck.isBingo()) {
            return;
        }
    }

    public List<WinDescribeTicket> execute(Ticket ticket,
            Map<String, PrizeLevel> bingoMap, String result)
    {
        String resultContent = result;
        bingoContent = new StringBuffer();
        isBingo = false;
        this.ticket = ticket;
        String[] sels = ticket.getContent().split("\\^"); // 将投注号码区分解成每注
        try {
            int matchCount = 0;
            for (int i = 0; i < sels.length; i++) {
                String sel = sels[i];
                if (ticket.getPlayType() == PlayType.ds) {// 单式或追加
                    String[] contents = resultContent.split("\\|"); // 将中奖号码分解(０:前区号码　１:后区号码)
                    String preNumber = contents[0]; // 前区中奖内容
                    String posNumber = contents[1];// 后区中奖内容

                    String[] totalNums = sel.split("\\,");


                    int preBingo = this.checkPre(totalNums, preNumber);// 前区命中号码个数

                    int posBingo = this.checkPos(totalNums, posNumber);// 后区命中号码个数
                    if (preBingo + posBingo > matchCount) {
                        matchCount = preBingo + posBingo;
                    }

                    check1(preBingo, posBingo);

                }
                else if (ticket.getPlayType() == PlayType.dt
                        || ticket.getPlayType() == PlayType.fs) {// 胆拖和复式或追加
                   // String[] contents = sel.split("-"); // 将每注的投注号码分解(０:前区号码　１:后区号码)
                    String preNumber = sel; // 前区投注内容    七乐彩没有前区和后区的分别
                    String posNumber = sel;// 后区投注内容
                    matchCount = check2(preNumber, posNumber, resultContent);
                }
            }
        }
        catch (Exception e) {
            // log.debug("Error:"+e.getMessage());
        }

        // 开始计算奖金
        BigDecimal firstPrize = bingoMap.get("一等奖").getPrize();
        BigDecimal secPrize = bingoMap.get("二等奖").getPrize();
        BigDecimal thirdPrize = bingoMap.get("三等奖").getPrize();
        // 一等奖税后奖金
        BigDecimal firstPrize_ = firstPrize.compareTo(new BigDecimal(10000)) > 0 ? firstPrize
                .multiply(new BigDecimal(0.8))
                : firstPrize;
        // 二等奖税后奖金
        BigDecimal secPrize_ = secPrize.compareTo(new BigDecimal(10000)) > 0 ? secPrize
                .multiply(new BigDecimal(0.8))
                : secPrize;
        BigDecimal thirdPrize_ = secPrize.compareTo(new BigDecimal(10000)) > 0 ? secPrize
                        .multiply(new BigDecimal(0.8))
                        : thirdPrize;      
        BigDecimal fourthPrize = bingoMap.get("四等奖").getPrize();
        BigDecimal fifthPrize = bingoMap.get("五等奖").getPrize();
        BigDecimal sixthPrize = bingoMap.get("六等奖").getPrize();
        BigDecimal seventhPrize = bingoMap.get("七等奖").getPrize();
        winDescribeTicket = new ArrayList<WinDescribeTicket>();
        if (zhudan[0] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("一等奖"));
            wdt1.setMoney(firstPrize.multiply(new BigDecimal(zhudan[0])));
            wdt1.setTaxmoney(firstPrize_.multiply(new BigDecimal(zhudan[0])));
            wdt1.setNumber(zhudan[0]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[1] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("二等奖"));
            wdt1.setMoney(secPrize.multiply(new BigDecimal(zhudan[1])));
            wdt1.setTaxmoney(secPrize_.multiply(new BigDecimal(zhudan[1])));
            wdt1.setNumber(zhudan[1]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[2] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("三等奖"));
            wdt1.setMoney(thirdPrize.multiply(new BigDecimal(zhudan[2])));
            wdt1.setTaxmoney(thirdPrize_.multiply(new BigDecimal(zhudan[2])));
            wdt1.setNumber(zhudan[2]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[3] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("四等奖"));
            wdt1.setMoney(fourthPrize.multiply(new BigDecimal(zhudan[3])));
            wdt1.setTaxmoney(fourthPrize.multiply(new BigDecimal(zhudan[3])));
            wdt1.setNumber(zhudan[3]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[4] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("五等奖"));
            wdt1.setMoney(fifthPrize.multiply(new BigDecimal(zhudan[4])));
            wdt1.setTaxmoney(fifthPrize.multiply(new BigDecimal(zhudan[4])));
            wdt1.setNumber(zhudan[4]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[5] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("六等奖"));
            wdt1.setMoney(sixthPrize.multiply(new BigDecimal(zhudan[5])));
            wdt1.setTaxmoney(sixthPrize.multiply(new BigDecimal(zhudan[5])));
            wdt1.setNumber(zhudan[5]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[6] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("七等奖"));
            wdt1.setMoney(seventhPrize.multiply(new BigDecimal(zhudan[6])));
            wdt1.setTaxmoney(seventhPrize.multiply(new BigDecimal(zhudan[6])));
            wdt1.setNumber(zhudan[6]);
            winDescribeTicket.add(wdt1);
        }

        return winDescribeTicket;
    }

    private void check1(int preBingo, int posBingo)
    {
        int count = 0;
        if (preBingo ==7) {// 一等奖:选中7个基本号码
            count = zhudan[0];
            count = count + ticket.getMultiple();
            zhudan[0] = count;
        }

        else if (preBingo == 6 && posBingo == 1) {// 二等奖:选中6个基本号码及1个特别号码
            count = zhudan[1];
            count = count + ticket.getMultiple();
            zhudan[1] = count;

        }

        else if (preBingo == 6) {// 三等奖:选中6个基本号码
            count = zhudan[2];
            count = count + ticket.getMultiple();
            zhudan[2] = count;

        }

        else if (preBingo == 5 && posBingo == 1) {// 四等奖:选中5个基本号码及1个特别号码
            count = zhudan[3];
            count = count + ticket.getMultiple();
            zhudan[3] = count;

        }

        else if (preBingo == 5 ) {// 五等奖:选中5个基本号码
            count = zhudan[4];
            count = count + ticket.getMultiple();
            zhudan[4] = count;

        }

        else if (preBingo == 4 && posBingo == 1) {// 六等奖:选中4个基本号码及1个特别
            count = zhudan[5];
            count = count + ticket.getMultiple();
            zhudan[5] = count;

        }

        else if (preBingo == 4) {// 七等奖:选中4个基本号码
            count = zhudan[5];
            count = count + ticket.getMultiple();
            zhudan[6] = count;

        }

        if (count != 0) {
            this.isBingo = true;
        }

    }

    @SuppressWarnings("unused")
    private int check2(String preNumber, String posNumber, String resultContent)
    {
        // 前区所需参数
        int k1;// 胆区选中k1个球
        int m1;// 拖区选中m1个球
        int y1;// 开奖中总球数y1为5个
        int j1;// k1个球中有j1个为开出号码
        int n1;// m1个球中有n1个为开出号码
        int total;//总的投注号码数量
        String[] dantuo = preNumber.split("\\$");
        if (dantuo.length == 1) {
            k1 = 0;
            m1 = dantuo[0].split(",").length;
            y1 = 7;
            j1 = 0;
            n1 = checkPre(dantuo[0].split("\\W"), resultContent.split("\\|")[0]);
            total=dantuo[0].split("\\W").length;
            
        }
        else {
            if (dantuo[0].equals("") || dantuo[0].equals(null)) {
                k1 = 0;
            }
            else {
                k1 = dantuo[0].split(",").length;// 胆码
            }
            m1 = dantuo[1].split(",").length;// 拖码
            y1 = 7;
            j1 = checkPre(dantuo[0].split(","), resultContent.split("\\|")[0]);
            n1 = checkPre(dantuo[1].split(","), resultContent.split("\\|")[0]);
            total=dantuo[1].split(",").length;
        }

        // 后区所需参数
        int m2;// 拖区选中m2个球
        int y2;// 开奖中总球数y2为1个
        int n2;// m2个球中有n2个为开出号码

        m2 = posNumber.split(",").length;
        y2 = 1;
        n2 = checkPre(posNumber.split("\\W"), resultContent.split("\\|")[1]);

        // 求注数公式:C(x-j) n * C(y-k)-(x-j) m-n
        // 求组合公式:MathUtil.getCombinationCount(n,r);n选r的组合
        // ---------------一等奖-------------------------
        int x1 = 7;
        int x2 = 0;
        // 前区中奖注数
        int preWinCount = MathUtil.getCombinationCount(n1, x1)*MathUtil.getCombinationCount(total-n1-n2, 7-x1-x2);
        // 后区中奖注数
        int posWinCount = MathUtil.getCombinationCount(n2, x2);
        // 中奖注数
        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[0] = preWinCount * posWinCount * ticket.getMultiple();

        // ----------------------------------------------

        // ---------------二等奖-------------------------
        x1 = 6;
        x2 = 1;
        // 前区中奖注数
         preWinCount = MathUtil.getCombinationCount(n1, x1)*MathUtil.getCombinationCount(total-n1-n2, 7-x1-x2);
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, x2);
        // 中奖注数
        // log.debug("2secCount:"+(preWinCount * posWinCount *
        // ticket.getMultiple()));
        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[1] = preWinCount * posWinCount * ticket.getMultiple();

        // ----------------------------------------------

        // ---------------三等奖-------------------------
        x1 = 6;
        x2 = 0;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, x1)*MathUtil.getCombinationCount(total-n1-n2, 7-x1-x2);
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, x2);
        // 中奖注数
        // log.debug("2ThirdCount:"+(preWinCount * posWinCount *
        // ticket.getMultiple()));
        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[2] = preWinCount * posWinCount * ticket.getMultiple();
        // ----------------------------------------------

        // ---------------四等奖-------------------------
        x1 = 5;
        x2 = 1;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, x1)*MathUtil.getCombinationCount(total-n1-n2, 7-x1-x2);
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, x2);
        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[3] = preWinCount * posWinCount * ticket.getMultiple();

        // ----------------------------------------------

        // ---------------五等奖-------------------------
        x1 = 5;
        x2 = 0;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, x1)*MathUtil.getCombinationCount(total-n1-n2, 7-x1-x2);
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, x2);

        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[4] = preWinCount * posWinCount * ticket.getMultiple();

        // ----------------------------------------------

        // ---------------六等奖-------------------------
        x1 = 4;
        x2 = 1;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, x1)*MathUtil.getCombinationCount(total-n1-n2, 7-x1-x2);
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, x2);

        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[5] = preWinCount * posWinCount * ticket.getMultiple();
        // ----------------------------------------------

        // ---------------七等奖-------------------------
        x1 = 4;
        x2 = 0;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, x1)*MathUtil.getCombinationCount(total-n1-n2, 7-x1-x2);
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, x2);

        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[6] = preWinCount * posWinCount * ticket.getMultiple();

        return j1 + n1;
    }


    /**
     *判断前区的中奖个数
     */
    private int checkPre(String[] preNums, String resultContent)
    {
        int preBingo = 0;// 前区命中号码个数

        for (int j = 0; j < preNums.length; j++) {
            if (resultContent.indexOf(preNums[j]) >= 0) {
                preBingo++;
            }
        }

        return preBingo;
    }

    /**
     * @描 述:判断后区的中奖个数
     */
    private int checkPos(String[] posNums, String resultContent)
    {
        // String[] strAwardCode = resultContent.split("\\,");//开奖号码
        int posBingo = 0;// 后区命中号码个数

        for (int j = 0; j < posNums.length; j++) {
            if (resultContent.indexOf(posNums[j]) >= 0) {
                posBingo++;
            }
        }
        return posBingo;
    }

    public String getBingoContent()
    {
        return this.bingoContent.toString();
    }

    @SuppressWarnings("unchecked")
    public HashMap getBingoHashMap()
    {
        return this.bingoHashMap;
    }

    public BigDecimal getBingoPosttaxTotal()
    {
        return null;
    }

    //
    public BigDecimal getBingoPretaxTotal()
    {
        return null;
    }

    public boolean isBingo()
    {
        return this.isBingo;
    }

    // 复制对象
    public BingoCheck clone() throws CloneNotSupportedException
    {
        return (BingoCheck) super.clone();
    }

    static public void main(String[] args)
    {
    	QlcBingoCheck check = new QlcBingoCheck();
        Ticket ticket = new Ticket();
        ticket.setOneMoney(new BigDecimal(2));
        ticket.setMultiple(1);
        // ticket.setAddAttribute(AddAttribute.ZJ);
        ticket.setContent("12 18 16 27 22 21 04");
        //ticket.setContent("04,12,16,18,21,22,27");
        //ticket.setContent("01,02,03,04,05,06,07");
        // ticket.setMultiple(new Long(1));
      //  ticket.setAmount(new Double(3));
         ticket.setPlayType(PlayType.fs);
       
        HashMap<String, PrizeLevel> openResultMap = new HashMap<String,
        PrizeLevel>();
        PrizeLevel leve1=new PrizeLevel();
        leve1.setLevel(1);
        leve1.setBetNum(2);
        leve1.setName("一等奖");
        leve1.setPrize(new BigDecimal(100));
        openResultMap.put("一等奖", leve1);
        PrizeLevel leve2=new PrizeLevel();
        leve2.setLevel(2);
        leve2.setBetNum(2);
        leve2.setName("二等奖");
        leve2.setPrize(new BigDecimal(200));
        openResultMap.put("二等奖", leve2);
        PrizeLevel leve3=new PrizeLevel();
        leve3.setLevel(3);
        leve3.setBetNum(3);
        leve3.setName("三等奖");
        leve3.setPrize(new BigDecimal(300));
        openResultMap.put("三等奖", leve3);
        PrizeLevel leve4=new PrizeLevel();
        leve4.setLevel(4);
        leve4.setBetNum(4);
        leve4.setName("四等奖");
        leve4.setPrize(new BigDecimal(200));
        openResultMap.put("四等奖", leve4);
        PrizeLevel leve5=new PrizeLevel();
        leve5.setLevel(5);
        leve5.setBetNum(5);
        leve5.setName("五等奖");
        leve5.setPrize(new BigDecimal(50));
        openResultMap.put("五等奖", leve5);
        PrizeLevel leve6=new PrizeLevel();
        leve6.setLevel(6);
        leve6.setBetNum(6);
        leve6.setName("六等奖");
        leve6.setPrize(new BigDecimal(10));
        openResultMap.put("六等奖", leve6);
        PrizeLevel leve7=new PrizeLevel();
        leve7.setLevel(7);
        leve7.setBetNum(7);
        leve7.setName("七等奖");
        leve7.setPrize(new BigDecimal(5));
        openResultMap.put("七等奖", leve7);
        
       // openResultMap.put("一等奖", "1");
      //  openResultMap.put("二等奖", "2");
//        openResultMap.put("prize3", "3");
//        openResultMap.put("prize4", "4");
//        openResultMap.put("prize5", "5");
//        openResultMap.put("prize6", "6");
//        openResultMap.put("prize7", "3000");
//        openResultMap.put("prize8", "8");
//        openResultMap.put("prize9", "9");
//        openResultMap.put("prize10", "10");
//        openResultMap.put("prize11", "11");
//        openResultMap.put("prize12", "12");
//        openResultMap.put("prize13", "13");
//        openResultMap.put("prize14", "14");
//        openResultMap.put("prize15", "15");
//        openResultMap.put("prize16", "16");
      //  openResultMap.put("result", "3,0,1,1,3,1,0,0,3,0,0,1,3,1");
       List<WinDescribeTicket> winDesc =check.execute(ticket, openResultMap, "01,04,06,12,16,22,23|05");
       //List<WinDescribeTicket> winDesc =check.execute(ticket, openResultMap, "01,04,06,02,16,22,23|05"); 
        
       for(int i=0;i<winDesc.size();i++)
       {
    	   System.out.println("====================");
        System.out.println(winDesc.get(i).getMoney());
        System.out.println(winDesc.get(i).getPrizeLevel().getName());
        System.out.println("中奖注数："+winDesc.get(i).getNumber());
        
       }
    }

    public boolean isOpenAble()
    {
        return false;
    }

    public String getBingoContentTicket()
    {
        return null;
    }
}
