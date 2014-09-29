package com.xsc.lottery.service.business.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.business.Filedownlod;
import com.xsc.lottery.service.business.FiledownlodService;

@Service("filedownlodService")
@Transactional
public class FiledownlodServiceImpl implements FiledownlodService {
	
	private PagerHibernateTemplate<Filedownlod, Long> filedownlodDao;
	
	@Autowired
    public void setSessionFactory(
            @Qualifier("sessionFactory") SessionFactory sessionfactory)
    {
        this.filedownlodDao = new PagerHibernateTemplate<Filedownlod, Long>(
                sessionfactory, Filedownlod.class);
    }

	public void delete(Filedownlod entity) {

		filedownlodDao.delete(entity);
	}

	public Filedownlod findById(Long id) {
		return null;
	}

	public Filedownlod load(Long id) {
		return null;
	}

	public Filedownlod save(Filedownlod entity) {
		this.filedownlodDao.save(entity);
        return entity;
	}

	public Filedownlod update(Filedownlod entity) {
		save(entity);
        return null;
	}

	@SuppressWarnings("unchecked")
	public List<Filedownlod> getAllFiledownlod() {
		Criteria cri = filedownlodDao.createCriteria();
		List<Filedownlod> list = cri.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Filedownlod> getFiledownlodByName(String filename) {
		Criteria cri = filedownlodDao.createCriteria();
		cri.add(Restrictions.eq("fileName", filename));
		return cri.list();
	}

}
