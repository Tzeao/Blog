package com.tzeao.controller;

import com.tzeao.entity.Type;
import com.tzeao.service.BlogService;
import com.tzeao.service.TypeService;
import com.tzeao.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author 君子慎独
 * @create 2021/8/23 0023 16:33
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/type/{id}")
    public String type(@PathVariable("id") Long id, @PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        List<Type> list = typeService.listTypeTop(99999);
        if (id == -1) {
            id = list.get(0).getId();
        }
        System.err.println(pageable);
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", list);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
