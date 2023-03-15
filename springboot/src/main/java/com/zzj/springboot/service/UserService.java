package com.zzj.springboot.service;

import com.github.pagehelper.PageInfo;
import com.zzj.springboot.pojo.User;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface UserService {
    List<User> queryAll();

    Integer add(User user);

    Integer edit(User user);

    Integer del(Integer id);

    User queryById(Integer id);

    String login(User user);

    PageInfo<User> getUserInfoByPage(User user);

    @Async
    public void longtime();

    String test04();
}
