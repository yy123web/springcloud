package com.zzj.democonsumer.service;

import com.zzj.democonsumer.pojo.User;

public interface UserService {
    void add(User user);
    void query(int id);
    void queryAll();
    void del(int id);
    void edit(User user);
}
