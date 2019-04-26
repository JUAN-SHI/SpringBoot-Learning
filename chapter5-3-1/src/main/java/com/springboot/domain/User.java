package com.springboot.domain;

import java.io.Serializable;

/**
 * Created by shijuan on 2018/3/14.
 */
public class User  implements Serializable{

    private static final long serialVersionUID=-1l;

    private String username;
    private Integer age;

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
