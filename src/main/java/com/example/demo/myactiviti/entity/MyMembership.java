package com.example.demo.myactiviti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_mymembership")
public class MyMembership {
	
	private Long id;
	private String userId;
	private String groupId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public String getGroupId() {
		return groupId;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
