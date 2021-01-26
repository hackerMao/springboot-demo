package com.hacker.utils;

import com.alibaba.fastjson.JSONObject;
import com.hacker.exception.ServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AliIdentifyAuthUtil {
    // 开通服务后 买家中心-查看AppCode
    private static final String appCode = "e912be0d31da439f831b5f240184ceb8";

    private static final String url = "https://naidcard.market.alicloudapi.com/nidCard";

    private static String send(String idNo, String name) {
        try {
            String urlSend = url + "?idCard=" + idNo + "&name=" + URLEncoder.encode(name, "UTF-8");// 拼接请求链接
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appCode);// 格式Authorization:APPCODE (中间是英文空格)

            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                return read(httpURLCon.getInputStream());
            } else {
                Map<String, List<String>> map = httpURLCon.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
                    log.error("AppCode错误 ");
                } else if (httpCode == 400 && error.equals("Invalid Url")) {
                    log.error("请求的 Method、Path 或者环境错误");
                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                    log.error("参数错误");
                } else if (httpCode == 403 && error.equals("Unauthorized")) {
                    log.error("服务未被授权（或URL和Path不正确）");
                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                    log.error("套餐包次数用完 ");
                } else {
                    log.error("参数名错误 或 其他错误");
                    System.out.println(error);
                }
                return null;
            }
        } catch (MalformedURLException e) {
            log.error("URL格式错误");
        } catch (UnknownHostException e) {
            log.error("URL地址错误");
        } catch (Exception e) {
            // 打开注释查看详细报错异常信息
            e.printStackTrace();
        }
        return null;
    }

    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static Boolean authPass(String idNo, String name) {
        String json = send(idNo, name);
        if (json == null) {
            throw new ServerErrorException(9999);
        } else {
            JSONObject jsonObject = JSONObject.parseObject(json);
            return jsonObject.get("status").equals("01");
        }
    }
}
