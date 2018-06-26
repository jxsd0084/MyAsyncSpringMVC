package com.java.async.controller;

import com.java.async.service.LongTimeAsyncCallService;
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
public class AsyncController {

	@Autowired
	private LongTimeAsyncCallService longTimeAsyncCallService;

	@RequestMapping("/async")
	public ModelAndView async() {

		ModelAndView mav = new ModelAndView("remotecalltask");

		longTimeAsyncCallService.asyncDoSth();

		mav.addObject("result", "异步返回");

		return mav;

	}

}
