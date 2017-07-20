package com.wangc;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;

/**
 * Created by wangc on 17-7-20.
 */
public class H5UploadFile {
    
    public static void main(String[] args){
        upload();  
          System.out.println(111);
    }
    
    public static void upload(){
        try{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/helloUpload");
            HttpEntity httpEntity =MultipartEntityBuilder.create()
                    .addTextBody("name","wangc")
                    .addTextBody("age","12")
                    .addBinaryBody("file",new File("/home/wangc/Pictures/cat.jpg"))
                    .build();
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println( response.getStatusLine().getStatusCode() );
            System.out.println(EntityUtils.toString(response.getEntity()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}               

