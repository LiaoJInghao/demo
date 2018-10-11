package com.example.demo.order.entity;

import org.springframework.beans.BeanUtils;

public class OrderDTO {
	private Long id;
	private String name;
	
	private Long company_id;
	private String companyName;
	private String companyAddress;
	
	private Long factory_id;
	private String factoryName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	public Long getFactory_id() {
		return factory_id;
	}
	public void setFactory_id(Long factory_id) {
		this.factory_id = factory_id;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public static void entityToDto(Order entity,OrderDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		dto.setCompanyAddress(entity.getCompany().getCompanyAddress());
		dto.setCompanyName(entity.getCompany().getCompanyName());
		dto.setCompany_id(entity.getCompany().getId());
		
		dto.setFactory_id(entity.getCompany().getFactory().getId());
		dto.setFactoryName(entity.getCompany().getFactory().getFactoryName());
	}
	

}
