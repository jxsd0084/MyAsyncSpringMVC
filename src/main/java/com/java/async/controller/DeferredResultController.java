package com.java.async.controller;

import com.java.async.service.LongTimeAsyncCallService;
import com.java.async.support.LongTermTaskCallback;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DeferredResultController {


	@Autowired
	private LongTimeAsyncCallService longTimeAsyncCallService;


	@RequestMapping(value = "/asynctask", method = RequestMethod.GET)
	public DeferredResult<ModelAndView> asyncTask() {

		DeferredResult<ModelAndView> deferredResult = new DeferredResult<>();

		System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());

		longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {

			@Override
			public void callback(Object result) {

				System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());

				ModelAndView mav = new ModelAndView("remotecalltask");

				mav.addObject("result", result);

				deferredResult.setResult(mav);
			}
		});

		return deferredResult;
	}


}
