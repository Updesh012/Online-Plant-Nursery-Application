package com.masai.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeedDto {
	
	@NotBlank(message = "Seed Name is Mandatory...")
	@Pattern(regexp = "^[A-Za-z]+$")
	private String name;
	
	@Pattern(regexp = "^[A-Za-z]+$")
	private String bloomTime;
	
	@Pattern(regexp = "^[A-Za-z]+$")
	private String watering;
	
	@NotBlank(message = "Temperature Field is mandatory")
	private String temperature;
	
	@NotBlank(message = "Type of Seed field is mandatory")
	@Pattern(regexp = "^[A-Za-z]+$")
	private String typeOfSeed;
	
	
	private String description;
	
	@NotNull(message = "stock value can not be null")
	@Min(value =  0,message = "stock value must be greater than equal to 0")
	private Integer stock;
	
	@NotNull
	@Min(value = 1 , message = "stock value must be greater than equal to 1")
	private Double cost;

	

}
