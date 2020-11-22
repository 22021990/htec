package com.stefan.salapura.htec.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.ApplicationUser;
import com.stefan.salapura.htec.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {

	private ApplicationUserRepository repository;
	
	@Autowired
	public ApplicationUserService(ApplicationUserRepository theApplicationUserRepository) {
		this.repository = theApplicationUserRepository;
	}
	
	@Transactional
	public ApplicationUser persistApplicationUser(ApplicationUser theApplicationUser) {
		return repository.save(theApplicationUser);
	}
	
	@Transactional
	public ApplicationUser findApplicationUserById(int applicationUserId) {
		Optional<ApplicationUser> searchedUser = repository.findById(applicationUserId);
		return searchedUser.get();
	}
	
	@Transactional
	public ApplicationUser findApplicationUserByUsername(String username) {
		return repository.findByUserName(username);
	}
}
