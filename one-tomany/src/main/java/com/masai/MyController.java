package com.masai;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@Autowired
	private Repo repo;
	
	@Autowired
	private Repo2 repo2;
	
	@PostMapping("/save")
	public String store(@Valid @RequestBody Planter obj) {
		
		
		
//		repo.save(obj);
		System.out.println("started");
		
		for(Seed seed : obj.getList()) {
			
//			obj.getList().add(seed);
			seed.setPlanter(obj);
			
		}
		
		System.out.println("ending");
		
		repo.save(obj);
		
		
		return "saved";
		
	}
	
	@PostMapping("/seed")
	public String addSed(@Valid @RequestBody Seed seed) {
		
		repo2.save(seed);
		
		return "saved";
	}
	
	
	
	
}
