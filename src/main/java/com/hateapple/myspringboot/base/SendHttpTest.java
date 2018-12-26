package com.hateapple.myspringboot.base;

import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SendHttpTest {

    private static final String net = "https://www.baidu.com/";

    public static void main(String[] args) throws IOException {


    }

    /**
     * 使用UrlConnnection发送http请求
     */
    public static void sendHttpByUrlConnection() throws IOException {
        URL url = new URL(net);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //必须设置false，否则会自动redirect到重定向后的地址
        conn.setInstanceFollowRedirects(false);
        conn.connect();
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = conn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        String result = buffer.toString();
        System.out.println(result);
    }


}
