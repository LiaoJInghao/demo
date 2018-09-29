package com.example.demo.myactiviti.managerFactory;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomUserEntityManagerFactory implements SessionFactory {

	@Override
	public Class<?> getSessionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session openSession(CommandContext commandContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
