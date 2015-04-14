package com.bookface.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bookface.dao.inter.IAuthorDAO;
import com.bookface.repository.Author;
import com.bookface.util.BookfaceException;

@Repository
public class AuthorDAO extends HibernateDaoSupport implements IAuthorDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getBookfaceSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public boolean insertNewAuthor(String author, String bio) throws BookfaceException {
		final Session session = this.getBookfaceSession();
		try {
			Author au=new Author();
			au.setName(author);
			au.setBio(bio);
			session.saveOrUpdate(au);
			return true;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	
	public List<Author> getAllData() throws BookfaceException {
		try {
			final Criteria criteria = getBookfaceSession().createCriteria(Author.class);
			List<Author> list = (List<Author>) criteria.list();
			return list;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	
	public Author getAllAuthorData(String author) throws BookfaceException {
		try {
			final Criteria criteria = getBookfaceSession().createCriteria(Author.class);
			criteria.add(Restrictions.eq("name", author));
			Author au = (Author) criteria.uniqueResult();
			return au;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	

}
