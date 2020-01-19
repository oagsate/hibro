package com.oagsate.hibro.web;

import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.service.UserService;
import com.oagsate.hibro.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/user/{id}")
    public User get(@PathVariable("id") int id) throws Exception{
        User user=userService.get(id);
        return user;
    }

    @GetMapping("/api/user/self")
    public JsonResult getSelf(HttpSession session) throws Exception{
        User user = (User) session.getAttribute("user");
        return new JsonResult(user);
    }

    @PostMapping("/api/user")
    public JsonResult create(@RequestBody User user) throws Exception{
        userService.create(user);
        JsonResult result=new JsonResult();
        return result;
    }

    @PostMapping("/api/register")
    public JsonResult register(@RequestBody User user, HttpSession session) throws Exception{
        JsonResult result=new JsonResult();
        long createTime=(long) Math.floor(System.currentTimeMillis()/1000);
        user.setCreateTime(createTime);
        try {
            userService.create(user);
            User u=userService.login(user);
            if(null==u){
                return new JsonResult("账号或密码错误",2);
            }
            session.setAttribute("user",u);
            result.setData(u);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(0);
            result.setErrmsg("用户名或邮箱已存在");
        }
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

    @PostMapping("/api/user/uploadAvatar")
    public JsonResult uploadAvatar(HttpServletRequest req, @RequestParam("file") MultipartFile file) throws Exception{
        JsonResult result=new JsonResult();
        try {
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            String destFileName=req.getServletContext().getRealPath("")+"uploaded"+ File.separator+fileName;
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(0);
        }
        return result;
    }
}
