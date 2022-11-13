package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.PlanterNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.Planter;
import com.masai.models.PlanterDto;
import com.masai.models.Seed;
import com.masai.services.PlanterService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlanterController {

	@Autowired
	private PlanterService pService;



	@PostMapping("/planters")
	public ResponseEntity<Planter> addPlanterOnly(@RequestBody PlanterDto pDto) {

		Planter planter = pService.addPlanterOnly(pDto);

		return new ResponseEntity<Planter>(planter, HttpStatus.CREATED);
	}

	@PostMapping("/plantersWithSeed")
	public ResponseEntity<Planter> addPlanterWithSeeds(@RequestBody Planter pObj) {

		Planter planter = pService.addPlanterWithTheirSeed(pObj);

		return new ResponseEntity<Planter>(planter, HttpStatus.CREATED);
	}
	
	@GetMapping("/seedsFromPlanterId/{planterId}")
	public ResponseEntity<List<Seed>> getAllSeedsAssociatedWithPlanterByIdHandler(@PathVariable("planterId") Integer planterId) throws SeedNotFoundException{
		
		List<Seed> seeds = pService.getAllSeedsAssociatedWithPlanterById(planterId);
		
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.OK);
	}
	
	
	@GetMapping("/planters/{PlanterId}/{SeedId}")
	public ResponseEntity<Planter> setSeedWithPlanterByIdsHandler(@PathVariable("PlanterId") Integer planterId,@PathVariable("SeedId") Integer SeedId) throws PlanterNotFoundException, SeedNotFoundException{
		
		Planter planter = pService.setSeedToPlanter(planterId, SeedId);
		
		return new ResponseEntity<Planter>(planter,HttpStatus.OK);
	}
	
	@GetMapping("/planters")
	public ResponseEntity<List<Planter>> getAllPlanterHandler() throws PlanterNotFoundException{
		
		List<Planter> list = pService.getAllPlanters();
		
		return new ResponseEntity<List<Planter>>(list,HttpStatus.OK);
	
	}
	
	
	// delete existing seed by SeedId

		@DeleteMapping("/planters/{id}")
		public ResponseEntity<Planter> deletePlanterByIdHandler(@PathVariable("id") Integer planterId)
				throws PlanterNotFoundException {

			Planter planter = pService.deletePlanterById(planterId);

			return new ResponseEntity<Planter>(planter, HttpStatus.OK);

		}
	
	

}
