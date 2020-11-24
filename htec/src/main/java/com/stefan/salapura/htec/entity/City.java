package com.stefan.salapura.htec.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@ToString
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String country;
	@Column(length = 700, nullable = false)
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cityId", referencedColumnName="id")
	private Set<Comment> comments;

	public City(String name, String country, String description) {
		this.name = name;
		this.country = country;
		this.description = description;
	}
	
}
