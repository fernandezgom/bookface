package com.bookface.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookface.service.inter.IBookService;
import com.bookface.vo.BookDetailsVO;
import com.bookface.vo.ListAuthorBooks;

/**
 * Handles requests for the application exercise sequence.
 */
@Controller
@RequestMapping("/book")
public class BookController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	/*Services*/
	private IBookService bookService;

	@Autowired
    public BookController(IBookService bookService) {
    	this.setBookService(bookService);
    }
    
	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "/{book}",method = RequestMethod.GET)
	public ModelAndView authorInit(@PathVariable String book) {
		logger.info("JLF --- AuthorController init");
		ModelAndView mod= new ModelAndView();
		try {
			mod.addObject("book", book.replace("_", " "));
			mod.setViewName("book");
			return mod;
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			mod.setViewName("redirect:/login");
			return mod;
		}
	}
	
	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "/addBook/{author}",method = RequestMethod.GET)
	public ModelAndView addAuthor(Model model, @PathVariable String author) {
		logger.info("JLF --- AuthorController init --- ");
		ModelAndView mod= new ModelAndView();
		try {
			mod.addObject("author", author.replace("_", " "));
			mod.setViewName("addBook");
			return mod;
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			mod.setViewName("redirect:/login");
			return mod;
		}
	}
	
	/**
	 * JLF: Get all authors an books
	 */
	@RequestMapping(value = "/getAllBook",method = RequestMethod.GET)
	public @ResponseBody BookDetailsVO getAllAuthorData(@RequestParam(value = "book") String book) {
		logger.info("JLF --- BookController getAllData ");
		try {
			return getBookService().getAllBookData(book.replace("_", " "));
		} catch (Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	

	@RequestMapping(value = "/registerBook", method = RequestMethod.POST)
    public @ResponseBody boolean registerUser(@RequestBody BookDetailsVO data, HttpServletRequest req){
		logger.info("JLF --- BookController registerBook() --- Register a book");
		try {
			return getBookService().insertNewBook(data.getBook(), data.getSyn(), data.getAuthor());
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		return false;
		
	}
	
	@RequestMapping(value = "/doLike/{book}", method = RequestMethod.GET)
    public String doLike(@PathVariable String book){
		logger.info("JLF --- BookController doLike() --- Like");
		try {
			User us = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    String name = us.getUsername();
			getBookService().doLike(book.replace("_", " "), name);
			return "redirect:/book/"+book;
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		return "";
	}

	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}



}
