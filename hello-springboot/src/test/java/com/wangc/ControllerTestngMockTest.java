package com.wangc;

import com.wangc.HelloSpringbootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by wangc on 2018/4/17.
 *
 * @Auther wangc
 */
@SpringBootTest(classes = HelloSpringbootApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTestngMockTest extends AbstractTestNGSpringContextTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @BeforeMethod
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    //get 
    public void testList() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostParam() throws Exception {
        mockMvc.perform(post("/helloPara")
                            .param("name", "wangc")
                            .param("age", "22") )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
