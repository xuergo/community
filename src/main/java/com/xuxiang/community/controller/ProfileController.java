package com.xuxiang.community.controller;

import com.xuxiang.community.dto.PageinationDto;
import com.xuxiang.community.mapper.UserMapper;
import com.xuxiang.community.model.User;
import com.xuxiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "2") Integer size,
                          Model model) {

        //验证是否登录 登录cook持久化
        User user =(User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";//重定向
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PageinationDto pageination = questionService.listById(page, size,user.getId());
        model.addAttribute("pageination", pageination);
        return "profile";
    }

}
