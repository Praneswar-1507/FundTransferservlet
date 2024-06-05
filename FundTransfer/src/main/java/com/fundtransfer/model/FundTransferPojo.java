package com.fundtransfer.model;

public class FundTransferPojo {
	String Password, username, email,phonenumber;
	int id;

	public FundTransferPojo() {

	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	


	public FundTransferPojo(String password, String username, String email, String phonenumber, int id) {
		super();
		Password = password;
		this.username = username;
		this.email = email;
		this.phonenumber = phonenumber;
		this.id = id;
	}

	@Override
	public String toString() {
		return "FundTransferPojo [Password=" + Password + ", username=" + username + ", email=" + email
				+ ", phonenumber=" + phonenumber + ", id=" + id + "]";
	}




}
