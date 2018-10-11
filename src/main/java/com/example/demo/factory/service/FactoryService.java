package com.example.demo.factory.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.company.entity.Company;
import com.example.demo.factory.entiry.Factory;
import com.example.demo.factory.repository.FactoryRepository;

@Service
@Transactional
public class FactoryService implements IFactoryService {
	
	@Autowired
	private FactoryRepository factoryRepository;

	@Override
	public void save(Factory factory) {
		factoryRepository.save(factory);
	}

	@Override
	public void delete(Long id) {
		factoryRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Factory> factorys = (List<Factory>) factoryRepository.findAllById(idLists);
		if(factorys!=null) {
			factoryRepository.deleteAll(factorys);
		}
	}

	@Override
	public Optional<Factory> findOne(Long id) {
		// TODO Auto-generated method stub
		return factoryRepository.findById(id);
	}

	@Override
	public Page<Factory> findAll(Specification<Factory> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return factoryRepository.findAll(spec, pageable);
	}

}
