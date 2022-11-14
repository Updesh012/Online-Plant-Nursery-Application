package com.masai.models;



import lombok.Data;

@Data
public class CustomerLoginDTO {

	

	private String mobileNo;
	private String password;
	private String Email;
//	private String role;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
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
	@Override
	public String toString() {
		return "LoginDTO [mobileNo=" + mobileNo + ", password=" + password + "]";
	}
	
	
}
