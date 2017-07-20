package com.wangc;

import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by wangc on 17-7-17.
 */

@RestController
public class HelloController {
    
    //http get 无参请求，直接返回json字符串
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String hello(){
        System.out.println("hello get");
        return "{'name':'wangc','age':12}";
    }

    //http post 有参body请求，直接返回请求参数+时间戳
    @RequestMapping(value="/hello",method = RequestMethod.POST)
    public String helloPost(@RequestBody String s){
        System.out.println("hello post");
        return s+System.currentTimeMillis();
    }
    
    //http post 有参键值对请求，返回对应的字符串
    @RequestMapping(value = "/helloPara",method=RequestMethod.POST)
    public String helloPara(@RequestParam String name,String age){
        return "name:"+name+",age:"+age;
    }
    
    //http post 请求，返回cookie
    @RequestMapping(value = "/helloCookie1",method = RequestMethod.GET)
    public String helloCookie1(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("mycookie","aaaaaa1");
        response.addCookie(cookie);
        return "you get cookie";
    }
    
    //http post 请求，获取cookie，如果有ok，没有报错
    @RequestMapping(value = "/helloCookie2",method = RequestMethod.GET)
    public String helloCookie2(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        
        if(null!=cookies 
                && cookies.length>0 
                && "mycookie".equals(cookies[0].getName())){
            return "you have cookie,you are right.";
        }else{
            return "you don't have cookie,no!";
        }
    }
    
    @RequestMapping(value = "helloHeader",method = RequestMethod.GET)
    public String helloHeader(HttpServletRequest request,HttpServletResponse response){
        String result = "";
        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()){
            String tmp = e.nextElement();
            result += (tmp+":"+request.getHeader(tmp)+"\n"); 
        }
        System.out.println(result);
        return "server get your heads.\n"+result;
    }

}
