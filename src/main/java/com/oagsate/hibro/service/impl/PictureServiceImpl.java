package com.oagsate.hibro.service.impl;

import com.oagsate.hibro.mapper.PictureMapper;
import com.oagsate.hibro.mapper.ThoughtMapper;
import com.oagsate.hibro.pojo.Thought;
import com.oagsate.hibro.service.PictureService;
import com.oagsate.hibro.service.ThoughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureMapper pictureMapper;

    @Override
    public void create(HashMap picture) {
        pictureMapper.create(picture);
    }

    @Override
    public List<HashMap> retrieveByUid(int uid) {
        return pictureMapper.retrieveByUid(uid);
    }

    @Override
    public List<HashMap> retrieve() {
        return  pictureMapper.retrieve();
    }
}
