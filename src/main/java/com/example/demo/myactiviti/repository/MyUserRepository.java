package com.example.demo.myactiviti.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.myactiviti.entity.MyUser;

@Repository
public interface MyUserRepository extends PagingAndSortingRepository<MyUser, Long>,JpaSpecificationExecutor<MyUser>{

}
