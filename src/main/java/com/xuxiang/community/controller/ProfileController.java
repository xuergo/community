package com.xuxiang.community.controller;

import com.xuxiang.community.dto.QuestionDto;
import com.xuxiang.community.mapper.UserMapper;
import com.xuxiang.community.model.User;
import com.xuxiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model) {
        User user = null;
        //验证是否登录 登录cook持久化
        if (request.getCookies() != null && request.getCookies().length != 0) {
            Cookie cookies[] = request.getCookies();
            for (Cookie cookie : cookies) {
                String tokenid = cookie.getName();
                if (tokenid.equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

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

        List<QuestionDto> questionList = questionService.listById(user.getId());
        model.addAttribute("questions", questionList);
        return "profile";
    }

}
