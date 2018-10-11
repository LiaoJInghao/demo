package com.example.demo.company.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.factory.entiry.Factory;

@Entity
@Table(name="t_company")
public class Company {
	private Long id;
	private String companyName;
	private String companyAddress;
	private Factory factory;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	@ManyToOne
	@JoinColumn(name="factory_id")
	public Factory getFactory() {
		return factory;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", companyAddress=" + companyAddress + "]";
	}
	
}
