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
		Query query = entityManager.createQuery("from City where name=:name and country=:country");
		query.setParameter("name", theCity.getName());
		query.setParameter("country", theCity.getCountry());
		
		City cityFromDatabase = null;
		try {
			cityFromDatabase = (City) query.getSingleResult();
		} catch(NoResultException noResultException) {
			//ignore
		}
		
		if(cityFromDatabase == null) {
			return false;
		}
		
		return true;
	}
	
}
