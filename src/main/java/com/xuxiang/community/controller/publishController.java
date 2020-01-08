package com.xuxiang.community.controller;

import com.xuxiang.community.mapper.QuestionMapper;
import com.xuxiang.community.model.Question;
import com.xuxiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        //验证输入是否完整
        if (title == "") {
            model.addAttribute("erro", "没有标题");
            return "publish";
        } else if (description == "") {
            model.addAttribute("erro", "没有内容");
            return "publish";
        } else if (tag == "") {
            model.addAttribute("erro", "没有标签");
            return "publish";
        }

        //验证是否登录
        User user =(User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("erro", "没有登录");
            return "publish";
        }

        //将问题存入到数据库中
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.creat(question);
        return "redirect:/";//重定向
    }

}
