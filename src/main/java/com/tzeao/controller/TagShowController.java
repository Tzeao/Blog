package com.tzeao.controller;


import com.tzeao.entity.Tags;
import com.tzeao.service.BlogService;
import com.tzeao.service.TagService;
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
public class TagShowController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String tag(@PathVariable("id") Long id, @PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        List<Tags> list = tagService.listTagsTop(99999);
        if (id == -1) {
            id = list.get(0).getId();
        }
        model.addAttribute("tags", list);
        model.addAttribute("page", blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
