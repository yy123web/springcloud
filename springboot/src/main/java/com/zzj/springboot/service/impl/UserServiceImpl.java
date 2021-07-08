package com.zzj.springboot.service.impl;

import com.zzj.springboot.dao.UserDao;
import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserDao userDao;
    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }
    @Override
    @Transactional
    public Integer add(User user) {
        if (user != null) {
            return userDao.add(user);
        }
        return 0;
    }
    @Override
    @Transactional
    public Integer edit(User user) {
        return userDao.edit(user);
    }

    @Override
    @Transactional
    public Integer del(Integer id) {

        Integer i = userDao.del(id);
        return i;
    }
}
