package com.example.demo.order.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.OrderDTO;

public interface IOrderService {
	public void save(Order order);
	public void delete(Long id);
	public void deleteAll(Long[] ids);
	public Optional<Order> findOne(Long id);
	
	public Page<OrderDTO> findAll(Specification<Order> spec, Pageable pageable);

}
