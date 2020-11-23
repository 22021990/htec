package com.stefan.salapura.htec.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.Airport;
import com.stefan.salapura.htec.entity.City;
import com.stefan.salapura.htec.repository.AirportRepository;

@Service
public class AirportService {

	private AirportRepository repository;
	private EntityManager entityManager;
	
	@Autowired
	public AirportService(AirportRepository theAirportRepository,
			EntityManager theEntityManager) {
		this.repository = theAirportRepository;
		this.entityManager = theEntityManager;
	}
	
	public Airport persistAirport(Airport theAirport) {
		return repository.save(theAirport);
	}
	
	public void persistAirports(List<Airport> airports) {
		Iterator<Airport> iterator = airports.iterator();
		while(iterator.hasNext()) {
			repository.save(iterator.next());
		}
	}
	
	public Airport findAirportById(int id) {
		Optional<Airport> optional = repository.findById(id);
		return optional.get();
	}
	
	public Airport findAirportByCityName(String cityName) {
		return repository.findByCity(cityName);
	}
	
	public List<Airport> findAllAirports() {
		return repository.findAll();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Airport> findAirportsForCity(City theCity) {
		Query query = entityManager.createQuery("from Airport where city=:city and country=:country");
		query.setParameter("city", theCity.getName());
		query.setParameter("country", theCity.getCountry());
		
		List<Airport> airportsForCity = query.getResultList(); 
		
		return airportsForCity;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Integer> findAirportIDs() {
		Query query = entityManager.createQuery("select id from Airport");
		
		List<Integer> airportIDs = null;
		try {
			airportIDs = query.getResultList();
		} catch(NoResultException noResultException) {
			//ignore
		}
		
		return airportIDs;
	}
	
}
