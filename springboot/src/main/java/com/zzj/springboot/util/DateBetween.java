package com.zzj.springboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateBetween {


    public static Long between_time(String startStr, String endStr) throws Exception{

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 自定义时间格式

        System.out.println("startstr="+startStr+" endstr="+endStr);

        try {
            Date e = simpleDateFormat.parse(startStr);
            Date endDate = simpleDateFormat.parse(endStr);
            Long day1 = (Long) ((endDate.getTime() - e.getTime()) / 3600000L/24L);
            System.out.println("时间间隔："+day1);
            return day1;
        } catch (Exception arg6) {
            throw new Exception("获得两个格式时间间的小时差出错", arg6);
        }
    }
}
