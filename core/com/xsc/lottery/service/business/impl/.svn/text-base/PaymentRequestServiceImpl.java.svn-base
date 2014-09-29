package com.xsc.lottery.service.business.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.service.business.PaymentRequestService;


@Service("paymentRequestService")
@Transactional
public class PaymentRequestServiceImpl implements PaymentRequestService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SimpleHibernateTemplate<PaymentRequest, Long> paymentRequestDao;

    @Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.paymentRequestDao = new SimpleHibernateTemplate<PaymentRequest, Long>(
                sessionfactory, PaymentRequest.class);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<PaymentRequest> findByProperty(String propertyName, Object value)
    {
        return paymentRequestDao.findByProperty(propertyName, value);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaymentRequest findById(Long id)
    {
        logger.debug("findById PaymentRequest");
        return (PaymentRequest) paymentRequestDao.getSession().get(
                PaymentRequest.class, id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PaymentRequest load(Long id)
    {
        logger.debug("load PaymentRequest");
        return paymentRequestDao.get(id);
    }

    public PaymentRequest save(PaymentRequest entity)
    {
        logger.debug("save PaymentRequest");
        paymentRequestDao.save(entity);
        return entity;
    }

    public PaymentRequest update(PaymentRequest entity)
    {
        logger.debug("update PaymentRequest");
        paymentRequestDao.save(entity);
        return entity;
    }

    public void delete(PaymentRequest entity)
    {
    }
}
