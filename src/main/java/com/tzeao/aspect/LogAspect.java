package com.tzeao.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author 君子慎独
 * @create 2021/8/19 0019 2:03
 * <p>
 * 使用切面AOP来进行  日志处理
 */
@Aspect
@Component
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义一个切点
     */
    @Pointcut("execution(* com.tzeao.controller.*.*(..))")
    public void log() {
    }

    /**
     * 切面之前,直接调用切点方法，也可以"execution(* com.tzeao.controller.*.*(..))"
     */
    @Before("log()")
    public void doBefore(JoinPoint joinpoint) {
        //  根据请求的上下文获取到Attributes
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        RequestLog requestLog = null;
        if (attributes != null) {
            //获取到request
            request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String ip = request.getRemoteAddr();
            String classMethod = joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName();
            Object[] args = joinpoint.getArgs();

            requestLog = new RequestLog(url, ip, classMethod, args);

        }

        logger.info("Request:{}", requestLog);
    }

    /**
     * 切面之后 方法执行结束后
     */
    @After("log()")
    public void doAfter() {
        //logger.info("---------doAfter=-------------");
    }

    /**
     * 返回结果,方法执行中返回的
     */
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturning(Object result) {
        logger.info("Result:{}", result);
    }

    /**
     * 封装为一个类
     */
    private static class RequestLog {
        private final String url;
        private final String ip;
        private final String classMethod;
        private final Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
