package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.user.entity.User;
import com.example.demo.user.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceTest {
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void save() {
		User u=new User();
		u.setUserName("king");
		u.setPassword("king");
		userService.save(u);
	}
}
