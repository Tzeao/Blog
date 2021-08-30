package com.tzeao.controller.admin;

import com.tzeao.entity.User;
import com.tzeao.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * @Author 君子慎独
 * @create 2021/8/23 0023 21:38
 */
@Controller
@RequestMapping("/admin")
public class SingUpController {
    @Autowired
    private UserService userService;

    @GetMapping("/singup")
    public String singUp(Model model) {
        model.addAttribute("test","a");
        return "admin/sing-up";

    }

    /**
     *
     * @return  注册账号
     */
    @PostMapping("/sing")
    public String sing(User user,Model model) {
        String username = user.getUsername();
        User user1 = userService.checkUser(username);
        if (user1 == null) {
            String password = user.getPassword();
            //盐
            String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
            //加密
            SimpleHash simpleHash = new SimpleHash("md5", password, salt, 1);
            String newPassword = simpleHash.toString();
            user.setPassword(newPassword);
            user.setSalt(salt);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userService.saveUser(user);
            return "admin/login";
        } else {
            model.addAttribute("test","aa");
            return "admin/sing-up";
        }
    }
}
