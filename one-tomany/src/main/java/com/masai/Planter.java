package com.masai;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Planter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pId;
	private String pname;
	private Double cost;
	
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "planter")
	private List<Seed> list = new ArrayList<>();
	
	@Override
	public String toString() {
		
		return "";
		
	}
}
