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

import com.bookface.service.inter.ILoginUserService;
import com.bookface.vo.UserDetailsVO;

/**
 * Handles requests for the application exercise sequence.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	/*Services*/
	private ILoginUserService loginUserService;

    @Autowired
    public RegisterController(ILoginUserService loginUserService) {
    	this.setLoginUserService(loginUserService);
    }
    
	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String registerInit(Model model) {
		logger.info("JLF --- RegisterController init --- Initiliasing the main view from the Wizard of Oz");
		try {
			return "register";
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			return "redirect:/login";
		}
	}
	

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public @ResponseBody boolean registerUser(@RequestBody UserDetailsVO messageForm, HttpServletRequest req){
		logger.info("JLF --- RegisterController registerUser() --- Inserting a sequence of exercises for that user");
		logger.info("user= "+ messageForm.getUsername());
		try{
			return getLoginUserService().insertNewUser(messageForm.getUsername(), messageForm.getPassword());
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		return false;
		
	}

	public ILoginUserService getLoginUserService() {
		return loginUserService;
	}

	public void setLoginUserService(ILoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}



}
