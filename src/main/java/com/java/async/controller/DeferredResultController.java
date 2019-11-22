package com.java.async.controller;

import com.java.async.service.LongTimeAsyncCallService;
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
@RequestMapping("/async")
public class DeferredResultController {


    @Autowired
    private LongTimeAsyncCallService service;


    @RequestMapping(value = "/task1", method = RequestMethod.GET)
    public DeferredResult<ModelAndView> task1() {
        DeferredResult<ModelAndView> result = new DeferredResult<>();
        System.out.println("/task1 调用！thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
        result.onTimeout(() -> {
            System.out.println("async task timeout:" + Thread.currentThread().getId());
            ModelAndView mav = new ModelAndView("task1");
            mav.addObject("result", "异步调用执行超时");
            result.setResult(mav);
        });
        return result;
    }


    @RequestMapping(value = "/task2", method = RequestMethod.GET)
    public DeferredResult<ModelAndView> task2() {
        DeferredResult<ModelAndView> result = new DeferredResult<>();
        System.out.println("/task2 调用！thread id is : " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
        service.callback(r -> {
            System.out.println("异步调用执行完成, thread id is : " + Thread.currentThread().getId());
            ModelAndView mav = new ModelAndView("task2");
            mav.addObject("result", r);
            result.setResult(mav);
        });
        return result;
    }


}
