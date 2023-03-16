package com.zzj.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jarvis.cache.lock.ILock;
import com.zzj.springboot.dao.ProtocolInfoDao;
import com.zzj.springboot.dao.UserDao;
import com.zzj.springboot.handler.DistributedLockHandler;
import com.zzj.springboot.pojo.Lock;
import com.zzj.springboot.pojo.Param;
import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.UserService;
import com.zzj.springboot.util.HttpUtil;
import com.zzj.springboot.util.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ILock lock;
    @Autowired(required = false)
    private ProtocolInfoDao protocolInfoDao;
    @Autowired(required = false)
    private UserDao userDao;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private DistributedLockHandler distributedLockHandler;

    //    public static void main(String[] args) throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setHost("localhost");
//        //建立到代理服务器到连接
//        Connection conn = factory.newConnection();
//        //获得信道
//        final Channel channel = conn.createChannel();
//        //声明交换器
//        String exchangeName = "hello-exchange";
//        channel.exchangeDeclare(exchangeName, "direct", true);
//        //声明队列
//        String queueName = channel.queueDeclare().getQueue();
//        String routingKey1 = "hola1";
//        String routingKey2 = "hola2";
//        //绑定队列，通过键 hola 将队列和交换器绑定起来
//        channel.queueBind(queueName, exchangeName, routingKey1);
//        channel.queueBind(queueName, exchangeName, routingKey2);
//
//        while(true) {
//            //消费消息
//            boolean autoAck = false;
//            String consumerTag = "";
//            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag,
//                                           Envelope envelope,
//                                           AMQP.BasicProperties properties,
//                                           byte[] body) throws IOException {
//                    String routingKey = envelope.getRoutingKey();
//                    String contentType = properties.getContentType();
//                    System.out.println("消费的路由键：" + routingKey);
//                    System.out.println("消费的内容类型：" + contentType);
//                    long deliveryTag = envelope.getDeliveryTag();
//                    //确认消息
//                    channel.basicAck(deliveryTag, false);
//                    System.out.println("消费的消息体内容：");
//                    String bodyStr = new String(body, "UTF-8");
//                    System.out.println(bodyStr);
//
//                }
//            });
//        }
//    }
    public static void main(String[] args) {
        int x;
        try {
            System.out.println("请输入楼层：");
            Scanner input = new Scanner(System.in);
            x = input.nextInt();
            if (x < 3 || x > 20) {
                System.out.println("楼层输入有误！");
                return;
            }
            System.out.println("你选择的楼层为" + x + "层");
        } catch (Exception e) {
            log.info("e", e);
            System.out.println("楼层输入有误！");
            return;
        }
        double time1;
        double time2;
        // 3号电梯
        time2 = (x - 1) * 0.5 + (x - 1) * 3 + 10;
        // 2号电梯：偶数楼层
        if (x % 2 == 0) {
            time1 = (x - 1) * 0.5 + (x / 2 - 1) * 5 + 15;
            if (time1 > time2) {
                System.out.println("乘坐3号电梯时间最短，时间为：" + time2 + "秒");
            } else {
                System.out.println("乘坐2号电梯时间最短，时间为：" + time1 + "秒");
            }
        } else {
            // 1号电梯：奇数楼层
            time1 = (x - 1) * 0.5 + ((x - 1) / 2 - 1) * 5 + 15;
            if (time1 > time2) {
                System.out.println("乘坐3号电梯时间最短，时间为：" + time2 + "秒");
            } else {
                System.out.println("乘坐1号电梯时间最短，时间为：" + time1 + "秒");
            }
        }
    }

    //查询
    @RequestMapping(value = "/hi")
//    @RequestParam String name
    public String Hello() {
        List<User> users = userService.queryAll();
        JSONObject obj = new JSONObject();
        obj.put("data", users);
        return obj.toString();
    }

    //查询
    @RequestMapping(value = "/getUserInfoByPage")
//    @RequestParam String name
    public PageInfo<User> getUserInfoByPage(@RequestBody User user) {
        PageInfo<User> userInfoByPage = userService.getUserInfoByPage(user);
        return userInfoByPage;
    }

    //查询
    @RequestMapping(value = "/query")
//    @RequestParam String name
    public String queryById(@RequestParam Integer id) {
        User user = userService.queryById(id);
        JSONObject obj = new JSONObject();
        obj.put("data", user);
        return obj.toString();
    }

    //登录
    @RequestMapping(value = "login")
    public String login(User user) {
        return userService.login(user);
    }

    // 测试
    @RequestMapping(value = "/paramTest")
    public void test(@RequestBody Param param) {
        System.out.println(param.getParam1());
        System.out.println(param);
    }

    //增加
    @RequestMapping(value = "/add")
    public String add(User user) {
        user.setId(4);
        user.setName("liliu");
        user.setPassword("123");
        Integer i = userService.add(user);
        if (i > 0) {
            return "添加成功！";
        }
        return "添加失败！";

    }

    //修改
    @RequestMapping(value = "/edit")
    public String edit(User user) {
        Integer i = userService.edit(user);
        if (i > 0) {
            return "修改成功！";
        }
        return "修改失败！";
    }

//    @RequestMapping("/json6")
//    @ResponseBody
//    public String json6() throws Exception {
//        List<User> userList = new ArrayList<User>();
//        User user1 = new User(01, "zhangsan", "123");
//        User user2 = new User(02, "lisi", "123");
//        User user3 = new User(03, "wangwu", "123");
//        User user4 = new User(04, "liliu", "123");
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);
//        userList.add(user4);
//
//        System.out.println("****Java对象 转 JSON字符串****");
//        String str1 = JSON.toJSONString(userList);
//        System.out.println(str1);
//        String str2 = JSON.toJSONString(user1);
//        Map map = JSONObject.parseObject(JSONObject.toJSONString(user1), Map.class);
//        System.out.println("map: " + map);
//        System.out.println("str2: " + str2);
//
//        System.out.println("****JSON字符串 转 Java对象****");
//        User jp_user1 = JSON.parseObject(str2, User.class);
//        System.out.println("jp_user1: " + jp_user1);
//
//        System.out.println("****Java对象 转 JSON对象****");
//        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
//        System.out.println(jsonObject1.getString("name"));
//
//        System.out.println("****JSON对象 转 Java对象****");
//        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
//        System.out.println("to_java_user: " + to_java_user);
//
//        return "Hello";
//    }

    //删除
    @RequestMapping(value = "/del")
    public String del(@RequestParam Integer id) {
        Integer i = userService.del(id);
        if (i > 0) {
            return "删除成功！";
        }
        return "删除失败！";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String getMap() {
        ArrayList<Object> al = new ArrayList<>();
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        String letters[] = {"a", "b", "c", "d", "e"};
        for (int i = 0; i < letters.length; i++) {
            map.put(letters[i], i + 1);

        }
        al.add(map);

        LinkedHashMap<String, Object> map1 = (LinkedHashMap<String, Object>) al.get(0);

        Set<Map.Entry<String, Object>> set = map1.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            if (entry.getKey().equals("a")) {
                Object value = entry.getValue();
                System.out.println(value);
                return value.toString();
            }
        }
        return map.toString();
    }

//    @RequestMapping("/test18")
//    public void test18() throws InterruptedException {
//        ILock lock = new
//        String key = "test123";
//        boolean b = lock.tryLock(key, 1000 * 200);
//        log.info("{}",b);
//        log.info("等待120秒");
//        Thread.sleep(120 * 1000);
//        lock.unlock(key);
//    }
//    @RequestMapping("/test19")
//    public void test19() {
//        String key = "test123";
//        boolean b = lock.tryLock(key, 1000 * 120);
//        log.info("{}",b);
//
//    }

    /**
     * @return double
     * @author jitwxs
     * @date 2021/7/22
     */
    @RequestMapping("/cal")
    @ResponseBody
    public double cal() {

        System.out.println("请输入a的值：");
        Scanner str1 = new Scanner(System.in);   //键入a的值并存入String类型变量str1中
        int a = Integer.valueOf(str1.nextLine());  /*nextline()方法读取a的值并通过valueOf()方法
		将String类型的数据转换为int型数据*/
        System.out.println("请输入b的值：");   //键入b的值并存入String类型变量str2中
        Scanner str2 = new Scanner(System.in);
        int b = Integer.valueOf(str2.nextLine());
        int c = 0;
        try {     //try代码块处理可能出现的异常信息
            c = a / b;
        } catch (Exception e) {
//            System.out.println("除数b不能为0，请重新输入b的值：");
//            Scanner str3 = new Scanner(System.in);
//            int b2 = Integer.valueOf(str3.nextLine());
//            c = a / b2;
            e.printStackTrace();
        }

        System.out.println("a = " + a);
        System.out.println("b = " + a);
        System.out.println("c = " + c);
        System.out.println("除数b不能为0，请重新输入b的值：");
        Scanner str3 = new Scanner(System.in);
        int b2 = Integer.valueOf(str3.nextLine());
        c = a / b2;
        return c;
    }

    @RequestMapping(value = "/synTest1")
//    @RequestParam String name
    public String synTest1() {
        System.out.println("主线程执行开始......");
        log.debug("主线程执行开始......");
        userService.longtime();
//        doAsyncMethod();
//        doAsyncMethod();
        System.out.println("主线程执行结束......");
        log.debug("主线程执行结束......");
        return "SUCCESS";
    }

    @RequestMapping(value = "/synTest2")
    public void synTest2() throws Exception {
        System.out.println("main函数开始执行");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("===task start===");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===task finish===");
            }
        });

        thread.start();
        System.out.println("main函数执行结束");

    }


//    @RequestMapping(value = "/queryByRedis")
////    @RequestParam String name
//    public String queryBy(@RequestParam Integer id) {
//        userDao.selectOne("")
//    }


    //增加
    @RequestMapping(value = "/httpTest02")
    public User httpTest02(@RequestBody User user) {
        System.out.println(user);
        user.setPassword("123");
        return user;
    }

    @RequestMapping(value = "/httpTest01")
    public void httpTest01() {
        User user = new User();
        user.setId(1);
        user.setName("http");
        User user02 = restTemplate.postForObject("http://127.0.0.1:8080/httpTest02", user, User.class);
        System.out.println(user02.getPassword());
    }

    @RequestMapping(value = "/test02")
    public void test02() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String localAddr = request.getLocalAddr();
        String serverPort = String.valueOf(request.getServerPort());
        String url = "http://" + localAddr + ":" + serverPort + "/httpTest02";
        System.out.println(url);
        User user = new User();
        user.setId(1);
        user.setName("http");
        String resp = HttpUtil.postJson(url, null, null, JSON.toJSONString(user));
        User user02 = JSON.parseObject(resp, User.class);
        System.out.println(user02);
    }

    @RequestMapping(value = "/test03")
    public void test03() {
        System.out.println(serverConfig.getUrl());
    }

    @RequestMapping(value = "/test04")
    public String test04() {
        return userService.test04();
    }

    @RequestMapping("index")
    public String index() {
        Lock lock = new Lock("lynn", "min");
        if (distributedLockHandler.tryLock(lock)) {
            try {
                //为了演示锁的效果，这里睡眠5000毫秒
                System.out.println("获取锁成功，开始执行方法index");
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                distributedLockHandler.releaseLock(lock);
                System.out.println("index锁释放掉了");
            }
        } else {
            log.error("获取index锁失败");
            return "获取index锁失败";
        }
        return "hello world!";
    }

    @RequestMapping("index1")
    public String index1() {
        Lock lock = new Lock("lynn", "min");
        if (distributedLockHandler.tryLock(lock, 3 * 100L, 30L, 30 * 1000L)) {
            try {
                //为了演示锁的效果，这里睡眠5000毫秒
                System.out.println("获取锁成功，开始执行方法index1");
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                distributedLockHandler.releaseLock(lock);
                System.out.println("index1锁释放掉了");
            }
        } else {
            log.error("获取index1锁失败");
            return "获取index1锁失败";
        }
        return "hello world!";
    }
}
