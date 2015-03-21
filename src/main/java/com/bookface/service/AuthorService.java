package com.bookface.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookface.dao.inter.IAuthorDAO;
import com.bookface.service.inter.IAuthorService;
import com.bookface.util.BookfaceException;

@Service("authorService")
@Transactional(rollbackFor = { BookfaceException.class, BookfaceException.class })
public class AuthorService implements IAuthorService {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthorService.class);

	public IAuthorDAO authorDAO;

	@Autowired
	public AuthorService(IAuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}

	public boolean insertNewAuthor(String author, String bio) throws BookfaceException {
		try {
			return getAuthorDAO().insertNewAuthor(author, bio);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return false;
	}

	public IAuthorDAO getAuthorDAO() {
		return authorDAO;
	}

	public void setAuthorDAO(IAuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}


}
