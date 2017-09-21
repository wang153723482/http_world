package com.wangc;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by wangc on 17-7-16.
 */
public class H2ParaRequest {
    
    public static void main(String[] args){
       
        try{
            post1();
            System.out.println("================");
            post2();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //使用URI这个类构造请求
    public static void post1() throws URISyntaxException, IOException {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("127.0.0.1:8080")
                .setPath("/helloPara")
                .setParameter("name", "httpclient")
                .setParameter("age", "httpclient")
                .build();
        HttpPost httpPost = new HttpPost(uri);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse  response = httpclient.execute(httpPost);
        System.out.println( response.getStatusLine().getStatusCode() );
        System.out.println(EntityUtils.toString(response.getEntity()));

    }
    
    
    //使用 StringEntity 构造请求，尽量使用这种方法
    public static void post2() throws Exception{

        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 目标地址    
        HttpPost httppost = new HttpPost("http://127.0.0.1:8080/helloPara");
        // 构造最简单的字符串数据    
        StringEntity reqEntity = new StringEntity("name=abcde&age=111");
        // 设置类型    
        reqEntity.setContentType("application/x-www-form-urlencoded");
        // 设置请求的数据    
        httppost.setEntity(reqEntity);
        // 执行    
        HttpResponse httpresponse = httpclient.execute(httppost);
        HttpEntity entity = httpresponse.getEntity();
        System.out.println( httpresponse.getStatusLine().getStatusCode() );
        String body = EntityUtils.toString(entity);
        System.out.println(body);
    }
}
