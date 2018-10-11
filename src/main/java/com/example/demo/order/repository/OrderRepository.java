package com.example.demo.order.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.order.entity.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>,JpaSpecificationExecutor<Order>{

}
