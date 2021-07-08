package com.zzj.springboot.service;

import com.zzj.springboot.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();

    Integer add(User user);

    Integer edit(User user);

    Integer del(Integer id);
}
