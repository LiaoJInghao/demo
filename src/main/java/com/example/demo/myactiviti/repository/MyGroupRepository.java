package com.example.demo.myactiviti.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.myactiviti.entity.MyGroup;

@Repository
public interface MyGroupRepository extends PagingAndSortingRepository<MyGroup, Long>,JpaSpecificationExecutor<MyGroup>{

}
