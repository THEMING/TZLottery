package com.xsc.lottery.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.util.Assert;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

@SuppressWarnings("unchecked")
public class PagerHibernateTemplate<T, PK extends Serializable> extends
        SimpleHibernateTemplate<T, PK>
{
    public PagerHibernateTemplate(SessionFactory sessionFactory,
            final Class<T> entityClass)
    {
        super(sessionFactory, entityClass);
    }

    public Page<T> findByCriteria(final Page page, Criteria c)
    {
        Assert.notNull(page);
        if (page.isAutoCount()) {
            page.setTotalCount(countQueryResult(page, c));
        }
        if (page.isFirstSetted()) {
            c.setFirstResult(page.getFirst());
        }
        if (page.isPageSizeSetted()) {
            c.setMaxResults(page.getPageSize());
        }

        if (page.isOrderBySetted()) {
            if (page.getOrder().equals(Page.ASC)) {
                c.addOrder(Order.asc(page.getOrderBy()));
            }
            else {
                c.addOrder(Order.desc(page.getOrderBy()));
            }
        }
        page.setResult(c.list());
        return page;
    }
}
