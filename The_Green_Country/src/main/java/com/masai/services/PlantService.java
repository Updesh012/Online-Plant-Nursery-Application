package com.masai.services;

import java.util.List;

import com.masai.exceptions.PlantNotFoundException;
import com.masai.models.Plant;

public interface PlantService{

	public Plant addPlant(Plant plant);
	
	public Plant updatePlant(Plant plant) throws PlantNotFoundException;
	
	public List<Plant> getAllPlants() throws PlantNotFoundException;
	
	public Plant deletePlantById(Integer plantId) throws PlantNotFoundException;
	
	public Plant viewPlantById(Integer plantId) throws PlantNotFoundException;
	
	public List<Plant> viewPlantByName(String name) throws PlantNotFoundException;
	
	public List<Plant> viewPlantsByPlantType(String type) throws PlantNotFoundException;
	
	public Plant changeQuantityOfPlantByPlantId(Integer plantid,Integer newQuantity) throws PlantNotFoundException;
	
	public Plant changePriceOfPlantByPlantId(Integer plantid,Double newPrice) throws PlantNotFoundException;
	
}
