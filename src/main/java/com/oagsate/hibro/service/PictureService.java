package com.oagsate.hibro.service;

import com.oagsate.hibro.pojo.Thought;

import java.util.HashMap;
import java.util.List;

public interface PictureService {
    public void create(HashMap picture);
    public List<HashMap> retrieveByUid(int uid);
    public List<HashMap> retrieve();
}