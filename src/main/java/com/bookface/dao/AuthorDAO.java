package com.bookface.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	

}
