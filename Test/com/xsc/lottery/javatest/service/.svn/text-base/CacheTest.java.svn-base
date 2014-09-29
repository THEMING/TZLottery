package com.xsc.lottery.javatest.service;

import java.math.BigDecimal;

import org.junit.Test;

import com.xsc.lottery.cache.HomepageCache;
import com.xsc.lottery.junitTest.tools.SuperTest;

public class CacheTest extends SuperTest
{

    private HomepageCache<String> cache;

    @SuppressWarnings("unchecked")
    @Test
    public void save()
    {
        cache = (HomepageCache<String>) springContext.getBean("homepageCache");
        cache.put("ddd", "dddd");
        System.out.println(cache.get("ddd"));
    }

    public static void main(String[] args)
    {
        System.out.println(new BigDecimal(1.0));
    }
}
