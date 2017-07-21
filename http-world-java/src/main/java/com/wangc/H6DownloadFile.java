package com.wangc;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by wangc on 17-7-21.
 */
public class H6DownloadFile {
    public static void main(String[] args){
        download();
    }
    
    public static void download(){
        try{
            String url = "http://127.0.0.1:8080/kittens.jpg";
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = client.execute(httpGet);
            /*response.getEntity().writeTo(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    File f = new File();
                    
                }
            });*/
            File f = new File("result.jgp");
            FileOutputStream out = new FileOutputStream(f);
            response.getEntity().writeTo(out);
            out.close();
            System.out.println("文件下载成功！");
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
