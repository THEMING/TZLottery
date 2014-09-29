package com.xsc.lottery.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HomepageCache<T extends Serializable>
{
    public void put(Serializable key, T t)
    {
        homepageCache.put(new Element(key, t));
    }

    @SuppressWarnings("unchecked")
    public T get(Serializable key)
    {
        Element e = homepageCache.get(key);
        if (e != null) {
            return (T) e.getValue();
        }
        else {
            return null;
        }
    }

    public boolean remove(Serializable key)
    {
        return homepageCache.remove(key);
    }

    @Autowired
    public void setHomepageCache(@Qualifier("cacheHomepagepro") Cache homepageCache)
    {
        this.homepageCache = homepageCache;
    }

    private Cache homepageCache;
}
