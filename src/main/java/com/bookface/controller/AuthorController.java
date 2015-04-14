package com.bookface.controller;

import java.util.ArrayList;

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

import com.bookface.service.inter.IAuthorService;
import com.bookface.vo.AuthorDetailsVO;
import com.bookface.vo.ListAuthorBooks;

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
	@RequestMapping(value = "/{auth}",method = RequestMethod.GET)
	public ModelAndView authorInit(@PathVariable String auth) {
		logger.info("JLF --- AuthorController init --- ");
		ModelAndView mod= new ModelAndView();
		try {
			mod.addObject("author", auth.replace("_", " "));
			mod.setViewName("author");
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
	@RequestMapping(value = "/addAuthor",method = RequestMethod.GET)
	public String addAuthor(Model model) {
		logger.info("JLF --- AuthorController addAuthor --- ");
		try {
			return "addAuthor";
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			return "redirect:/login";
		}
	}
	
	
	/**
	 * JLF: Get all authors an books
	 */
	@RequestMapping(value = "/getAll",method = RequestMethod.GET)
	public @ResponseBody ArrayList<ListAuthorBooks> getAllData() {
		logger.info("JLF --- AuthorController getAllData ");
		try {
			return getAuthorService().getAllData();
		} catch (Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	
	/**
	 * JLF: Get all authors an books
	 */
	@RequestMapping(value = "/getAllLikes",method = RequestMethod.GET)
	public @ResponseBody ArrayList<ListAuthorBooks> getAllDataLikes() {
		logger.info("JLF --- AuthorController getAllData ");
		try {
			User us = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return getAuthorService().getAllDataLikes(us.getUsername());
		} catch (Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	
	/**
	 * JLF: Get all authors an books
	 */
	@RequestMapping(value = "/getAllAuthor",method = RequestMethod.GET)
	public @ResponseBody ListAuthorBooks getAllAuthorData(@RequestParam(value = "author") String author) {
		logger.info("JLF --- AuthorController getAllData ");
		try {
			return getAuthorService().getAllAuthorData(author);
		} catch (Exception e){
			logger.error(e.toString());
		}
		return null;
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
