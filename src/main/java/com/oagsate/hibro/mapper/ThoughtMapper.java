package com.oagsate.hibro.mapper;

import com.oagsate.hibro.pojo.Thought;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThoughtMapper {
    public void create(Thought thought);
}
