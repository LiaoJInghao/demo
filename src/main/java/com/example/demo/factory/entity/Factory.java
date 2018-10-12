package com.example.demo.factory.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_factory")
public class Factory {
	
	private Long id;
	private String factoryName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	

}
