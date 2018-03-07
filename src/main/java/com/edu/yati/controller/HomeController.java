package com.edu.yati.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value="/")
public class HomeController {
	@RequestMapping(value="/education", method=RequestMethod.GET)
    public String home() {
        return "index.html";
    }
	
	@RequestMapping(value="/education/index.html", method=RequestMethod.GET)
    public String home1() {
        return "index.html";
    }
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String home2() {
        return "index.html";
    }
}
