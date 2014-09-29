package com.xsc.lottery.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class LotteryTermCache<T extends Serializable>
{
    public void put(Serializable key, T t)
    {
        remove(key);
        termCache.put(new Element(key, t));
    }

    @SuppressWarnings("unchecked")
    public T get(Serializable key)
    {
        Element e = termCache.get(key);
        if (e != null) {
            return (T) e.getValue();
        }
        else {
            return null;
        }
    }

    public boolean remove(Serializable key)
    {
        return termCache.remove(key);
    }

    private Cache termCache;

    public void setTermCache(Cache termCache)
    {
        this.termCache = termCache;
    }
}
