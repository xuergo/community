package com.xuxiang.community.service;

import com.xuxiang.community.dto.QuestionDto;
import com.xuxiang.community.mapper.QuestionMapper;
import com.xuxiang.community.mapper.UserMapper;
import com.xuxiang.community.model.Question;
import com.xuxiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> list() {
        List<Question> list = questionMapper.list();
        List<QuestionDto> questionDtolist = new ArrayList<QuestionDto>();
        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto); //spring 给的方法 可直接将一个对象的值赋给 另一个对象
            questionDto.setUser(user);
            questionDtolist.add(questionDto);
        }
        return questionDtolist;


    }
}
