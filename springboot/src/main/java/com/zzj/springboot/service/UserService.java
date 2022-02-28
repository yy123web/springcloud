package com.zzj.springboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzj.springboot.pojo.User;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    List<User> queryAll();

    Integer add(User user);

    Integer edit(User user);

    Integer del(Integer id);

    User queryById(Integer id);

    String login(User user);
    @Async
    public void longtime();
}
