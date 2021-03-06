package com.example.demo.contract.entity;

import java.util.Date;

import com.example.demo.activiti.domain.ProcessStatus;

public class ContractDTO {
	
	/**------------业务数据--------------**/
    private Long id;
	private String contractNumber;   
	private String customerName;      
	private String hoseName;         
	private String employeeName;     
	private Date startTime;          
	private Date endTime;           
	private String contractType;     
	private double total;          
	private String area;           
	private ProcessStatus processStatus;
    private String userId;
    
    /**------------流程数据--------------**/
    /*任务*/
    private String taskId;
    private String taskName;
    private Date   taskCreateTime;
    private String assignee;
    private String taskDefinitionKey;
    /*流程实例*/
    private String processInstanceId;
    /*流程图定义*/
    private String processDefinitionId;
    private boolean suspended;
    private int version;
    
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
	public Date getStartTime() {
		return startTime;
	}
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
	public ProcessStatus getProcessStatus() {
		return processStatus;
	}
	public String getUserId() {
		return userId;
	}
	public String getTaskId() {
		return taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public Date getTaskCreateTime() {
		return taskCreateTime;
	}
	public String getAssignee() {
		return assignee;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public boolean isSuspended() {
		return suspended;
	}
	public int getVersion() {
		return version;
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
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	public void setVersion(int version) {
		this.version = version;
	}

}
