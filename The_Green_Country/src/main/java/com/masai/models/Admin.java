package com.masai.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Admin {

	@Id
	@Min(value = 1, message = "Admin Id must be always more than 0  !!")
	@NotNull
	private Integer adminId;
	
	@NotEmpty(message = "password must not Empty or null!!")
	@Size(min = 3, max = 10, message = "admin passsword should contain min 3 and max 10 chars!!")
	private String adminPass;

	public Admin() {
		
	}
	

}
