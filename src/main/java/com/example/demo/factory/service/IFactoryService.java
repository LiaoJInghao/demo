package com.example.demo.factory.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.factory.entiry.Factory;


public interface IFactoryService {
	public void save(Factory factory);
	public void delete(Long id);
	public void deleteAll(Long[] ids);
	public Optional<Factory> findOne(Long id);
	
	public Page<Factory> findAll(Specification<Factory> spec, Pageable pageable);

}
