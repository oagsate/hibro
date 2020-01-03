package com.oagsate.hibro.web;

import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.service.UserService;
import com.oagsate.hibro.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/user/{id}")
    public User get(@PathVariable("id") int id) throws Exception{
        User user=userService.get(id);
        return user;
    }

    @PostMapping("/api/user")
    public JsonResult create(@RequestBody User user) throws Exception{
        userService.create(user);
        JsonResult result=new JsonResult();
        return result;
    }

    @PostMapping("/api/login")
    public JsonResult login(@RequestBody User user, HttpSession session) throws Exception{
        User u=userService.login(user);
        if(null==u){
            return new JsonResult("账号或密码错误",2);
        }
        session.setAttribute("user",u);
        return new JsonResult(u);
    }

    @PutMapping("/api/user")
    public JsonResult update(@RequestBody User user) throws Exception{
        userService.update(user);
        return new JsonResult();
    }
}
