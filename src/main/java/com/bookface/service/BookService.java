package com.bookface.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookface.dao.inter.IBookDAO;
import com.bookface.service.inter.IBookService;
import com.bookface.util.BookfaceException;

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

	public boolean insertNewBook(String book, String syn, int idAuthor) throws BookfaceException {
		try {
			return getBookDAO().insertNewBook(book, syn, idAuthor);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return false;
	}
	
	public IBookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(IBookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}



}
