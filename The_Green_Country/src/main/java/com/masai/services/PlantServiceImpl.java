package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.PlantNotFoundException;
import com.masai.models.Plant;
import com.masai.repositories.PlantRepo;

@Service
public class PlantServiceImpl implements PlantService {

	@Autowired
	private PlantRepo pRepo;

	@Override
	public Plant addPlant(Plant plant) {

		pRepo.save(plant);
		return plant;

	}

	@Override
	public Plant updatePlant(Plant plant) throws PlantNotFoundException {

		Optional<Plant> opt = pRepo.findById(plant.getPlantId());

		if (opt.isPresent()) {

			return pRepo.save(plant);

		} else {
			throw new PlantNotFoundException("No plant found with this PlantId");
		}

	}

	@Override
	public List<Plant> getAllPlants() throws PlantNotFoundException {

		List<Plant> list = pRepo.findAll();

		if (list.isEmpty()) {
			throw new PlantNotFoundException("No plant found...");
		} else {
			return list;
		}

	}

	@Override
	public Plant deletePlantById(Integer plantId) throws PlantNotFoundException {

		Optional<Plant> opt = pRepo.findById(plantId);

		if (opt.isPresent()) {
			Plant plant = opt.get();
			pRepo.delete(plant);
			;
			return plant;
		} else {
			throw new PlantNotFoundException("No plant present with this PlantId");
		}

	}

	@Override
	public Plant viewPlantById(Integer plantId) throws PlantNotFoundException {

		Optional<Plant> plant = pRepo.findById(plantId);

		if (plant.isPresent()) {

			return plant.get();

		} else {
			throw new PlantNotFoundException("Plant does not exist with this PlantId");
		}

	}

	@Override
	public List<Plant> viewPlantByName(String name) throws PlantNotFoundException {

		List<Plant> list = pRepo.findByCommonName(name);

		if (list.isEmpty()) {

			throw new PlantNotFoundException("Plant does not exist with this Name");
		} else {
			return list;
		}

	}

	@Override
	public List<Plant> viewPlantsByPlantType(String type) throws PlantNotFoundException {

		List<Plant> list = pRepo.findByTypeOfPlant(type);

		if (list.isEmpty()) {

			throw new PlantNotFoundException("There is no Plants exist with this PlantType");
		} else {

			return list;
		}

	}

	
	
	@Override
	public Plant changeQuantityOfPlantByPlantId(Integer plantid, Integer newQuantity) throws PlantNotFoundException {

		Optional<Plant> opt = pRepo.findById(plantid);

		if (opt.isPresent()) {
			Plant plant = opt.get();
			plant.setPlantsStock(newQuantity);
			pRepo.save(plant);
			return plant;
		} else {

			throw new PlantNotFoundException("Plant does not exist with this PlantId");
		}

	}

	
	
	@Override
	public Plant changePriceOfPlantByPlantId(Integer plantid, Double newPrice) throws PlantNotFoundException {

		Optional<Plant> opt = pRepo.findById(plantid);

		if (opt.isPresent()) {
			Plant plant = opt.get();
			plant.setPlantCost(newPrice);
			pRepo.save(plant);
			return plant;
		} else {

			throw new PlantNotFoundException("Plant does not exist with this PlantId");
		}

	}

}
