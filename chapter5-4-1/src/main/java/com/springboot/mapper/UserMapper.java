package com.springboot.mapper;

import com.springboot.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by shijuan on 2018/3/15.
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE NAME=#{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME,AGE) VALUES(#{name},#{age})")
    int insert(@Param("name") String name,@Param("age") Integer age);


}
