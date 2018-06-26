package com.java.async.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author jakeChen
 * @Description:
 * @date 26/06/2018
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/index")
	public String index() {
		return "demo";
	}

}
