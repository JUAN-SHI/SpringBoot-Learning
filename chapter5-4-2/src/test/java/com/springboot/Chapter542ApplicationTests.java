package com.springboot;

import com.springboot.domain.User;
import com.springboot.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter542ApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Test
	public void testUserMapper()throws Exception{

		//insert一条数据，并select出来验证
//		userMapper.insert("BBB",20);
//
//		User u =userMapper.findByName("BBB");
//		Assert.assertEquals(20,u.getAge().intValue());


//		List<User> userList=userMapper.findAll();
//		for(User user:userList){
//			Assert.assertEquals(null,user.getId());
//			Assert.assertNotEquals(null,user.getName());
//		}


		Map<String,Object> map=new HashMap<>();
		map.put("name","CCC");
		map.put("age",40);
		userMapper.insertByMap(map);
		Assert.assertEquals(40,userMapper.findByName("CCC").getAge().intValue());

//		//update一条数据，并SELECT出来验证
//		u.setAge(30);
//		userMapper.update(u);
//		u=userMapper.findByName("BBB");
//		Assert.assertEquals(30,u.getAge().intValue());
//
//		//删除这条数据，并SELECT验证
//		userMapper.detele(u.getId());
//		u=userMapper.findByName("BBB");
//		Assert.assertEquals("AAA",u.getAge());


	}

}
