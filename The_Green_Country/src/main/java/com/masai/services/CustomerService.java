package com.masai.services;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.models.Customer;

public interface CustomerService {
	
	
	public Customer createCustomer(Customer customer)throws CustomerException;
	
	public Customer updateCustomer(Customer customer,String key)throws CustomerException;

	public String deleteCustomerById(Integer customerId)throws CustomerException;

	public List<Customer> getAllCustomersDeatils()throws CustomerException;

}
