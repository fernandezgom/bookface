package com.bookface.dao.inter;

import java.util.List;

import com.bookface.repository.Book;
import com.bookface.repository.Comment;
import com.bookface.util.BookfaceException;

public interface ICommentsDAO {
	
	public List<Comment> getAllComments(String book) throws BookfaceException;
	
	public boolean insertComment(String book, String comment, String user) throws BookfaceException;

}
