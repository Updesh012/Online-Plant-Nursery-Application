package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.models.Customer;
import com.masai.services.CustomerService;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CustomerService cService;

	@PostMapping("/customers")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) throws CustomerException {

		Customer savedCustomer = cService.createCustomer(customer);

		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer,
			@RequestParam(required = false) String key) throws CustomerException {

		Customer updatedCustomer = cService.updateCustomer(customer, key);

		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);

	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId)
			throws CustomerException {
		String message = cService.deleteCustomerById(customerId);

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping("/getcustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerException {
		List<Customer> list = cService.getAllCustomersDeatils();

		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);

	}

}
