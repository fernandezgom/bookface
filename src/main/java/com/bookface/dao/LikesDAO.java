package com.bookface.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bookface.dao.inter.ILikesDAO;
import com.bookface.repository.Likes;
import com.bookface.util.BookfaceException;

@Repository
public class LikesDAO extends HibernateDaoSupport implements ILikesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getBookfaceSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	
	public List<Likes> getAllDataLikes(String user) throws BookfaceException {
		try {
			final Criteria criteria = getBookfaceSession().createCriteria(Likes.class);
			List<Likes> list = (List<Likes>) criteria.list();
			criteria.createCriteria("users", "u");
			criteria.add(Restrictions.eq("u.username", user));
			criteria.setResultTransformer(Criteria.ROOT_ENTITY);
			return list;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	
}
