package com.bookface.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application exercise sequence.
 */
@Controller
@RequestMapping("/likes")
public class LikesController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(LikesController.class);
	

	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ModelAndView authorInit() {
		logger.info("JLF --- LikesController init");
		ModelAndView mod= new ModelAndView();
		try {
			mod.setViewName("likes");
			return mod;
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			mod.setViewName("redirect:/login");
			return mod;
		}
	}
	



}
