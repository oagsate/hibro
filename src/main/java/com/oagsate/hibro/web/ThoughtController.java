package com.oagsate.hibro.web;

import com.oagsate.hibro.pojo.Thought;
import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.service.ThoughtService;
import com.oagsate.hibro.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ThoughtController {
    @Autowired
    ThoughtService thoughtService;

    @PostMapping
    public JsonResult create(@RequestBody Thought thought, HttpServletRequest request){
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        int uid=user.getId();
        thought.setUid(uid);
        thoughtService.create(thought);
        return new JsonResult();
    }
}
