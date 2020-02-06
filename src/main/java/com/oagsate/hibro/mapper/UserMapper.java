package com.oagsate.hibro.mapper;

import com.oagsate.hibro.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {
    public HashMap get(int id);

    public void create(User user);

    public HashMap login(User user);

    public void update(HashMap user);
}
