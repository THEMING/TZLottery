package com.xsc.lottery.javatest.service;

import org.junit.Test;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;

import com.xsc.lottery.junitTest.tools.SuperTest;

@SuppressWarnings("unused")
public class LotteryTermTest extends SuperTest
{
    @Test
    public void term()
    {
        /*
         * LotteryTermService termservic = (LotteryTermService)
         * springContext.getBean("lotteryTermService"); PrizeLevel pl =
         * termservic.getPlbyid(new PrizeLevel());
         * System.out.println(pl.getName());
         */
    }

    public static void main(String[] args)
    {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        System.out.println(encoder.encodePassword("aaaaaa", null));
    }
}
