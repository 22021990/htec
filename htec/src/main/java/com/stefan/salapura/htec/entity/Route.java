package com.stefan.salapura.htec.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 2B,410,AER,2965,KZN,2990,,0,CR2,95.87
 * 
 * Field:					Value:		Description:
 * 
 * Airline  				2B			2-letter (IATA) or 3-letter (ICAO) code of the airline.
 * Airline ID 				410
 * Source airport			AER			3-letter (IATA) or 4-letter (ICAO) code of the source airport.
 * Source airport ID		2965		Identifier for source airport.
 * Destination airport		KZN			3-letter (IATA) or 4-letter (ICAO) code of the destination airport.
 * Destination airport ID	2990	
 * Codeshare							"Y" if this flight is a codeshare, empty otherwise.
 * Stops					0			Number of stops on this flight ("0" for direct).
 * Equipment				CR2			3-letter codes for plane type(s) generally used on this flight, separated by spaces.
 * Price					95.87		Flight cost.
 */

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@ToString 
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	//	IdentityGenerator, meaning they are auto-incremented. 
	private int id;
	private String airline;
	private int airlineId;
	private String sourceAirport;
	private int sourceAirportId;
	private String destinationAirport;
	private int destinationAirportId;
	private Character codeshare;
	private short stops;
	private String equipment;
	private float price;

	public Route(String airline, int airlineId, String sourceAirport, int sourceAirportId, String destinationAirport,
			int destinationAirportId, Character codeshare, short stops, String equipment, float price) {
		this.airline = airline;
		this.airlineId = airlineId;
		this.sourceAirport = sourceAirport;
		this.sourceAirportId = sourceAirportId;
		this.destinationAirport = destinationAirport;
		this.destinationAirportId = destinationAirportId;
		this.codeshare = codeshare;
		this.stops = stops;
		this.equipment = equipment;
		this.price = price;
	}

}
