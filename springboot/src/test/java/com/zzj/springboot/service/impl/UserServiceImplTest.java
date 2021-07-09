package com.zzj.springboot.service.impl;

import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

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
}