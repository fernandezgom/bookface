package com.bookface.dao.inter;

import com.bookface.repository.Book;
import com.bookface.util.BookfaceException;

public interface IBookDAO {
	
	public boolean insertNewBook(String book, String syn, String author) throws BookfaceException;
	
	public Book getAllBookData(String book) throws BookfaceException;
	
	public boolean doLike(String book, String user) throws BookfaceException;
	

}
