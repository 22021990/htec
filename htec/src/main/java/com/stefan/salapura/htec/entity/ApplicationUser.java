package com.stefan.salapura.htec.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class ApplicationUser {

	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String salt;
	private boolean administrator;
	
	public ApplicationUser(String firstName, String lastName, String userName, String password, String salt,
			boolean administrator) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.salt = salt;
		this.administrator = administrator;
	}
}
