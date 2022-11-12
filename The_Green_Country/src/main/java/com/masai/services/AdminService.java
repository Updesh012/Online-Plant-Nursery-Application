package com.masai.services;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.models.Admin;
import com.masai.models.Customer;

public interface AdminService {
	
	public Customer createCustomer(Customer customer)throws CustomerException;
	
	public Admin createAdmin(Admin admin)throws AdminException;
	
	public Admin updateAdmin(Admin admin,String key)throws AdminException;

	public Customer updateCustomer(Customer customer,String key)throws CustomerException;


	public String deleteAdminById(Integer adminId)throws AdminException;

	public List<Admin> getAllAdminDeatils()throws AdminException;

}
