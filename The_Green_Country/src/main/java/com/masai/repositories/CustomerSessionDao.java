package com.masai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.CurrentUserSession;


public interface CustomerSessionDao extends JpaRepository<CurrentUserSession, Integer> {

	
	public  CurrentUserSession  findByUuid(String uuid);
}
