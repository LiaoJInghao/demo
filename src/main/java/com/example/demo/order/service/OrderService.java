package com.example.demo.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.OrderDTO;
import com.example.demo.order.repository.OrderRepository;

@Service
@Transactional
public class OrderService implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Order> orders = (List<Order>) orderRepository.findAllById(idLists);
		if(orders!=null) {
			orderRepository.deleteAll(orders);
		}
	}

	@Override
	public Order findOne(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public Page<Order> findAll(Specification<Order> spec, Pageable pageable) {
		return orderRepository.findAll(spec, pageable);
	}

	@Override
	public Page<OrderDTO> findByOrderName(Specification<Order> spec, Pageable pageable) {
		Page<Order> list =  orderRepository.findAll(spec, pageable);
		List<OrderDTO> dtoLists = new ArrayList<OrderDTO>();
		for (Order entity : list.getContent()) {
			OrderDTO dto = new OrderDTO();
			BeanUtils.copyProperties(entity, dto);
			if(entity.getCompany()!=null) {
				dto.setCompanyName(entity.getCompany().getCompanyName());
			}
			dtoLists.add(dto);
		}
		return new PageImpl<OrderDTO>(dtoLists, pageable, list.getTotalElements());
	}

	@Override
	public List<Order> findByCompanyId(Long companyId) {
		return orderRepository.findByCompanyId(companyId);
	}

}
