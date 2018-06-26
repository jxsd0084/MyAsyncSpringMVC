package com.java.async.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author jakeChen
 * @Description:
 * @date 26/06/2018
 */
@Controller
public class DefferedResultController {

	public DeferredResult<ModelAndView> asyncTask() {

		return null;
	}

}
