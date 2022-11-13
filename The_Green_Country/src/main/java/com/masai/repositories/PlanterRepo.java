package com.masai.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.models.Planter;
import com.masai.models.Seed;

@Repository
public interface PlanterRepo extends JpaRepository<Planter, Integer>{

	@Query("select p.seeds from Planter p where p.planterId = ?1")
	public List<Seed> getAllSeedsByPlanterId(Integer id);
	
	
	
}
