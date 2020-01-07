package com.xuxiang.community.controller;

import com.xuxiang.community.dto.PageinationDto;
import com.xuxiang.community.dto.QuestionDto;
import com.xuxiang.community.mapper.UserMapper;
import com.xuxiang.community.model.Question;
import com.xuxiang.community.model.User;
import com.xuxiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {
        //验证是否登录 登录cook持久化
        if (request.getCookies() != null && request.getCookies().length != 0) {
            Cookie cookies[] = request.getCookies();
            for (Cookie cookie : cookies) {
                String tokenid = cookie.getName();
                if (tokenid.equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }



        PageinationDto pageination = questionService.list(page, size);
        model.addAttribute("pageination", pageination);
        return "index";
    }
}
