package com.masai.services;

import java.util.List;

import com.masai.exceptions.PlantNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.Plant;
import com.masai.models.Seed;
import com.masai.models.SeedDto;

public interface SeedService {
	
	public Seed addSeed(Seed seed) throws SeedNotFoundException;
	
	public Seed updateSeed(Seed seed) throws SeedNotFoundException;
	
	public List<Seed> getAllSeeds() throws SeedNotFoundException;
	
	public Seed deleteSeedById(Integer seedId) throws SeedNotFoundException;
	
	public Seed viewSeedById(Integer seedId) throws SeedNotFoundException;
	
	public List<Seed> viewSeedByName(String name) throws SeedNotFoundException;
	
	public List<Seed> viewSeedsBySeedType(String type) throws SeedNotFoundException;
	
	public Seed changeQuantityOfSeedBySeedId(Integer seedid,Integer newQuantity) throws SeedNotFoundException;
	
	public Seed changePriceOfSeedBySeedId(Integer seedid,Double newPrice) throws SeedNotFoundException;
	
	
	

}
