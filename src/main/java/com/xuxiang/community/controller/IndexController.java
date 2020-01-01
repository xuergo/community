package com.xuxiang.community.controller;

import com.xuxiang.community.mapper.UserMapper;
import com.xuxiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        if (request.getCookies() != null&&request.getCookies().length !=0) {
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

        return "index";
    }
}
