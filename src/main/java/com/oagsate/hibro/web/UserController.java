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
import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/user/{id}")
    public JsonResult get(@PathVariable("id") int id,HttpSession session) throws Exception{
        if(-1==id){
            HashMap user = (HashMap) session.getAttribute("user");
            id=(int) user.get("id");
        }
        HashMap user=userService.get(id);
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
            HashMap u=userService.login(user);
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
        HashMap u=userService.login(user);
        if(null==u){
            return new JsonResult("账号或密码错误",2);
        }
        session.setAttribute("user",u);
        long lastTime=(long) Math.floor(System.currentTimeMillis()/1000);
        HashMap refresher=new HashMap();
        refresher.put("id", u.get("id"));
        refresher.put("lastTime",lastTime);
        userService.update(refresher);
        return new JsonResult(u);
    }

    @PostMapping("/api/logout")
    public JsonResult logout(HttpSession session) throws Exception{
        session.removeAttribute("user");
        return new JsonResult();
    }

    @PutMapping("/api/user")
    public JsonResult update(@RequestBody HashMap<String,Object> user, HttpSession session) throws Exception{
        HashMap self = (HashMap) session.getAttribute("user");
        int id=(int) self.get("id");
        user.put("id",id);
        userService.update(user);
        return new JsonResult();
    }

    @PostMapping("/api/user/uploadAvatar")
    public JsonResult uploadAvatar(@RequestParam("file") MultipartFile file,HttpSession session) throws Exception{
        JsonResult result=new JsonResult();
        HashMap current=(HashMap) session.getAttribute("user");
        int id=(int) current.get("id");
        try {
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            String fileDir="src/main/resources/static/avatar/" + id;
            String absoluteDir=new File(fileDir).getAbsolutePath();
            File folder=new File(absoluteDir);
            deleteFolder(folder);
            String destFileName=absoluteDir+ File.separator + fileName;
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);

            HashMap u=new HashMap();
            u.put("id",id);
            u.put("avatar","static/avatar/" +id+"/"+fileName);
            userService.update(u);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(0);
            result.setErrmsg("上传失败");
        }
        return result;
    }

    private void deleteFolder(File folder){
        File[] files = folder.listFiles();
        if(null!=files){
            for(File f:files){
                if(f.isDirectory()){
                    deleteFolder(f);
                }else{
                    f.delete();
                }
            }
        }
    }
}
