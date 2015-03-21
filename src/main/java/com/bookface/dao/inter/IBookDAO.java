package com.bookface.dao.inter;

import com.bookface.util.BookfaceException;

public interface IBookDAO {
	
	public boolean insertNewBook(String book, String syn, int idAuthor) throws BookfaceException;

}
