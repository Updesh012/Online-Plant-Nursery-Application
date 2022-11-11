package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.Planter;

@Repository
public interface PlanterRepo extends JpaRepository<Planter, Integer>{

	
	
}
