/**
 * 
 */
package com.bookface.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/login")
public class LoginLogoutController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginLogoutController.class);
        

	/**
	 * Handles and retrieves the login page
	 * 
	 * @return the name of the page
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam(value="error", required=false) boolean error,ModelMap model) {
		logger.info("JLF --- LoginLogoutController getLoginPage --- Getting the loginpage");
		ModelAndView mod=new ModelAndView();
		mod.setViewName("login");
		String err="You have entered invalid credentials or your user is already logged in the platform!";
		if (error == true) {
			// Assign an error message
			model.put("err", "You have entered invalid credentials or your user is already logged in the platform!");
			model.addAttribute(err);
		} else {
			model.put("err", "");
		}
		return mod;
	}
	
	/**
	 * Handles and retrieves the denied HTML page. This is shown whenever a regular user
	 * tries to access an admin only page.
	 * 
	 * @return the name of the HTML page
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
 	public String getDeniedPage() {
		logger.info("JLF --- LoginLogoutController getDeniedPage --- Getting the denied page for users, which did not provide credentials");
		// This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "logoutSuccess";
	}
}