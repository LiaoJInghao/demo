package com.example.demo.myactiviti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_mygroup")
public class MyGroup {
	
	private Long id;
	private String post;
	private String groupName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getPost() {
		return post;
	}
	public String getGroupName() {
		return groupName;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
