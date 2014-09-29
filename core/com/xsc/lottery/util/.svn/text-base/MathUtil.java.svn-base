package com.xsc.lottery.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class MathUtil
{

    /**
     * @描述:求组合C(n,r)
     * @param n
     * @param r
     * @return
     */
    final static public int getCombinationCount(int n, int r)
    {
        if (r > n)
            return 0;
        if (r < 0 || n < 0)
            return 0;
        return getFactorial(n).divide(getFactorial(r),
                BigDecimal.ROUND_HALF_DOWN).divide(getFactorial((n - r)),
                BigDecimal.ROUND_HALF_DOWN).intValue();
    }

    /**
     * @描述:求排列p(n,r)
     * @param n
     * @param r
     * @return
     */
    final static public int getPermutationCount(int n, int r)
    {
        if (r > n)
            return 0;
        if (r < 0 || n < 0)
            return 0;
        return getFactorial(n).divide(getFactorial(n - r)).intValue();
    }

    /**
     * @描述:求n的阶乘
     * @param n
     * @return
     */
    final static public BigDecimal getFactorial(int num)
    {
        BigDecimal sum = new BigDecimal(1.0);
        for (int i = 1; i <= num; i++) {
            BigDecimal a = new BigDecimal(new BigInteger(i + ""));
            sum = sum.multiply(a);
        }
        return sum;
    }

    /**
     * @描述:随机取的01-36之间的数字
     * @param n
     *            取的个数
     * @param area
     *            区间
     * @return
     */
    final static public String getRandom(int area, int n)
    {
        Random rd1 = new Random();
        Set<String> set = new HashSet<String>();
        for (int i = 1; i < area; i++) {

            int num = rd1.nextInt(area);
            String strNum = "";
            if (num < 10) {
                if (num != 0) {
                    strNum = "0" + num;
                    set.add(strNum);
                }

            } else {
                strNum = "" + num;
                set.add(strNum);
            }
            if (set != null && set.size() >= n) {
                break;
            }
        }

        String str = "";// 返回拼的字符串
        Iterator<String> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            if (i < n) {
                str += it.next() + ",";
            } else {
                str += it.next() + "";
            }

        }
        return str;
    }

    /**
     * 求幂
     * 
     * @param m
     *            底数
     * @param n
     *            幂
     * @return
     */
    public static final long getPower(int m, int n)
    {
        assert n >= 0;
        if (n == 0)
            return 1;
        if (n == 1)
            return m;
        long temp = getPower(m, n / 2);
        return n % 2 == 0 ? temp * temp : temp * temp * m;
    }

    /** 生成一个随机密码 */
    public static final String getRandomPassword(int m)
    {
        String code = "";
        for (int i = 0; i < m; i++) {
            code += "0";
        }
        DecimalFormat df = new DecimalFormat(code);
        double temp = Math.random() * Math.pow(10, m);
        return df.format(temp);
    }

    /**
     * @see 生成长度在12和26之间的序列ＩＤ
     * @param m
     *            m>12 m<26。
     * @return
     */
    public static final String getSerialNumber(int m)
    {
        m = m > 12 ? m : 12;
        m = m < 26 ? m : 26;
        String q[] = new SimpleDateFormat("yy HHmmss").format(new Date())
                .split(" ");
        StringBuilder stru = new StringBuilder();
        stru.append(q[0]).append((int) (Math.random() * Math.pow(10, 4)));
        stru.append(q[1]).append((int) (Math.random() * Math.pow(10, m - 12)));
        if (stru.length() < m)
            return getSerialNumber(m);
        return stru.toString();
    }

    public static final boolean repeatChar(String strs, int min, int max)
    {
        char[] cs = strs.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            for (int j = i + 1; j < cs.length; j++) {
                if (cs[i] == cs[j]
                        || (Integer.parseInt(cs[j] + "") < min || Integer
                                .parseInt(cs[j] + "") > max)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static final boolean repeatString(String strs, int min, int max)
    {
        String[] cs = strs.split("\\,");
        for (int i = 0; i < cs.length; i++) {
            for (int j = i + 1; j < cs.length; j++) {
                if (cs[i].equals(cs[j])
                        || (Integer.parseInt(cs[i]) < min || Integer
                                .parseInt(cs[i]) > max)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 20; i++) {
            // double dd=Math.random();
            // System.out.println(dd+"-----------"+i+"----------"+(int)(dd*100));
            String dd = getSerialNumber(16);
            System.out.println(dd + "---------------------" + dd.length());
        }

    }
}
