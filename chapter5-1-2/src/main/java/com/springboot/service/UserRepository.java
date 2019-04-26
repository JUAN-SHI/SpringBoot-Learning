package com.springboot.service;

import com.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by shijuan on 2018/2/28.
 */
public interface UserRepository  extends JpaRepository<User,Long> {

    User findByName(String name);

    User findByNameAndAge(String name,Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param(("name")) String name);
}
