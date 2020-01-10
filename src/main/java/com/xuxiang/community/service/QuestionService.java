package com.xuxiang.community.service;

import com.xuxiang.community.dto.PageinationDto;
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

    public PageinationDto list(Integer page, Integer size) {
        Integer tootle = questionMapper.pageTootl(); //总条数
        PageinationDto pageinationDto = new PageinationDto();
        pageinationDto.setPageination(tootle, page, size);

        //防止sql出错
        if (page < 1) {
            page = 1;
        }
        if (page > pageinationDto.getTootlePage()) {
            page = pageinationDto.getTootlePage();
        }


        Integer offset = size * (page - 1);//公式 开始条数=size*(page-1)
        List<Question> questions = questionMapper.list(offset, size);//页面数据
        List<QuestionDto> questionDtolist = new ArrayList<QuestionDto>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto); //spring 给的方法 可直接将一个对象的值赋给 另一个对象
            questionDto.setUser(user);
            questionDtolist.add(questionDto);
        }

        pageinationDto.setQuestion(questionDtolist);

        return pageinationDto;


    }


    //我的问题
    public PageinationDto listById(Integer page, Integer size, Integer userId) {

        Integer tootle = questionMapper.pageTootlById(userId); //总条数
        PageinationDto pageinationDto = new PageinationDto();
        pageinationDto.setPageination(tootle, page, size);

        //防止sql出错
        if (page < 1) {
            page = 1;
        }
        if (page > pageinationDto.getTootlePage()) {
            page = pageinationDto.getTootlePage();
        }


        Integer offset = size * (page - 1);//公式 开始条数=size*(page-1)
        List<Question> questions = questionMapper.listById(offset, size, userId);//页面数据
        List<QuestionDto> questionDtolist = new ArrayList<QuestionDto>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto); //spring 给的方法 可直接将一个对象的值赋给 另一个对象
            questionDto.setUser(user);
            questionDtolist.add(questionDto);
        }

        pageinationDto.setQuestion(questionDtolist);
        return pageinationDto;


    }


    public QuestionDto getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDto questionDto = new QuestionDto();

        BeanUtils.copyProperties(question, questionDto); //spring 给的方法 可直接将一个对象的值赋给 另一个对象
        User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }


    public void creatorUpdate(Question question) {
        //更新
        if (question.getId() != null) {
            questionMapper.updateByid(question);
        }

        //插入
        if (question.getId() == null) {
            questionMapper.creat(question);
        }
    }
}
