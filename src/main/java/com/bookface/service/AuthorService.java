package com.bookface.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookface.dao.inter.IAuthorDAO;
import com.bookface.dao.inter.ILikesDAO;
import com.bookface.repository.Author;
import com.bookface.repository.Book;
import com.bookface.repository.Likes;
import com.bookface.service.inter.IAuthorService;
import com.bookface.util.BookfaceException;
import com.bookface.vo.BookDetailsVO;
import com.bookface.vo.ListAuthorBooks;

@Service("authorService")
@Transactional(rollbackFor = { BookfaceException.class, BookfaceException.class })
public class AuthorService implements IAuthorService {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthorService.class);

	public IAuthorDAO authorDAO;
	public ILikesDAO likesDAO;

	@Autowired
	public AuthorService(IAuthorDAO authorDAO, ILikesDAO likesDAO) {
		this.authorDAO = authorDAO;
		this.likesDAO = likesDAO;
	}


	public boolean insertNewAuthor(String author, String bio) throws BookfaceException {
		try {
			return getAuthorDAO().insertNewAuthor(author, bio);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return false;
	}
	
	public ArrayList<ListAuthorBooks> getAllData() throws BookfaceException {
		try {
			List<Author> res= getAuthorDAO().getAllData();
			List<ListAuthorBooks> ab= new ArrayList<ListAuthorBooks>();
			for (Author entity : res) {
				final ListAuthorBooks aux= new ListAuthorBooks();
				aux.setAuthor(entity.getName());
				List<BookDetailsVO> auxbooks= new ArrayList<BookDetailsVO>();
				for (Book bo : entity.getBooks()) {
					final BookDetailsVO bd= new BookDetailsVO();
					bd.setBook(bo.getName());
					auxbooks.add(bd);
				}
				aux.setBooks(auxbooks);
				ab.add(aux);
			}
			return (ArrayList<ListAuthorBooks>) ab;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}
	
	public ArrayList<ListAuthorBooks> getAllDataLikes(String user) throws BookfaceException {
		try {
			List<Likes> res= getLikesDAO().getAllDataLikes(user);
			List<ListAuthorBooks> ab= new ArrayList<ListAuthorBooks>();
			for (Likes entity : res) {
				final ListAuthorBooks aux= new ListAuthorBooks();
				aux.setAuthor(entity.getBook().getAuthor().getName());
				List<BookDetailsVO> auxbooks= new ArrayList<BookDetailsVO>();
				final BookDetailsVO bd= new BookDetailsVO();
				bd.setBook(entity.getBook().getName());
				auxbooks.add(bd);
				aux.setBooks(auxbooks);
				ab.add(aux);
			}
			return (ArrayList<ListAuthorBooks>) ab;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}
	
	public ListAuthorBooks getAllAuthorData(String author) throws BookfaceException {
		try {
			Author res= getAuthorDAO().getAllAuthorData(author);
			final ListAuthorBooks aux= new ListAuthorBooks();
			aux.setAuthor(res.getName());
			aux.setBio(res.getBio());
			List<BookDetailsVO> auxbooks= new ArrayList<BookDetailsVO>();
			for (Book bo : res.getBooks()) {
				final BookDetailsVO bd= new BookDetailsVO();
				bd.setBook(bo.getName());
				auxbooks.add(bd);
			}
			aux.setBooks(auxbooks);
			return aux;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}

	public IAuthorDAO getAuthorDAO() {
		return authorDAO;
	}

	public void setAuthorDAO(IAuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}
	
	public ILikesDAO getLikesDAO() {
		return likesDAO;
	}

	public void setLikesDAO(ILikesDAO likesDAO) {
		this.likesDAO = likesDAO;
	}


}
