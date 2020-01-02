package com.xuxiang.community.dto;

import com.xuxiang.community.model.Question;
import com.xuxiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDto extends Question {

    private User user;
}
