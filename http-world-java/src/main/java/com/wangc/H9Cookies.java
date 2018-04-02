package com.wangc;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;

import java.io.IOException;

/**
 * Created by wangc on 2018/3/30.
 */
public class H9Cookies {
    public static void main(String[] args) throws IOException {
        cookies();
    }
    
    private static void cookies() throws IOException {
        String url = "http://127.0.0.1:8080/helloCookie2";
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID","wangc");
        cookie.setAttribute("name1","wangc1");
        cookie.setDomain(".127.0.0.1");//可以设置空字符串，但是domain不能为null
        cookieStore.addCookie(cookie);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = client.execute(httpGet);
        System.out.println( response.getStatusLine().getStatusCode() );

    }
}
