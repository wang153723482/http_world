package com.wangc;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by wangc on 17-7-21.
 */
public class H7Proxy {
    public static void main(String[] args){
        proxy();
    }
    
    private static void proxy(){
        try{
            String url = "http://www.facebook.com";
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            
            HttpHost proxy = new HttpHost("127.0.0.1",9080);
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setProxy(proxy)
                    .build();
            httpGet.setConfig(config);

            CloseableHttpResponse response = client.execute(httpGet);

            System.out.println( response.getStatusLine().getStatusCode() );
            System.out.println(EntityUtils.toString( response.getEntity() ));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private static void timeout(){
        try{
            String url = "http://www.facebook.com";
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            httpGet.setConfig(config);
            
            CloseableHttpResponse response = client.execute(httpGet);
            
            System.out.println( response.getStatusLine().getStatusCode() );
            System.out.println(EntityUtils.toString( response.getEntity() ));
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
