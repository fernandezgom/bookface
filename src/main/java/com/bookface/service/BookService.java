package com.bookface.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookface.dao.inter.IBookDAO;
import com.bookface.repository.Book;
import com.bookface.repository.Likes;
import com.bookface.service.inter.IBookService;
import com.bookface.util.BookfaceException;
import com.bookface.vo.BookDetailsVO;
import com.bookface.vo.UserDetailsVO;

@Service("bookService")
@Transactional(rollbackFor = { BookfaceException.class, BookfaceException.class })
public class BookService implements IBookService {

	private static final Logger logger = LoggerFactory
			.getLogger(BookService.class);

	public IBookDAO bookDAO;

	@Autowired
	public BookService(IBookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public boolean insertNewBook(String book, String syn, String author) throws BookfaceException {
		try {
			return getBookDAO().insertNewBook(book, syn, author);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return false;
	}
	
	public boolean doLike(String book, String user) throws BookfaceException {
		try {
			return getBookDAO().doLike(book, user);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return false;
	}
	
	public BookDetailsVO getAllBookData(String book) throws BookfaceException {
		try {
			Book res= getBookDAO().getAllBookData(book);
			final BookDetailsVO aux= new BookDetailsVO();
			aux.setBook(res.getName());
			aux.setSyn(res.getSynopsis());
			List<UserDetailsVO> auxlikes= new ArrayList<UserDetailsVO>();
			for (Likes li : res.getLikeses()) {
				final UserDetailsVO bd= new UserDetailsVO();
				bd.setUsername(li.getUsers().getUsername());
				auxlikes.add(bd);
			}
			aux.setLikes(auxlikes);
			return aux;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}
	
	public IBookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(IBookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}



}
