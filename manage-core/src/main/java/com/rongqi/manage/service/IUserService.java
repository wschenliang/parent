package com.rongqi.manage.service;

import com.rongqi.manage.pojo.User;

public interface IUserService {
    User findByAccount(String account);
}
