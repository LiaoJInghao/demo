package com.example.demo.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.OrderDTO;
import com.example.demo.order.entity.OrderQueryDTO;
import com.example.demo.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public Page<OrderDTO> find(OrderQueryDTO orderQueryDTO,ExtjsPageRequest pageable) {
		orderQueryDTO.setOrderName("admin3");
		//orderQueryDTO.setCompanyName("KDF4");
		return orderService.findByOrderName(OrderQueryDTO.getWhereClause(orderQueryDTO), pageable.getPageable());
	}
	
	@DeleteMapping
	public String delete() {
		Order o=orderService.findOne(1L);
		if(o!=null) {
			orderService.delete(o.getId());
			return "success";
		}
		return "false";
		
	}

}
