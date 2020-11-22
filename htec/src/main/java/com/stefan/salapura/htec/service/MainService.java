package com.stefan.salapura.htec.service;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.ApplicationUser;
import com.stefan.salapura.htec.entity.City;
import com.stefan.salapura.htec.entity.Comment;

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
