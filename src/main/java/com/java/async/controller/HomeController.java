package com.java.async.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author jakeChen
 * @Description:
 * @date 26/06/2018
 */
@Controller
public class HomeController {

	@RequestMapping("/index")
	public String index() {
		return "home";
	}

}
