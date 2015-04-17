package com.bookface.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bookface.dao.inter.ICommentsDAO;
import com.bookface.repository.Book;
import com.bookface.repository.Comment;
import com.bookface.repository.Users;
import com.bookface.util.BookfaceException;

@Repository
public class CommentsDAO extends HibernateDaoSupport implements ICommentsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getBookfaceSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	
	public List<Comment> getAllComments(String book) throws BookfaceException {
		try {
			final Criteria criteria = getBookfaceSession().createCriteria(Comment.class);
			criteria.createCriteria("book", "b");
			criteria.add(Restrictions.eq("b.name", book));
			criteria.setResultTransformer(Criteria.ROOT_ENTITY);
			return (List<Comment>) criteria.list();
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	
	public boolean insertComment(String book, String comment, String user) throws BookfaceException {
		final Session session = this.getBookfaceSession();
		try {
			final Criteria criteria = getBookfaceSession().createCriteria(Book.class);
			criteria.add(Restrictions.eq("name", book));
			Book bo = (Book) criteria.uniqueResult();
			final Criteria criteria2 = getBookfaceSession().createCriteria(Users.class);
			criteria2.add(Restrictions.eq("username", user));
			Users us = (Users) criteria2.uniqueResult();
			Comment co=new Comment();
			co.setComment(comment);
			co.setBook(bo);
			co.setUsers(us);
			session.saveOrUpdate(co);
			return true;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	
	
}
