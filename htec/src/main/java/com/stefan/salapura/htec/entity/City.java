package com.stefan.salapura.htec.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	/*
	 * Namestio sam da radi kako zelim. Na grad se dodaju komentari, mogu da se brisu pojedinacno.
	 * Ako se obrise grad brisu se i svi komentari vezani za grad nadam se.
	 * 
	 * Ali uglavnom ne razumem bas funcionisanje hiberanate-a.
	 * Mislim da pravim pauzu sa ovim projektom dok ne shvatim kako baza funcionise.
	 * 
	 * 
	 * 2020-11-24
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String country;
	@Column(length = 700, nullable = false)
	private String description;
	
	@OneToMany(mappedBy = "city", orphanRemoval = true)
	private Set<Comment> comments = new HashSet<Comment>();

	public City(String name, String country, String description) {
		this.name = name;
		this.country = country;
		this.description = description;
	}
	
}
