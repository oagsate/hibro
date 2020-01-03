package com.oagsate.hibro.service;

import com.oagsate.hibro.pojo.Thought;

import java.util.List;

public interface ThoughtService{

    public void create(Thought thought);
    public List<Thought> retrieve(int uid);
    public int delete(Thought thought);
}