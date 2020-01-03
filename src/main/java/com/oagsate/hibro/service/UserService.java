package com.oagsate.hibro.service;

import com.oagsate.hibro.pojo.User;

public interface UserService {
    public User get(int id);

    public void create(User user);

    public User login(User user);

    public void update(User user);
}
