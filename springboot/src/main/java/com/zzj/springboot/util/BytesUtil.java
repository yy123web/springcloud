package com.zzj.springboot.util;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * @author zhaozj37918
 * @date 2021年11月11日 10:33
 */
public class BytesUtil {
    /**
     * 整形转换成网络传输的字节流（字节数组）型数据
     *
     * @param num 一个整型数据
     * @return 4个字节的自己数组
     */
    public static byte[] intToBytes(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (0xff & (num >> 0));
        bytes[1] = (byte) (0xff & (num >> 8));
        bytes[2] = (byte) (0xff & (num >> 16));
        bytes[3] = (byte) (0xff & (num >> 24));
        return bytes;
    }

    /**
     * 四个字节的字节数据转换成一个整形数据
     *
     * @param bytes 4个字节的字节数组
     * @return 一个整型数据
     */
    public static int byteToInt(byte[] bytes) {
        int num = 0;
        int temp;
        temp = (0x000000ff & (bytes[0]));
        num = num | temp;
        temp = (0x000000ff & (bytes[1])) << 8;
        num = num | temp;
        temp = (0x000000ff & (bytes[2])) << 16;
        num = num | temp;
        temp = (0x000000ff & (bytes[3])) << 24;
        num = num | temp;
        return num;
    }

    /**
     * 长整形转换成网络传输的字节流（字节数组）型数据
     *
     * @param num 一个长整型数据
     * @return 4个字节的自己数组
     */
    public static byte[] longToBytes(long num) {
        byte[] bytes = new byte[8];
        int range = 8;
        for (int i = 0; i < range; i++) {
            bytes[i] = (byte) (0xff & (num >> (i * 8)));
        }

        return bytes;
    }

    /**
     * 根据字节数组获得值(十六进制数字)
     *
     * @param bytes
     * @param upperCase
     * @return
     */
    public static String getHexString(byte[] bytes, boolean upperCase) {
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            ret.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return upperCase ? ret.toString().toUpperCase() : ret.toString();
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]) & 0xff);
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] objectToByte(Object object) {
        byte[] bytes = null;
        bytes = JSON.toJSONString(object, true).getBytes();
        return bytes;
    }

    /**
     * 根据字节长度截取字符串
     *
     * @param str
     * @param len
     * @param cs
     * @return
     */
    public static String substr(String str, int len, String cs) {
        String res = null;
        try {
            byte[] bt = str.getBytes(cs);
            if (bt.length <= len) {
                return str;
            }
            byte[] br = new byte[len];
            System.arraycopy(bt, 0, br, 0, len);
            res = new String(br, cs);
            int resLen = res.length();
            if (str.substring(0, resLen).getBytes(cs).length > len) {
                res = str.substring(0, resLen - 1);
            }
        } catch (UnsupportedEncodingException e) {
            return res;
        }
        return res;
    }

}

