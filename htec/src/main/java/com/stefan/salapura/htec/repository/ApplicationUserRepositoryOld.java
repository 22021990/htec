package com.stefan.salapura.htec.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stefan.salapura.htec.entity.ApplicationUser;

@Repository
public class ApplicationUserRepositoryOld {

	private EntityManager entityManager;
	
	@Autowired
	public ApplicationUserRepositoryOld(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}
	
	public ApplicationUser addNewApplicationUser(ApplicationUser theApplicationUser) {
		entityManager.persist(theApplicationUser);
		return theApplicationUser;
	}
	
	public ApplicationUser findApplicationUserById(int id) {
		ApplicationUser user = null;
		user = entityManager.find(ApplicationUser.class, id);
		return user;
	}
	
	public ApplicationUser findApplicationUserByUsername(String username) {
		Query query = entityManager.createQuery("from ApplicationUser where userName=:username");
		query.setParameter("username", username);
		
		ApplicationUser user = null;
		user = (ApplicationUser) query.getSingleResult();
		return user;
	}
	
}
