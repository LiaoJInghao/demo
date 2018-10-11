package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.company.entity.Company;
import com.example.demo.company.service.ICompanyServie;
import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.OrderQueryDTO;
import com.example.demo.order.service.IOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class OrderTest {
	
	@Autowired
	private ICompanyServie companyService;
	
	@Autowired
	private IOrderService orderService;
	
	@Test
	public void save() {
		Company c=new Company();
		c.setCompanyAddress("DG");
		c.setCompanyName("KDG1");
		companyService.save(c);
		
		Company c2=new Company();
		c2.setCompanyAddress("DG");
		c2.setCompanyName("KDG2");
		companyService.save(c2);
		
		Order o=new Order();
		o.setName("admin");
		o.setCompany(c);
		orderService.save(o);
		
		Order o1=new Order();
		o1.setName("admin1");
		o1.setCompany(c2);
		orderService.save(o1);
		
		Order o2=new Order();
		o2.setName("admin2");
		o2.setCompany(c2);
		orderService.save(o2);
	}
	
	
	@Test
	public void delete() {
		orderService.delete(4L);
	}
	
	@Test
	public void update() {
		Company c=companyService.findOne(3L).get();
		c.setCompanyName("GZ");
		companyService.save(c);
	}

}
