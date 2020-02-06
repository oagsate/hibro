package com.oagsate.hibro.service.impl;

import com.oagsate.hibro.mapper.AreaMapper;
import com.oagsate.hibro.mapper.ThoughtMapper;
import com.oagsate.hibro.pojo.Thought;
import com.oagsate.hibro.service.AreaService;
import com.oagsate.hibro.service.ThoughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaMapper areaMapper;

    @Override
    public List<HashMap> retrieve() {
        return areaMapper.retrieve();
    }
}
