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

import com.bookface.service.inter.IAuthorService;
import com.bookface.service.inter.ILoginUserService;
import com.bookface.vo.AuthorDetailsVO;

/**
 * Handles requests for the application exercise sequence.
 */
@Controller
@RequestMapping("/author")
public class AuthorController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

	/*Services*/
	private IAuthorService authorService;

	@Autowired
    public AuthorController(IAuthorService authorService) {
    	this.setAuthorService(authorService);
    }
    
	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String authorInit(Model model) {
		logger.info("JLF --- AuthorController init --- Initiliasing the main view from the Wizard of Oz");
		try {
			return "author";
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			return "redirect:/login";
		}
	}
	
	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "/addAuthor",method = RequestMethod.GET)
	public String addAuthor(Model model) {
		logger.info("JLF --- AuthorController init --- Initiliasing the main view from the Wizard of Oz");
		try {
			return "addAuthor";
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			return "redirect:/login";
		}
	}
	

	@RequestMapping(value = "/registerAuthor", method = RequestMethod.POST)
    public @ResponseBody boolean registerUser(@RequestBody AuthorDetailsVO data, HttpServletRequest req){
		logger.info("JLF --- AuthorController registerAuthor() --- Register an author");
		try {
			return getAuthorService().insertNewAuthor(data.getAuthor(), data.getBiography());
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		return false;
		
	}

	public IAuthorService getAuthorService() {
		return authorService;
	}

	public void setAuthorService(IAuthorService authorService) {
		this.authorService = authorService;
	}



}
