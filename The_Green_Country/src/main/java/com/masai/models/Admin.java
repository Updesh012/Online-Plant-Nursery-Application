package com.masai.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Admin {

	@Id
	private Integer adminId;
	private String adminPass;

	public Admin() {
		
	}
	

}
