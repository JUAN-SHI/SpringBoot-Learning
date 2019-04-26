package com.springboot.exception;



/**
 * Created by shijuan on 2018/1/30.
 */
/*创建一个自定义异常，用来实验捕获该异常，*/
public class MyException extends Exception {
     public MyException(String message){
         super(message);
     }
}
