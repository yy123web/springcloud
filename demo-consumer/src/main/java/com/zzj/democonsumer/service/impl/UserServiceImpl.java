package com.zzj.democonsumer.service.impl;

import com.jarvis.cache.annotation.Cache;
import com.jarvis.cache.annotation.CacheDelete;
import com.jarvis.cache.annotation.CacheDeleteKey;
import com.zzj.democonsumer.dao.UserDao;
import com.zzj.democonsumer.pojo.User;
import com.zzj.democonsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaozj37918
 * @date 2022年03月02日 14:39
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserDao userDao;
    @Override
    public void add(User user) {
        userDao.insert(user);
    }

    @Override
    @Cache(expire = 3600, key = "'getUser_' + #args[0]")
    public void query(int id) {
        userDao.selectById(id);
    }

    @Override
    public void queryAll() {
        userDao.selectList(null);
    }

    @Override
    public void del(int id) {
        userDao.deleteById(id);
    }

    @Override
    @CacheDelete({@CacheDeleteKey(value = "'getUser_' + #args[0].id")})
    public void edit(User user) {
        userDao.updateById(user);
    }
}
