package com.example.demo.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.company.entity.Company;
import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.OrderDTO;
import com.example.demo.order.entity.OrderQueryDTO;
import com.example.demo.order.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	

	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping
	public Page<OrderDTO> find(OrderQueryDTO orderQueryDTO,ExtjsPageRequest pageable) {
		
		orderQueryDTO.setCompanyName("KDG1");
		orderQueryDTO.setName("admin1");
		if(orderQueryDTO.getCompanyName()!=null) {
			System.out.println("yes");
		}
		return orderService.findAll(OrderQueryDTO.getWhereClause(orderQueryDTO), pageable.getPageable());
		
	}
	
	
	@RequestMapping("/findAll")
	public Page<OrderDTO> findAll(ExtjsPageRequest pageable) {
		
		String companyName="KDG2";
		return orderService.findOrderByCompanyName(companyName, pageable.getPageable());
		
	}

}
