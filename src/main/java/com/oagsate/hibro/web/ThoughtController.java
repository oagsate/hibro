package com.oagsate.hibro.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oagsate.hibro.pojo.Thought;
import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.service.ThoughtService;
import com.oagsate.hibro.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
public class ThoughtController {
    @Autowired
    ThoughtService thoughtService;

    @PostMapping("/api/thought")
    public JsonResult create(@RequestBody Thought thought, HttpSession session) throws Exception{
        HashMap user=(HashMap) session.getAttribute("user");
        int uid=(int) user.get("id");
        long createTime=(long) Math.floor(System.currentTimeMillis()/1000);
        thought.setUid(uid);
        thought.setCreateTime(createTime);
        thoughtService.create(thought);
        return new JsonResult();
    }

    @GetMapping("/api/thought")
    public JsonResult retrieve(@RequestParam(value = "start") int start,@RequestParam(value = "size") int size) throws Exception{
        PageHelper.startPage(start, size,"thought.id desc");
        List<HashMap> thoughtList=thoughtService.retrieve();
        PageInfo<HashMap> page = new PageInfo<>(thoughtList);
        return new JsonResult(page);
    }

    @GetMapping("/api/thought/{uid}")
    public JsonResult retrieveByUid(@PathVariable("uid") int uid,@RequestParam(value = "start") int start,@RequestParam(value = "size") int size,HttpSession session) throws Exception{
        if(-1==uid){
            HashMap user=(HashMap) session.getAttribute("user");
            uid=(int) user.get("id");
        }
        PageHelper.startPage(start, size,"thought.id desc");
        List<HashMap> thoughtList=thoughtService.retrieveByUid(uid);
        PageInfo<HashMap> page = new PageInfo<>(thoughtList);
        return new JsonResult(page);
    }

    @DeleteMapping("/api/thought/{id}")
    public JsonResult delete(@PathVariable("id") int id,HttpSession session) throws Exception{
        HashMap user=(HashMap) session.getAttribute("user");
        int uid=(int) user.get("id");
        Thought thought =new Thought();
        thought.setId(id);
        thought.setUid(uid);
        int deleteCount = thoughtService.delete(thought);
        if(1!=deleteCount){
            return new JsonResult("删除失败",3);
        }
        return new JsonResult();
    }
}
