package com.bookface.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bookface.dao.inter.IBookDAO;
import com.bookface.repository.Author;
import com.bookface.repository.Book;
import com.bookface.repository.Likes;
import com.bookface.repository.Users;
import com.bookface.util.BookfaceException;

@Repository
public class BookDAO extends HibernateDaoSupport implements IBookDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getBookfaceSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public boolean insertNewBook(String book, String syn, String author) throws BookfaceException {
		final Session session = this.getBookfaceSession();
		try {
			final Criteria criteria = getBookfaceSession().createCriteria(Author.class);
			criteria.add(Restrictions.eq("name", author));
			Author au = (Author) criteria.uniqueResult();
			Book bo=new Book();
			bo.setName(book);
			bo.setSynopsis(syn);
			bo.setAuthor(au);
			session.saveOrUpdate(bo);
			return true;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	
	
	public boolean doLike(String book, String user) throws BookfaceException {
		final Session session = this.getBookfaceSession();
		try {
			final Criteria criteria = getBookfaceSession().createCriteria(Book.class);
			criteria.add(Restrictions.eq("name", book));
			Book bo = (Book) criteria.uniqueResult();
			final Criteria criteria2 = getBookfaceSession().createCriteria(Users.class);
			criteria2.add(Restrictions.eq("username", user));
			Users us = (Users) criteria2.uniqueResult();
			Likes li=new Likes();
			li.setBook(bo);
			li.setUsers(us);
			session.saveOrUpdate(li);
			return true;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	
	public Book getAllBookData(String book) throws BookfaceException {
		try {
			final Criteria criteria = getBookfaceSession().createCriteria(Book.class);
			criteria.add(Restrictions.eq("name", book));
			Book bo = (Book) criteria.uniqueResult();
			return bo;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	
	
}
