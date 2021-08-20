package com.tzeao.controller;


import com.tzeao.error.NotFoundException;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 君子慎独
 * @create 2021/8/18 0018 23:25
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        //int a =  1/0;
        String blog = null;
        //  如果博客数据没有找到时 跳转到404页面
        //if (blog == null) {
        //    //自定义 NotFoundException
        //    throw  new NotFoundException("博客不存在");
        //}
        //System.out.println("aaaa");
        return "admin/blogs-input";
    }
}
