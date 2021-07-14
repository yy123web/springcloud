package com.zzj.springboot.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}