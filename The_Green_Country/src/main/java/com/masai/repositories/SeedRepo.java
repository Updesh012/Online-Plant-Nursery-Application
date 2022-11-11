package com.masai.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.Plant;
import com.masai.models.Seed;

@Repository
public interface SeedRepo extends JpaRepository<Seed, Integer>{

	public List<Seed> findByName(String name);
	
	public List<Seed> findByTypeOfSeed(String type);
	
}
