package com.bookface.service.inter;

import com.bookface.util.BookfaceException;

public interface IBookService {
	
	public boolean insertNewBook(String book, String syn, int idAuthor) throws BookfaceException;

}
