package com.oagsate.hibro.service;

import com.oagsate.hibro.pojo.Thought;

import java.util.HashMap;
import java.util.List;

public interface ThoughtService{
    public void create(Thought thought);
    public List<HashMap> retrieveByUid(int uid);
    public List<HashMap> retrieve();
    public int delete(Thought thought);
}