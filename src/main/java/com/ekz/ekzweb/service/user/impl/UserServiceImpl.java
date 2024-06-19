package com.ekz.ekzweb.service.user.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ekz.ekzweb.domain.user.User;
import com.ekz.ekzweb.mapper.user.UserMapper;
import com.ekz.ekzweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
}
