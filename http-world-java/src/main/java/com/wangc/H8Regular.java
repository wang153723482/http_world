package com.wangc;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by wangc on 17-7-21.
 */
public class H8Regular {
    public static void main(String[] args){
        jsoup();
    }
    
    private static void regular(){
          
    }
    
    private static void jsoup(){
        Document document = Jsoup.parse( getHtml() );
        System.out.println( document.getElementById("pc_info") );;
        
        Elements es = document.select("#pc_info");
        System.out.println( es.get(0).toString() );
        System.out.println( es.size() );
        
        
      
        
    }
    
    private static String getHtml(){
        try{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/user_info.html");
            CloseableHttpResponse response = client.execute(httpGet);
            return EntityUtils.toString( response.getEntity() );
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
