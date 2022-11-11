package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.models.Planter;
import com.masai.models.PlanterDto;
import com.masai.services.PlanterService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlanterController {
	
	@Autowired
	private PlanterService pService;

	@PostMapping("/planter")
	public String post(@RequestBody Planter obj) {
		
		return "done";
		
	}
	
	@PostMapping("/planters")
	public ResponseEntity<Planter> addPlanterOnly(@RequestBody PlanterDto pDto){
		
		Planter planter = pService.addPlanterOnly(pDto);
		
		return new ResponseEntity<Planter>(planter,HttpStatus.CREATED);
	}
	
	
	
	
	
	
}
