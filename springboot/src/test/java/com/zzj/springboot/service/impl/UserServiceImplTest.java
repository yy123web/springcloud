package com.zzj.springboot.service.impl;

import com.jcraft.jsch.*;
import com.zzj.springboot.pojo.User;
import com.zzj.springboot.service.UserService;
import com.zzj.springboot.util.BytesUtil;
import com.zzj.springboot.util.UnFileUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import org.apache.commons.io.LineIterator;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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
                List<String> list = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    StringBuffer sb = new StringBuffer();
                    sb.append(separatorString);
                    StringBuilder stringBuilder = new StringBuilder(line);
                    Pattern pattern = Pattern.compile("[0-9],[0-9]");
                    Matcher matcher = pattern.matcher(stringBuilder);
                    SimpleDateFormat f = new SimpleDateFormat();
                    while (matcher.find()) {
                        stringBuilder.replace(matcher.start() + 1, matcher.end() - 1, ";");
                    }
                    line = stringBuilder.toString().replace("\"", "").replace(" ", "");
                    Charset gbk = Charset.forName("GBK");
                    String[] s = line.split(separatorString, -1);
                    System.out.println(line);
                    list.add(line);

                    //一行数据 通过分隔符分割 成数组  在转成list
//                    List<String> list = Arrays.asList(line.split(String.valueOf((sb))));
//                    User user = new User();
//                    user.setName(list.get(0));
//                    user.setPassword(list.get(1));
//                    userService.add(user);
                }
                System.out.println(list);
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

    @Test
    public void connectTest() throws Exception {
        String user = "dev";
        String passwd = "dev@123";
        String host = "10.20.36.131";
        int post = 22;
        connect(user, passwd, host, post);
        System.out.println(123);
    }


    public static void connect(String user, String passwd, String host, int post) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, post);
        if (session == null) {
            throw new Exception("session is null");
        }
        session.setPassword(passwd);
        java.util.Properties config = new java.util.Properties();
        //第一次登陆
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        try {
            session.connect(30000);
        } catch (Exception e) {
            throw new Exception("连接远程端口无效或用户名密码错误");
        }
    }


    //    public static void execCmd(String command, String user, String passwd, String host,int post) throws Exception {
//        System.out.println(command);
//        connect(user, passwd, host,post);
//        BufferedReader reader = null;
//        Channel channel = null;
//        Session session = null;
//        try {
//            channel = session.openChannel("exec");
//            ((ChannelExec) channel).setCommand(command);
//
//            channel.setInputStream(null);
//            ((ChannelExec) channel).setErrStream(System.err);
//
//            channel.connect();
//            InputStream in = channel.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(in));
//            String buf = null;
//            List<LoggerForm> list = new ArrayList<LoggerForm>();
//            //返回数据
//            while ((buf = reader.readLine()) != null) {
//                String[] arr = buf.split("\|");
//                for (int i = 0; i < arr.length; i++) {
//                    System.out.println("异常时间:"+arr[1]+"错误信息:"+arr[2]+"异常类型:"+getArr(arr[0])[2]+"异常信息所在文件行数:"+getArr(arr[0])[1]+"日志文件名称:"+getArr(arr[0])[0]);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSchException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            channel.disconnect();
//            session.disconnect();
//        }
//    }
    public static String beforeDate(String name) {
        Date d = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String date = f.format(now.getTime());
        String fileName = name.replaceFirst("\\.", "_" + date + ".");
        return fileName;
    }

    @Test
    public void test03() {
        String name = "/home/gaps/file/test/ccms_auth.tar.gz";
        System.out.println(name);
        name = beforeDate(name);
        System.out.println(name);

    }

    @Test
    public void test04() {
        String phones = "13012345678,13112345678";
        if (phones.contains(",")) {
            String[] phoneList = phones.split(",");
            for (String phone : phoneList) {
                System.out.println(phone);
            }
        } else {
            System.out.println(phones);
        }

    }

//    @Test
//    public void test05() {
//        String excelPath = "C:\\Users\\hspcadmin\\Desktop\\测试.xlsx";
//        List<String> list = null;
//        String cellData = null;
//        Workbook wb = null;
//        Sheet sheet = null;
//        Row row = null;
//        wb = readExcel(excelPath);
//        if (wb != null) {
//            // 用来存放表中数据
//            list = new ArrayList<String>();
//            // 获取第一个sheet
//            sheet = wb.getSheetAt(0);
//            // 获取最大行数
//            int rownum = sheet.getPhysicalNumberOfRows();
//            // 获取第二行
//            row = sheet.getRow(1);
//            // 获取最大列数
//            int colnum = row.getPhysicalNumberOfCells();
//            for (int i = 1; i < rownum; i++) {
//                row = sheet.getRow(i);
//                StringBuffer sb = new StringBuffer();
//                if (row != null) {
//                    for (int j = 0; j < colnum; j++) {
//                        DataFormatter formatter = new DataFormatter();
//                        cellData = formatter.formatCellValue(row.getCell(j));
//                        if (j == (colnum - 1)) {
//                            sb.append(cellData);
//                        } else {
//                            sb.append(cellData + "|");
//                        }
//                    }
//                    System.out.println(sb.toString());
//                } else {
//                    break;
//                }
//                list.add(sb.toString());
//            }
//            try {
//                wb.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

//    public static Workbook readExcel(String filePath) {
//        Workbook wb = null;
//        if (filePath == null) {
//            return null;
//        }
//        String extString = filePath.substring(filePath.lastIndexOf("."));
//        InputStream is = null;
//        try {
//            is = new FileInputStream(filePath);
//            if (".xls".equals(extString)) {
//                return wb = new HSSFWorkbook(is);
//            } else if (".xlsx".equals(extString)) {
//                return wb = new XSSFWorkbook(is);
//            } else {
//                return wb = null;
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return wb;
//    }

    @Test
    public void test06() {
//        User user1 = new User();
//        user1.setName("张三");
//        user1.setName("李四");
//        System.out.println(user1);
//        User user2 = new User();
//        user2.setName("张三");
//        user2.setName("李四");
//        System.out.println(user2);
//        List<String> list = new ArrayList();
//        list.add("zhangsan");
//        list.add("lisi");
//        list.add("wangwu");
//        for(String i : list){
//            if(i.equals("lisi")){
//                continue;
//            }
//            System.out.println(i);
//        }
//        String s = "20211120173600";
//        String substring = s.substring(0, 8);
//        System.out.println("截取前：" + s);
//        System.out.println("截取后：" + substring);
        User user1 = new User();
        user1.setName("20211127103700");
        User user2 = new User();
        user2.setName("20211127103700");
        if (!Strings.isNullOrEmpty(user2.getName())) {
            if (user1.getName().compareTo(user2.getName()) > 0) {
                System.out.println("user1大于user2");
            } else {
                System.out.println("user2小于user1");
            }
        } else {
            System.out.println("user2为空");
        }

    }

    public void buildUser(User user) {
        if ("zhangsan1".equals(user.getName())) {
            user.setName("lisi1");
            user.setPassword("123");
        } else if ("zhangsan2".equals(user.getName())) {
            user.setName("lisi2");
            user.setPassword("123");
        } else if ("zhangsan".equals(user.getName())) {
            user.setName("lisi");
            user.setPassword("123");
        }

    }

    @Test
    public void test07() {
//        User user = new User();
//        user.setId(1);
//        user.setName("zhangsan");
//        buildUser(user);
//        System.out.println(user);
//        String s = "C:\\Users\\hspcadmin\\Desktop\\test.tar.gz";
//        if (s.contains(".gz") || s.contains(".tar") || s.contains("zip")) {
//            System.out.println("为压缩文件");
//        } else {
//            System.out.println("为普通文件");
//        }
    }

    @Test
    public void test08() {
        String fullPathFile = "";
        BufferedReader reader = null;
        List<String> fileLines = new ArrayList<>();
        try {
            File file = new File(fullPathFile);
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                fileLines.add(tempString);
                line++;
            }
            reader.close();
        } catch (Exception e) {

        }

    }

    @Test
    public void test09() throws IOException {
        String fullPathFile = "C:\\Users\\hspcadmin\\Desktop\\test.txt";
        String line = null;
        int lineNo = 0;
        LineIterator it = FileUtils.lineIterator(new File(fullPathFile), "UTF-8");
        try {
            while (it.hasNext()) {
                line = it.nextLine();
                System.out.println(line);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }

    @Test
    public void testFilter() {
        List<Integer> nums = Arrays.asList(10, 2, 2, 5, 6, 6, 3);
        //从流中选出偶数，且没有重复
        nums.stream().filter(i -> i % 2 == 0)  //过滤
                .distinct()  //去重
                .sorted((x, y) -> y.compareTo(x)) //排序
                .forEach(x -> System.out.println(x + "-"));
    }

    @Test
    public void testMap2() {
        List<String> strs = Arrays.asList("a", "bb", "ccc", "dd");
        List<Integer> collect = strs.stream().map(x -> x.length())   // 方法引用：map(String::length)
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    @Test
    public void test10() {
        String fullPathFile = "C:\\Users\\hspcadmin\\Desktop\\jdk-8u181-linux-x64.tar.gz";
        String newFullPathFile = "C:\\Users\\hspcadmin\\Desktop";
        try {
            List<String> lists = new ArrayList<>();
            lists = UnFileUtil.deCompressGZipFile(fullPathFile, newFullPathFile, lists);
            if (null != lists && !lists.isEmpty()) {
                String name = lists.get(0);
                newFullPathFile = newFullPathFile + "/" + name;
                System.out.println(newFullPathFile);
            } else {
                log.info("文件不存在");
            }
        } catch (Exception e) {
            log.error("解压失败", e);
        }
    }

    @Test
    public void test12() {
        String newFullPathFile = "C:\\Users\\hspcadmin\\Desktop\\HMC.txt";
//        File checkFile = FileUtils.getFile(newFullPathFile, String.format("%s.txt", "HMC"));
        File checkFile = FileUtils.getFile(newFullPathFile);
        log.info("检查.ok文件是否已经生成[{}]:", checkFile.getAbsolutePath());
        int alreadyWaitSeconds = 0;
        while (!checkFile.exists()) {
            try {
                log.info(".ok文件未生成[{}],尝试等待[{}]秒", checkFile.getAbsolutePath(), "120");
                Thread.sleep(120 * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            alreadyWaitSeconds += 120;
            if (alreadyWaitSeconds >= 1200) {
                log.info(".ok文件未生成[{}]等待超时", checkFile.getAbsolutePath());
                return;
            }
        }
        log.info("文件已经生成");
    }

    @Test
    public void test13() throws IOException {
        String fullPathFile = "C:\\Users\\hspcadmin\\Desktop\\test.txt";
        String checkFilePath = fullPathFile + ".ok";
        File checkOkFile = FileUtils.getFile(checkFilePath);
        File checkHmcFile = FileUtils.getFile(fullPathFile);
        if (checkOkFile.exists() || !checkHmcFile.exists()) {
            log.info("任务执行完毕或文件不存在");
            return;
        }
//        if (!checkHmcFile.exists()) {
//            log.info("文件不存在");
//            return;
//        }

        File file = new File(checkFilePath);
        boolean newFile = file.createNewFile();
        System.out.println(newFile);
    }

    @Test
    public void test11() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress:" + addr.getHostAddress());
        String hostname = addr.getHostName();
        System.out.println("Local host name: " + hostname);
    }


}