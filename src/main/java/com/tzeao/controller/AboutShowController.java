package com.tzeao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 君子慎独
 * @create 2021/8/23 0023 19:51
 */
@Controller
public class AboutShowController {
    @GetMapping("/about")
    public String about(){

        return "about";
    }
}
