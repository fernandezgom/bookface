package com.bookface.service.inter;

import com.bookface.util.BookfaceException;

public interface ILoginUserService {
	
	public boolean insertNewUser(String username, String password) throws BookfaceException;

}
