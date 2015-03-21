package com.bookface.dao.inter;

import com.bookface.util.BookfaceException;

public interface IAuthorDAO {
	
	public boolean insertNewAuthor(String author, String bio) throws BookfaceException;

}
