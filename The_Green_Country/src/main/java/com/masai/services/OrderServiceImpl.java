package com.masai.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.function.StandardAnsiSqlAggregationFunctions.SumFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.InsufficientQuantity;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.PlantNotFoundException;
import com.masai.exceptions.PlanterNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.CartDto;
import com.masai.models.Orders;
import com.masai.models.Plant;
import com.masai.models.Planter;
import com.masai.models.Seed;
import com.masai.repositories.OrderRepo;
import com.masai.repositories.PlantRepo;
import com.masai.repositories.PlanterRepo;
import com.masai.repositories.SeedRepo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepo oRepo;
	
	@Autowired
	private PlantRepo pRepo;
	
	@Autowired
	private SeedRepo sRepo;
	
	@Autowired
	private PlanterRepo planterRepo;
	
	
	@Override
	public Orders buyPlantByPlantId(String sessionId, Integer plantId, Integer quantity)
			throws CustomerException, PlantNotFoundException, InsufficientQuantity {
		
		Optional<Plant> opt = pRepo.findById(plantId);
		
		if(opt.isPresent()) {
			
			Plant plant = opt.get();
			if(plant.getPlantsStock() < quantity) {
				
				throw new InsufficientQuantity("Plant Stock is lesser than your quantity");
			}else {
				
				Orders order = new Orders();
				order.setDateAndTime(LocalDateTime.now());
				order.setProductType("Plant");
				order.setProductId(plantId);
				order.setQuantity(quantity);
				order.setSessionId(sessionId);
				order.setTotalCost(quantity*plant.getPlantCost());
				
				Orders obj = oRepo.save(order);
				
				plant.setPlantsStock(plant.getPlantsStock()-quantity);
				pRepo.save(plant);
				return obj;
			}
			
			
		}else {
			
			throw new PlantNotFoundException("No plant found with this Id");
		}
			
			
	}

	// seed buying
	
	@Override
	public Orders buySeedBySeedId(String sessionId, Integer seedId, Integer quantity)
			throws CustomerException, SeedNotFoundException, InsufficientQuantity {
		
		
		Optional<Seed> opt = sRepo.findById(seedId);
		
		if(opt.isPresent()) {
			
			Seed seed = opt.get();
			if(seed.getStock() < quantity) {
				
				throw new InsufficientQuantity("Seed Stock is lesser than your quantity");
			}else {
				
				Orders order = new Orders();
				order.setDateAndTime(LocalDateTime.now());
				order.setProductType("Seed");
				order.setProductId(seedId);
				order.setQuantity(quantity);
				order.setSessionId(sessionId);
				order.setTotalCost(quantity*seed.getCost());
				
				Orders obj = oRepo.save(order);
				
				seed.setStock(seed.getStock() - quantity);
				sRepo.save(seed);
				return obj;
			}
			
			
		}else {
			
			throw new SeedNotFoundException("No seed found with this Id");
		}
		
		
		
	}
	
	// buying planter

	@Override
	public Orders buyPlanterByPlanterId(String sessionId, Integer PlanterId, Integer quantity)
			throws CustomerException, PlanterNotFoundException, InsufficientQuantity {
		
		
		Optional<Planter> opt = planterRepo.findById(PlanterId);
		
		if(opt.isPresent()) {
			
			Planter planter = opt.get();
			if(planter.getPlanterStock() < quantity) {
				
				throw new InsufficientQuantity("Planter Stock is lesser than your quantity");
			}else {
				
				Orders order = new Orders();
				order.setDateAndTime(LocalDateTime.now());
				order.setProductType("Planter");
				order.setProductId(PlanterId);
				order.setQuantity(quantity);
				order.setSessionId(sessionId);
				order.setTotalCost(quantity*planter.getPlanterCost());
				
				Orders obj = oRepo.save(order);
				
				planter.setPlanterStock(planter.getPlanterStock() - quantity);
				planterRepo.save(planter);
				return obj;
			}
			
			
		}else {
			
			throw new PlanterNotFoundException("No planter found with this Id");
		}
		
		
		
	}

	@Override
	public List<Orders> buyPlanterWithSeedByPlanterAndSeedId(String sessionId, Integer planterId, Integer seedId,
			Integer PlanterQuantity,Integer SeedQuantity)
			throws CustomerException, PlanterNotFoundException, SeedNotFoundException, InsufficientQuantity {
		
		List<Orders> orders = new ArrayList<>();
		Optional<Planter> pOpt = planterRepo.findById(planterId);
		Optional<Seed> sOpt = sRepo.findById(seedId);
		
		if(pOpt.isPresent()) { //s1
			
			Planter planter = pOpt.get();
			
			if(planter.getPlanterStock() < PlanterQuantity) { //s2
				
				throw new InsufficientQuantity("Planter stock is less then your given Planter quantity");
			}else { // e2
				
				List<Seed> list = planter.getSeeds();
				
				if(list.isEmpty()) { // s
					throw new SeedNotFoundException("Zero Seed associated with this Planter");
				}
				
				boolean flag = true;
				for(Seed seed : list) {
					
					if(seed.getSeedId() == seedId) {
						flag = false;
						
						if(seed.getStock() < SeedQuantity) {
							throw new InsufficientQuantity("Seed stock is lesser than your quantity");
						}else {
							
							Orders order1 = new Orders();
							order1.setDateAndTime(LocalDateTime.now());
							order1.setProductType("PlanterWithSeed");
							order1.setProductId(planterId);
							order1.setQuantity(PlanterQuantity);
							order1.setSessionId(sessionId);
							order1.setTotalCost(PlanterQuantity*planter.getPlanterCost());
							
							Orders obj1 = oRepo.save(order1);
							
							planter.setPlanterStock(planter.getPlanterStock() - PlanterQuantity);
							planterRepo.save(planter);

							
							
							Orders order = new Orders();
							order.setDateAndTime(LocalDateTime.now());
							order.setProductType("SeedWithPlanter");
							order.setProductId(seedId);
							order.setQuantity(SeedQuantity);
							order.setSessionId(sessionId);
							order.setTotalCost(SeedQuantity*seed.getCost());
							
							Orders obj2 = oRepo.save(order);
							
							seed.setStock(seed.getStock() - SeedQuantity);
							sRepo.save(seed);
							
							
							
							orders.add(obj1);
							orders.add(obj2);
							
							return orders;
						}
						
					}
					
				}
				
				if(flag) {
					throw new SeedNotFoundException("No seed found with this Seed Id");
				}
				
				
			}
			
			
			
			
		}else { // e1
			
			throw new PlanterNotFoundException("No planter found with this Planter Id");
		}
		
		return orders;
		
	}

	@Override
	public CartDto visitYourCart(String customerKey) throws CustomerException,OrderException {
		
		List<Orders> list = oRepo.findBySessionId(customerKey);
		
		if(list.isEmpty()) {
			throw new OrderException("You do not have any item in your cart");
		}
		
		Double sum = 0.00;
		
		for(Orders order : list) {
			
			sum += order.getTotalCost();
			
		}
		
		
		CartDto cart = new CartDto();
		cart.setList(list);
		cart.setTotalBill(sum);
		
		return cart;
		
	}

	@Override
	public String payAmount(String customerKey,Double amount) throws CustomerException, OrderException {
	
		List<Orders> list = oRepo.findBySessionId(customerKey);
		Double sum = 0.00;
		
		for(Orders order : list) {
			
			sum += order.getTotalCost();
		}
	
		int x = Double.compare(amount, sum);
		
		if(x == 0) {
//			System.out.println(true);
//			oRepo.deleteBySessionId(customerKey);
			oRepo.deleteAll();
			return "Payment Successfully done";
			
		}else {
			
			throw new OrderException("Amount should be equal to : "+sum);
		}
		
		
	}

	@Override
	public Orders deleteProductByOrderId(String customerKey, Integer orderId) throws CustomerException, OrderException {
		
		Optional<Orders> opt = oRepo.findById(orderId);
		
		if(opt.isPresent()) {
			
			oRepo.deleteById(orderId);
			return opt.get();
			
		}else {
			
			throw new OrderException("No order Present with this Order Id");
		}
		
	}

	@Override
	public Orders updateOrderByOrderId(String customer, Integer orderId, Integer newQuantity)
			throws CustomerException, OrderException, InsufficientQuantity {
		
		if(newQuantity < 0) throw new InsufficientQuantity("Negative quatity can not be allowed");
		Optional<Orders> opt = oRepo.findById(orderId);
		
		if(opt.isPresent()) {
			
			Orders order = opt.get();
			Integer productId = order.getProductId();
			Integer oldQuantity = order.getQuantity();
			
			if(order.getProductType().equals("Seed") || order.getProductType().equals("SeedWithPlanter")) {
				
				Optional<Seed> sOpt = sRepo.findById(productId);
				Seed sObj = sOpt.get();
				sObj.setStock(sObj.getStock() + oldQuantity );
				
				
				if(sObj.getStock() < newQuantity) {
					throw new InsufficientQuantity("Seed stock is lesser than your Quantity");
				}else {
					sObj.setStock(sObj.getStock() - newQuantity);
					sRepo.save(sObj);
					order.setQuantity(newQuantity);
					order.setTotalCost(newQuantity*sObj.getCost());
					order.setDateAndTime(LocalDateTime.now());
					oRepo.save(order);
					return order;
				}
				
			}else if(order.getProductType().equals("Plant")) {
				
				Optional<Plant> pOpt = pRepo.findById(productId);
				Plant pObj = pOpt.get();
				pObj.setPlantsStock(pObj.getPlantsStock() + oldQuantity );
				
				
				if(pObj.getPlantsStock() < newQuantity) {
				
					throw new InsufficientQuantity("Plant stock is lesser than your Quantity");
				}else {
					
					pObj.setPlantsStock(pObj.getPlantsStock() - newQuantity);
					pRepo.save(pObj);
					
					order.setQuantity(newQuantity);
					order.setTotalCost(newQuantity*pObj.getPlantCost());
					order.setDateAndTime(LocalDateTime.now());
					oRepo.save(order);
					return order;
				}
				
				
				
			}else if(order.getProductType().equals("Planter") || order.getProductType().equals("PlanterWithSeed")) {
				
				Optional<Planter> planterOpt = planterRepo.findById(productId);
				Planter planterObj = planterOpt.get();
				planterObj.setPlanterStock(planterObj.getPlanterStock() + oldQuantity);
				
				
				if(planterObj.getPlanterStock() < newQuantity) {
					throw new InsufficientQuantity("Planter stock is lesser than your Quantity");
				}else {
					planterObj.setPlanterStock(planterObj.getPlanterStock() - newQuantity);
					planterRepo.save(planterObj);
					
					order.setQuantity(newQuantity);
					order.setTotalCost(newQuantity*planterObj.getPlanterCost());
					order.setDateAndTime(LocalDateTime.now());
					oRepo.save(order);
					return order;
				}
				
				
			}else {
				return null;
			}
			
		}else {
			
			throw new OrderException("No order present with this Order Id");
		}
		
		
	}
		
		
		
		
		
		

	

	
	
}
