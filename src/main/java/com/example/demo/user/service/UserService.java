package com.example.demo.user.service;

import javax.transaction.Transactional;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IdentityService identityService;
	
	@Override
	public void save(User user) {
		userRepository.save(user);
		String userId=user.getUserName();
		org.activiti.engine.identity.User u=identityService.newUser(userId);
		u.setPassword(user.getPassword());
		identityService.saveUser(u);
		identityService.createMembership(userId, "financeManager");
	}

}
