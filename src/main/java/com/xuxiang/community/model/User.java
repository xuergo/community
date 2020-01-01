package com.xuxiang.community.model;

import lombok.Data;

//用户 与数据库表user对应的
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private  Long gmtModified;
    private String avatarUrl;
}
