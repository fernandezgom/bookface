package com.bookface.service.inter;

import com.bookface.util.BookfaceException;
import com.bookface.vo.BookDetailsVO;

public interface IBookService {
	
	public boolean insertNewBook(String book, String syn, String author) throws BookfaceException;
	
	public BookDetailsVO getAllBookData(String book) throws BookfaceException;
	
	public boolean doLike(String book, String user) throws BookfaceException;
	

}
