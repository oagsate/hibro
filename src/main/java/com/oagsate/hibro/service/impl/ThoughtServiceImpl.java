package com.oagsate.hibro.service.impl;

import com.oagsate.hibro.mapper.ThoughtMapper;
import com.oagsate.hibro.pojo.Thought;
import com.oagsate.hibro.service.ThoughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThoughtServiceImpl implements ThoughtService {
    @Autowired
    ThoughtMapper thoughtMapper;

    @Override
    public void create(Thought thought) {
        thoughtMapper.create(thought);
    }

    @Override
    public List<Thought> retrieve(int uid) {
        return thoughtMapper.retrieve(uid);
    }

    @Override
    public int delete(Thought thought) {
        return thoughtMapper.delete(thought);
    }
}
