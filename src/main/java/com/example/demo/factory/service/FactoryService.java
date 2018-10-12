package com.example.demo.factory.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.factory.entity.Factory;
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
	public Factory findOne(Long id) {
		return factoryRepository.findById(id).get();
	}

	@Override
	public Page<Factory> findAll(Specification<Factory> spec, Pageable pageable) {
		return factoryRepository.findAll(spec, pageable);
	}

}
