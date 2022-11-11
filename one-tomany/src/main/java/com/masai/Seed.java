package com.masai;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Seed {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "field can't be null")
	@Pattern(regexp = "^[A-Za-z]+$")
	private String name;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Planter planter;
	
	@Override
	public String toString() {
		
		return "";
		
	}
	
	

}
