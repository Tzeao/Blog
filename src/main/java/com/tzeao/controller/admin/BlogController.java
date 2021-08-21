package com.tzeao.controller.admin;

import com.tzeao.entity.Blog;
import com.tzeao.entity.Type;
import com.tzeao.service.BlogService;
import com.tzeao.service.TypeService;
import com.tzeao.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 15:58
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model) {
        Page<Blog> blogs = blogService.listBlog(pageable, blog);
        List<Type> types = typeService.listType();
        model.addAttribute("page", blogs);
        model.addAttribute("types", types);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model) {
        Page<Blog> blogs = blogService.listBlog(pageable, blog);
        model.addAttribute("page", blogs);
        // 只返回一个片段
        return "admin/blogs :: blogList";
    }
}
