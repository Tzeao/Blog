package com.tzeao.controller.admin;

import com.tzeao.entity.Type;
import com.tzeao.service.TypeService;
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
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page, Model model) {

        Page<Type> types = typeService.listType(page);

        model.addAttribute("page", types);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    @PostMapping("/types")
    public String save(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type name = typeService.findByName(type.getName());
        if (name != null) {
            result.rejectValue("name", "nameError", "该分类已经存在");
        }
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        Type saveType = typeService.saveType(type);
        if (saveType == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        Type type = typeService.getType(id);
        model.addAttribute("type", type);
        return "admin/type-input";
    }

    @PostMapping("/types/{id}")
    public String editSave(@Valid Type type, BindingResult result, RedirectAttributes attributes, @PathVariable("id") Long id) {
        Type name = typeService.findByName(type.getName());
        if (name != null) {
            result.rejectValue("name", "nameError", "该分类已经存在");
        }
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        Type saveType = typeService.updateType(id, type);
        if (saveType == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable("id") Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "更新成功");
        return "redirect:/admin/types";
    }

}
