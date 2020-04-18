package com.wangc;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;

/**
 * Created by wangc on 17-7-17.
 */

@RestController
public class TestTimeOutController {
    
    //http get 无参请求，直接返回json字符串
    @RequestMapping(value="/hello_timeout",method = RequestMethod.GET)
    public String hello(){
        System.out.println("hello get");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "{'name':'wangc','age':12,'pet':{'p_type':'dog'}}{'name':'wangc','age':12,'pet':{'p_type':'dog'}}{'name':'wangc','age':12,'pet':{'p_type':'dog'}}{'name':'wangc','age':12,'pet':{'p_type':'dog'}}{'name':'wangc','age':12,'pet':{'p_type':'dog'}}{'name':'wangc','age':12,'pet':{'p_type':'dog'}}{'name':'wangc','age':12,'pet':{'p_type':'dog'}}{'name':'wangc','age':12,'pet':{'p_type':'dog'}}{'name':'wangc','age':12,'pet':{'p_type':'dog'}}";
    }
    

    //http post 有参body请求，直接返回请求参数+时间戳
    @RequestMapping(value="/hello_timeout",method = RequestMethod.POST)
    public String helloPost(@RequestBody String s){
        System.out.println("hello post");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s+System.currentTimeMillis();
    }
    
}
