package com.zzj.springboot.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;


/**
 * @ClassName HttpUtil
 * @Description http请求工具类
 * @Author yuss14950
 * @Date 2021/4/29 19:14
 **/
@Slf4j
public class HttpUtil {

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final int TIMEOUT = 30000;
    public static final int FILE_TIMEOUT = 300000;
    /**
     * 是request头和上传文件内容的分隔符
     */
    public static final String BOUNDARY = "---------------------------";
    public static final String RESULT_FILE = "attachment";


    /**
     * POST 请求
     *
     * @param serverUrl
     * @param params
     * @return
     */
    public static String postHttp(String serverUrl, Map<String, String> params) {
        return postHttp(serverUrl, params, null);
    }

    /**
     * POST 请求
     *
     * @param serverUrl
     * @param params
     * @param headers
     * @return
     */
    public static String postHttp(String serverUrl, Map<String, String> params, Map<String, String> headers) {
        return postHttp(serverUrl, params, headers, POST, TIMEOUT);
    }

    /**
     * 发送HTTP请求
     *
     * @param serverUrl
     * @param params
     * @param headers
     * @param type
     * @param timeout
     * @return
     */
    public static String postHttp(String serverUrl, Map<String, String> params, Map<String, String> headers,
                                  String type, int timeout) {
        log.info("[请求地址]:{} \n[请求参数]:{}", serverUrl, params);
        HttpURLConnection conn = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        try {
            // 发送请求
            URL url = new URL(serverUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.setRequestMethod(type);
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setReadTimeout(timeout);
            conn.setConnectTimeout(timeout);
            if (mapIsNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // 构建请求参数
            StringBuilder sbParam = new StringBuilder();
            if (mapIsNotEmpty(params)) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    sbParam.append(entry.getKey());
                    sbParam.append("=");
                    sbParam.append(entry.getValue());
                    sbParam.append("&");
                }
            }

            // 获得输出流
            osw = new OutputStreamWriter(conn.getOutputStream(), UTF_8);
            osw.write(sbParam.toString());
            osw.flush();
            osw.close();

            log.info("[HttpsUtls][postHttp] connection success.");
            // 获取状态码
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                log.error("[HttpsUtls][postHttp] response status = {}", responseCode);
                return StringUtils.EMPTY;
            }

            // 取得返回信息
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            log.info("【响应数据】: {} ", result);
            // JSON处理
            return result.toString();
        } catch (Exception e) {
            log.error("[HttpsUtls][postHttp] Exception", e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postHttp] osw.close() is Exception. {}", e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postHttp] br.close() is Exception. {}", e);
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postHttp] conn.disconnect() is Exception. {}", e);
                }
            }
        }

        return StringUtils.EMPTY;
    }

    /**
     * Post 请求 格式为 application/json
     *
     * @param serverUrl
     * @param urlParams
     * @param headers
     * @param jsonParam
     * @return
     */
    public static String postJson(String serverUrl, Map<String, String> urlParams, Map<String, String> headers,
                                  String jsonParam) {
        log.info("[请求地址]:{},[请求JSON]:{}", serverUrl, jsonParam);
        HttpURLConnection conn = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        try {
            // 构建请求参数
            StringBuilder sbParam = new StringBuilder(serverUrl);
            if (mapIsNotEmpty(urlParams)) {
                sbParam.append("?");
                if (mapIsNotEmpty(urlParams)) {
                    for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                        sbParam.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                        sbParam.append("=");
                        sbParam.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                        sbParam.append("&");
                    }
                }

            }
            // 发送请求
            URL url = new URL(sbParam.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (mapIsNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            conn.setRequestMethod(POST);
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setReadTimeout(TIMEOUT);
            conn.setConnectTimeout(TIMEOUT);
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            conn.setRequestProperty("accept", "application/json");
            // 往服务器里面发送数据
            if (jsonParam != null) {
                byte[] writebytes = jsonParam.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
            }

            // 获得输出流
            osw = new OutputStreamWriter(conn.getOutputStream(), UTF_8);
            if (jsonParam != null) {
                osw.write(jsonParam);
            }
            osw.flush();
            osw.close();
            log.info("[HttpsUtls][postJson] connection success.");

            // 构建请求参数
            sbParam = new StringBuilder();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                log.error("[HttpsUtls][postJson] responseCode：{}", responseCode);
                log.info("调用http失败,对方服务器未能成功处理请求");
                String returnString = "{\"corebank\":{\"resp\":{\"msgid\":\"9888\",\"errmsg\":\"调用http失败,对方服务器未能成功处理请求\"}}}";
                return returnString;
                // return StringUtils.EMPTY;
            }
            // 取得返回信息
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            log.info("[响应数据]:{} ", result);
            // JSON处理
            return result.toString();
        } catch (Exception e) {
            log.error("[HttpsUtls][postJson] Exception", e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postJson] osw.close() is Exception. {}", e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postJson] br.close() is Exception. {}", e);
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postJson] conn.disconnect() is Exception. {}", e);
                }
            }
        }

        log.info("调用http失败,返回空结果");
        String returnStringNullJosn = "{\"\":\"\"}";
        return returnStringNullJosn;
        // return StringUtils.EMPTY;
    }

    /**
     * post 请求 格式为 text/plain
     *
     * @param serverUrl
     * @param urlParams
     * @param headers
     * @param jsonParam
     * @return
     */
    public static String postText(String serverUrl, Map<String, String> urlParams, Map<String, String> headers,
                                  String jsonParam) {
        log.info("【请求地址】: {} 【请求参数】: {} 【请求头部】: {}【请求JSON】: {}", serverUrl, urlParams, headers, jsonParam);
        HttpURLConnection conn = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        try {
            // 构建请求参数
            StringBuilder sbParam = new StringBuilder(serverUrl);
            if (mapIsNotEmpty(urlParams)) {
                sbParam.append("?");
                if (mapIsNotEmpty(urlParams)) {
                    for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                        sbParam.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                        sbParam.append("=");
                        sbParam.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                        sbParam.append("&");
                    }
                }

            }
            // 发送请求
            URL url = new URL(sbParam.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (mapIsNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            conn.setRequestMethod(POST);
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setReadTimeout(TIMEOUT);
            conn.setConnectTimeout(TIMEOUT);
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            // 设置接收类型否则返回415错误
            conn.setRequestProperty("accept", "application/json");
            // 往服务器里面发送数据
            if (jsonParam != null) {
                byte[] writebytes = jsonParam.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
            }

            // 获得输出流
            osw = new OutputStreamWriter(conn.getOutputStream(), UTF_8);
            if (jsonParam != null) {
                osw.write(jsonParam);
            }
            osw.flush();
            osw.close();
            log.info("[HttpsUtls][postText] connection success.");

            // 构建请求参数
            sbParam = new StringBuilder();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                log.error("[HttpsUtls][postText] responseCode：{}", responseCode);
                return StringUtils.EMPTY;
            }
            // 取得返回信息
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            log.info("【响应数据】: {} ", result);
            // JSON处理
            return result.toString();
        } catch (Exception e) {
            log.error("[HttpsUtls][postText] Exception", e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postText] osw.close() is Exception. {}", e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postText] br.close() is Exception. {}", e);
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    log.error("[HttpsUtls][postText] conn.disconnect() is Exception. {}", e);
                }
            }
        }

        return StringUtils.EMPTY;
    }

    /**
     * * 上传多个文件
     *
     * @param urlStr
     * @param urlParams
     * @param fileMap
     * @return
     */
    public static String formUpload(String urlStr, Map<String, String> urlParams, Map<String, String> fileMap) {
        if (mapIsEmpty(fileMap)) {
            log.info("【请求地址】: {} 【请求参数】: {} ", urlStr, urlParams);
        } else {
            log.info("【请求地址】: {} 【请求参数】: {} 【请求文件】: {}", urlStr, urlParams, fileMap);
        }

        String res = "";
        HttpURLConnection conn = null;
        DataInputStream in = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(FILE_TIMEOUT);
            conn.setReadTimeout(FILE_TIMEOUT);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // urlParams
            if (urlParams != null) {
                StringBuilder strBuf = new StringBuilder();
                Iterator<Map.Entry<String, String>> iter = urlParams.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }

            // file
            if (fileMap != null) {
                Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = FileUtils.getFile(inputValue);
                    if (!file.exists()) {
                        log.error("[HttpsUtls][formUpload] file is not exist. name = {}", inputValue);
                        return res;
                    }
                    String filename = file.getName();

                    StringBuilder strBuf = new StringBuilder();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + "multipart/form-data;boundary=" + "*****" + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                    in = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuilder strBuf = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line);
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            log.error("[HttpsUtls][formUpload] is Exception. {}", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls][formUpload] in.close is Exception. {}", e);
                }
            }
        }
        log.info("【响应数据】: {} ", res);
        return res;
    }

    /**
     * 通过url下载单个文件
     *
     * @param serverUrl
     * @param params
     * @param folder
     * @param filePath
     * @return
     */
    public static String downloadFile(String serverUrl, Map<String, String> params, String folder, String filePath) {
        log.info("【请求地址】: {} 【请求参数】: {} ", serverUrl, params);
        HttpURLConnection conn = null;
        InputStream in = null;
        FileOutputStream os = null;
        try {
            // 构建请求参数
            StringBuilder sbParam = new StringBuilder(serverUrl);
            if (mapIsNotEmpty(params)) {
                sbParam.append("?");
                if (mapIsNotEmpty(params)) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        sbParam.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                        sbParam.append("=");
                        sbParam.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                        sbParam.append("&");
                    }
                }

            }
            // 发送请求
            URL url = new URL(sbParam.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.setRequestMethod(GET);
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setReadTimeout(TIMEOUT);
            conn.setConnectTimeout(TIMEOUT);

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                log.error("[HttpsUtls][downloadFile] responseCode：{}", responseCode);
                return StringUtils.EMPTY;
            }

            boolean resultIsFile = parseIsFile(conn);
            // 返回不是文件流，则返回字符串
            if (!resultIsFile) {
                // 读取返回数据
                StringBuilder strBuf = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    strBuf.append(line);
                }
                reader.close();
                reader = null;
                log.info("【响应数据】: {} ", strBuf);
                return strBuf.toString();
            }

            // 返回文件流
            File targetFile = FileUtils.getFile(folder, filePath);
            in = conn.getInputStream();
            os = new FileOutputStream(targetFile);
            byte[] buffer = new byte[4 * 1024];
            int read;
            while ((read = in.read(buffer)) > -1) {
                os.write(buffer, 0, read);
            }
            os.flush();
            return filePath;
        } catch (Exception e) {
            log.error("[HttpsUtls][downloadFile] Exception", e);
            return StringUtils.EMPTY;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls] [downloadFile] in.close is exception. {}", e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    log.error("[HttpsUtls] [downloadFile] os.close is exception. {}", e);
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    log.error("[HttpsUtls] [downloadFile] conn.disconnect is exception. {}", e);
                }
            }
        }
    }

    /**
     * * 是否是文件
     *
     * @param conn
     * @return
     */
    private static boolean parseIsFile(HttpURLConnection conn) {
        String isFile = conn.getHeaderField("Content-Disposition");
        if (StringUtils.isEmpty(isFile)) {
            return false;
        }
        return StringUtils.contains(isFile, RESULT_FILE);
    }

    /**
     * 判断Map是否不为空
     *
     * @param m
     * @return
     */
    public static boolean mapIsNotEmpty(Map<?, ?> m) {
        return !mapIsEmpty(m);
    }

    /**
     * 判断Map是否为空
     *
     * @param m
     * @return
     */
    public static boolean mapIsEmpty(Map<?, ?> m) {
        return m == null || m.isEmpty();
    }
}
