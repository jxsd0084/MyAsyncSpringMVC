package com.java.async.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.Callable;

/**
 * @author jakeChen
 * @Description:
 * @date 26/06/2018
 */
@Controller
public class WebAsyncTaskController {


	@RequestMapping(value = "/longtimetask", method = RequestMethod.GET)
	public WebAsyncTask longTimeTask() {

		System.out.println("/longtimetask被调用 thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());

		Callable<ModelAndView> callable = new Callable<ModelAndView>() {

			public ModelAndView call() throws Exception {

				Thread.sleep(3000L); // 假设是一些长时间任务

				ModelAndView mav = new ModelAndView("longtimetask");

				mav.addObject("result", "执行成功");

				System.out.println("执行成功 thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());

				return mav;
			}
		};

		// return new WebAsyncTask(callable);

		WebAsyncTask asyncTask = new WebAsyncTask(2000, callable);

		asyncTask.onTimeout(new Callable<ModelAndView>() {

			public ModelAndView call() throws Exception {

				ModelAndView mav = new ModelAndView("remotecalltask");

				mav.addObject("result", "执行超时");

				System.out.println("task timeout:" + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());

				return mav;
			}
		});

		return new WebAsyncTask(3000, callable);
	}


}
