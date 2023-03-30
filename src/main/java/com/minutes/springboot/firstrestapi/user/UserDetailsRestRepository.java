package com.minutes.springboot.firstrestapi.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserDetailsRestRepository extends PagingAndSortingRepository<UserDetails, Long>{
	// findBy"Field" is a jpaRepository method to search through a field declared in the entity
	//http://localhost:8080/userDetailses?sort=role
	List<UserDetails> findByRole(String role);
}
