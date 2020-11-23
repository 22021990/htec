package com.stefan.salapura.htec.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.Airport;
import com.stefan.salapura.htec.entity.City;
import com.stefan.salapura.htec.entity.Route;

@Service
public class MainService {

	private ApplicationUserService userService;
	private CityService cityService;
	private CommentService commentService;
	private AirportService airportService;
	private RouteService routeService;
	
	private final String ROUTES_FILE_PATH = "src/main/resources/static/routes.txt";
	private final String AIRPORTS_FILE_PATH = "src/main/resources/static/airports.txt";
	
	@Autowired
	public MainService(ApplicationUserService theApplicationUserService,
			CityService theCityService,
			CommentService theCommentService,
			AirportService theAirportService,
			RouteService theRouteService) {
		this.userService = theApplicationUserService;
		this.cityService = theCityService;
		this.commentService = theCommentService;
		this.airportService = theAirportService;
		this.routeService = theRouteService;
	}
	
	
	public Object addCity(City theCity) {
		//check does city, country pair already exists in database
		if(cityService.alreadyExistsInDatabase(theCity)) {
			
			String returnMessage = theCity.getName() + ", " + theCity.getCountry() + " already exists in database. Process aborted.";
			return returnMessage;
		} else {
			//add new city
			cityService.persistCity(theCity);
			
			//search for airports for new city from file (airports.txt)
			List<Airport> airportsForNewCity = findAirportsFromFileForCity(theCity);
			
			//add new airports to database
			airportService.persistAirports(airportsForNewCity);
			
			//add new routes
//			addRoutesTest(theCity);
			
			//return airports from database
			return findAirportsFromDatabseForCity(theCity);
		}

	}
	
	
	public List<Airport> findAirportsFromFileForCity(City theCity) {
		List<Airport> airportsForCity = new LinkedList<Airport>();
		String testString = "\"" + theCity.getName() + "\"" + "," + "\"" + theCity.getCountry() + "\""; //"Paris","France"
		
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(AIRPORTS_FILE_PATH), StandardCharsets.UTF_8))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains(testString)) { 
					airportsForCity.add(new Airport(line));
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return airportsForCity;
	}
	
	public List<Airport> findAirportsFromDatabseForCity(City theCity) {
		List<Airport> airportsForCity = airportService.findAirportsForCity(theCity);
		return airportsForCity;
	}
	
	
	/*
	 * dodamo city
	 * dodamo airport
	 * na kraju route, sto znaci da imamo sve airport i cities vec dodate u bazu
	 * 
	 * radi, samo da probam da napisem bolji algoritam
	 */
	public void addRoutesTest(City theCity) {
		List<City> cities = cityService.findAllCities();
		if (cities.size() <= 1) {
			return;
		}

		List<Route> routesToAdd = new LinkedList<Route>();
		List<Integer> allAirportIDs = airportService.findAirportIDs();

		// mozemo ovo da radimo jer su vec dodati u bazu
		List<Airport> newCityAirports = airportService.findAirportsForCity(theCity);
		newCityAirports.forEach(newCityAirport -> {
			String keyWord = String.valueOf(newCityAirport.getId());

			List<Integer> restAirports = new ArrayList<Integer>(allAirportIDs);
			restAirports.remove(Integer.valueOf(newCityAirport.getId()));

			try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(ROUTES_FILE_PATH), StandardCharsets.UTF_8))) {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					if (line.contains(keyWord)) {
						Route route = new Route(line);
						if (route.getAirlineId() == Integer.valueOf(keyWord)
								|| route.getStops() == Short.valueOf(keyWord) || route.getEquipment().equals(keyWord)
								|| (!(route.getSourceAirportId() == Integer.valueOf(keyWord)) && !(route.getDestinationAirportId() == Integer.valueOf(keyWord)))) {
							continue;
						}

						Integer otherAirportId = (route.getSourceAirportId() == Integer.valueOf(keyWord))
								? route.getDestinationAirportId()
								: route.getSourceAirportId();

						if (restAirports.contains(otherAirportId)) {
							routesToAdd.add(route);
						}
					}
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}

		});
		
		routeService.persistRoutes(routesToAdd);
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<Route> insertAndGetRoutesFromDatabase() {
//		List<Route> routes = readXNumberOfRoutes(50);
//		routes.forEach(r -> routeService.persistRoute(r));
//		
//		List<Airport> airportsWithCityBelgrade = readFiles2("Belgrade");
//		Airport belgradeAirport = airportsWithCityBelgrade.get(0);
//		airportService.persistAirport(belgradeAirport);
//		
////		List<Route> selectedRoute = routeService.findRouteFromToWithAirportCode("KZN", "ASF");
//		List<Route> selectedRoute = routeService.findRouteFromToWithAirportId(2990, 2966);
//		return selectedRoute;
//	}
//	
//	public List<Route> readXNumberOfRoutes(int limit) {
//		String path = "src/main/resources/static/routes.txt";
//		List<Route> routes = new LinkedList<Route>();
//		try (BufferedReader bufferedReader = new BufferedReader(
//				new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
//			String line;
//			int x = 0;
//			while ((line = bufferedReader.readLine()) != null) {
//				Route route = new Route(line);
//				routes.add(route);
//				x++;
//				if(x > limit) {
//					break;
//				}
//			}
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//
//		return routes;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
//	public String readFiles() {
//		String path = "src/main/resources/static/airports.txt";
//		List<String> airports = new LinkedList<String>();
//		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//				airports.add(line);
//			}
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//		
//		return airports.get(177);
//	}
	
//	public String readFiles() {
//		String path = "src/main/resources/static/airports.txt";
//		List<String> airports = new LinkedList<String>();
//		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//				airports.add(line);
//			}
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//		
//		return airports.get(177);
//	}
//
//	public List<Airport> readFiles2(String cityName) {
//		String path = "src/main/resources/static/airports.txt";
//		List<Airport> airports = new LinkedList<Airport>();
//		try (BufferedReader bufferedReader = new BufferedReader(
//				new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//				if (line.contains(cityName)) {
//					Airport airport = new Airport(line);
//					if (airport.getCity().equals(cityName)) {
//						airports.add(airport);
//					}
//				} else {
//					continue;
//				}
//			}
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//
//		return airports;
//	}
//	
//	public List<Airport> readFiles2(int limit) {
//		String path = "src/main/resources/static/airports.txt";
//		List<Airport> airports = new LinkedList<Airport>();
//		try (BufferedReader bufferedReader = new BufferedReader(
//				new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
//			String line;
//			int x = 0;
//			while ((line = bufferedReader.readLine()) != null) {
//				Airport airport = new Airport(line);
//				airports.add(airport);
//				x++;
//				if(x > limit) {
//					break;
//				}
//			}
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//
//		return airports;
//	}
//	
//	public Airport readFiles3() {
//		String path = "src/main/resources/static/airports.txt";
//		List<String> airports = new LinkedList<String>();
//		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
//				airports.add(line);
//			}
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//		
//		return new Airport(airports.get(1685));
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void createAllObjectsAndPersist() {
//		ApplicationUser user = userService.persistApplicationUser(new ApplicationUser("Stefan", "Salapura", "salapura90", "Aristotel90", true));
//		City city = cityService.persistCity(new City("Belgrade", "Serbia", "Belgrade is the capital of the southeast European country of Serbia. Its most significant landmark is the Beogradska Tvrđava, an imposing fortress at the confluence of the Danube and the Sava rivers. The fort is a testament to the city’s strategic importance to the Roman, Byzantine, Ottoman, Serbian and Austrian empires, and it's now the site of several museums as well as Kalemegdan, a vast park."));
//		
//		Comment comment = new Comment("Beautiful city!!! FANTASTIC NIGHT LIFE! I recommend it to everyone <3");
//		comment.setCityId(city.getId());
//		comment.setApplicationUserId(user.getId());
//		commentService.persistComment(comment);
//	}
//	
//	public Object getCommentFromCityObject() {
//		City city = cityService.findCityById(1);
//		Set<Comment> comments = city.getComments();
//		
//		if(comments.isEmpty()) {
//			return "city.getComments() returned an empty set.";
//		}
//		
//		Iterator<Comment> iterator = comments.iterator();
//		Comment firstComment = iterator.next();
//		return firstComment;
//	}
//	
//	public Comment updateComment() {
//		Comment comment = (Comment) getCommentFromCityObject();
//		comment.setDescription("Terrible, dirty city. I'm not going back to Belgrade ever again!");
//		return commentService.updateComment(comment);
//	}
	
}
