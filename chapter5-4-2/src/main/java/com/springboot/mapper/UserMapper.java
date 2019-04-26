package com.springboot.mapper;

import com.springboot.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by shijuan on 2018/3/16.
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where name=#{name}")
    User findByName(@Param("name")String name);

    @Insert("insert into user(name,age) values(#{name},#{age})")
    int insert(@Param("name") String name,@Param("age") Integer age);

    @Insert("insert into user(name,age) values(#{name},#{age})")
    int insertByUser(User user);

    @Insert("insert into user(name,age) values(#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    int  insertByMap(Map<String,Object> map);

    @Update("update user set age=#{age} where name=#{name}")
    void  update(User user);

    @Delete("delete from user where id=#{id}")
    void detele(Integer id);


    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "age",column = "age")
    })
    @Select("select name,age from user")
    List<User> findAll();


}
