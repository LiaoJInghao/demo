package com.example.demo.myactiviti.util;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;

import com.example.demo.myactiviti.entity.MyUser;

public class ActivitiUserUtils {
	
	public static User toActivitiUser(MyUser u){
		User userEntity=new UserEntityImpl();
		userEntity.setId(u.getUserName());
		userEntity.setPassword(u.getPassword());
		return userEntity;
    }

}
