package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.models.CurrentAdminSession;
import com.masai.models.CurrentCustomerSession;
import com.masai.models.Customer;
import com.masai.repositories.AdminSessionDao;
import com.masai.repositories.CustomerDao;
import com.masai.repositories.CustomerSessionDao;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao cDao;
	
	@Autowired
	private CustomerSessionDao sDao;
	
	@Autowired
	private AdminSessionDao asDao;
	
	
	@Override
	public Customer createCustomer(Customer customer)throws CustomerException {
		
		
		Customer existingCustomer= cDao.findByEmail(customer.getEmail());
		
		
		
		if(existingCustomer != null) 
			throw new CustomerException("Customer Already Registered with Email");
			
		
		
		
			return cDao.save(customer);
			
			
		}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException{
	
		CurrentCustomerSession loggedInUser= sDao.findByUuid(key);
	
		if(loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to update a customer");
		}
		
		
	
		
		if(customer.getCustomerId() == loggedInUser.getUserId()) {
			//If LoggedInUser id is same as the id of supplied Customer which we want to update
			return cDao.save(customer);
		}
		else
			throw new CustomerException("Invalid Customer Details, please login first");
	
	}

	@Override
	public String deleteCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> opt =  cDao.findById(customerId);
	
		if(opt.isPresent()) {
			Customer customer  = opt.get();
			cDao.delete(customer);
			return "Customer deleted successfully";
		}else {
			throw new CustomerException("No customer available with this id");
		}
	
		
	}

	@Override
	public List<Customer> getAllCustomersDeatils(String key) throws CustomerException, AdminException {
		   CurrentAdminSession casDao =  asDao.findByUuid(key);
		   
		   if(casDao!=null) {
		
			   List<Customer>list =  cDao.findAll();
				if(list.size()!=0) {
					return list;
				}else {
					throw new CustomerException("List is empty..!");
				}
		   }else {
			   throw new AdminException("Wrong key..!");
		   }
		
	}
		
		
		
	}


