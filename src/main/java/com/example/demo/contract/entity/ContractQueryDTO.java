package com.example.demo.contract.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

public class ContractQueryDTO {
	
	private String userId;
	
	private String contractNumber;     //合同号
	
	private String customerName;       //客户姓名
	
	private String employeeName;      //房产经纪人姓名
	
	private String contractType;     //合同类型
	
	private String area;            //地方
	
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")  
	private Date timeStart;

	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")  
	private Date timeEnd;

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@SuppressWarnings({ "serial"})
	public static Specification<Contract> getWhereClause(final ContractQueryDTO contractQueryDTO) {
		return new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (null!=contractQueryDTO.getUserId()) {
					predicate.add(criteriaBuilder.equal(root.get("userId").as(String.class),
							contractQueryDTO.getUserId()));
				}
				
				if(StringUtils.isNoneBlank(contractQueryDTO.getContractNumber())) {
					predicate.add(criteriaBuilder.like(root.get("contractNumber").as(String.class),
							"%" + contractQueryDTO.getContractNumber() + "%"));
				}
				if(StringUtils.isNoneBlank(contractQueryDTO.getCustomerName())) {
					predicate.add(criteriaBuilder.like(root.get("customerName").as(String.class),
							"%" + contractQueryDTO.getCustomerName() + "%"));
				}
				if(StringUtils.isNoneBlank(contractQueryDTO.getEmployeeName())) {
					predicate.add(criteriaBuilder.like(root.get("employeeName").as(String.class),
							"%" + contractQueryDTO.getEmployeeName() + "%"));
				}
				if(StringUtils.isNoneBlank(contractQueryDTO.getContractType())) {
					predicate.add(criteriaBuilder.like(root.get("contractType").as(String.class),
							"%" + contractQueryDTO.getContractType() + "%"));
				}
				if(StringUtils.isNoneBlank(contractQueryDTO.getArea())) {
					predicate.add(criteriaBuilder.like(root.get("area").as(String.class),
							"%" + contractQueryDTO.getArea() + "%"));
				}
				if (null!=contractQueryDTO.getTimeStart()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("time").as(Date.class),
							contractQueryDTO.getTimeStart()));
				}
				if (null!=contractQueryDTO.getTimeEnd()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("time").as(Date.class),
							contractQueryDTO.getTimeEnd()));
				}
				//predicate.add(criteriaBuilder.equal(root.get("id").as(Integer.class), userQueryDTO.getId()));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}

}
