package com.bookface.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bookface.dao.inter.IBookDAO;
import com.bookface.util.BookfaceException;

@Repository
public class BookDAO extends HibernateDaoSupport implements IBookDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getBookfaceSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public boolean insertNewBook(String book, String syn, int idAuthor) throws BookfaceException {
		final Session session = this.getBookfaceSession();
		try {
//			Author au=new Author();
//			au.setName(author);
//			au.setBio(bio);
//			session.saveOrUpdate(au);
			return true;
		} catch (Exception e){
			throw new BookfaceException(e);
		}
	}
	

}
