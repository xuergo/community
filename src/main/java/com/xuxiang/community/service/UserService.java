package com.xuxiang.community.service;


import com.xuxiang.community.mapper.UserMapper;
import com.xuxiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void creatOrUpdateUser(User user) {
       User dbuser=userMapper.findByAccountId( user.getAccountId());
       if (dbuser==null){
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insert(user);
       }else {
           dbuser.setName(user.getName());
           dbuser.setAvatarUrl(user.getAvatarUrl());
           dbuser.setGmtCreate(System.currentTimeMillis());
           dbuser.setGmtModified(user.getGmtCreate());
           dbuser.setToken(user.getToken());
           userMapper.update(dbuser);
       }


    }


}
