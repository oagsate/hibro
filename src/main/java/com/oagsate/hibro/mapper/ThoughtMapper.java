package com.oagsate.hibro.mapper;

import com.oagsate.hibro.pojo.Thought;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ThoughtMapper {
    public void create(Thought thought);
    public List<Thought> retrieve(int uid);
    public int delete(Thought thought);
}
