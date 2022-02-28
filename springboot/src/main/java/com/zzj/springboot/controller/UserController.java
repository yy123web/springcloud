package com.zzj.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jarvis.cache.lock.ILock;
import com.zzj.springboot.dao.ProtocolInfoDao;
import com.zzj.springboot.dao.UserDao;
import com.zzj.springboot.pojo.ProtocolInfo;
import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.*;
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl helloService;
    @Autowired
    private ILock lock;
    @Autowired(required = false)
    private ProtocolInfoDao protocolInfoDao;
    @Autowired(required = false)
    private UserDao userDao;
    //查询
    @RequestMapping(value = "/hi")
//    @RequestParam String name
    public String Hello() {
        List<User> users = helloService.queryAll();
        JSONObject obj = new JSONObject();
        obj.put("data", users);
        return obj.toString();
    }

    //查询
    @RequestMapping(value = "/query")
//    @RequestParam String name
    public String queryById(@RequestParam Integer id) {
        User user = helloService.queryById(id);
        JSONObject obj = new JSONObject();
        obj.put("data", user);
        return obj.toString();
    }

    //登录
    @RequestMapping(value = "login")
    public String login(User user) {
        return helloService.login(user);
    }


    //增加
    @RequestMapping(value = "/add")
    public String add(User user) {
        user.setId(4);
        user.setName("liliu");
        user.setPassword("123");
        Integer i = helloService.add(user);
        if (i > 0) {
            return "添加成功！";
        }
        return "添加失败！";

    }

    //修改
    @RequestMapping(value = "/edit")
    public String edit(User user) {
        Integer i = helloService.edit(user);
        if (i > 0) {
            return "修改成功！";
        }
        return "修改失败！";
    }

    //删除
    @RequestMapping(value = "/del")
    public String del(@RequestParam Integer id) {
        Integer i = helloService.del(id);
        if (i > 0) {
            return "删除成功！";
        }
        return "删除失败！";
    }

    @RequestMapping("/json6")
    @ResponseBody
    public String json6() throws Exception {
        List<User> userList = new ArrayList<User>();
        User user1 = new User(01, "zhangsan", "123");
        User user2 = new User(02, "lisi", "123");
        User user3 = new User(03, "wangwu", "123");
        User user4 = new User(04, "liliu", "123");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        System.out.println("****Java对象 转 JSON字符串****");
        String str1 = JSON.toJSONString(userList);
        System.out.println(str1);
        String str2 = JSON.toJSONString(user1);
        Map map = JSONObject.parseObject(JSONObject.toJSONString(user1), Map.class);
        System.out.println("map: " + map);
        System.out.println("str2: " + str2);

        System.out.println("****JSON字符串 转 Java对象****");
        User jp_user1 = JSON.parseObject(str2, User.class);
        System.out.println("jp_user1: " + jp_user1);

        System.out.println("****Java对象 转 JSON对象****");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println(jsonObject1.getString("name"));

        System.out.println("****JSON对象 转 Java对象****");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println("to_java_user: " + to_java_user);

        return "Hello";
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

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        final Channel channel = conn.createChannel();
        //声明交换器
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);
        //声明队列
        String queueName = channel.queueDeclare().getQueue();
        String routingKey1 = "hola1";
        String routingKey2 = "hola2";
        //绑定队列，通过键 hola 将队列和交换器绑定起来
        channel.queueBind(queueName, exchangeName, routingKey1);
        channel.queueBind(queueName, exchangeName, routingKey2);

        while(true) {
            //消费消息
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键：" + routingKey);
                    System.out.println("消费的内容类型：" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消费的消息体内容：");
                    String bodyStr = new String(body, "UTF-8");
                    System.out.println(bodyStr);

                }
            });
        }
    }
    @RequestMapping(value = "/synTest1")
//    @RequestParam String name
    public String synTest1() {
        System.out.println("主线程执行开始......");
        log.debug("主线程执行开始......");
        helloService.longtime();
//        doAsyncMethod();
//        doAsyncMethod();
        System.out.println("主线程执行结束......");
        log.debug("主线程执行结束......");
        return "SUCCESS";
    }

    @RequestMapping(value = "/synTest2")
    public void synTest2() throws Exception {
        System.out.println("main函数开始执行");
        Thread thread=new Thread(new Runnable() {
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



}
