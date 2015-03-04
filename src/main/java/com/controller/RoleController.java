package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleController {
	
	@RequestMapping("/public")
    public String accessPublicPage(Model model) {
        model.addAttribute("message", "This page is publicly accessible. No authentication is required to view.");
 
        return "public";
    }
     
    @RequestMapping("/secured/mypage")
    public String accessSecuredPage(Model model) {
        model.addAttribute("message", "Only you are authenticated and authorized to view this page.");
 
        return "/secured/mypage";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "denied";
	}
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String listEmployees(ModelMap map) {
    	map.addAttribute("message", "Only you are authenticated and authorized to view this page.");
		return "public";
	}
}
