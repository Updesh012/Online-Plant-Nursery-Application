package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.models.Admin;
import com.masai.models.CurrentAdminSession;
import com.masai.models.Customer;
import com.masai.repositories.AdminDao;
import com.masai.repositories.AdminSessionDao;
import com.masai.repositories.CustomerDao;
import com.masai.repositories.CustomerSessionDao;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao aDao;
	
	@Autowired
	private AdminSessionDao asDao;
	
	@Autowired
	private CustomerDao cDao;
	
	@Autowired
	private CustomerSessionDao sDao;
	
	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException{
	
		CurrentAdminSession loggedInUser= asDao.findByUuid(key);
	
		if(loggedInUser == null) {
			throw new CustomerException("Please provide a valid key to update a customer");
		}
		
		
	
		
			return cDao.save(customer);
	
	}

	
	
	@Override
	public Admin createAdmin(Admin admin)throws AdminException {
		

		Admin existingAdmin= aDao.findByAdminId(admin.getAdminId());
		
		
		
		if(existingAdmin != null) 
			throw new AdminException("Admin Already Registered with this Admin Id ");
			
		
		
		
			return aDao.save(admin);
			
		}

	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException{
	
		CurrentAdminSession loggedInAdmin= asDao.findByUuid(key);
	
		if(loggedInAdmin == null) {
			throw new AdminException("Please provide a valid key to update a Admin");
		}
		
		
	
		
		if(admin.getAdminId() == loggedInAdmin.getUserId()) {
			//If LoggedInUser id is same as the id of supplied Customer which we want to update
			return aDao.save(admin);
		}
		else
			throw new AdminException("Invalid Admin Details, please login first");
	
	}

	@Override
	public String deleteAdminById(Integer adminId) throws AdminException {
		Optional<Admin> opt =  aDao.findById(adminId);
	
		if(opt.isPresent()) {
			Admin admin  = opt.get();
			aDao.delete(admin);
			return "Admin deleted successfully";
		}else {
			throw new AdminException("No admin available with this Admin id");
		}
	
		
	}

	@Override
	public List<Admin> getAllAdminDeatils() throws AdminException {
		 List<Admin>list =  aDao.findAll();
		if(list.size()!=0) {
			return list;
		}else {
			throw new AdminException("List is empty..!");
		}
	}
	@Override
	public Customer createCustomer(Customer customer)throws CustomerException {
		
		
		Customer existingCustomer= cDao.findByEmail(customer.getEmail());
		
		
		
		if(existingCustomer != null) 
			throw new CustomerException("Customer Already Registered with Email");
			
		
		
		
			return cDao.save(customer);
			
			
		}

		
		
	}


