package com.masai.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanterDto {
	
	@NotNull(message = "Planter height should not be null")
	@Min(value = 1, message = "Planter height is must be greater than equal to 1")
	private Integer planterHeight;
	
	@NotNull(message = "Planter capacity should not be null")
	@Min(value = 1, message = "Planter capacity is must be greater than equal to 1")
	private Integer PlanterCapacity;
	
	@NotNull(message = "Planter Holes should not be null")
	@Min(value = 1, message = "Planter holes is must be greater than equal to 1")
	private Integer planterHoles;
	
	private String planterColor;
	
	private String planterShape;
	
	@NotNull(message = "Planter Stock should not be null")
	@Min(value = 0, message = "Planter stock is must be greater than equal to 1")
	private Integer PlanterStock;
	
	@NotNull(message = "Planter Cost should not be null")
	@Min(value = 1, message = "Planter cost is must be greater than equal to 1")
	private Double PlanterCost;
	
	
	

}
