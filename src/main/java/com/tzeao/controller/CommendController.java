package com.tzeao.controller;

import com.tzeao.entity.Comment;
import com.tzeao.entity.User;
import com.tzeao.service.BlogService;
import com.tzeao.service.CommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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
    public String post(Comment comment, HttpSession session) {
        Long id = comment.getBlog().getId();
        comment.setBlog(blogService.getBolg(id));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        commendService.saveCommend(comment);
        return "redirect:/commends/" + comment.getBlog().getId();
    }
}
