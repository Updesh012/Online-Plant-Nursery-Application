package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.models.Admin;
import com.masai.models.CurrentAdminSession;
import com.masai.models.Customer;
import com.masai.repositories.AdminSessionDao;
import com.masai.services.AdminService;
import com.masai.services.CustomerService;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CustomerService cService;

	@Autowired
	private AdminService aService;

	@Autowired
	private AdminSessionDao asDao;

	@PostMapping("/createadmin")
	public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin) throws AdminException {

		Admin savedAdmin = aService.createAdmin(admin);

		return new ResponseEntity<Admin>(savedAdmin, HttpStatus.CREATED);
	}

	@PostMapping("/customers/{adminkey}")
	public ResponseEntity<Customer> saveCustomer(@PathVariable("adminkey") String key, @Valid @RequestBody Customer customer)
			throws CustomerException, AdminException {

		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			Customer savedCustomer = aService.createCustomer(customer);

			return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
		}

	}

	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,
			@RequestParam(required = false) String key) throws CustomerException, AdminException {

		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			Customer updatedCustomer = aService.updateCustomer(customer,key);

			return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);

		}

	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId,
			@RequestParam(required = false) String key) throws CustomerException, AdminException {
		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			String message = cService.deleteCustomerById(customerId);

			return new ResponseEntity<String>(message, HttpStatus.OK);

		}
	}

	@GetMapping("/getcustomers/{adminkey}")
	public ResponseEntity<List<Customer>> getAllCustomers(@PathVariable("adminkey") String key)
			throws CustomerException, AdminException {

		CurrentAdminSession loggedInAdmin = asDao.findByUuid(key);

		if (loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key");
		} else {

			List<Customer> list = cService.getAllCustomersDeatils(key);

			return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);

		}

	}

}
