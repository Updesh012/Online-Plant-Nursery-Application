package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.PlanterNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.CurrentAdminSession;
import com.masai.models.Plant;
import com.masai.models.Planter;
import com.masai.models.PlanterDto;
import com.masai.models.Seed;
import com.masai.repositories.AdminSessionDao;
import com.masai.services.AdminService;
import com.masai.services.PlanterService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlanterController {

	@Autowired
	private PlanterService pService;
	@Autowired
	private AdminService aService;

	@Autowired
	private AdminSessionDao asDao;




	@PostMapping("admin/planters/{adminkey}")
	public ResponseEntity<Planter> addPlanterOnly(@PathVariable("adminkey") String key,@Valid @RequestBody PlanterDto pDto) throws AdminException {


		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);
		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			Planter planter = pService.addPlanterOnly(pDto);

			return new ResponseEntity<Planter>(planter, HttpStatus.CREATED);
		}
	}

	@PostMapping("admin/plantersWithSeed/{adminkey}")
	public ResponseEntity<Planter> addPlanterWithSeeds(@PathVariable("adminkey") String key,@Valid @RequestBody Planter pObj) throws AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);
		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			Planter planter = pService.addPlanterWithTheirSeed(pObj);

			return new ResponseEntity<Planter>(planter, HttpStatus.CREATED);	
			}
		
	}
	
	@GetMapping("/seedsFromPlanterId/{planterId}")
	public ResponseEntity<List<Seed>> getAllSeedsAssociatedWithPlanterByIdHandler(@PathVariable("planterId") Integer planterId) throws SeedNotFoundException{
		
		List<Seed> seeds = pService.getAllSeedsAssociatedWithPlanterById(planterId);
		
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.OK);
	}
	
	
	@GetMapping("admin/planters/{PlanterId}/{SeedId}/{adminkey}")
	public ResponseEntity<Planter> setSeedWithPlanterByIdsHandler(@PathVariable("adminkey") String key,@PathVariable("PlanterId") Integer planterId,@PathVariable("SeedId") Integer SeedId) throws PlanterNotFoundException, SeedNotFoundException, AdminException{
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);
		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			
			Planter planter = pService.setSeedToPlanter(planterId, SeedId);
			
			return new ResponseEntity<Planter>(planter,HttpStatus.OK);
			}
	}
	
	@GetMapping("/planters")
	public ResponseEntity<List<Planter>> getAllPlanterHandler() throws PlanterNotFoundException{
		
		List<Planter> list = pService.getAllPlanters();
		
		return new ResponseEntity<List<Planter>>(list,HttpStatus.OK);
	
	}
	
	
	// delete existing seed by SeedId

		@DeleteMapping("admin/planters/{id}/{adminkey}")
		public ResponseEntity<Planter> deletePlanterByIdHandler(@PathVariable("adminkey") String key,@PathVariable("id") Integer planterId)
				throws PlanterNotFoundException, AdminException {
			CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);
			if (loggedInAdmin == null) {
				throw new AdminException("Please provide a valid key");
			} else {
				
				Planter planter = pService.deletePlanterById(planterId);
				
				return new ResponseEntity<Planter>(planter, HttpStatus.OK);
				}

		}
	
	

}
