package com.stefan.salapura.htec.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
//@ToString
public class Comment {
	
	//TODO:Add unique constraint (city_id, user_id)
	 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 700, nullable = false)
	private String description;
	@Column(nullable = false)
	private Timestamp timeCreated;
	private Timestamp timeModified;
	
	@ManyToOne(cascade=CascadeType.PERSIST /*{CascadeType.PERSIST, CascadeType.REMOVE}*/)
	@JoinColumn(name="city_id")
	@JsonIgnore
	private City city;
	private int applicationUserId;
	
	public Comment(String description) {
		this.description = description;
		this.timeCreated = new Timestamp(new java.util.Date().getTime());
	}
	
	@Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Comment)) {
            return false;
        }
        
        Comment comment = (Comment) o;
        return this.id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    /*
     * ovde printujemo cityId da bi sprecili StackOverflowError -> String objekat preplavi stack memoriju jer imamo 'rekurziju'.
     * comment objekat u sebi ima referencu ka city objektu, koji u sebi ima referencu ka comment objektu, koji u sebi ima referencu ka city objektu itd...
     * u stvari imamo samo dva objekta u memoriji ali imamo tu rekurziju sa referencama i ako pozovemo toString(), return String ce postati prevelik u jednom trenutku.
     */
	@Override
	public String toString() {
		return "Comment [id=" + id + ", description=" + description + ", timeCreated=" + timeCreated + ", timeModified="
				+ timeModified + ", cityId=" + city.getId() + ", applicationUserId=" + applicationUserId + "]";
	}
    
}
