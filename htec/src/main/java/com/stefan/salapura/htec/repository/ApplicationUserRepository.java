package com.stefan.salapura.htec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefan.salapura.htec.entity.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
	
	ApplicationUser findByUserName(String username);
}
