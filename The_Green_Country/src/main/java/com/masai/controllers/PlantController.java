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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.PlantNotFoundException;
import com.masai.models.CurrentAdminSession;
import com.masai.models.Customer;
import com.masai.models.Plant;
import com.masai.repositories.AdminSessionDao;
import com.masai.services.AdminService;
import com.masai.services.PlantService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlantController {

	//	just checking
	@GetMapping("/welcome")
	public String welcome() {
	
		return "welcome to The Green Country";
	
	}

	@Autowired
	private PlantService pService;




	@Autowired
	private AdminService aService;

	@Autowired
	private AdminSessionDao asDao;



//	adding new plant in database

	@PostMapping("admin/plants{adminkey}")
	public ResponseEntity<Plant> addPlantHandler(@PathVariable("adminkey") String key,@Valid @RequestBody Plant plant) throws AdminException {

		
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			Plant pObj = pService.addPlant(plant);

			return new ResponseEntity<Plant>(pObj, HttpStatus.CREATED);
			
			
		}
		
		
		
		

	}

//	getting all plants details from database

	@GetMapping("/plants")
	public ResponseEntity<List<Plant>> getAllPlantsHandler() throws PlantNotFoundException {

		List<Plant> plants = pService.getAllPlants();

		return new ResponseEntity<List<Plant>>(plants, HttpStatus.OK);
	}

	// updating existing plant details

	@PutMapping("admin/plants/{adminkey}")
	public ResponseEntity<Plant> updatePlantHandler(@Valid @RequestBody Plant plant,@PathVariable("adminkey") String key)
			throws PlantNotFoundException, AdminException {

	
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			Plant pObj = pService.updatePlant(plant);

			return new ResponseEntity<Plant>(pObj, HttpStatus.ACCEPTED);
			
			
		}

	}

	// delete existing plant by PlantId

	@DeleteMapping("admin/plants/{id}/{adminkey}")
	public ResponseEntity<Plant> deletePlantByIdHandler(@PathVariable("id") Integer plantId,@PathVariable("adminkey")String key)
			throws PlantNotFoundException, AdminException {

		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			Plant plant = pService.deletePlantById(plantId);

			return new ResponseEntity<Plant>(plant, HttpStatus.OK);

			
		}
		
	}

	// view plant by PlantId

	@GetMapping("/plants/{id}")
	public ResponseEntity<Plant> viewPlantByIdHandler(@PathVariable("id") Integer plantId)
			throws PlantNotFoundException {

		Plant plant = pService.viewPlantById(plantId);

		return new ResponseEntity<Plant>(plant, HttpStatus.OK);

	}

	// view Plant by Plant name

	@GetMapping("/plant/{name}")
	public ResponseEntity<List<Plant>> viewPlantByPlantNameHandler(@PathVariable("name") String name)
			throws PlantNotFoundException {

		List<Plant> list = pService.viewPlantByName(name);

		return new ResponseEntity<List<Plant>>(list, HttpStatus.OK);

	}

	// view plants by plantType
	@GetMapping("/getPlants/{plantType}")
	public ResponseEntity<List<Plant>> viewPlantsByPlantTypeHandler(@PathVariable("plantType") String type)
			throws PlantNotFoundException {

		List<Plant> list = pService.viewPlantsByPlantType(type);

		return new ResponseEntity<List<Plant>>(list, HttpStatus.OK);
	}

	// set new Quantity of plant

	@PutMapping("admin/setPlantQuantity/{id}/{quantity}/{adminkey}")
	public ResponseEntity<Plant> setPlantQuantityByplantIdHandler(@PathVariable("id") Integer plantid,
			@PathVariable("quantity") Integer quantity,@PathVariable("adminkey") String key) throws PlantNotFoundException, AdminException {

		

		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			Plant plant = pService.changeQuantityOfPlantByPlantId(plantid, quantity);

			return new ResponseEntity<Plant>(plant, HttpStatus.OK);
		
			
		}
	}

	// set new Price of plant

	@PutMapping("admin/setPlantPrice/{id}/{price}/{adminkey}")
	public ResponseEntity<Plant> setPlantPriceByplantIdHandler(@PathVariable("adminkey") String key,@PathVariable("id") Integer plantid,
			@PathVariable("price") Double price) throws PlantNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
	
			
			Plant plant = pService.changePriceOfPlantByPlantId(plantid, price);
			
			return new ResponseEntity<Plant>(plant, HttpStatus.OK);
		}
	}
	


}
