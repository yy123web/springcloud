package com.zzj.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzj.springboot.dao.UserDao;
import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService, Runnable {
    @Autowired(required = false)
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private JedisCluster jedisCluster;

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

    @Override
    public User queryById(Integer id) {
//        redisTemplate.delete("user_" + id);
//        User user = (User) redisTemplate.opsForValue().get("user_" + id);
//        // 如果缓存中没有，则从数据库中查询并放入缓存中
//        if (user == null) {
//            System.out.println("从数据库查询数据");
//            user = userDao.queryById(id);
//            redisTemplate.opsForValue().set("user_" + id.toString(), user);
//        }
        User user = userDao.queryById(id);
        // 返回从redis缓存中获得的数据
        return user;
    }

    @Override
    public String login(User user) {
        User isUser = userDao.login(user);
        if (isUser != null) {
            System.out.println("登录成功！");
            return "登录成功！";
        }
        System.out.println("登录失败！");
        return "登录失败！";


    }

    @Override
    public PageInfo<User> getUserInfoByPage(User user) {
        PageHelper.startPage(user.getCurrPageNo(), user.getPageSize());
        List<User> users = userDao.queryAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
//        pageInfo.setTotal(pageInfo.getTotal());
//        pageInfo.setPages(pageInfo.getPages());
        return pageInfo;
    }

    public void redisDelTest() {
        ScanParams scanParams = new ScanParams().match("rosterInfo".concat("*")).count(200);
        String cur = ScanParams.SCAN_POINTER_START;
        boolean hasNext = true;
        int count = 0;
        while (hasNext) {
            count++;
            ScanResult<String> scanResult = jedisCluster.scan(cur, scanParams); //key的正则表达式
            List<String> keys = scanResult.getResult();
            for (String key : keys) {
                jedisCluster.del(key);
            }
            cur = scanResult.getCursor();  //返回用于下次遍历的游标
            if (StringUtils.equals("0", cur)) { //说明遍历已结束
                hasNext = false;
            }
        }
    }

    @Async
    public void longtime() {
        System.out.println("我在执行一项耗时任务");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成");

    }

    @Override
    public String test04() {
        log.info("-----------------主线程开始执行-----------------");
        ThreadPoolExecutor es = new ThreadPoolExecutor(50, 50, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                log.info("创建线程: [{}]", t);
                return t;
            }
        });
        UserServiceImpl userService = new UserServiceImpl();
        es.submit(userService);
        log.info("-----------------主线程执行结束-----------------");
        return "ok";
    }

    @Override
    public void run() {
        log.info("-----------------子线程开始执行-----------------");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("-----------------子线程执行结束-----------------");
    }
}
