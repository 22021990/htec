package com.stefan.salapura.htec.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.City;
import com.stefan.salapura.htec.repository.CityRepository;

@Service
public class CityService {

	private CityRepository repository;
	private EntityManager entityManager;
	
	@Autowired
	public CityService(CityRepository theCityRepository,
			EntityManager theEntityManager) {
		this.repository = theCityRepository;
		this.entityManager = theEntityManager;
	}
	
	public City persistCity(City theCity) {
		return repository.save(theCity);
	}
	
	public City findCityById(int id) {
		Optional<City> searchedCity = repository.findById(id);
		return searchedCity.get();
	}
	
	public List<City> findAllCities() {
		return repository.findAll();
	}
	
	public void deleteCityById(int id) {
		repository.deleteById(id);
	}
	
	public void deleteCity(City theCity) {
		repository.delete(theCity);
	}
	
	@Transactional
	public boolean alreadyExistsInDatabase(City theCity) {
		City cityFromDatabase = searchByNameAndCountry(theCity);
		
		if(cityFromDatabase == null) {
			return false;
		}
		
		return true;
	}
	
	@Transactional
	public City findCityByNameAndCountry(City theCity) {
		return searchByNameAndCountry(theCity);
	}
	
	@Transactional
	public void refreshEntity(City theCity) {
		entityManager.refresh(theCity);
	}
	
	@Transactional
	public void mergeCity(City theCity) {
		entityManager.merge(theCity);
	}
	
//	@Transactional
//	public void test() {
//		System.out.println("brmbrmmmmm");
//	}
	
	private City searchByNameAndCountry(City theCity) {
		Query query = entityManager.createQuery("from City where name=:name and country=:country");
		query.setParameter("name", theCity.getName());
		query.setParameter("country", theCity.getCountry());
		
		City cityFromDatabase = null;
		try {
			cityFromDatabase = (City) query.getSingleResult();	// if there isn't one it throws NoResultException
		} catch(NoResultException noResultException) {
			//ignore
		}
		
		return cityFromDatabase;
	}
	
}
