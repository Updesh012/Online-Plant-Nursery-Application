package com.masai.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.Plant;

@Repository
public interface PlantRepo extends JpaRepository<Plant, Integer>{

	public List<Plant> findByCommonName(String name);
	
	public List<Plant> findByTypeOfPlant(String type);
	
	
}
