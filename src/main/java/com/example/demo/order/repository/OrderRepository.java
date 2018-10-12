package com.example.demo.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.order.entity.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>,JpaSpecificationExecutor<Order>{
	
	@Query("from Order o where o.company.id=?1")
	public List<Order> findByCompanyId(Long companyId);
}
