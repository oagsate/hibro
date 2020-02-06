package com.oagsate.hibro.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.service.AreaService;
import com.oagsate.hibro.service.UserService;
import com.oagsate.hibro.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;

@RestController
public class AreaController {
    @Autowired
    AreaService areaService;

    @GetMapping("/api/area")
    public JsonResult retrieveSelf() throws Exception{
        List<HashMap> list=areaService.retrieve();
        return new JsonResult(list);
    }
}
