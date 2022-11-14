package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seedId;
	
	@NotBlank(message = "Seed Name is Mandatory...")
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String name;
	
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String bloomTime;
	
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String watering;
	
	@NotBlank(message = "Temperature Field is mandatory")
	private String temperature;
	
	@NotBlank(message = "Type of Seed field is mandatory")
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String typeOfSeed;
	
	
	private String description;
	
	@NotNull(message = "stock value can not be null")
	@Min(value =  0,message = "stock value must be greater than equal to 0")
	private Integer stock;
	
	@NotNull
	@Min(value = 1 , message = "stock value must be greater than equal to 1")
	private Double cost;

	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "planterId")
//	@JsonIgnore
//	private Planter planter;



	
	
	
	
	
}
