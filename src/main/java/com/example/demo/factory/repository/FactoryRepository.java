package com.example.demo.factory.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.factory.entiry.Factory;

@Repository
public interface FactoryRepository extends PagingAndSortingRepository<Factory, Long>,JpaSpecificationExecutor<Factory>{

}
