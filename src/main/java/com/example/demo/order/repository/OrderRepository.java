package com.example.demo.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.order.entity.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>,JpaSpecificationExecutor<Order>{
	
	@Query("from Order o where o.company.companyName =?1")
	public Page<Order> findOrderByCompanyName(String companyName, Pageable pageable);

}
