package com.example.demo;


import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmailTest{
	@Autowired  
	private RuntimeService runtimeService;  
 
	@Test
	@Deployment(resources = "progress/email.bpmn")
	public void test() {
		//直接开启流程就好了
		runtimeService.startProcessInstanceByKey("SendEmail");
	}
 
}

