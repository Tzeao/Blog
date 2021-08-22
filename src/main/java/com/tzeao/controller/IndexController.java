package com.tzeao.controller;


import com.tzeao.error.NotFoundException;
import com.tzeao.service.BlogService;
import com.tzeao.service.TagService;
import com.tzeao.service.TypeService;
import com.tzeao.vo.BlogQuery;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 君子慎独
 * @create 2021/8/18 0018 23:25
 */
@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagsTop(10));
        model.addAttribute("recommend",blogService.listBlogTop(8));

        String blog = null;
        //  如果博客数据没有找到时 跳转到404页面
        //if (blog == null) {
        //    //自定义 NotFoundException
        //    throw  new NotFoundException("博客不存在");
        //}
        return "index";
    }

    public static void main(String[] args) {
        //盐
        String Salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        //加密
        SimpleHash simpleHash = new SimpleHash("md5","111",Salt,1);
        String NewPassword = simpleHash.toString();
        System.out.println(NewPassword);
        System.out.println("aaa"+Salt);
    }
}
