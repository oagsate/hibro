package com.oagsate.hibro.service.impl;

import com.oagsate.hibro.mapper.UserMapper;
import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public HashMap get(int id) {
        return userMapper.get(id);
    }

    @Override
    public void create(User user) {
        userMapper.create(user);
    }

    @Override
    public HashMap login(User user) {
        return userMapper.login(user);
    }

    @Override
    public void update(HashMap user) {
        userMapper.update(user);
    }
}
