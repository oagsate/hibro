package com.oagsate.hibro.web;

import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public User get(@PathVariable("id") int id) throws Exception{
        User user=userService.get(id);
        return user;
    }
}