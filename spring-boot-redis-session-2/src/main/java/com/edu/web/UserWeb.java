package com.edu.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.anno.AuthToken;
import com.edu.bean.User;
import com.edu.hb.repository.UserDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserWeb {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/userList")
    @AuthToken(hasRole = { "admin", "Administrator" })
    public String userList (HttpServletRequest request){
        
        return "userList";
    }

    @RequestMapping(value = "/userListData")
    @ResponseBody
    public Object getSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("result", 1);
        map.put("data", userDao.findAll());
        return map;
    }

}