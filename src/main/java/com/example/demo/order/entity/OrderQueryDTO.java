package com.example.demo.order.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class OrderQueryDTO {
	
	private String orderName;
	private String companyName;

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@SuppressWarnings({ "serial"})
	public static Specification<Order> getWhereClause(final OrderQueryDTO orderQueryDTO) {
		return new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
		
				if (null!=orderQueryDTO.getOrderName()) {
					predicate.add(criteriaBuilder.equal(root.get("orderName").as(String.class),
							orderQueryDTO.getOrderName()));
				}
	
				if (null!=orderQueryDTO.getCompanyName()) {
					predicate.add(criteriaBuilder.equal(root.get("company").get("companyName").as(String.class),
							orderQueryDTO.getCompanyName()));
				}
				
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

}
