package com.stefan.salapura.htec.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@ToString
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private boolean administrator;
	private String salt;
	private String sha256hex;

	public ApplicationUser(String firstName, String lastName, String userName, String password,
			boolean administrator) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.administrator = administrator;
		
		this.salt = RandomStringUtils.randomAlphabetic(10);
		this.sha256hex = DigestUtils.sha256Hex(this.password + this.salt);
	}

}
