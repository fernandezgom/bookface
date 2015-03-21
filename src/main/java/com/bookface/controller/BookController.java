package com.bookface.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookface.service.inter.IBookService;
import com.bookface.vo.BookDetailsVO;

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
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String authorInit(Model model) {
		logger.info("JLF --- AuthorController init --- Initiliasing the main view from the Wizard of Oz");
		try {
			return "books";
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			return "redirect:/login";
		}
	}
	
	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "/addBook",method = RequestMethod.GET)
	public String addAuthor(Model model) {
		logger.info("JLF --- AuthorController init --- Initiliasing the main view from the Wizard of Oz");
		try {
			return "addBook";
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			return "redirect:/login";
		}
	}
	

	@RequestMapping(value = "/registerBook", method = RequestMethod.POST)
    public @ResponseBody boolean registerUser(@RequestBody BookDetailsVO data, HttpServletRequest req){
		logger.info("JLF --- AuthorController registerAuthor() --- Register an author");
		try {
			return getBookService().insertNewBook(data.getBook(), data.getSyn(), Integer.parseInt(data.getIdAuthor()));
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		return false;
		
	}

	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}



}
