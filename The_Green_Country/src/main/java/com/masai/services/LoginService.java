package com.masai.services;

import com.masai.exceptions.LoginException;
import com.masai.models.LoginDTO;

public interface LoginService {
	
	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
