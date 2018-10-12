package com.example.demo.order.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.OrderDTO;

public interface IOrderService {
	//请假业务
	public void save(Order order);
	public void delete(Long id);
	public void deleteAll(Long[] ids);
	public Order findOne(Long id);
	
	public Page<Order> findAll(Specification<Order> spec, Pageable pageable);
	
	public Page<OrderDTO> findByOrderName(Specification<Order> spec, Pageable pageable);

	public List<Order> findByCompanyId(Long companyId);
}
