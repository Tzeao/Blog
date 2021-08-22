package com.tzeao.controller;

import com.tzeao.entity.Comment;
import com.tzeao.service.BlogService;
import com.tzeao.service.CommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author 君子慎独
 * @create 2021/8/23 0023 0:52
 */
@Controller
public class CommendController {
    @Autowired
    private CommendService commendService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;


    @GetMapping("/commends/{blogId}")
    public String commends(@PathVariable("blogId") Long id, Model model) {

        model.addAttribute("commends", commendService.listCommendByBlogId(id));
        return "blog::commendList";
    }

    @PostMapping("/commends")
    public String post(Comment comment) {
        Long id = comment.getBlog().getId();
        comment.setBlog(blogService.getBolg(id));
        comment.setAvatar(avatar);
        commendService.saveCommend(comment);
        return "redirect:/commends/" + comment.getBlog().getId();
    }
}
