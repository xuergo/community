package com.xuxiang.community.controller;

import com.xuxiang.community.dto.PageinationDto;
import com.xuxiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    //退出登录
    @GetMapping("/quit")
    public String quit(
            HttpServletRequest request,
            HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";//重定向
    }

}
