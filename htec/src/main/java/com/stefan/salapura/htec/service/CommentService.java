package com.stefan.salapura.htec.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefan.salapura.htec.entity.Comment;
import com.stefan.salapura.htec.repository.CommentRepository;

@Service
public class CommentService {

	private EntityManager entityManager;
	private CommentRepository repository;
	
	@Autowired 
	public CommentService(CommentRepository theCommentRepository,
			EntityManager theEntityManager) {
		this.repository = theCommentRepository;
		this.entityManager = theEntityManager;
	}
	
	public Comment persistComment(Comment theComment) {
		return repository.save(theComment);
	}
	
	public Comment findCommentById(int id) {
		Optional<Comment> optional = repository.findById(id);
		return optional.get();
	}
	
	public void deleteComment(Comment theComment) {
		repository.delete(theComment);
	}
	
	public void deleteCommentById(int id) {
		repository.deleteById(id);
	}
	
	public List<Comment> findAllComments() {
		return repository.findAll();
	}
	
	public List<Comment> findXNumberOfComments(int x) {
		List<Comment> allComments = findAllComments();
		List<Comment> xNumberOfComments = allComments.stream()
				.limit(x)
				.collect(Collectors.toList());
		
		return xNumberOfComments;
	}
	
	@Transactional
	public Comment updateComment(Comment theComment) {
		theComment.setTimeModified(new Timestamp(new java.util.Date().getTime()));
		return entityManager.merge(theComment);
	}
}
