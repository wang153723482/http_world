package com.wangc.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by wangchao on 2017/8/12.
 */
public class O1SimpleRequest {
    
    public static void main(String[] args){
        request();
    }
    
    public static void request(){
        try {
            String url = "http://127.0.0.1:8080/hello";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println( response.code() );
            System.out.println( response.message() );
            System.out.println( response.body().string() );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
