package com.example.demo.contract.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.activiti.domain.ProcessStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_contract")
public class Contract {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String contractNumber;     //合同号
	
	private String customerName;       //客户姓名
	
	private String hoseName;          //房源名称
	
	private String employeeName;      //房产经纪人姓名
	
	private Date startTime;          //签约时间
	
	private Date endTime;             //失效时间
	
	private String contractType;     //合同类型
	
	private double total;           //金额
	
	private String area;           //地方
	
	private ProcessStatus processStatus;//流程状态
    //工作流程数据字段
    private String userId;//启动流程的用户ID
    //流程实例Id：用于关联流程引擎相关数据没有启动流程之前为""
    private String processInstanceId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getHoseName() {
		return hoseName;
	}

	public String getEmployeeName() {
		return employeeName;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getStartTime() {
		return startTime;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getEndTime() {
		return endTime;
	}

	public String getContractType() {
		return contractType;
	}

	public double getTotal() {
		return total;
	}
	
	public String getArea() {
		return area;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProcessStatus getProcessStatus() {
		return processStatus;
	}

	public String getUserId() {
		return userId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setHoseName(String hoseName) {
		this.hoseName = hoseName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public void setProcessStatus(ProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", contractNumber=" + contractNumber + ", customerName=" + customerName
				+ ", hoseName=" + hoseName + ", employeeName=" + employeeName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", contractType=" + contractType + ", total=" + total + ", area=" + area
				+ "]";
	}
	
}
