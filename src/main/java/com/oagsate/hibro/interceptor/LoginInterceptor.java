package com.oagsate.hibro.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.oagsate.hibro.pojo.User;
import com.oagsate.hibro.util.JsonResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        HttpSession session = httpServletRequest.getSession();
        if (("/api/register".equals(uri) && "POST".equals(method)) || "/api/login".equals(uri)) {
            return true;
        }
        HashMap user = (HashMap) session.getAttribute("user");
        if (user == null) {
            httpServletResponse.setStatus(200);
            JsonResult result = new JsonResult("用户未登录", 1);
            String jsonObjectString = JSONObject.toJSONString(result);
            returnJson(httpServletResponse, jsonObjectString);
            return false;
        }
        return true;
    }


    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
