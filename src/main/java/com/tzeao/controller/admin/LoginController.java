package com.tzeao.controller.admin;

import com.tzeao.entity.User;
import com.tzeao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 0:26
 */
@Controller
@RequestMapping("/admin")

public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes) {
        User checkUser = userService.checkUser(username, password);
        if (checkUser != null) {
            checkUser.setPassword(null);
            session.setAttribute("user", checkUser);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误！");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin";
    }
}
