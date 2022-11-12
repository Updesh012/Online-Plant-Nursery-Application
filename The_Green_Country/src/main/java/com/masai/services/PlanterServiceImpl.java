package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.PlanterNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.Planter;
import com.masai.models.PlanterDto;
import com.masai.models.Seed;
import com.masai.repositories.PlanterRepo;
import com.masai.repositories.SeedRepo;

@Service
public class PlanterServiceImpl implements PlanterService{

	@Autowired
	private PlanterRepo pRepo;
	
	@Autowired
	private SeedRepo sRepo;

	@Override
	public Planter addPlanterOnly(PlanterDto pDto) {
		
		Planter pObj = new Planter();
		pObj.setPlanterCapacity(pDto.getPlanterCapacity());
		pObj.setPlanterColor(pDto.getPlanterColor());
		pObj.setPlanterCost(pDto.getPlanterCost());
		pObj.setPlanterHeight(pDto.getPlanterHeight());
		pObj.setPlanterHoles(pDto.getPlanterHoles());
		pObj.setPlanterShape(pDto.getPlanterShape());
		pObj.setPlanterStock(pDto.getPlanterStock());
		
	    return pRepo.save(pObj);
		
		
	}

	@Override
	public Planter addPlanterWithTheirSeed(Planter planter) {
		
		return pRepo.save(planter);
		
		
	}

	@Override
	public List<Seed> getAllSeedsAssociatedWithPlanterById(Integer Id) throws SeedNotFoundException{
		
		List<Seed> seeds = pRepo.getAllSeedsByPlanterId(Id);
		
		if(seeds.isEmpty()) {
			
			throw new SeedNotFoundException("Currently no seed associated with this Planter");
			
		}else {
			
			return seeds;
		}
		
		
	}

	@Override
	public Planter setSeedToPlanter(Integer planterId, Integer seedId) throws PlanterNotFoundException,SeedNotFoundException {
		
		Optional<Seed> sOpt = sRepo.findById(seedId);
		
		if(sOpt.isPresent()) {
			
			Optional<Planter> pOpt = pRepo.findById(planterId);
			if(pOpt.isPresent()) {
				
				Planter planter = pOpt.get();
				planter.getSeeds().add(sOpt.get());
				pRepo.save(planter);
				return planter;
			}else {
				
				throw new PlanterNotFoundException("No Planter found with this PlanterId");
			}
			
			
		}else {
			throw new SeedNotFoundException("No Seed found with this SeedId");
		}
	}

	@Override
	public List<Planter> getAllPlanters() throws PlanterNotFoundException {
		
		List<Planter> list = pRepo.findAll();
		
		if(list.isEmpty()) {
			
			throw new PlanterNotFoundException("No planter exist");
			
		}else {
			
			return list;
			
		}
		
	}

	@Override
	public Planter deletePlanterById(Integer planterId) throws PlanterNotFoundException {
		
		Optional<Planter> opt = pRepo.findById(planterId);

		if (opt.isPresent()) {
			Planter planter = opt.get();
			pRepo.delete(planter);
			
			return planter;
		} else {
			throw new PlanterNotFoundException("No Planter present with this SeedId");
		}
		
		
	}
		
		
	
	
	
	
	
	
	
		
		
	
	
	
	
	
}
