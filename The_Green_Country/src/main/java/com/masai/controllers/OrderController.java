package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.InsufficientQuantity;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.PlantNotFoundException;
import com.masai.exceptions.PlanterNotFoundException;
import com.masai.exceptions.SeedNotFoundException;
import com.masai.models.CartDto;
import com.masai.models.CurrentCustomerSession;
import com.masai.models.Orders;
import com.masai.repositories.CustomerSessionDao;
import com.masai.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService oService;
	
	@Autowired
	private CustomerSessionDao csDao;
	
	@GetMapping("/buyPlant/{customerKey}/{plantId}/{quantity}")
	public ResponseEntity<Orders> buyPlantByPlantIdHandler(@PathVariable("customerKey") String key,@PathVariable("plantId") Integer plantId,@PathVariable("quantity") Integer quantity) throws CustomerException, PlantNotFoundException, InsufficientQuantity {
		
		CurrentCustomerSession ccs = csDao.findByUuid(key);
		
		if(ccs == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			Orders order = oService.buyPlantByPlantId(key, plantId, quantity);
//			return order;
			return new ResponseEntity<Orders>(order,HttpStatus.OK);
		}
		
		
	}
	
	
	
	@GetMapping("/buySeed/{customerKey}/{seedId}/{quantity}")
	public ResponseEntity<Orders> buySeedBySeedIdHandler(@PathVariable("customerKey") String key,@PathVariable("seedId") Integer seedId,@PathVariable("quantity") Integer quantity) throws CustomerException, SeedNotFoundException, InsufficientQuantity {
		
		CurrentCustomerSession ccs = csDao.findByUuid(key);
		
		if(ccs == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			Orders order = oService.buySeedBySeedId(key, seedId, quantity);
//			return order;
			
			return new ResponseEntity<Orders>(order,HttpStatus.OK);
		}
		
		
	}
	
	
	@GetMapping("/buyPlanter/{customerKey}/{planterId}/{quantity}")
	public ResponseEntity<Orders> buyPlanterByPlanterIdHandler(@PathVariable("customerKey") String key,@PathVariable("planterId") Integer planterId,@PathVariable("quantity") Integer quantity) throws CustomerException, PlanterNotFoundException, InsufficientQuantity {
		
		CurrentCustomerSession ccs = csDao.findByUuid(key);
		
		if(ccs == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			Orders order = oService.buyPlanterByPlanterId(key, planterId, quantity);
//			return order;
			return new ResponseEntity<Orders>(order,HttpStatus.OK);
		}
		
		
	}
	
	
	@GetMapping("/buyPlanterWithSeed/{customerKey}/{planterId}/{seedId}/{pQuantity}/{sQuantity}")
	public ResponseEntity<List<Orders>> buyPlanterWithSeedByPlanterAndSeedId(@PathVariable("customerKey") String key,@PathVariable("planterId") Integer planterId,@PathVariable("seedId") Integer seedId,
			@PathVariable("pQuantity") Integer PlanterQuantity,@PathVariable("sQuantity") Integer SeedQuantity)
			throws CustomerException, PlanterNotFoundException, SeedNotFoundException, InsufficientQuantity {
	
	
		CurrentCustomerSession ccs = csDao.findByUuid(key);
		
		if(ccs == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			List<Orders> list = oService.buyPlanterWithSeedByPlanterAndSeedId(key, planterId, seedId, PlanterQuantity, SeedQuantity);
	
			return new ResponseEntity<List<Orders>>(list,HttpStatus.OK);
		}
		
		
		
	}
	
	@GetMapping("/{customerKey}")
	public ResponseEntity<CartDto> visitYourCartHandler(@PathVariable("customerKey") String key) throws OrderException,CustomerException{
		
		CurrentCustomerSession css = csDao.findByUuid(key);
		
		if(css == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			CartDto cartdto = oService.visitYourCart(key);
			return new ResponseEntity<CartDto>(cartdto,HttpStatus.OK);
		}
		
		
	}
	
	@DeleteMapping("/{customerKey}/{amount}")
	public ResponseEntity<String> payYourAmount(@PathVariable("customerKey") String key,@PathVariable("amount") Double amount) throws OrderException,CustomerException{
		
		CurrentCustomerSession css = csDao.findByUuid(key);
		
		if(css == null) {
			
			throw new CustomerException("No customer exist with this Key");
		}else {
			
			String string = oService.payAmount(key, amount);
			
			return new ResponseEntity<String>(string,HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/delete/{customerKey}/{orderId}")
	public ResponseEntity<Orders> deleteProductByOrderIdHandler(@PathVariable("customerKey") String key,@PathVariable("orderId") Integer orderId) throws CustomerException, OrderException {
		
		CurrentCustomerSession css = csDao.findByUuid(key);
		
		
		if(css == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			Orders order = oService.deleteProductByOrderId(key, orderId);
			return new ResponseEntity<Orders>(order,HttpStatus.OK);
		}
		
		
	}
	
	
	@PostMapping("{customerKey}/{orderId}/{newQuantity}")
	public ResponseEntity<Orders> updateOrderByOrderId(@PathVariable("customerKey") String key,@PathVariable("orderId") Integer orderId,@PathVariable("newQuantity") Integer newQuantity)
			throws CustomerException, OrderException, InsufficientQuantity {
	
		CurrentCustomerSession css = csDao.findByUuid(key);
		
		if(css == null) {
			throw new CustomerException("Customer not present with this key");
		}else {
			
			Orders order = oService.updateOrderByOrderId(key, orderId, newQuantity);
			return new ResponseEntity<Orders>(order,HttpStatus.OK);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
