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
@RequestMapping("/async")
public class WebAsyncTaskController {


    @RequestMapping(value = "/task3", method = RequestMethod.GET)
    public WebAsyncTask task3() {
        System.out.println("/task3 被调用 thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
        Callable<ModelAndView> c = () -> {
            Thread.sleep(3000L); // 假设是一些长时间任务
            ModelAndView mav = new ModelAndView("longtimetask");
            mav.addObject("result", "执行成功");
            System.out.println("执行成功 thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
            return mav;
        };
        WebAsyncTask asyncTask = new WebAsyncTask(2000, c);
        asyncTask.onTimeout((Callable<ModelAndView>) () -> {
            ModelAndView mav = new ModelAndView("remotecalltask");
            mav.addObject("result", "执行超时");
            System.out.println("task timeout:" + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
            return mav;
        });
        return new WebAsyncTask(3000, c);
    }


    @RequestMapping(value = "/task4", method = RequestMethod.GET)
    public WebAsyncTask task4() {
        System.out.println("/longtimetask 被调用 thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
        Callable<ModelAndView> c = () -> {
            Thread.sleep(3000L); // 假设是一些长时间任务
            ModelAndView mav = new ModelAndView("longtimetask");
            mav.addObject("result", "执行成功");
            System.out.println("执行成功 thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
            return mav;
        };
        return new WebAsyncTask(c);
    }


}
