package com.bookface.service.inter;

import com.bookface.util.BookfaceException;

public interface IAuthorService {
	
	public boolean insertNewAuthor(String author, String bio) throws BookfaceException;

}
