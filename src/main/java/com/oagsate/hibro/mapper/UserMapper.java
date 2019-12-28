package com.oagsate.hibro.mapper;

import com.oagsate.hibro.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User get(int id);

    public void create(User user);
}
