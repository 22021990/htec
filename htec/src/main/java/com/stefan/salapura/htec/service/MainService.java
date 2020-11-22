package com.stefan.salapura.htec.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.Airport;

@Service
public class MainService {

	private ApplicationUserService userService;
	private CityService cityService;
	private CommentService commentService;
	
	@Autowired
	public MainService(ApplicationUserService theApplicationUserService,
			CityService theCityService,
			CommentService theCommentService) {
		this.userService = theApplicationUserService;
		this.cityService = theCityService;
		this.commentService = theCommentService;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
//				if (!(line.contains("Belgrade"))) {
//					continue;
//				} else {
//					Airport airport = new Airport(line);
//					if (airport.getName().equals(cityName)) {
//						airports.add(airport);
//					}
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
