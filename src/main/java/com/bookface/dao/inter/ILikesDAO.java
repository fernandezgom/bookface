package com.bookface.dao.inter;

import java.util.List;

import com.bookface.repository.Author;
import com.bookface.repository.Likes;
import com.bookface.util.BookfaceException;

public interface ILikesDAO {
	
	public List<Likes> getAllDataLikes(String user) throws BookfaceException;

}
