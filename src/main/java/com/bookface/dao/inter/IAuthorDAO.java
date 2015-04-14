package com.bookface.dao.inter;

import java.util.List;

import com.bookface.repository.Author;
import com.bookface.util.BookfaceException;

public interface IAuthorDAO {
	
	public boolean insertNewAuthor(String author, String bio) throws BookfaceException;
	
	public List<Author> getAllData() throws BookfaceException;
	
	public Author getAllAuthorData(String author) throws BookfaceException;

}
