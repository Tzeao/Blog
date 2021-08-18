package com.tzeao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author 君子慎独
 * @create 2021/8/19 0019 0:39
 * 处理错误信息页面
 */
@ControllerAdvice //实现全局异常处理
public class ExceptionController {
    /**
     * 实现全局异常处理
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(Exception.class) // 代表这个方法用来处理错误页面
    public ModelAndView exceptionController(HttpServletRequest request, Exception e) {
         logger.error("Requst URL : {}, Exception :{}",request.getRequestURL(),e);

         ModelAndView mv = new ModelAndView();
         mv.addObject("url",request.getRequestURL());
         mv.addObject("exception",e);
         mv.setViewName("error/error");
         return mv;
    }
}
