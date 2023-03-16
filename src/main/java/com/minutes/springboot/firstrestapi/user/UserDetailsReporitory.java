package com.minutes.springboot.firstrestapi.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsReporitory extends JpaRepository<UserDetails, Long>{

}
