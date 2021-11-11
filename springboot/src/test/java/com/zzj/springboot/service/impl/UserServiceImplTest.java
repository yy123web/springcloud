package com.zzj.springboot.service.impl;

import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.UserService;
import com.zzj.springboot.util.BytesUtil;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.commands.MultiKeyCommands;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;


    @Autowired(required = false)
    private JedisCluster jedisCluster;

    @Test
    void queryAll() {
        List<User> users = userService.queryAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    void queryById() {
        User user = userService.queryById(1);
        System.out.println(user);
    }

    @Test
    void add() {
        User user = new User();
        user.setId(4);
        user.setName("wangwu");
        user.setPassword("123");
        userService.add(user);
    }

    @Test
    void edit() {
        User user = new User();
        user.setId(4);
        user.setName("liliu");
        userService.edit(user);
    }

    @Test
    void del() {
        userService.del(4);
    }

    @Test
    void test() throws InterruptedException {
        JSONObject blackCar;
        JSONArray blackArray = new JSONArray();
        for (int i = 0; i < 3; i++) {
            blackCar = new JSONObject();
            blackCar.put("carNo", 1);
            blackCar.put("blackType", 2);
            blackCar.put("bankId", 3);
            blackArray.add(blackCar);

        }
        System.out.println(blackArray.toString());
        for (int i = 0; i < blackArray.size(); i++) {
            System.out.println(blackArray.get(i));
        }

    }

    @Test
    void login() {
        User user = new User();
        user.setId(1);
        user.setName("zhangsan");
        user.setPassword("123");
        userService.login(user);
    }


    @Test
    public void redisDel() {
        Jedis jedis = new Jedis();
        ScanParams scanParams = new ScanParams();
        scanParams.match("*" + "redis_" + "*");
        scanParams.count(10);
        String cur = "0";
        boolean hasNext = true;
        try {
            while (hasNext) {
                ScanResult<String> scanResult = jedis.scan(cur, scanParams);
                List<String> keys = scanResult.getResult();
                System.out.println(scanResult.getResult());
                for (String key : keys) {
                    System.out.println(jedis.get(key));
                    System.out.println(jedis.exists(key));
                    jedis.del(key);
                }
                cur = scanResult.getCursor();  //返回用于下次遍历的游标
                if (StringUtils.equals("0", cur)) { //说明遍历已结束
                    hasNext = false;
                }
            }
        } catch (Exception e) {

        } finally {
            jedis.close();
        }
    }


    @Test
    public void getByScan() {
        Jedis jedis = new Jedis();
        // 去重
        Set<String> retSet = new HashSet<>();
        String scanRet = "0";
        try {
            do {
                ScanParams scanParams = new ScanParams();
                scanParams.match("*" + "user_" + "*");
                // 可有可无的设置
                scanParams.count(20);
                ScanResult<String> scanResult = jedis.scan(scanRet, scanParams);
                scanRet = scanResult.getCursor();
                retSet.addAll(scanResult.getResult());
                // 游标值为0表示遍历结束
            } while (!"0".equals(scanRet));
        } catch (Exception e) {
        } finally {
            jedis.close();
        }
        System.out.println(retSet.toString());
    }


    @Test
    public void redisDelTest() {
        ScanParams scanParams = new ScanParams().match("*" + "test_" + "*").count(200);
        String cur = ScanParams.SCAN_POINTER_START;
        boolean hasNext = true;
        int count = 0;
        Jedis jedis = new Jedis();
        while (hasNext) {
            count++;
            ScanResult<String> scanResult = jedis.scan(cur, scanParams); //key的正则表达式
            List<String> keys = scanResult.getResult();
            for (String key : keys) {
                System.out.println(jedis.get("\\xac\\xed\\x00\\x05t\\x00\\x06test_1"));
                jedis.del(key);
            }
            cur = scanResult.getCursor();  //返回用于下次遍历的游标
            if (StringUtils.equals("0", cur)) { //说明遍历已结束
                hasNext = false;
            }
        }
    }


    @Test
    public void fileImport() {
        byte[] splitChars = BytesUtil.hexStringToBytes("2C");
        String separatorString = new String(splitChars);
        try {
            File file = new File("C:\\Users\\hspcadmin\\Desktop\\test.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    StringBuffer sb = new StringBuffer();
                    sb.append(separatorString);
                    StringBuilder stringBuilder = new StringBuilder(line);
                    Pattern pattern = Pattern.compile("[0-9],[0-9]");
                    Matcher matcher = pattern.matcher(stringBuilder);
                    while (matcher.find()) {
                        stringBuilder.replace(matcher.start() + 1, matcher.end() - 1, ";");
                    }
                    line = stringBuilder.toString().replace("\"", "").replace(" ", "");
                    Charset gbk = Charset.forName("GBK");
                    String[] s = line.split(separatorString, -1);
                    System.out.println(s.length);
                    System.out.println(line);

                    //一行数据 通过分隔符分割 成数组  在转成list
                    List<String> list = Arrays.asList(line.split(String.valueOf((sb))));
                    User user = new User();
                    user.setName(list.get(0));
                    user.setPassword(list.get(1));
                    userService.add(user);
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }

    }

    @Test
    public void test02() {
        String a = "\"aaa\",\"bbbbb\",,,\"1234,5678\",\"4321,8765\"";
        StringBuilder stringBuilder = new StringBuilder(a);
        Pattern pattern = Pattern.compile("[0-9],[0-9]");
        Matcher matcher = pattern.matcher(stringBuilder);
        while (matcher.find()) {
            stringBuilder.replace(matcher.start() + 1, matcher.end() - 1, ";");
        }
        System.out.println("b = " + stringBuilder.toString());
    }
}