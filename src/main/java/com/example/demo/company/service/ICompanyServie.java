package com.example.demo.company.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.company.entity.Company;

public interface ICompanyServie {
	public void save(Company company);
	public void delete(Long id);
	public void deleteAll(Long[] ids);
	public Optional<Company> findOne(Long id);
	
	public Page<Company> findAll(Specification<Company> spec, Pageable pageable);
}
