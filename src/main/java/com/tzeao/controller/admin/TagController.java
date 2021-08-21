package com.tzeao.controller.admin;

import com.tzeao.entity.Tags;
import com.tzeao.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 16:43
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page, Model model) {

        Page<Tags> tags = tagService.listTag(page);

        model.addAttribute("page", tags);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tags());
        return "admin/tag-input";
    }

    @PostMapping("/tags")
    public String save(@Valid Tags tags, BindingResult result, RedirectAttributes attributes) {
        Tags name = tagService.findByName(tags.getName());
        if (name != null) {
            result.rejectValue("name", "nameError", "该分类已经存在");
        }
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        Tags saveTags = tagService.saveTag(tags);
        if (saveTags == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        Tags tags = tagService.getTag(id);
        model.addAttribute("tag", tags);
        return "admin/tag-input";
    }

    @PostMapping("/tags/{id}")
    public String editSave(@Valid Tags tags, BindingResult result, RedirectAttributes attributes, @PathVariable("id") Long id) {
        Tags name = tagService.findByName(tags.getName());
        if (name != null) {
            result.rejectValue("name", "nameError", "该分类已经存在");
        }
        if (result.hasErrors()) {
            return "admin/tag-input";
        }
        Tags saveTag = tagService.updateTag(id, tags);
        if (saveTag == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteType(@PathVariable("id") Long id, RedirectAttributes attributes) {
        tagService.deleteTags(id);
        attributes.addFlashAttribute("message", "更新成功");
        return "redirect:/admin/tags";
    }

}
