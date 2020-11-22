package com.stefan.salapura.htec.entity;

import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 1739,"Belgrade Nikola Tesla Airport","Belgrade","Serbia","BEG","LYBE",44.8184013367,20.3090991974,335,1,"E","Europe/Belgrade","airport","OurAirports"
 * 
 * Field:					Value:								Description:
 * 
 * Airport ID				1739
 * Name						"Belgrade Nikola Tesla Airport"
 * City						"Belgrade"
 * Country					"Serbia"
 * IATA						"BEG"                        		3-letter IATA code. Null if not assigned/unknown.
 * ICAO						"LYBE"								4-letter ICAO code. Null if not assigned.					
 * Latitude					44.818401336
 * Longitude				20.3090991974
 * Altitude					335
 * Timezone					1									Hours offset from UTC.
 * DST						"E"									Daylight saving time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand), N (None) or U (Unknown).
 * Tz database time zone	"Europe/Belgrade"					Timezone in "tz" (Olson) format, eg. "America/Los_Angeles".
 * Type						"airport"
 * Source					"OurAirports"
 */

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@ToString 
public class Airport {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String city;
	private String country;
	private String iata;
	private String icao;
	private Double latitude;
	private Double longitude;
	private int altitude;
	private short timezone;
	private Character dst;
	private ZoneId tz; //= ZoneId.of( "Europe/Belgrade" ) ;
	private String type;
	private String source;
	
	public Airport(String name, String city, String country, String iata, String icao, Double latitude,
			Double longitude, int altitude, short timezone, Character dst, ZoneId tz, String type, String source) {
		this.name = name;
		this.city = city;
		this.country = country;
		this.iata = iata;
		this.icao = icao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.timezone = timezone;
		this.dst = dst;
		this.tz = tz;
		this.type = type;
		this.source = source;
	}
	
}














































