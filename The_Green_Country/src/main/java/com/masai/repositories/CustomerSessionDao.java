package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.CurrentCustomerSession;


public interface CustomerSessionDao extends JpaRepository<CurrentCustomerSession, Integer> {

	
	public  CurrentCustomerSession  findByUuid(String uuid);
}
