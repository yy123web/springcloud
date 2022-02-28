package com.zzj.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zhaozj37918
 * @date 2021年07月23日 9:23
 */
public class Test3 {
    @Autowired
    private UserController userController;
//    public static void main(String[] args) {
//        f();
//    }

    private static void f() {
        System.out.println("输入两个浮点数：");
        double a = new Scanner(System.in).nextDouble();
        double b = new Scanner(System.in).nextDouble();
        try {
            double r = divide(a, b);
            System.out.println(r);
        } catch (ArithmeticException e) {
            System.out.println("不能除零，是我们的错，请鞭笞我们吧！");
            e.printStackTrace();
        }
    }

    private static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return a / b;
    }


    public static JSONObject getObj(){
        Map map = new HashMap<>();
        map.put("1","zhangsan");
        map.put("2","lisi");
        JSONObject obj1 = new JSONObject();
        obj1.put("1","zhangsan");
        obj1.put("2","lisi");


        JSONObject obj = new JSONObject();
        obj.put("test1","test1");
        obj.put("obj1",obj1);
        obj.put("test2","test2");
        obj.put("map",map);
        return obj;
    }
    @Test
    public void Test(){
        JSONObject json = getObj();
        String test1 = json.getString("test1");
        String test2 = json.getString("test2");

        JSONObject obj2 = json.getJSONObject("obj1");
        String json1 = obj2.getString("1");
        String json2 = obj2.getString("2");

        JSONObject map = json.getJSONObject("map");
        String map1 = map.getString("1");
        String map2 = map.getString("2");



        System.out.println(test1);
        System.out.println(test2);

        System.out.println(json1);
        System.out.println(json2);

        System.out.println(map1);
        System.out.println(map2);

    }

}
