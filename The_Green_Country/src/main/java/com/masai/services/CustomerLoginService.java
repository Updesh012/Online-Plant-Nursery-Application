package com.masai.services;

import com.masai.exceptions.LoginException;
import com.masai.models.CustomerLoginDTO;

public interface CustomerLoginService {
	
	public String logIntoAccount(CustomerLoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
