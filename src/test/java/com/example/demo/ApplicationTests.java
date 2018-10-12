package com.example.demo;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.company.entity.Company;
import com.example.demo.company.service.CompanyService;
import com.example.demo.factory.entity.Factory;
import com.example.demo.factory.service.FactoryService;
import com.example.demo.order.entity.Order;
import com.example.demo.order.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationTests 
{
	@Autowired  
	private RepositoryService repositoryService; 
	@Autowired  
	private RuntimeService runtimeService;  
	@Autowired  
	private TaskService taskService; 
	
	@Autowired  
	private IdentityService identityService; 
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private FactoryService factoryService;

	@Test
	public void helloProcessInstance() {
		//根据bpmn文件部署流程,可以思考是否需要多次部署？查看数据库表：act_re_procdef
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/email.bpmn").deploy();
		System.out.println("创建流程部署，部署Id："+deployment.getId());
		//获取流程定义 
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		System.out.println("根据 流程部署Id 创建 流程定义，定义Id："+processDefinition.getId());
		//启动流程定义，返回流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
		String processId = pi.getId();
		System.out.println("根据 流程定义Id 创建 流程实例，实例Id："+processId);
		
		System.out.println("根据实例Id推进流程任务：");
		
		Task task=taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("》》》》》》》》》》第一次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());
 
		/*task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("》》》》》》》》》》》》》》》》》》》》第二次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());*/
 
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》任务执行完毕,task为："+task);
	}
	
	@Test
	@Transactional
	public void save() {
		
		Factory f=new Factory();
		f.setFactoryName("DGF1");
		factoryService.save(f);
		
		Factory f2=new Factory();
		f2.setFactoryName("DGF2");
		factoryService.save(f2);
		
		Company c=new Company();
		c.setCompanyAddress("GZ");
		c.setCompanyName("KC");
		c.setFactory(f);
		companyService.save(c);
		
		Company c1=new Company();
		c1.setCompanyAddress("GZ");
		c1.setCompanyName("KCA");
		c.setFactory(f);
		companyService.save(c1);
		
		Order o=new Order();
		o.setOrderName("admina");
		o.setCompany(c);
		orderService.save(o);
		
		Order o1=new Order();
		o1.setOrderName("adminb");
		o1.setCompany(c);
		orderService.save(o1);
	}
	
	@Test
	public void deleteByOrder() {
		Order o=orderService.findOne(1L);
		if(o!=null) {
			o.setOrderName("aaa");
			orderService.save(o);
		}
	}
	
	
	@Test
	public void deleteByCompany() {
		Company c=companyService.findOne(1L);
		if(c!=null) {
			List<Order> orderList=orderService.findByCompanyId(c.getId());
			for(Order o:orderList) {
				o.setCompany(null);
				orderService.save(o);
				orderService.delete(o.getId());
			}
			companyService.delete(c.getId());
		}
	}
	
	
}
