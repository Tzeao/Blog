package com.tzeao.controller;

import com.tzeao.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 君子慎独
 * @create 2021/8/23 0023 18:51
 */
@Controller
public class ArchivesShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/ac")
    public String archives(Model model){
        model.addAttribute("archive",blogService.archivesBlog());
        model.addAttribute("count",blogService.count());
        return "archives";
    }
}
