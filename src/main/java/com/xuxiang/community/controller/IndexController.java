package com.xuxiang.community.controller;

import com.xuxiang.community.dto.PageinationDto;
import com.xuxiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {
        //验证是否登录 登录cook持久化




        PageinationDto pageination = questionService.list(page, size);
        model.addAttribute("pageination", pageination);
        return "index";
    }
}
