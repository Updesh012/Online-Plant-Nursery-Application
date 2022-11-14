package com.masai.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Plant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer plantId;
	
	@NotNull(message = "plant height can not be null")
	@Min(value = 1, message = "Planter height is must be greater than equal to 1")
	private Integer plantHeight;
	
	
	private String bloomTime;
	
	@NotBlank(message = "TypeOfPlant can not be blank")
//	@Pattern(regexp = "[a-z]" , message = "TypeOfPlant must contain only lower or upper case alphabet")
	private String typeOfPlant;
	
	@NotBlank(message = "Plant name can not be blank")
//	@Pattern(regexp = "[a-zA-Z]" , message = "Plant Name must contain only lower or upper case alphabet")
	private String commonName;
	
	private String Exposure;
	

	private String flowerColor;
	
	
	private String temperature;
	
	private String description;

	@NotNull(message = "Plant Stock can not be null")
	@Min(value = 1, message = "Planter height is must be greater than equal to 1")
	private Integer plantsStock;
	
	@NotNull(message = "plant cost can not be null")
	@Min(value = 1, message = "Planter height is must be greater than equal to 1")
	private Double plantCost;
	
	
}
