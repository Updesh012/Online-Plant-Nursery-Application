package com.masai.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.PlantNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.Plant;
import com.masai.models.Seed;
import com.masai.repositories.SeedRepo;

@Service
public class SeedServiceImpl implements SeedService{

	@Autowired
	private SeedRepo sRepo;
	
	@Override
	public Seed addSeed(Seed seed) throws SeedNotFoundException{
		
		if(sRepo.findById(seed.getSeedId()).isPresent()) {
			
			throw new SeedNotFoundException("Seed already exist with this Seed Id");
		}else {
			
			Seed obj = sRepo.save(seed);
			return obj;
		}
				
	}
			
		
		
		

	
	
	@Override
	public Seed updateSeed( Seed seed) throws SeedNotFoundException {

		Optional<Seed> opt = sRepo.findById(seed.getSeedId());

		if (opt.isPresent()) {

			return sRepo.save(seed);

		} else {
			throw new SeedNotFoundException("No Seed found with this SeedId");
		}

	}

	
	
	@Override
	public List<Seed> getAllSeeds() throws SeedNotFoundException {

		List<Seed> list = sRepo.findAll();

		if (list.isEmpty()) {
			throw new SeedNotFoundException("No seed found...");
		} else {
			return list;
		}

	}

	
	
	@Override
	public Seed deleteSeedById(Integer seedId) throws SeedNotFoundException {

		Optional<Seed> opt = sRepo.findById(seedId);

		if (opt.isPresent()) {
			Seed seed = opt.get();
			sRepo.delete(seed);
			
			return seed;
		} else {
			throw new SeedNotFoundException("No seed present with this SeedId");
		}

	}

	
	
	@Override
	public Seed viewSeedById(Integer seedId) throws SeedNotFoundException {

		Optional<Seed> seed = sRepo.findById(seedId);

		if (seed.isPresent()) {

			return seed.get();

		} else {
			throw new SeedNotFoundException("Seed does not exist with this SeedId");
		}

	}

	
	
	@Override
	public List<Seed> viewSeedByName(String name) throws SeedNotFoundException {

		List<Seed> list = sRepo.findByName(name);

		if (list.isEmpty()) {

			throw new SeedNotFoundException("Seed does not exist with this Name");
		} else {
			return list;
		}
		

	}

	@Override
	public List<Seed> viewSeedsBySeedType(String type) throws SeedNotFoundException {

		List<Seed> list = sRepo.findByTypeOfSeed(type);

		if (list.isEmpty()) {

			throw new SeedNotFoundException("There is no Seeds exist with this SeedType");
		} else {

			return list;
		}

	}


	
	
	@Override
	public Seed changeQuantityOfSeedBySeedId(Integer seedid, Integer newQuantity) throws SeedNotFoundException {

		Optional<Seed> opt = sRepo.findById(seedid);

		if (opt.isPresent()) {
			Seed seed = opt.get();
			seed.setStock(newQuantity);
			sRepo.save(seed);
			return seed;
		} else {

			throw new SeedNotFoundException("Seed does not exist with this SeedId");
		}

	}

	
	
	@Override
	public Seed changePriceOfSeedBySeedId(Integer seedid, Double newPrice) throws SeedNotFoundException {

		Optional<Seed> opt = sRepo.findById(seedid);

		if (opt.isPresent()) {
			Seed seed = opt.get();
			seed.setCost(newPrice);
			sRepo.save(seed);
			return seed;
		} else {

			throw new SeedNotFoundException("Seed does not exist with this SeedId");
		}

	}

	
	
	
	
}
