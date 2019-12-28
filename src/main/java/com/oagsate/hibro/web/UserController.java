package com.oagsate.hibro.web;

import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.service.UserService;
import com.oagsate.hibro.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
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
}