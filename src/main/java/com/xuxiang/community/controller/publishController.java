package com.xuxiang.community.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class publishController {

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

}
