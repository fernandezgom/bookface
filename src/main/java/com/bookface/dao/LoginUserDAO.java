package com.bookface.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.bookface.dao.inter.ILoginUserDAO;
import com.bookface.repository.UserRoles;
import com.bookface.repository.Users;
import com.bookface.util.BookfaceException;

@Repository
public class LoginUserDAO extends HibernateDaoSupport implements ILoginUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public final Session getBookfaceSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	
	public boolean insertNewUser(String username, String password, String email) throws BookfaceException {
		final Session session = this.getBookfaceSession();
		byte b=1;
		try {
			Users us=new Users();
			us.setUsername(username);
			us.setPassword(password);
			us.setEnabled(b);
			us.setEmail(email);
			session.saveOrUpdate(us);
			UserRoles ur= new UserRoles();
			ur.setRole("ROLE_USER");
			ur.setUsers(us);
			session.saveOrUpdate(ur);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			throw new BookfaceException(e);
		}
	}
	

}
