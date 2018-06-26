package com.java.async.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jakeChen
 * @Description:
 * @date 26/06/2018
 */
@Controller
@RequestMapping("/async")
public class DeferredResultController {


	// @Autowired
	// private LongTimeAsyncCallService longTimeAsyncCallService;


	@RequestMapping(value = "/asynctask", method = RequestMethod.GET)
	public DeferredResult<ModelAndView> asyncTask() {

		DeferredResult<ModelAndView> deferredResult = new DeferredResult<>();

		System.out.println("/asynctask调用！thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());

		// longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(result -> {
		//
		// 	System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
		//
		// 	ModelAndView mav = new ModelAndView("remotecalltask");
		//
		// 	mav.addObject("result", result);
		//
		// 	deferredResult.setResult(mav);
		// });

		deferredResult.onTimeout(() -> {

			System.out.println("async task timeout:" + Thread.currentThread().getId());

			ModelAndView mav = new ModelAndView("remotecalltask");

			mav.addObject("result", "异步调用执行超时");

			deferredResult.setResult(mav);
		});

		return deferredResult;
	}


}
