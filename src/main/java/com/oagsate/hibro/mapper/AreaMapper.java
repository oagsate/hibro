package com.oagsate.hibro.mapper;

import com.oagsate.hibro.pojo.Thought;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AreaMapper {
    public List<HashMap> retrieve();
}
