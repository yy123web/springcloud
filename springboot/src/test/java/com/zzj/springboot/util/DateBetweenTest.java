package com.zzj.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzj.springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class DateBetweenTest {


    @Test
    void between_time() throws Exception {
        String startStr = "20170901";
        String endStr = "20210713";
        try {
            DateBetween.between_time(startStr, endStr);
        } catch (Exception e) {
            throw new Exception("获得两个格式时间间的小时差出错", e);
        }

    }

    @Test
    void test() {
        String words = "I am a handsome boy and you are a beautiful girl , so we can marry , then make love , finally create new lives . I believe certainly "
                + "that our son will be a handsome boy and daughter will be a beautiful girl too , because I am a handsome boy and you are a beautiful girl .";
        Map<String, Integer> counts = new HashMap<>();
        Scanner in = new Scanner(words);
        String word = null;
        while (in.hasNext()) {
            word = in.next();
            if (counts.get(word) == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, counts.get(word) + 1);
            }
        }
        in.close();
        System.out.println(counts);
    }

    @Test
    public void test12() {
        User user = new User();
        user.setId(1);
        user.setName("http");
        user.setPassword("123");
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.postForObject("http://127.0.0.1:8080/httpTest", user, String.class));
    }
    @Test
    public void test13() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("主机名："+localHost.getHostName());
            System.out.println("本地ip地址："+localHost.getHostAddress());
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            String localAddr = request.getLocalAddr();
            int serverPort = request.getServerPort();
            System.out.println("http://"+localAddr +":"+ serverPort);
        } catch (Exception e) {

        }

    }
    @Test
    public void test14() {}
}