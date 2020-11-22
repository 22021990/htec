package com.stefan.salapura.htec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.City;
import com.stefan.salapura.htec.repository.CityRepository;

@Service
public class CityService {

	private CityRepository repository;
	
	@Autowired
	public CityService(CityRepository theCityRepository) {
		this.repository = theCityRepository;
	}
	
	public City persistCity(City theCity) {
		return repository.save(theCity);
	}
	
	public City findCityById(int id) {
		Optional<City> searchedCity = repository.findById(id);
		return searchedCity.get();
	}
	
	public void deleteCityById(int id) {
		repository.deleteById(id);
	}
	
	public void deleteCity(City theCity) {
		repository.delete(theCity);
	}
	
}
