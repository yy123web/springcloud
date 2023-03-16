package com.zzj.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jarvis.cache.annotation.Cache;
import com.jarvis.cache.annotation.CacheDelete;
import com.jarvis.cache.annotation.CacheDeleteKey;
import com.zzj.springboot.pojo.User;

import java.util.List;

public interface UserDao extends BaseMapper<User> {
    List<User> queryAll();

    Integer add(User user);

    @CacheDelete({@CacheDeleteKey(value = "'getUser_' + #args[0].id")})
    Integer edit(User user);

    Integer del(Integer id);

    @Cache(expire = 3600, key = "'getUser_' + #args[0]")
    User queryById(Integer id);

    User login(User user);

    User findByCondition(User user);
}
