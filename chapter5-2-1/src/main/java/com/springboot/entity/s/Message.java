package com.springboot.entity.s;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by shijuan on 2018/3/7.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long  id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    public Message() {

    }

    public Message(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
