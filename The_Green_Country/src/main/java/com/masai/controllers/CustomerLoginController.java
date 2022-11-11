package com.masai.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.LoginException;
import com.masai.models.LoginDTO;
import com.masai.services.LoginService;

@RestController
@RequestMapping("/customer")
public class CustomerLoginController {

	@Autowired
	private LoginService customerLogin;
	
	@PostMapping("/login")
	public ResponseEntity<String> logInCustomer(@RequestBody LoginDTO dto) throws LoginException {
		
		String result = customerLogin.logIntoAccount(dto);
		

		
		return new ResponseEntity<String>(result,HttpStatus.OK );
		
		
	}
	
	@PostMapping("/logout")
	public String logoutCustomer(@RequestParam(required = false) String key) throws LoginException {
		return customerLogin.logOutFromAccount(key);
		
	}
	
	
	
}
