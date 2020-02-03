package com.oagsate.hibro.web;

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

    @GetMapping("/api/thought/{uid}")
    public JsonResult retrieve(@PathVariable("uid") int uid) throws Exception{
        List<Thought> thoughtList=thoughtService.retrieve(uid);
        return new JsonResult(thoughtList);
    }

    @GetMapping("/api/thought/self")
    public JsonResult retrieveSelf(HttpSession session) throws Exception{
        HashMap user=(HashMap) session.getAttribute("user");
        int uid=(int) user.get("id");
        List<Thought> thoughtList=thoughtService.retrieve(uid);
        return new JsonResult(thoughtList);
    }

    @DeleteMapping("/api/thought/{id}")
    public JsonResult delete(@PathVariable("id") int id,HttpSession session) throws Exception{
        User user=(User) session.getAttribute("user");
        int uid=user.getId();
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
