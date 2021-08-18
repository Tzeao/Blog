package com.tzeao.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author 君子慎独
 * @create 2021/8/19 0019 0:39
 * 处理错误信息页面
 * <p>
 * ControllerAdvice //实现全局异常处理
 */
@ControllerAdvice
public class ExceptionController {
    /**
     * 实现全局异常处理
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @return 代表这个方法用来处理错误页面
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionController(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL : {}, Exception :{}", request.getRequestURL(), e);

        //判断当 页面有状态码可以处理时，交给状态码处理，不进行拦截
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
                //存在 ，spring处理
                throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }
}
