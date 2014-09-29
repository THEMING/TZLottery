package com.xsc.lottery.dyj.sendticket.bingocheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xsc.lottery.util.MathUtil;

public class BingoUtil
{
    /**
     * 插入排序
     */
    static public void sort(String[] data)
    {
        String temp;
        for (int i = 1; i < data.length; i++) {
            for (int j = i; (j > 0)
                    && (Integer.parseInt(data[j]) < Integer
                            .parseInt(data[j - 1])); j--) {
                temp = data[j];
                data[j] = data[j - 1];
                data[j - 1] = temp;
            }
        }
    }

    static public int getTotalByContent(String content, String codeSplit)
    {
        int ret = 0;
        String[] codes = content.split(codeSplit);
        for (int i = 0; i < codes.length; i++) {
            ret += Integer.parseInt(codes[i]);
        }
        return ret;
    }

    /**
     * 得到中奖注数
     * 
     * @param prizeBingoCount
     *            这一奖级需要中多少个
     * @param bingoCount
     *            实际中了多少个
     * @param buyCount
     *            买了多少个
     * @param lotteryNumCount
     *            彩种号码有多少个
     * @return
     */
    static public int getBingoPieceCount(int prizeBingoCount, int bingoCount,
            int buyCount, int lotteryNumCount)
    {
        return MathUtil.getCombinationCount(bingoCount, prizeBingoCount)
                * MathUtil.getCombinationCount(buyCount - bingoCount,
                        lotteryNumCount - prizeBingoCount);
    }

    /**
     * 按区得到中奖号码个数 开奖号码 1+2+3 投注号码 1,2+2+4 得到2个号码中奖号码
     * 
     * @param content
     * @param areaSplitChar
     * @param numberSplitChar
     * @param bingoContent
     * @param bingoNumberSplitChar
     * @return
     */
    static public int getBingoNumCountByAreas(String content,
            String areaSplitChar, String bingoContent,
            String bingoNumberSplitChar)
    {
        String[] areas = content.split(areaSplitChar);
        int bingoNumCount = 0;
        String[] bingoNumbers = bingoContent.split(bingoNumberSplitChar);
        for (int i = 0; i < areas.length; i++) {
            String[] coder = areas[i].split("");
            for (int j = 1; j < coder.length; j++) {
                if (coder[j].equals(bingoNumbers[i])) {
                    bingoNumCount++;
                }
            }
        }
        return bingoNumCount;
    }
    
    static public int getBingoNumCountByAreassfc(String content,
            String areaSplitChar, String bingoContent,
            String bingoNumberSplitChar)
    {
        String[] areas = content.split(areaSplitChar);
        int bingoNumCount = 0;
        String[] bingoNumbers = bingoContent.split(bingoNumberSplitChar);
        for (int i = 0; i < areas.length; i++) {
            String[] coder = areas[i].split("");
            for (int j = 1; j < coder.length; j++) {
                if (coder[j].equals(bingoNumbers[i])) {
                    bingoNumCount++;
                }
            }
        }
        return bingoNumCount;
    }

    /**
     * 按区得到持续中奖号码个数 开奖号码 1+2+3 投注号码 1,2+2+4 得到2个号码中奖号码
     */
    static public int getBingoNumCountByQxc(String content,
            String areaSplitChar, String bingoContent,
            String bingoNumberSplitChar)
    {
        List<Integer> countList = new ArrayList<Integer>();
        String[] areas = content.split(areaSplitChar);
        int bingoNumCount = 0;
        int ret = 0;
        String[] bingoNumbers = bingoContent.split(bingoNumberSplitChar);
        for (int i = 0; i < areas.length; i++) {
            if (areas[i].contains(bingoNumbers[i])) {
                bingoNumCount++;
                if(bingoNumCount>ret)
                    ret=bingoNumCount;
            }
            else {
                if (bingoNumCount != 0) {
                    countList.add(bingoNumCount);
                    bingoNumCount = 0;
                }
            }
        }
        return ret;
    }
    
    /**
     * 不按区得到中奖号码个数 开奖号码 1+2+3 投注号码 1,2,3,6 得到3个号码中奖号码
     */
    @SuppressWarnings("unchecked")
    static public int getBingoNumCountNotByAreas(String content,
            String numberSplitChar, String resultContent,
            String resultNumberSplitChar)
    {

        int bingoNumCount = 0;
        String[] bingoNumbers = resultContent.split(resultNumberSplitChar);
        HashMap bingoMap = new HashMap<String, String>();
        
        for (int i = 0; i < bingoNumbers.length; i++) {
            bingoMap.put(bingoNumbers[i], bingoNumbers[i]);
        }
        
        String[] codes = content.split(numberSplitChar);
        for (int j = 0; j < codes.length; j++) {
            if (bingoMap.containsKey(codes[j])) {
                bingoNumCount++;
            }
        }

        return bingoNumCount;
    }

    static public int getContinuousCounts(String content, String codeSplitChar)
    {
        String[] codes = content.split(codeSplitChar);

        int tempCodes = 0;
        int lastCount = 0;
        int countTemp = 0;
        for (int i = 0; i < codes.length; i++) {
            if (i == 0) {
                tempCodes = Integer.valueOf(codes[i]);
                countTemp++;
            }
            else {
                if (tempCodes + 1 == Integer.valueOf(codes[i])) {
                    countTemp++;
                }
                else {
                    if (countTemp > lastCount) {
                        lastCount = countTemp;
                    }
                    countTemp = 1;
                }
                tempCodes = Integer.valueOf(codes[i]);
            }

            if (i + 1 == codes.length) {
                if (countTemp > lastCount) {
                    lastCount = countTemp;
                }
            }
        }
        return lastCount;
    }

    /**
     * 
     * @param n
     *            总共有多少个号码
     * @param a
     *            a[0...N] = 0;
     * @param b
     *            中奖情况b[0...N] = 0,1,1,1,1,1,0
     * @param c
     * @param d
     * @param map
     */
    static public void checkQxcBingoMap(int n, int m, int l, int[] a, int[] b,
            int[] c, int[] d, HashMap<Integer, Integer> map)
    {
        // int tempN = n;
        if (n == -1)
            return;
        // 判断a数组最大连续数;
        // 中奖注数为d数组所有数相乘;
        if (n == l) {
            int maxCount = 0;
            int currentCount = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == 1) {
                    currentCount++;
                }
                else {
                    if (currentCount != 0 && currentCount > maxCount) {
                        maxCount = currentCount;
                    }
                    currentCount = 0;
                }
            }
            if (currentCount != 0 && currentCount > maxCount) {
                maxCount = currentCount;
            }
            int bingoCount = 1;
            for (int i = 0; i < d.length; i++) {
                bingoCount *= d[i];
            }

            if (bingoCount != 0 && maxCount >= m) {
                Integer count = map.get(maxCount);
                map.put(maxCount, count + bingoCount);
            }

        }
        if (a[n] == 0) {
            a[n] = 1;
            d[n] = b[n];
            n = l;
            checkQxcBingoMap(n, m, l, a, b, c, d, map);
        }
        else {
            a[n] = 0;
            d[n] = c[n];
            n = n - 1;
            checkQxcBingoMap(n, m, l, a, b, c, d, map);
        }

    }

    // 解析Llc字符串2(-),2(-),3(-),4(-),5(-)
    public static int[] getLlcBingoNumCount(String ticket, String resultContent)
    {
        int[] ret = new int[2];
        int resultNumber = 0;
        int bingoCount = 1;
        String[] single = ticket.split(",");
        for (int i = 0; i < single.length; i++) {
            for (String result : resultContent.split(",")) {
                int singleNum = Integer.valueOf(single[i].substring(0,
                        single[i].indexOf("(")));
                int resultNum = Integer.valueOf(result.substring(0, result
                        .indexOf("(")));
                /*
                 * if(single[i].substring(0,single[i].indexOf("(")).equals(result
                 * .substring(0,result.indexOf("(")))) { resultNumber++; }
                 */
                if (singleNum == resultNum) {
                    resultNumber++;

                    String peace = single[i].substring(
                            single[i].indexOf("(") + 1, single[i].indexOf(")"));
                    bingoCount *= peace.split("\\;").length;
                }
            }

        }
        ret[0] = resultNumber;
        ret[1] = bingoCount;
        return ret;
    }

    // 单式解析ttc字符串2(-),2(-),3(-),4(-),5(-)
    public static int getTtcBingoNumCount(String ticket, String resultContent)
    {
        int ret = 0;
        String[] single = ticket.split(",");
        String resultArray[] = resultContent.split(",");
        String ticketItem, resultItem;
        String[] ticketLine, resultLine;
        for (String singleVo : single) {
            ticketItem = singleVo.trim().substring(0,
                    singleVo.trim().indexOf("(")).trim();
            ticketLine = singleVo.trim().substring(
                    singleVo.trim().indexOf("(") + 1,
                    singleVo.trim().indexOf(")")).split(";");
            for (String resultVo : resultArray) {
                resultItem = resultVo.trim().substring(0,
                        resultVo.trim().indexOf("(")).trim();
                resultLine = resultVo.trim().substring(
                        resultVo.trim().indexOf("(") + 1,
                        resultVo.trim().indexOf(")")).split(";");
                if (ticketItem.equals(resultItem)) {
                    if ("*".equals(resultLine[0])) {
                        ret++;
                    }
                    else {
                        for (String vo : resultLine) {
                            if (vo.equals(ticketLine[0])) {
                                ret++;
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public static int getTtcFsBingoNumCount(String ticket, String resultContent)
    {
        List rstList = new ArrayList();
        String[] single = ticket.split(",");
        String resultArray[] = resultContent.split(",");
        String ticketItem, resultItem;
        String[] ticketLine, resultLine;
        for (String singleVo : single) {
            ticketItem = singleVo.trim().substring(0,
                    singleVo.trim().indexOf("(")).trim();
            ticketLine = singleVo.trim().substring(
                    singleVo.trim().indexOf("(") + 1,
                    singleVo.trim().indexOf(")")).split(";");
            for (String resultVo : resultArray) {
                int ret = 0;
                resultItem = resultVo.trim().substring(0,
                        resultVo.trim().indexOf("(")).trim();
                resultLine = resultVo.trim().substring(
                        resultVo.trim().indexOf("(") + 1,
                        resultVo.trim().indexOf(")")).split(";");
                if (ticketItem.equals(resultItem)) {
                    if ("*".equals(resultLine[0])) {
                        ret++;
                    }
                    else {
                        for (String rst : resultLine) {
                            for (String vo : ticketLine) {
                                if (rst.equals(vo)) {
                                    ret++;
                                    break;
                                }
                            }
                        }
                    }
                    rstList.add(ret);
                    break;
                }
            }
        }
        return getNumber(rstList);
    }

    @SuppressWarnings("unchecked")
    private static int getNumber(List rstList)
    {
        int retBetNum = 0;
        if (rstList.size() >= 6) {
            int tmpLen = rstList.size();
            for (int ss1 = 0; ss1 < tmpLen - 5; ss1++) {
                for (int ss2 = ss1 + 1; ss2 < tmpLen - 4; ss2++) {
                    for (int ss3 = ss2 + 1; ss3 < tmpLen - 3; ss3++) {
                        for (int ss4 = ss3 + 1; ss4 < tmpLen - 2; ss4++) {
                            for (int ss5 = ss4 + 1; ss5 < tmpLen - 1; ss5++) {
                                for (int ss6 = ss5 + 1; ss6 < tmpLen; ss6++) {
                                    retBetNum = retBetNum
                                            + Integer.parseInt(rstList.get(ss1)
                                                    .toString())
                                            * Integer.parseInt(rstList.get(ss2)
                                                    .toString())
                                            * Integer.parseInt(rstList.get(ss3)
                                                    .toString())
                                            * Integer.parseInt(rstList.get(ss4)
                                                    .toString())
                                            * Integer.parseInt(rstList.get(ss5)
                                                    .toString())
                                            * Integer.parseInt(rstList.get(ss6)
                                                    .toString());
                                }
                            }
                        }
                    }
                }
            }

        }
        return retBetNum;
    }

    /**
     * 得到中奖注数 cont1："14,26,37" subchar1:"," * cont2："1,2,3" subchar2:"," return
     * 3;
     */
    public static int substring(String cont1, String subchar1, String cont2,
            String subchar2)
    {
        int number = 0;
        String[] insertArry = cont1.split(subchar1);
        String[] openrstArray = cont2.split(subchar2);
        int suff = -1;
        for (int i = 0; i < openrstArray.length; i++) {
            suff = insertArry[i].indexOf(openrstArray[i]);
            if (suff > -1) {
                number++;
                suff = -1;
            }
        }
        return number;
    }

    /**
     * 判断11选5的号码中奖个数
     * 
     * @param content
     *            票的内容
     * @param resultContent
     *            开奖的内容
     * @param wflx
     *            笼统玩法类型 0: 任选1 1: 任选2--任选8以及前二前三组选 2：任选5胆拖 3：前二直选复试 4: 前三直选复试
     *            5:前三直（组）选单式 6: 前二直（组）选单式 7：前三组选复试 8：前二组选复试
     * @return
     */
    @SuppressWarnings("unchecked")
    public static int getSd11x5BingoCount(String content, String resultContent,
            int wflx)
    {
        int bingoNumCount = 0;

        String[] bingoNumbers = resultContent.split("\\,"); // 中奖号码

        HashMap bingoMap = new HashMap();
        for (int i = 0; i < bingoNumbers.length; i++) {
            bingoMap.put(bingoNumbers[i], bingoNumbers[i]); // 把中奖号码放到hashMap里
        }

        // content.split("\\*");
        if (wflx == 0) { // 任选1
            String[] codes = convertToStringArray(content);
            for (int j = 0; j < codes.length; j++) {
                if (codes[j].equals(bingoNumbers[0].trim())) {
                    bingoNumCount = 1;
                    break;
                }
            }
        }
        else if (wflx == 1) { // 任选2--8 和 组选单式
            String[] codes = convertToStringArray(content);
            for (int j = 0; j < codes.length; j++) {
                if (bingoMap.containsKey(codes[j]))
                    bingoNumCount++;
            }
        }
        else if (wflx == 2) { // 任选5--8胆拖
            String[] codes = content.split("\\*");
            String[] danCodes = convertToStringArray(codes[0]);
            String[] tuoCodes = convertToStringArray(codes[1]);

            for (int j = 0; j < danCodes.length; j++) {
                if (bingoMap.containsKey(danCodes[j]))
                    bingoNumCount++;
            }
            if (bingoNumCount == danCodes.length) { // 胆码的中奖个数等于胆码的个数
                for (int j = 0; j < tuoCodes.length; j++) {
                    if (bingoMap.containsKey(tuoCodes[j]))
                        bingoNumCount++;
                }
            }
        }
        else if (wflx == 3) { // 前二直选复试
            String[] codes = content.split("\\*");
            String[] firstCodes = convertToStringArray(codes[0]);
            String[] secondCodes = convertToStringArray(codes[1]);
            for (int j = 0; j < firstCodes.length; j++) {
                if (firstCodes[j].trim().equals(bingoNumbers[0].trim()))
                    bingoNumCount++;
            }
            if (bingoNumCount == 1) {
                for (int j = 0; j < secondCodes.length; j++) {
                    if (secondCodes[j].trim().equals(bingoNumbers[1].trim()))
                        bingoNumCount++;
                }
            }
        }
        else if (wflx == 4) { // 前三直选复试
            String[] codes = content.split("\\*");
            String[] firstCodes = convertToStringArray(codes[0]);
            String[] secondCodes = convertToStringArray(codes[1]);
            String[] thirdCodes = convertToStringArray(codes[2]);
            for (int j = 0; j < firstCodes.length; j++) {
                if (firstCodes[j].trim().equals(bingoNumbers[0].trim()))
                    bingoNumCount++;
            }
            if (bingoNumCount == 1) {
                for (int j = 0; j < secondCodes.length; j++) {
                    if (secondCodes[j].trim().equals(bingoNumbers[1].trim()))
                        bingoNumCount++;
                }
            }
            if (bingoNumCount == 2) {
                for (int j = 0; j < thirdCodes.length; j++) {
                    if (thirdCodes[j].trim().equals(bingoNumbers[2].trim())) {
                        bingoNumCount++;
                    }
                }
            }
        }
        else if (wflx == 5) { // 前三直(组)选单式
            if (content.trim().length() == 6) {
                String firstCode = content.trim().substring(0, 2);
                String secondCode = content.trim().substring(2, 4);
                String thirdCode = content.trim().substring(4, 6);
                if (firstCode.equals(bingoNumbers[0].trim())
                        && secondCode.equals(bingoNumbers[1].trim())
                        && thirdCode.equals(bingoNumbers[2].trim()))
                    bingoNumCount = 3;
                else
                    bingoNumCount = 0;
            }
        }
        else if (wflx == 6) { // 前二直（组）选单式
            if (content.trim().length() == 4) {
                String firstCode = content.trim().substring(0, 2);
                String secondCode = content.trim().substring(2, 4);
                if (firstCode.equals(bingoNumbers[0].trim())
                        && secondCode.equals(bingoNumbers[1].trim()))
                    bingoNumCount = 2;
                else
                    bingoNumCount = 0;
            }
        }
        else if (wflx == 7) { // 前三组选复试
            String[] codes = convertToStringArray(content);
            for (int j = 0; j < codes.length; j++) {
                for (int i = 0; i < 3; i++) {
                    if (codes[j].equals(bingoNumbers[i].trim())) {
                        bingoNumCount++;
                        break;
                    }
                }
            }
            if (bingoNumCount > 3)
                bingoNumCount = 0;
        }// 前二组选复试
        else if (wflx == 8) {
            String[] codes = convertToStringArray(content);
            for (int j = 0; j < codes.length; j++) {
                for (int i = 0; i < 2; i++) {
                    if (codes[j].equals(bingoNumbers[i].trim())) {
                        bingoNumCount++;
                        break;
                    }
                }
            }
            if (bingoNumCount > 2)
                bingoNumCount = 0;
        }
        return bingoNumCount;
    }

    /**
     * 判断江西时时彩的号码中奖个数
     * 
     * @param content
     *            票的内容
     * @param resultContent
     *            开奖的内容
     * @param wflx
     *            1：一星 2：二星 3：三星 4：四星 5：五星
     * @param isFuxan
     *            是否为复选
     * @return 中奖的个数
     */
    static int getJxsscBingoCount(String content, String resultContent,
            int wflx, boolean isFuxan)
    {

        int bingoNumCount = 0;

        String[] bingoNumbers = resultContent.split("\\,"); // 中奖号码

        String[] codes = content.split("\\,"); // 买的号码
        if (codes.length != 5) // 号码的长度不是5，说明号码格式不正确
            return 0;
        String[] firstCodes = codes[0].split("");
        String[] secondCodes = codes[1].split("");
        String[] thirdCodes = codes[2].split("");
        String[] fouthCodes = codes[3].split("");
        String[] fifthCodes = codes[4].split("");
        if (wflx == 1) { // 一星
            if (haveNumber(fifthCodes, bingoNumbers[4]))
                bingoNumCount++;
        }
        if (wflx == 2 && !isFuxan) { // 二星直选
            if (haveNumber(fifthCodes, bingoNumbers[4])) {
                if (haveNumber(fouthCodes, bingoNumbers[3]))
                    bingoNumCount = 2;
            }
        }
        if (wflx == 2 && isFuxan) { // 二星复选
            if (codes[4].equals(bingoNumbers[4])) {
                bingoNumCount = 1;
                if (codes[3].equals(bingoNumbers[3]))
                    bingoNumCount = 2;
            }
        }
        if (wflx == 3 && !isFuxan) { // 三星直选
            if (haveNumber(fifthCodes, bingoNumbers[4])) {
                if (haveNumber(fouthCodes, bingoNumbers[3]))
                    if (haveNumber(thirdCodes, bingoNumbers[2]))
                        bingoNumCount = 3;
            }
        }
        if (wflx == 3 && isFuxan) { // 三星复选
            if (codes[4].equals(bingoNumbers[4])) {
                bingoNumCount = 1;
                if (codes[3].equals(bingoNumbers[3])) {
                    bingoNumCount = 2;
                    if (codes[2].equals(bingoNumbers[2]))
                        bingoNumCount = 3;
                }
            }
        }
        if (wflx == 4 && !isFuxan) { // 四星直选
            if (haveNumber(fifthCodes, bingoNumbers[4])) {
                if (haveNumber(fouthCodes, bingoNumbers[3]))
                    if (haveNumber(thirdCodes, bingoNumbers[2]))
                        if (haveNumber(secondCodes, bingoNumbers[1]))
                            bingoNumCount = 4;
            }
        }
        if (wflx == 4 && isFuxan) { // 四星复选
            if (codes[4].equals(bingoNumbers[4])) {
                bingoNumCount = 1;
                if (codes[3].equals(bingoNumbers[3])) {
                    bingoNumCount = 2;
                    if (codes[2].equals(bingoNumbers[2])) {
                        bingoNumCount = 3;
                        if (codes[1].equals(bingoNumbers[1]))
                            bingoNumCount = 4;
                    }
                }
            }
        }
        if (wflx == 5 && !isFuxan) { // 五星直选
            if (haveNumber(fifthCodes, bingoNumbers[4])) {
                if (haveNumber(fouthCodes, bingoNumbers[3]))
                    if (haveNumber(thirdCodes, bingoNumbers[2]))
                        if (haveNumber(secondCodes, bingoNumbers[1]))
                            if (haveNumber(firstCodes, bingoNumbers[0]))
                                bingoNumCount = 5;
            }
        }
        if (wflx == 5 && isFuxan) { // 五星复选
            if (codes[4].equals(bingoNumbers[4])) {
                bingoNumCount = 1;
                if (codes[3].equals(bingoNumbers[3])) {
                    bingoNumCount = 2;
                    if (codes[2].equals(bingoNumbers[2])) {
                        bingoNumCount = 3;
                        if (codes[1].equals(bingoNumbers[1])) {
                            bingoNumCount = 4;
                            if (codes[0].equals(bingoNumbers[0]))
                                bingoNumCount = 5;
                        }
                    }
                }
            }
        }
        System.out.println("bingoNumCount: " + bingoNumCount);
        return bingoNumCount;
    }

    /**
     * 判断一个数组是否有某个号码
     * 
     * @param str
     *            数组
     * @param number
     *            号码
     * @return 有则返回 true 否则返回 false
     */
    static boolean haveNumber(String[] str, String number)
    {
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(number))
                return true;
        }
        return false;
    }

    public static int combination(int m, int n)// m为下标，n为上标
    {
        if (m < 0 || n < 0 || m < n)
            return -1;
        // 数据不符合要求，返回错误信息
        n = n < (m - n) ? n : m - n;// C(m,n)=C(m,m-n)
        if (n == 0)
            return 1;
        int result = m;// C(m,1);
        for (int i = 2, j = result - 1; i <= n; i++, j--) {
            result = result * j / i;// 得到C(m,i)
        }
        return result;
    }

    /**
     * 将一个字符串转换为一个数组 例如将 0102030405 转换为 01,02,03,04,05的数组
     * 
     * @param str
     *            要转换的字符串
     * @return 返回一个数组
     */
    public static String[] convertToStringArray(String str)
    {
        if (null == str || str.length() < 1)
            return new String[0];
        String[] codes = new String[str.length() / 2];
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i, i + 2);
            // System.out.println(temp);
            codes[i / 2] = temp;
            i++;
        }
        return codes;
    }

    public static void main(String[] args) throws Exception
    {

        @SuppressWarnings("unused")
        String ticketContent = "0";
        // int isBingo = getSd11x5BingoCount(ticketContent,resultContent,7);

        BingoUtil.getBingoNumCountByAreas("12,23,34", "\\,", "1,3,4", "\\,");
        System.out.println("中奖个数：" + MathUtil.getCombinationCount(2, 2));

        // System.out.println("23".substring(1,2));
        // System.out.println(getBingoNumCountByAreas("0102,03,0304","\\,","*","01,03,04","\\,",2));

        // System.out.println(BingoUtil.getBingoNumCountByQxc("12,2,3,4,5,6",
        // "\\,", "0,2,3,4,7,8", "\\,"));
        // System.out.println(BingoUtil.getContinuousCounts("9,1,2,3,0","\\,"));

        // SFCBingoResult result =
        // BingoUtil.getBingoResultCountBySFC("1,31,1,1,1,1,1,1,1,1,1,1,1,1",
        // "\\,", "\\*", "1,*,1,1,1,1,1,1,1,1,1,1,1,1", "\\,",1);

        // for(int i=0;i<result.getBingoCount().length;i++){
        // System.out.print(result.getBingoCount()[i]);
        // }
        // System.out.println();
        // for(int i=0;i<result.getNoBingoCount().length;i++){
        // System.out.print(result.getNoBingoCount()[i]);
        // }
        // System.out.println();
        // System.out.println(result.getBingoTotalCount());
        // String [] bingoContent = "1,*,1,1,1,1,1,1,1,1,1,1,1,1".split("\\,");
        //		
        // int totalCount2 = 0;//二等奖中奖数
        // for (int k = 0; k < result.getNoBingoCount().length; k++) {
        // int temp = result.getNoBingoCount()[k];
        // for (int h = 0; h < result.getBingoCount().length; h++) {
        // if (k != h ) {
        // temp = temp * result.getBingoCount()[h];
        // }
        // }
        // System.out.println(k+"|"+temp);
        // totalCount2 += temp;
        // }
        //		
        // System.out.println(totalCount2);

    }

}
