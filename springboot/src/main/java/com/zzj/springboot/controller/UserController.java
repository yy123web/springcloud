package com.zzj.springboot.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.activiti.engine.impl.util.json.JSONObject;
import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
