package com.java.async.controller;

import com.java.async.service.LongTimeAsyncCallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jakeChen
 * @Description:
 * @date 26/06/2018
 */
@Controller
@RequestMapping("/async")
public class AsyncController {

	@Autowired
	private LongTimeAsyncCallServiceImpl longTimeAsyncCallService;

	@RequestMapping("/task")
	public ModelAndView async() {

		ModelAndView mav = new ModelAndView("remotecalltask");

		longTimeAsyncCallService.asyncDoSth();

		mav.addObject("result", "异步返回");

		return mav;

	}

}
