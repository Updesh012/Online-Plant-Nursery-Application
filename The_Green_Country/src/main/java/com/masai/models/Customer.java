package com.masai.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer customerId;
	
	@NotEmpty
	@Size(min = 3, message = "Costomer Name should contain 3 or more than 3 latters !!")
	private String name;
	
	@NotEmpty
	@Size(min = 10, max = 10, message = "mobile No.  must be exact 10 digit !!")
	@Digits(fraction = 0, integer = 10, message = "mobile No. should contains the number only !!")
	private String mobileNo;
	
	@NotEmpty
	@Size(min = 4, max=10, message = "customer password should contains the minimum 4 and maximum 10 chars !!")
	private String password;
	
	@NotEmpty
	@Email
	@Pattern(regexp ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Please Enter valid Email Id included @ and proper Name !!" )
	private String email;
	
	
	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	

}
