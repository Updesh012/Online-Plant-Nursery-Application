package com.masai.services;

import java.util.List;

import com.masai.exceptions.PlanterNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.Planter;
import com.masai.models.PlanterDto;
import com.masai.models.Seed;

public interface PlanterService {
	
	public Planter addPlanterOnly(PlanterDto pDto);
	
	public Planter addPlanterWithTheirSeed(Planter planter);

	public List<Seed> getAllSeedsAssociatedWithPlanterById(Integer Id) throws SeedNotFoundException;
	
	public Planter setSeedToPlanter(Integer planterId,Integer seedId)throws PlanterNotFoundException,SeedNotFoundException;
	
	public List<Planter> getAllPlanters() throws PlanterNotFoundException;
	
	public Planter deletePlanterById(Integer planterId) throws PlanterNotFoundException;
	
	
	
}
