package com.stefan.salapura.htec.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String country;
	private String description;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="comment_id")
	private Comment comment;

	public City(String name, String country, String description) {
		this.name = name;
		this.country = country;
		this.description = description;
	}
	
}
