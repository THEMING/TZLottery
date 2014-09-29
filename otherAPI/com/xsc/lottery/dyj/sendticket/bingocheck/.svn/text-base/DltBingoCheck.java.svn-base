package com.xsc.lottery.dyj.sendticket.bingocheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xsc.lottery.entity.business.PrizeLevel;
import com.xsc.lottery.entity.business.Ticket;
import com.xsc.lottery.entity.business.WinDescribeTicket;
import com.xsc.lottery.entity.enumerate.PlayType;
import com.xsc.lottery.handle.BingoCheck;
import com.xsc.lottery.util.MathUtil;

public class DltBingoCheck implements BingoCheck
{
    public Ticket ticket;

    private List<WinDescribeTicket> winDescribeTicket;

    private boolean isBingo;

    //private int[] zhudan = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private int[] zhudan = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

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
        isBingo = false;
        this.ticket = ticket;
        String[] sels = ticket.getContent().split("\\^"); // 将投注号码区分解成每注
        try {
            int matchCount = 0;
            for (int i = 0; i < sels.length; i++) {
                String sel = sels[i];

                if (ticket.getPlayType() == PlayType.ds) {// 单式或追加
                    String[] contents = sel.split("\\|"); // 将每注的投注号码分解(０:前区号码　１:后区号码)
                    String preNumber = contents[0]; // 前区投注内容
                    String posNumber = contents[1];// 后区投注内容

                    String[] preNums = preNumber.split("\\,");
                    String[] posNums = posNumber.split("\\,");

                    int preBingo = this.checkPre(preNums, resultContent);// 前区命中号码个数

                    int posBingo = this.checkPos(posNums, resultContent);// 后区命中号码个数
                    if (preBingo + posBingo > matchCount) {
                        matchCount = preBingo + posBingo;
                    }

                    check1(preBingo, posBingo);

                }
                else if (ticket.getPlayType() == PlayType.dt
                        || ticket.getPlayType() == PlayType.fs) {// 胆拖和复式或追加
                    String[] contents = sel.split("-"); // 将每注的投注号码分解(０:前区号码　１:后区号码)
                    String preNumber = contents[0]; // 前区投注内容
                    String posNumber = contents[1];// 后区投注内容
                    matchCount = check2(preNumber, posNumber, resultContent);
                }
                /*
                else if (ticket.getPlayType() == PlayType.dlt_sxlds
                        || ticket.getPlayType() == PlayType.dlt_sxlfs) {// 幸运彩复式
                    matchCount = check4(sel, resultContent);
                }
                */
            }
        }
        catch (Exception e) {
            // log.debug("Error:"+e.getMessage());
        }

        // 开始计算奖金
        BigDecimal firstPrize = bingoMap.get("一等奖").getPrize();
        BigDecimal secPrize = bingoMap.get("二等奖").getPrize();
        BigDecimal thirdPrize = bingoMap.get("三等奖").getPrize();

        BigDecimal addfirstPrize = bingoMap.get("一等奖").getAddPrize();
        BigDecimal addsecPrize = bingoMap.get("二等奖").getAddPrize();
        BigDecimal addthirdPrize = bingoMap.get("三等奖").getAddPrize();

        // 一等奖税后奖金
        BigDecimal firstPrize_ = firstPrize.compareTo(new BigDecimal(10000)) > 0 ? firstPrize
                .multiply(new BigDecimal(0.8))
                : firstPrize;
        // 二等奖税后奖金
        BigDecimal secPrize_ = secPrize.compareTo(new BigDecimal(10000)) > 0 ? secPrize
                .multiply(new BigDecimal(0.8))
                : secPrize;
        // 三等奖税后奖金
        BigDecimal thirdPrize_ = thirdPrize.compareTo(new BigDecimal(10000)) > 0 ? thirdPrize
                .multiply(new BigDecimal(0.8))
                : thirdPrize;
        // 追加一等奖税后奖金
        BigDecimal addfirstPrize_ = addfirstPrize.compareTo(new BigDecimal(
                10000)) > 0 ? addfirstPrize.multiply(new BigDecimal(0.8))
                : addfirstPrize;
        // 追加二等奖税后奖金
        BigDecimal addsecPrize_ = addsecPrize.compareTo(new BigDecimal(10000)) > 0 ? addsecPrize
                .multiply(new BigDecimal(0.8))
                : addsecPrize;
        // 追加三等奖税后奖金
        BigDecimal addthirdPrize_ = addthirdPrize.compareTo(new BigDecimal(
                10000)) > 0 ? addthirdPrize.multiply(new BigDecimal(0.8))
                : addthirdPrize;

        BigDecimal fourthPrize = bingoMap.get("四等奖").getPrize();
        BigDecimal addfourthPrize = bingoMap.get("四等奖").getAddPrize();
        BigDecimal fifthPrize = bingoMap.get("五等奖").getPrize();
        BigDecimal addfifthPrize = bingoMap.get("五等奖").getAddPrize();
        BigDecimal sixthPrize = bingoMap.get("六等奖").getPrize();
       // BigDecimal addsixthPrize = bingoMap.get("六等奖").getAddPrize();
       // BigDecimal seventhPrize = bingoMap.get("七等奖").getPrize();
       // BigDecimal addseventhPrize = bingoMap.get("七等奖").getAddPrize();
       // BigDecimal eigthPrize = bingoMap.get("八等奖").getPrize();
       // BigDecimal luckyPrize = bingoMap.get("12选2").getPrize();
        winDescribeTicket = new ArrayList<WinDescribeTicket>();
        if (zhudan[1] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("一等奖"));
            wdt1.setMoney(firstPrize.multiply(new BigDecimal(zhudan[1])).add(
                    addfirstPrize.multiply(new BigDecimal(zhudan[2]))));
            wdt1.setTaxmoney(firstPrize_.multiply(new BigDecimal(zhudan[1]))
                    .add(addfirstPrize_.multiply(new BigDecimal(zhudan[2]))));
            wdt1.setNumber(zhudan[1]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[3] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("二等奖"));
            wdt1.setMoney(secPrize.multiply(new BigDecimal(zhudan[3])).add(
                    addsecPrize.multiply(new BigDecimal(zhudan[4]))));
            wdt1.setTaxmoney(secPrize_.multiply(new BigDecimal(zhudan[3])).add(
                    addsecPrize_.multiply(new BigDecimal(zhudan[4]))));
            wdt1.setNumber(zhudan[3]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[5] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("三等奖"));
            wdt1.setMoney(thirdPrize.multiply(new BigDecimal(zhudan[5])).add(
                    addthirdPrize.multiply(new BigDecimal(zhudan[6]))));
            wdt1.setTaxmoney(thirdPrize_.multiply(new BigDecimal(zhudan[5]))
                    .add(addthirdPrize_.multiply(new BigDecimal(zhudan[6]))));
            wdt1.setNumber(zhudan[5]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[7] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("四等奖"));
            wdt1.setMoney(fourthPrize.multiply(new BigDecimal(zhudan[7])).add(
                    addfourthPrize.multiply(new BigDecimal(zhudan[8]))));
            wdt1.setTaxmoney(fourthPrize.multiply(new BigDecimal(zhudan[7]))
                    .add(addfourthPrize.multiply(new BigDecimal(zhudan[8]))));
            wdt1.setNumber(zhudan[7]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[9] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("五等奖"));
            wdt1.setMoney(fifthPrize.multiply(new BigDecimal(zhudan[9])).add(
                    addfifthPrize.multiply(new BigDecimal(zhudan[10]))));
            wdt1.setTaxmoney(fifthPrize.multiply(new BigDecimal(zhudan[9]))
                    .add(addfifthPrize.multiply(new BigDecimal(zhudan[10]))));
            wdt1.setNumber(zhudan[9]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[11] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("六等奖"));
            wdt1.setMoney(sixthPrize.multiply(new BigDecimal(zhudan[11])));
            wdt1.setTaxmoney(sixthPrize.multiply(new BigDecimal(zhudan[11])));
            wdt1.setNumber(zhudan[11]);
            winDescribeTicket.add(wdt1);
        }
        /*
        if (zhudan[13] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("七等奖"));
            wdt1.setMoney(seventhPrize.multiply(new BigDecimal(zhudan[13]))
                    .add(addseventhPrize.multiply(new BigDecimal(zhudan[14]))));
            wdt1.setTaxmoney(seventhPrize.multiply(new BigDecimal(zhudan[13]))
                    .add(addseventhPrize.multiply(new BigDecimal(zhudan[14]))));
            wdt1.setNumber(zhudan[13]);
            winDescribeTicket.add(wdt1);
        }
        if (zhudan[15] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("八等奖"));
            wdt1.setMoney(eigthPrize.multiply(new BigDecimal(zhudan[15])));
            wdt1.setTaxmoney(eigthPrize.multiply(new BigDecimal(zhudan[15])));
            wdt1.setNumber(zhudan[15]);
            winDescribeTicket.add(wdt1);
        }
        
        if (zhudan[16] > 0) {
            WinDescribeTicket wdt1 = new WinDescribeTicket();
            wdt1.setPrizeLevel(bingoMap.get("12选2"));
            wdt1.setMoney(luckyPrize.multiply(new BigDecimal(zhudan[16])));
            wdt1.setTaxmoney(luckyPrize.multiply(new BigDecimal(zhudan[16])));
            wdt1.setNumber(zhudan[16]);
            winDescribeTicket.add(wdt1);
        }
        */

        return winDescribeTicket;
    }

    private void check1(int preBingo, int posBingo)
    {
        int count = 0;
        if (preBingo == 5 && posBingo == 2) {// 一等奖:选中5个前区号码及2个后区号码
            count = zhudan[1];
            count = count + ticket.getMultiple();
            zhudan[1] = count;
            if (ticket.getOneMoney().intValue() == 3) {
                zhudan[2] = count;
            }
        }

        else if (preBingo == 5 && posBingo == 1) {// 二等奖:选中5个前区号码及2个后区号码中的任意1个
            count = zhudan[3];
            count = count + ticket.getMultiple();
            zhudan[3] = count;
            if (ticket.getOneMoney().intValue() == 3) {
                zhudan[4] = count;
            }
        }

        else if (preBingo == 5 || (preBingo == 4 && posBingo == 2)) {// 三等奖:选中5个前区号码  ps:新规 4+2 彭方良
            count = zhudan[5];
            count = count + ticket.getMultiple();
            zhudan[5] = count;
            if (ticket.getOneMoney().intValue() == 3) {
                zhudan[6] = count;
            }
        }

        else if ((preBingo == 4 && posBingo == 1) || (preBingo == 3 && posBingo == 2)) {// 四等奖:选中4个前区号码及2个后区号码  ps:新规 4+1 3+2
            count = zhudan[7];
            count = count + ticket.getMultiple();
            zhudan[7] = count;
            if (ticket.getOneMoney().intValue() == 3) {
                zhudan[8] = count;
            }
        }

        else if ((preBingo == 4 && posBingo == 0)|| (preBingo == 3 && posBingo == 1) || (preBingo == 2 && posBingo == 2)) {// 五等奖:选中4个前区号码及2个后区号码中的任意1个  ps 4+0 3+1 2+2
            count = zhudan[9];
            count = count + ticket.getMultiple();
            zhudan[9] = count;
            if (ticket.getOneMoney().intValue() == 3) {
                zhudan[10] = count;
            }
        }

        else if ((preBingo == 3 && posBingo == 0) || (preBingo == 2 && posBingo == 1) || (preBingo == 1 && posBingo == 2) || (preBingo == 0 && posBingo == 2)) {// 六等奖:选中3个前区号码及2个后区号码或选中4个前区号码  ps:3+0 2+1 1+2 0+2
            count = zhudan[11];
            count = count + ticket.getMultiple();
            zhudan[11] = count;
            /*
            if (ticket.getOneMoney().intValue() == 3) {
                zhudan[12] = count;
            }
            */
        }
        /*
        else if ((preBingo == 3 && posBingo == 1)
                || (preBingo == 2 && posBingo == 2)) {// 七等奖:选中3个前区号码及2个后区号码中的任意1个或选中2个前区号码及2个后区号码
            count = zhudan[13];
            count = count + ticket.getMultiple();
            zhudan[13] = count;
            if (ticket.getOneMoney().intValue() == 3) {
                zhudan[14] = count;
            }
        }

        else if (preBingo == 3 || (preBingo == 1 && posBingo == 2)
                || (preBingo == 2 && posBingo == 1) || posBingo == 2) {// 八等奖:选中3个前区号码或选中1个前区号码及2个后区号码或2个前区号码及2个后区号码中的任意1个或只选中2个后区号码
            count = zhudan[15];
            count = count + ticket.getMultiple();
            zhudan[15] = count;
        }
        if (count != 0) {
            this.isBingo = true;
        }
		*/
    }

    private int check2(String preNumber, String posNumber, String resultContent)
    {
        // 前区所需参数
        int k1;// 胆区选中k1个球
        int m1;// 拖区选中m1个球
        int y1;// 开奖中总球数y1为5个
        int j1;// k1个球中有j1个为开出号码
        int n1;// m1个球中有n1个为开出号码

        String[] dantuo = preNumber.split("\\$");
        if (dantuo.length == 1) {
            k1 = 0;
            m1 = dantuo[0].split(" ").length;
            y1 = 5;
            j1 = 0;
            n1 = checkPre(dantuo[0].split(" "), resultContent.split("\\|")[0]);
        }
        else {
            if (dantuo[0].equals("") || dantuo[0].equals(null)) {
                k1 = 0;
            }
            else {
                k1 = dantuo[0].split(" ").length;// 胆码
            }
            m1 = dantuo[1].split(" ").length;// 拖码
            y1 = 5;
            j1 = checkPre(dantuo[0].split(" "), resultContent.split("\\|")[0]);
            n1 = checkPre(dantuo[1].split(" "), resultContent.split("\\|")[0]);
        }

        // 后区所需参数
        int k2;// 胆区选中k2个球
        int m2;// 拖区选中m2个球
        int y2;// 开奖中总球数y2为5个
        int j2;// k2个球中有j2个为开出号码
        int n2;// m2个球中有n2个为开出号码

        dantuo = posNumber.split("\\$");
        if (dantuo.length == 1) {
            k2 = 0;
            m2 = dantuo[0].split(" ").length;
            y2 = 2;
            j2 = 0;
            n2 = checkPre(dantuo[0].split(" "), resultContent.split("\\|")[1]);
        }
        else {
            if (dantuo[0].equals("") || dantuo[0].equals(null)) {
                k2 = 0;
            }
            else {
                k2 = dantuo[0].split(" ").length;// 胆码
            }
            m2 = dantuo[1].split(" ").length;// 拖码
            y2 = 2;
            j2 = checkPos(dantuo[0].split(" "), resultContent.split("\\|")[1]);
            n2 = checkPos(dantuo[1].split(" "), resultContent.split("\\|")[1]);
        }

        // 求注数公式:C(x-j) n * C(y-k)-(x-j) m-n
        // 求组合公式:MathUtil.getCombinationCount(n,r);n选r的组合

        // ---------------一等奖-------------------------
        int x1 = 5;
        int x2 = 2;
        // 前区中奖注数
        int preWinCount = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        int posWinCount = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        // 中奖注数
        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[1] = preWinCount * posWinCount * ticket.getMultiple();
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[2] = preWinCount * posWinCount * ticket.getMultiple();
        }
        // ----------------------------------------------

        // ---------------二等奖-------------------------
        x1 = 5;
        x2 = 1;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        // 中奖注数
        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[3] = preWinCount * posWinCount * ticket.getMultiple();
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[4] = preWinCount * posWinCount * ticket.getMultiple();
        }
        // ----------------------------------------------

        // ---------------三等奖-------------------------
        x1 = 5;
        x2 = 0;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        // 中奖注数
        //if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
        //    this.isBingo = true;
        // }
        /*
        zhudan[5] = preWinCount * posWinCount * ticket.getMultiple();
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[6] = preWinCount * posWinCount * ticket.getMultiple();
        }
        */
        //或
        x1 = 4;
        x2 = 2;
        // 前区中奖注数
        int preWinCount1 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        int posWinCount1 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        // 中奖注数
        //if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
        //    this.isBingo = true;
        //}
        int count3 = 0;
        count3 = (preWinCount*posWinCount)+(preWinCount1*posWinCount1);
        count3 *= ticket.getMultiple();
        if(count3 != 0)
        {
        	this.isBingo = true;
        }
        zhudan[5] = count3;
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[6] = count3;
        }
        // ----------------------------------------------

        // ---------------四等奖-------------------------
        x1 = 4;
        x2 = 1;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        // 中奖注数
        /*
        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[7] = preWinCount * posWinCount * ticket.getMultiple();
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[8] = preWinCount * posWinCount * ticket.getMultiple();
        }*/
        //或
        x1 = 3;
        x2 = 2;
        // 前区中奖注数
        preWinCount1 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount1 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        int count4 = 0;
        count4 = (preWinCount*posWinCount)+(preWinCount1*posWinCount1);
        count4 *= ticket.getMultiple();
        if(count4 != 0)
        {
        	this.isBingo = true;
        }
        zhudan[7] = count4;
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[8] = count4;
        }
        
        // ----------------------------------------------

        // ---------------五等奖-------------------------
        x1 = 4;
        x2 = 0;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        // 中奖注数
        /*
        if (preWinCount * posWinCount * ticket.getMultiple() != 0) {
            this.isBingo = true;
        }
        zhudan[9] = preWinCount * posWinCount * ticket.getMultiple();
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[10] = preWinCount * posWinCount * ticket.getMultiple();
        }
        */
        //或
        x1 = 3;
        x2 = 1;
        // 前区中奖注数
        preWinCount1 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount1 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        //或
        x1 = 2;
        x2 = 2;
        // 前区中奖注数
       int preWinCount2 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
       int posWinCount2 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
       
       int count5 = 0;
       count5 = (preWinCount*posWinCount)+(preWinCount1*posWinCount1)+(preWinCount2*posWinCount2);
       count5 *= ticket.getMultiple();
       if(count5 != 0)
       {
       	this.isBingo = true;
       }
       zhudan[9] = count5;
       if (ticket.getOneMoney().intValue() == 3) {
           zhudan[10] = count5;
       }
        // ----------------------------------------------

        // ---------------六等奖-------------------------
        x1 = 3;
        x2 = 0;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));

        // 或

        x1 = 1;
        x2 = 2;
        // 前区中奖注数
        preWinCount1 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount1 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        
        // 或

        x1 = 2;
        x2 = 1;
        // 前区中奖注数
        preWinCount2 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount2 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));
        // 或

        x1 = 0;
        x2 = 2;
        // 前区中奖注数
        int preWinCount3 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        int posWinCount3 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));

        // 中奖注数
        int count6 = 0;
        count6 = (preWinCount * posWinCount) + (preWinCount1 * posWinCount1) + (preWinCount2 * posWinCount2) + (preWinCount3 * posWinCount3);
        count6 *= ticket.getMultiple();
        if (count6 != 0) {
            this.isBingo = true;
        }
        zhudan[11] = count6;
        /*
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[12] = count6;
        }
        */
        // ----------------------------------------------
        /*
        // ---------------七等奖-------------------------
        x1 = 3;
        x2 = 1;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));

        // 或

        x1 = 2;
        x2 = 2;
        // 前区中奖注数
        preWinCount1 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount1 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));

        // 中奖注数
        int count7 = 0;
        count7 = (preWinCount * posWinCount) + (preWinCount1 * posWinCount1);
        count7 *= ticket.getMultiple();
        if (count7 != 0) {
            this.isBingo = true;
        }
        zhudan[13] = count7;
        if (ticket.getOneMoney().intValue() == 3) {
            zhudan[14] = count7;
        }

        // ----------------------------------------------

        // ---------------八等奖-------------------------
        x1 = 3;
        x2 = 0;
        // 前区中奖注数
        preWinCount = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));

        // 或

        x1 = 1;
        x2 = 2;
        // 前区中奖注数
        preWinCount1 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount1 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));

        // 或

        x1 = 2;
        x2 = 1;
        // 前区中奖注数
        preWinCount2 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount2 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));

        // 或

        x1 = 0;
        x2 = 2;
        // 前区中奖注数
        preWinCount3 = MathUtil.getCombinationCount(n1, (x1 - j1))
                * MathUtil.getCombinationCount(m1 - n1, (y1 - k1) - (x1 - j1));
        // 后区中奖注数
        posWinCount3 = MathUtil.getCombinationCount(n2, (x2 - j2))
                * MathUtil.getCombinationCount(m2 - n2, (y2 - k2) - (x2 - j2));

        // 中奖注数
        int count8 = 0;
        count8 = (preWinCount * posWinCount) + (preWinCount1 * posWinCount1)
                + (preWinCount2 * posWinCount2) + (preWinCount3 * posWinCount3);
        count8 *= ticket.getMultiple();
        if (count8 != 0) {
            this.isBingo = true;
        }
        zhudan[15] = count8;
		*/
        return j1 + n1;
    }

    /**
     * Author:shenxinhui
     * 
     * @描 述:幸运奖复式中奖判断
     * @param posNumber
     *            String
     * @return void
     */
    private int check4(String posNumber, String resultContent)
    {
        @SuppressWarnings("unused")
        int m2;// 选中m2个球
        int n2;// m2个球中有n2个为开出号码

        n2 = checkPre(posNumber.split(" "), resultContent.split("\\|")[1]);

        int x2 = 2;
        // 后区中奖注数
        int posWinCount = MathUtil.getCombinationCount(n2, x2);
        // 中奖注数
        int luckyCount = 0;
        luckyCount = posWinCount * ticket.getMultiple();
        if (luckyCount != 0) {
            this.isBingo = true;
        }
        zhudan[16] = luckyCount;
        return n2;
    }

    /**
     * Author:shenxinhui
     * 
     * @描 述:判断前区的中奖个数
     * @param nums
     *            String
     * @return int
     */
    private int checkPre(String[] preNums, String resultContent)
    {
        //
        // String[] strAwardCode = resultContent.split("\\,");//开奖号码
        int preBingo = 0;// 前区命中号码个数

        for (int j = 0; j < preNums.length; j++) {

            if (resultContent.indexOf(preNums[j]) >= 0) {
                preBingo++;
            }
        }

        return preBingo;
    }

    /**
     * Author:wwl
     * 
     * @描 述:判断后区的中奖个数
     * @param posNums
     *            String
     * @return int
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
        /*
         * DltBingoCheck check = new DltBingoCheck(); Ticket ticket = new
         * Ticket(); ticket.setOneMoney(new BigDecimal(3));
         * ticket.setMultiple(1); // ticket.setAddAttribute(AddAttribute.ZJ);
         * ticket.setPlayType(PlayType.dlt_sxlfs); ticket.setContent("07,09");
         */
        // ticket.setMultiple(new Long(1));
        // ticket.setAmount(new Double(3));
        // ticket.setPlayType(PlayType.DS);

        /*
         * HashMap<String, String> openResultMap = new HashMap<String,
         * String>(); openResultMap.put("prize1", "1");
         * openResultMap.put("prize2", "2"); openResultMap.put("prize3", "3");
         * openResultMap.put("prize4", "4"); openResultMap.put("prize5", "5");
         * openResultMap.put("prize6", "6"); openResultMap.put("prize7",
         * "3000"); openResultMap.put("prize8", "8");
         * openResultMap.put("prize9", "9"); openResultMap.put("prize10", "10");
         * openResultMap.put("prize11", "11"); openResultMap.put("prize12",
         * "12"); openResultMap.put("prize13", "13");
         * openResultMap.put("prize14", "14"); openResultMap.put("prize15",
         * "15"); openResultMap.put("prize16", "16");
         * 
         * openResultMap.put("result", "03,07,20,21,30|07,09");
         * check.check4("07,09", "03,07,20,21,30|07,09");
         */
        // check.execute(ticket, bingoMap, result)

    }

    public boolean isOpenAble()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public String getBingoContentTicket()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
