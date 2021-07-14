package com.zzj.springboot.service.impl;

import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.UserService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void queryAll() {
        List<User> users = userService.queryAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    void queryById() {
        User user = userService.queryById(1);
        System.out.println(user);
    }

    @Test
    void add() {
        User user = new User();
        user.setId(4);
        user.setName("wangwu");
        user.setPassword("123");
        userService.add(user);
    }

    @Test
    void edit() {
        User user = new User();
        user.setId(4);
        user.setName("liliu");
        userService.edit(user);
    }

    @Test
    void del() {
        userService.del(4);
    }

    @Test
    void test() throws InterruptedException {
        JSONObject blackCar;
        JSONArray blackArray = new JSONArray();
        for (int i = 0; i<3; i++) {
            blackCar = new JSONObject();
            blackCar.put("carNo", 1);
            blackCar.put("blackType", 2);
            blackCar.put("bankId", 3);
            blackArray.add(blackCar);

        }
        System.out.println(blackArray.toString());
        for (int i = 0; i < blackArray.size(); i++) {
            System.out.println(blackArray.get(i));
        }

    }

    @Test
    void login(){
        User user = new User();
        user.setId(1);
        user.setName("zhangsan");
        user.setPassword("123");
        userService.login(user);
    }
}