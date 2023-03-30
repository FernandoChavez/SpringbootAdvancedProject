package com.minutes.springboot.firstrestapi.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{
	// findBy"Field" is a jpaRepository method to search through a field declared in the entity
	List<UserDetails> findByRole(String role);
}
