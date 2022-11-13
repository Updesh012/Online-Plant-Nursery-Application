package com.masai.services;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.InsufficientQuantity;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.PlantNotFoundException;
import com.masai.exceptions.PlanterNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.CartDto;
import com.masai.models.Orders;

public interface OrderService {

	public Orders buyPlantByPlantId(String sessionId,Integer planterId,Integer quantity) throws CustomerException,PlantNotFoundException,InsufficientQuantity;
	
	public Orders buySeedBySeedId(String sessionId,Integer seedId,Integer quantity) throws CustomerException,SeedNotFoundException,InsufficientQuantity;
	
	public Orders buyPlanterByPlanterId(String sessionId,Integer PlanterId,Integer quantity) throws CustomerException,PlanterNotFoundException,InsufficientQuantity;
	
	public List<Orders> buyPlanterWithSeedByPlanterAndSeedId(String sessionId,Integer planterId,Integer seedId,Integer PlanterQuantity,Integer SeedQuantity) throws CustomerException,PlanterNotFoundException,SeedNotFoundException,InsufficientQuantity;
	
	public CartDto visitYourCart(String customerKey) throws CustomerException,OrderException;
	
	public String payAmount(String customerKey,Double amount) throws CustomerException,OrderException;
	
	public Orders deleteProductByOrderId(String customerKey,Integer orderId) throws CustomerException,OrderException;
	
	public Orders updateOrderByOrderId(String customer,Integer orderId,Integer newQuantity) throws CustomerException,OrderException,InsufficientQuantity;
	
	
}
