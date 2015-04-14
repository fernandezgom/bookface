package com.bookface.service.inter;

import java.util.ArrayList;

import com.bookface.util.BookfaceException;
import com.bookface.vo.ListAuthorBooks;

public interface IAuthorService {
	
	public boolean insertNewAuthor(String author, String bio) throws BookfaceException;
	
	public ArrayList<ListAuthorBooks> getAllData() throws BookfaceException;
	
	public ListAuthorBooks getAllAuthorData(String author) throws BookfaceException;
	
	public ArrayList<ListAuthorBooks> getAllDataLikes(String user) throws BookfaceException;
	
}
