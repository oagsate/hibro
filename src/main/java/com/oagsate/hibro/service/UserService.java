package com.oagsate.hibro.service;

import com.oagsate.hibro.pojo.User;

import java.util.HashMap;

public interface UserService {
    public HashMap get(int id);

    public void create(User user);

    public HashMap login(User user);

    public void update(User user);
}
