package com.stefan.salapura.htec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stefan.salapura.htec.entity.City;
import com.stefan.salapura.htec.service.MainService;

@RestController
public class MyController {
	
	private MainService mainService;
	
	@Autowired
	public MyController(MainService theMainService) {
		this.mainService = theMainService;
	}
	
	
	@PostMapping("/addCity")
	private Object addCity(@RequestBody City theCity) {
		return mainService.testCascadeWithCityAndComment(theCity);
	}
	
	/*
	 * obrise grad i sve komentare vezane za taj grad
	 */
	@PostMapping("/deleteCity")
	private Object deleteCity(@RequestBody City theCity) {
		mainService.deleteCityAndComment(theCity);
		return "Go see database.";
	}
	
	/*
	 * obrise samo jedan komentar, sve ostalo ostane
	 */
	@GetMapping("/delete1Comment")
	private Object delete1Comment() {
		return mainService.deleteOneCommentOnly();
	}
	
	@GetMapping("/update")
	private Object updateCity() {
		return mainService.updateCity();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@GetMapping("/testRoutes")
//	public List<Route> testRoutes() {
//		return mainService.insertAndGetRoutesFromDatabase();
//	}
	
	
	
	
	
	
	
	
	

//	@GetMapping("/readFile")
//	public String readFile() {
//		return mainService.readFiles();
//	}
	
//	@GetMapping("/readFile2")
//	public List<Airport> readFile2() {
//		return mainService.readFiles2("Belgrade");
//	}
//	
//	@GetMapping("/readFile3")
//	public Airport readFile3() {
//		return mainService.readFiles3();
//	}
//	

	
	
	
	
	
	
	
	
//	@GetMapping("/test")
//	public String test() {
//		mainService.createAllObjectsAndPersist();
//		return "";
//	}
//	
//	@GetMapping("/commentFromCity")
//	public Object getCommentForCityObject() {
//		return mainService.getCommentFromCityObject();
//	}
//	
//	@GetMapping("/testUpdateComment")
//	public Comment updateComment() {
//		return mainService.updateComment();
//	}
	

	
	
	
	
	
	
	
//	@GetMapping("/persist")
//	public Object testSave() {
//		return mainService.persistApplicationUser(new ApplicationUser("Stefan", "Salapura", "salapura90", "Aristote90", true));
//	}
//
//	@GetMapping("/findByUsername")
//	public Object testFindByUsername() {
//		return mainService.findApplicationUserByUsername("salapura90");
//	}
//	
//	@GetMapping("/findById")
//	public Object testFindById() {
//		return mainService.findApplicationUserById(1);
//	}
	
}
