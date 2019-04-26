package com.springboot.entity.s;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shijuan on 2018/3/7.
 */
public interface MessageReposity  extends JpaRepository<Message,Long>{
}
