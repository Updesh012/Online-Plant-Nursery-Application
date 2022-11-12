package com.masai.services;

import com.masai.exceptions.LoginException;
import com.masai.models.AdminLoginDTO;

public interface AdminLoginService {
	
	public String logIntoAccount(AdminLoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
