package com.example.demo.order.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import com.example.demo.company.entity.Company;

import org.apache.commons.lang3.StringUtils;

public class OrderQueryDTO {
	
	private String name;
	private Company company;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	@SuppressWarnings({ "serial"})
	public static Specification<Order> getWhereClause(final OrderQueryDTO orderQueryDTO) {
		return new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
		
				if(StringUtils.isNoneBlank(orderQueryDTO.getName())) {
					predicate.add(criteriaBuilder.like(root.get("name").as(String.class),"%" + orderQueryDTO.getName() + "%"));
				}
				if(StringUtils.isNotBlank(orderQueryDTO.getCompany().getCompanyName())) {
					predicate.add(criteriaBuilder.like(root.get("company").get("companyName"),"%"+orderQueryDTO.getCompany().getCompanyName()+"%"));
				}
				/*if(null!=orderQueryDTO.getCompanyName()) {
					Join<Order,Company> join=root.join("company",JoinType.LEFT);
					predicate.add(criteriaBuilder.like(join.get("companyName").as(String.class),"%"+orderQueryDTO.getCompanyName()+"%"));
					//predicate.add(criteriaBuilder.like(root.get("company").get("companyName").as(String.class),"%" + orderQueryDTO.getCompanyName() + "%"));
					
				}*/
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

}
