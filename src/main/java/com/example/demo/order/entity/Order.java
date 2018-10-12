package com.example.demo.order.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.company.entity.Company;

@Entity
@Table(name="t_order")
public class Order {
	
	private Long id;
	private String orderName;
	
	private Company company;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getOrderName() {
		return orderName;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="company_id")
	public Company getCompany() {
		return company;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
}
