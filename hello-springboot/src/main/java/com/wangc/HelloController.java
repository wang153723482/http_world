package com.wangc;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;

/**
 * Created by wangc on 17-7-17.
 */

@RestController
public class HelloController {

    //http get 无参请求，直接返回json字符串
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        System.out.println("hello get");
        return "{'mark':'you are get','name':'wangc','age':12,'pet':{'p_type':'dog'}}";
    }

    //http get 无参请求，直接返回json字符串
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello_post(HttpServletRequest request) {
        System.out.println("hello post");
        System.out.println("helloSession");
        String sessionId = request.getSession().getId();
        System.out.println("session id :"+sessionId);
        return "{'session_id':'"+sessionId+"','name':'wangc','age':12,'pet':{'p_type':'dog'}}";
    }

    //http get 无参请求，返回session id
    @RequestMapping(value = "/helloSession", method = RequestMethod.GET)
    public String helloSession(HttpServletRequest request) {
        System.out.println("helloSession");
        String sessionId = request.getSession().getId();
        System.out.println("helloSession  session id :"+sessionId);
        return "{'session_id':'"+sessionId+"','name':'wangc','age':12,'pet':{'p_type':'dog'}}";
    }


    //http get 无参请求，直接返回json字符串
    @RequestMapping(value = "/hello_put", method = RequestMethod.PUT)
    public String hello2(@RequestBody String s) {
        System.out.println("接收到的参数为：" + s);
        return "{'name':'wangc','age':12}";
    }

    //http post 有参body请求，直接返回请求参数+时间戳
    @RequestMapping(value = "/hello_body", method = RequestMethod.POST)
    public String helloPost(@RequestBody String s) {
        System.out.println("hello post" + s);
        return s + System.currentTimeMillis();
    }

    //http post 有参body请求，直接返回请求参数+时间戳
    @RequestMapping(value = "/helloJson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String helloPostJson(@RequestBody String s) {
        System.out.println("hello post" + s);
        return s + System.currentTimeMillis();
    }

    //http post 有参键值对请求，返回对应的字符串
    @RequestMapping(value = "/helloPara", method = RequestMethod.POST)
    public String helloPara(@RequestParam String name, String age) {
        return "name:" + name + ",age:" + age;
    }
    //http post 有参键值对请求，返回对应的字符串
    @RequestMapping(value = "/helloParaGet", method = RequestMethod.GET)
    public String helloParaGet(@RequestParam String name, String age) {
        return "method:get,name:" + name + ",age:" + age;
    }

    //http get 请求，返回cookie
    @RequestMapping(value = "/helloCookie1", method = RequestMethod.GET)
    public String helloCookie1(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("mycookie", "aaaaaa1");
        response.addCookie(cookie);
        response.setContentType("");
        return "you get cookie";
    }

    //http post 请求，获取cookie，如果有ok，没有报错
    @RequestMapping(value = "/helloCookie2", method = RequestMethod.GET)
    public String helloCookie2(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (null != cookies
                && cookies.length > 0
                && "mycookie".equals(cookies[0].getName())) {
            return "you have cookie,you are right.";
        } else {
            return "you don't have cookie,no!";
        }
    }

    @RequestMapping(value = "/helloHeader", method = {RequestMethod.POST, RequestMethod.GET})
    public String helloHeader(HttpServletRequest request, HttpServletResponse response) {
        String result = "";
        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String tmp = e.nextElement();
            result += (tmp + ":" + request.getHeader(tmp) + "\n");
        }
        System.out.println(result);
        return "server get your heads.\n" + result;
    }

    @RequestMapping(value = "helloUpload", method = RequestMethod.POST)
    public String helloUpload(@RequestParam MultipartFile file,
                              @RequestParam String name,
                              @RequestParam String age) {
        String fileAllPath = System.getProperty("user.dir") + File.separator + file.getOriginalFilename(); //完整路径
        System.out.println(fileAllPath);
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(fileAllPath)));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("file is empty.");
        }
        return "file is save at:" + fileAllPath
                + "<br>name:" + name
                + "<br>age:" + age;
    }

}
