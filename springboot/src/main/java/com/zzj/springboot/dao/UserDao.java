package com.zzj.springboot.dao;

import com.zzj.springboot.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> queryAll();

    Integer add(User user);

    Integer edit(User user);

    Integer del(Integer id);
}
