package com.zzj.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl helloService;

    //查询
    @RequestMapping(value = "/hi")
//    @RequestParam String name
    public String Hello() {
        List<User> users = helloService.queryAll();
        JSONObject obj = new JSONObject();
        obj.put("data", users);
        return obj.toString();
    }

    //查询
    @RequestMapping(value = "/query")
//    @RequestParam String name
    public String queryById(@RequestParam Integer id) {
        User user = helloService.queryById(id);
        JSONObject obj = new JSONObject();
        obj.put("data", user);
        return obj.toString();
    }
    //登录
    @RequestMapping(value = "login")
    public String login(User user){
        return helloService.login(user);
    }


    //增加
    @RequestMapping(value = "/add")
    public String add(User user) {
        user.setId(4);
        user.setName("liliu");
        user.setPassword("123");
        Integer i = helloService.add(user);
        if (i > 0) {
            return "添加成功！";
        }
        return "添加失败！";

    }

    //修改
    @RequestMapping(value = "/edit")
    public String edit(User user) {
        Integer i = helloService.edit(user);
        if (i > 0) {
            return "修改成功！";
        }
        return "修改失败！";
    }

    //删除
    @RequestMapping(value = "/del")
    public String del(@RequestParam Integer id) {
        Integer i = helloService.del(id);
        if (i > 0) {
            return "删除成功！";
        }
        return "删除失败！";
    }

    @RequestMapping("/json6")
    @ResponseBody
    public String json6() throws Exception {
        List<User> userList = new ArrayList<User>();
        User user1 = new User(01,"zhangsan","123");
        User user2 = new User(02,"lisi","123");
        User user3 = new User(03,"wangwu","123");
        User user4 = new User(04,"liliu","123");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        System.out.println("user1: "+user1);
        System.out.println("****Java对象 转 JSON字符串****");
        String str1 = JSON.toJSONString(userList);
        System.out.println("str1: "+str1);
        String str2 = JSON.toJSONString(user1);
        Map map = JSONObject.parseObject(JSONObject.toJSONString(user1), Map.class);
        System.out.println("map: "+map);
        System.out.println("str2: "+str2);

        System.out.println("****JSON字符串 转 Java对象****");
        User jp_user1 = JSON.parseObject(str2, User.class);
        System.out.println("jp_user1: "+jp_user1);

        System.out.println("****Java对象 转 JSON对象****");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println(jsonObject1.getString("name"));

        System.out.println("****JSON对象 转 Java对象****");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println("to_java_user: "+to_java_user);

        return "Hello";
    }
}
