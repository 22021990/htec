package com.stefan.salapura.htec.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.Route;
import com.stefan.salapura.htec.repository.RouteRepository;

@Service
public class RouteService {

	private RouteRepository repository;
	private EntityManager entityManager;
	
	@Autowired
	public RouteService(RouteRepository theRouteRepository,
			EntityManager theEntityManager) {
		this.repository = theRouteRepository;
		this.entityManager = theEntityManager;
	}
	
	public Route persistRoute(Route theRoute) {
		return repository.save(theRoute);
	}
	
	public void persistRoutes(List<Route> routes) {
		Iterator<Route> iterator = routes.iterator();
		while(iterator.hasNext()) {
			repository.save(iterator.next());
		}
	}
	
	public Route findRouteById(int id) {
		Optional<Route> optional = repository.findById(id);
		return optional.get();
	}
	
	public List<Route> findAllRoutes() {
		return repository.findAll();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Route> findRouteFromToWithAirportCode(String sourceAirport, String destinationAirport) {
		Query query = entityManager.createQuery("from Route where source_airport=:sourceAirport and destination_airport=:destinationAirport order by price asc");
		query.setParameter("sourceAirport", sourceAirport);
		query.setParameter("destinationAirport", destinationAirport);
		
		return query.getResultList();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Route> findRouteFromToWithAirportId(int sourceAirportId, int destinationAirportId) {
		Query query = entityManager.createQuery("from Route where source_airport_id=:sourceAirportId and destination_airport_id=:destinationAirportId order by price asc");
		query.setParameter("sourceAirportId", sourceAirportId);
		query.setParameter("destinationAirportId", destinationAirportId);
		
		return query.getResultList();
	}
}
