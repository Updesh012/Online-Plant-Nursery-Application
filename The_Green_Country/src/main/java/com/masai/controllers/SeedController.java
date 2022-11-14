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
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.CurrentAdminSession;
import com.masai.models.Plant;
import com.masai.models.Seed;
import com.masai.repositories.AdminSessionDao;
import com.masai.services.AdminService;
import com.masai.services.SeedService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class SeedController {

	@Autowired
	private SeedService sService;

	@Autowired
	private AdminService aService;

	@Autowired
	private AdminSessionDao asDao;

//	adding new seed in database

	@PostMapping("admin/seeds/{adminkey}")
	public ResponseEntity<Seed> addSeedHandler(@PathVariable("adminkey") String key,@Valid @RequestBody Seed seed)
			throws SeedNotFoundException, AdminException {

		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			Seed sObj = sService.addSeed(seed);

			return new ResponseEntity<Seed>(sObj, HttpStatus.CREATED);
		}

	}

	// updating existing seed details

	@PutMapping("admin/seeds/{adminkey}")
	public ResponseEntity<Seed> updateSeedHandler(@Valid @RequestBody Seed seed, @PathVariable("adminkey") String key)
			throws SeedNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {
			Seed sObj = sService.updateSeed(seed);

			return new ResponseEntity<Seed>(sObj, HttpStatus.ACCEPTED);
		}

	}

//	getting all seeds details from database

	@GetMapping("/seeds")
	public ResponseEntity<List<Seed>> getAllSeedsHandler() throws SeedNotFoundException {

		List<Seed> seeds = sService.getAllSeeds();

		return new ResponseEntity<List<Seed>>(seeds, HttpStatus.OK);
	}

	// delete existing seed by SeedId

	@DeleteMapping("admin/seeds/{id}/{adminkey}")
	public ResponseEntity<Seed> deleteSeedByIdHandler(@PathVariable("id") Integer seedId,
			@PathVariable("adminkey") String key) throws SeedNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			Seed seed = sService.deleteSeedById(seedId);

			return new ResponseEntity<Seed>(seed, HttpStatus.OK);

		}
	}

	// view seed by SeedId

	@GetMapping("/seeds/{id}")
	public ResponseEntity<Seed> viewSeedByIdHandler(@PathVariable("id") Integer seedId) throws SeedNotFoundException {

		Seed seed = sService.viewSeedById(seedId);

		return new ResponseEntity<Seed>(seed, HttpStatus.OK);

	}

	// view Seed by Seed name

	@GetMapping("/seed/{name}")
	public ResponseEntity<List<Seed>> viewSeedBySeedNameHandler(@PathVariable("name") String name)
			throws SeedNotFoundException {

		List<Seed> list = sService.viewSeedByName(name);

		return new ResponseEntity<List<Seed>>(list, HttpStatus.OK);

	}

	// view seeds by seedType
	@GetMapping("/getSeeds/{seedType}")
	public ResponseEntity<List<Seed>> viewSeedsBySeedTypeHandler(@PathVariable("seedType") String type)
			throws SeedNotFoundException {

		List<Seed> list = sService.viewSeedsBySeedType(type);

		return new ResponseEntity<List<Seed>>(list, HttpStatus.OK);
	}

	// set new Quantity of seed

	@PutMapping("admin/setSeedQuantity/{id}/{quantity}/{adminkey}")
	public ResponseEntity<Seed> setSeedQuantityBySeedIdHandler(@PathVariable("adminkey")String key,@PathVariable("id") Integer seedid,
			@PathVariable("quantity") Integer quantity) throws SeedNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);
		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {


			Seed seed = sService.changeQuantityOfSeedBySeedId(seedid, quantity);
			
			return new ResponseEntity<Seed>(seed, HttpStatus.OK);

		}
	}

	// set new Price of seed

	@PutMapping("admin/setSeedPrice/{id}/{price}/{adminkey}")
	public ResponseEntity<Seed> setSeedPriceBySeedIdHandler(@PathVariable("adminkey") String key,@PathVariable("id") Integer seedid,
			@PathVariable("price") Double price) throws SeedNotFoundException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);
		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {


		
		Seed seed = sService.changePriceOfSeedBySeedId(seedid, price);

		return new ResponseEntity<Seed>(seed, HttpStatus.OK);
		}
	}

}
