package com.example.demo.factory.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.factory.entity.Factory;

public interface IFactoryService {
	
	public void save(Factory factory);
	public void delete(Long id);
	public void deleteAll(Long[] ids);
	public Factory findOne(Long id);
	
	public Page<Factory> findAll(Specification<Factory> spec, Pageable pageable);

}
