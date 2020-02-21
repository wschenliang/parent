package com.rongqi.manage.service.impl;

import com.rongqi.manage.mapper.UserMapper;
import com.rongqi.manage.pojo.User;
import com.rongqi.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }
}
