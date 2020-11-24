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
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * mozda da unosim vrednosti iz fajla za id.
	 */
	private int id;
	private String name;
	private String city;
	private String country;
	private String iata;
	private String icao;
	private Double latitude;
	private Double longitude;
	private int altitude;
	private Short timezone;
	private Character dst;
	private ZoneId tz; //= ZoneId.of("Europe/Belgrade") ;
	private String type;
	private String source;
	
	public Airport(String lineFromFile) {
		lineFromFile = lineFromFile.replaceAll("\"", "");
		lineFromFile = lineFromFile.replaceAll(", ", "*");
		String[] fields = lineFromFile.split(",");
		
		this.id = Integer.valueOf(fields[0]);
		if(fields[1].contains("*")) {
			String airportName = fields[1].replace("*", ", ");
			this.name = airportName;
		} else {
			this.name = fields[1];
		}
		if(fields[2].contains("*")) {
			String cityName = fields[2].replace("*", ", ");
			this.city = cityName;
		} else {
			this.city = fields[2];
		}
		this.country = fields[3];
		this.iata = fields[4];
		this.icao = fields[5];
		this.latitude = Double.valueOf(fields[6]);
		this.longitude = Double.valueOf(fields[7]);
		this.altitude = Integer.valueOf(fields[8]);
		try {
			this.timezone = Short.valueOf(fields[9]);
		} catch(Exception exc) {
			//invalid Timezone, has decimal point! if invalid leave null value
			this.timezone = null;
		}
		this.dst = fields[10].charAt(0);
		this.tz = ZoneId.of(fields[11]);
		this.type = fields[12];
		this.source = fields[13];
	}
	
}














































