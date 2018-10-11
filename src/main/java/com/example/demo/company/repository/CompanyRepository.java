package com.example.demo.company.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.company.entity.Company;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long>,JpaSpecificationExecutor<Company>{

}
