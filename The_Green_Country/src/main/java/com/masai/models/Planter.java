package com.masai.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Planter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer planterId;
	
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
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seed> seeds = new ArrayList<>(); 
	
	
}
