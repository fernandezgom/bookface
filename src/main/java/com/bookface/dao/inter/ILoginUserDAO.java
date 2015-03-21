package com.bookface.dao.inter;

import com.bookface.util.BookfaceException;

public interface ILoginUserDAO {
	
	public boolean insertNewUser(String username, String password) throws BookfaceException;

}
