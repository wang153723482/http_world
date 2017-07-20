package com.wangc;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by wangc on 17-7-18.
 */
public class H3BodyRequest {    
    
    public static void main(String[] args){
        try{
            post1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void post1() throws Exception{

        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 目标地址    
        HttpPost httppost = new HttpPost("http://127.0.0.1:8080/hello");
        // 构造最简单的字符串数据    
        StringEntity reqEntity = new StringEntity("{'name':'wangc'}");
        // 设置类型    
        reqEntity.setContentType("application/json");
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
