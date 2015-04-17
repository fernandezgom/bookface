package com.bookface.service.inter;

import java.util.List;

import com.bookface.util.BookfaceException;
import com.bookface.vo.BookDetailsVO;
import com.bookface.vo.CommentsVO;

public interface IBookService {
	
	public boolean insertNewBook(String book, String syn, String author) throws BookfaceException;
	
	public BookDetailsVO getAllBookData(String book) throws BookfaceException;
	
	public boolean doLike(String book, String user) throws BookfaceException;
	
	public List<CommentsVO> getAllComments(String book) throws BookfaceException;
	
	public boolean insertComment(String book, String comment, String user) throws BookfaceException;
	
}
