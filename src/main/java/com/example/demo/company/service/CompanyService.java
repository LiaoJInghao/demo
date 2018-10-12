package com.example.demo.company.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.company.entity.Company;
import com.example.demo.company.repository.CompanyRepository;

@Service
@Transactional
public class CompanyService implements ICompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public void save(Company company) {
		companyRepository.save(company);
	}

	@Override
	public void delete(Long id) {
		companyRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Company> orders = (List<Company>) companyRepository.findAllById(idLists);
		if(orders!=null) {
			companyRepository.deleteAll(orders);
		}
	}

	@Override
	public Company findOne(Long id) {
		return companyRepository.findById(id).get();
	}

	@Override
	public Page<Company> findAll(Specification<Company> spec, Pageable pageable) {
		return companyRepository.findAll(spec, pageable);
	}

}
