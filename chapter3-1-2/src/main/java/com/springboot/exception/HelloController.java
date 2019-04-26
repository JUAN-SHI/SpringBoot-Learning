package com.springboot.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shijuan on 2018/1/30.
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello()throws Exception{
        throw  new  Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json()throws Exception{
        throw new  MyException("发生json错误2");
    }
}
