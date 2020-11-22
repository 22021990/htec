package com.stefan.salapura.htec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefan.salapura.htec.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
