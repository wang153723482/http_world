package com.wangc;

import org.apache.http.HeaderIterator;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by wangc on 17-7-19.
 */
public class H4Header {
    public static void main(String[] args) throws IOException {
        postHeader();
    }
    
    public static void postHeader() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/helloHeader");
        httpGet.setHeader("xx","hahhah");
        httpGet.setHeader("content","text");
        CloseableHttpResponse response = client.execute(httpGet);
        System.out.println( response.getStatusLine().getStatusCode() );
        System.out.println("---------------------------");
        System.out.println(EntityUtils.toString(response.getEntity()));
        System.out.println("---------------------------");
        //获取单个响应头，根据key获取的是数组，这里假定只有1个
        System.out.println( response.getHeaders("Content-Type")[0] );
        System.out.println( response.getHeaders("Content-Length")[0] );
        System.out.println("---------------------------");
        //遍历响应头
        HeaderIterator it = response.headerIterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
