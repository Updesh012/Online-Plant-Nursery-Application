package com.masai.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	@NotNull
	private LocalDateTime dateAndTime;
	
	@NotNull(message =  "quantity is mandatory")
	@Min(value = 1,message = "Minimum quantity of Product must be greater than 0")
	private Integer quantity;
	
	
	private Double totalCost;
	
	private String productType;
	
	@NotNull
	private Integer productId;
	
	private String sessionId;
	
	
	
	
	

}
