package com.stefan.salapura.htec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefan.salapura.htec.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer>{

	Airport findByCity(String cityName);
}
