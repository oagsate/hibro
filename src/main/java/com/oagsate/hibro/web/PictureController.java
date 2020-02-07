package com.oagsate.hibro.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oagsate.hibro.pojo.Thought;
import com.oagsate.hibro.service.PictureService;
import com.oagsate.hibro.service.ThoughtService;
import com.oagsate.hibro.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;

@RestController
public class PictureController {
    @Autowired
    PictureService pictureService;

    @PostMapping("/api/picture")
    public JsonResult create(@RequestParam("file") MultipartFile file,@RequestParam("name") String name, HttpSession session) throws Exception{
        JsonResult result=new JsonResult();
        HashMap current=(HashMap) session.getAttribute("user");
        int id=(int) current.get("id");
        try {
            long createTime=System.currentTimeMillis();
            String fileName = createTime+file.getOriginalFilename();
            String fileDir="src/main/resources/static/picture/" + id;
            String absoluteDir=new File(fileDir).getAbsolutePath();
            String destFileName=absoluteDir+ File.separator + fileName;
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);

            HashMap picture=new HashMap();
            picture.put("uid",id);
            picture.put("uri","static/picture/" +id+"/"+fileName);
            picture.put("createTime",Math.floor(createTime/1000));
            picture.put("name",name);
            pictureService.create(picture);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(0);
            result.setErrmsg("上传失败");
        }
        return result;
    }

    @GetMapping("/api/picture/{uid}")
    public JsonResult retrieveByUid(@PathVariable("uid") int uid,@RequestParam(value = "current") int current,@RequestParam(value = "pageSize") int pageSize,HttpSession session) throws Exception{
        if(-1==uid){
            HashMap user=(HashMap) session.getAttribute("user");
            uid=(int) user.get("id");
        }
        PageHelper.startPage(current, pageSize,"picture.id desc");
        List<HashMap> list=pictureService.retrieveByUid(uid);
        PageInfo<HashMap> page = new PageInfo<>(list);
        return new JsonResult(page);
    }

    @GetMapping("/api/picture")
    public JsonResult retrieve(@RequestParam(value = "current") int current,@RequestParam(value = "pageSize") int pageSize,HttpSession session) throws Exception{
        PageHelper.startPage(current, pageSize,"picture.id desc");
        List<HashMap> list=pictureService.retrieve();
        PageInfo<HashMap> page = new PageInfo<>(list);
        return new JsonResult(page);
    }
}
