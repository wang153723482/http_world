package com.wangc;

import com.wangc.HelloController;
import com.wangc.HelloSpringbootApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * Created by wangc on 2018/4/17.
 *
 * @Auther wangc
 * 使用junit进行单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloSpringbootApplication.class, 
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private URL base;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    @Test
    public void test1() throws Exception {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.base.toString() + "/hello", String.class, "");
        //建议使用断言
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }
    
}
