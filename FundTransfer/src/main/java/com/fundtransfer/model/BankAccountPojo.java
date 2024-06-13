package com.fundtransfer.model;

public class BankAccountPojo {
String firstName,lastName,phonenumber,date,aadharNumber,iFSCcode,address,accountId;
int accountBalance;
int userId;
public BankAccountPojo() {

}



public String getFirstName() {
	return firstName;
}



public void setFirstName(String firstName) {
	this.firstName = firstName;
}



public String getLastName() {
	return lastName;
}



public void setLastName(String lastName) {
	this.lastName = lastName;
}



public String getPhonenumber() {
	return phonenumber;
}



public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}



public String getDate() {
	return date;
}



public void setDate(String date) {
	this.date = date;
}



public String getAadharNumber() {
	return aadharNumber;
}



public void setAadharNumber(String aadharNumber) {
	this.aadharNumber = aadharNumber;
}



public String getiFSCcode() {
	return iFSCcode;
}



public void setiFSCcode(String iFSCcode) {
	this.iFSCcode = iFSCcode;
}



public String getAddress() {
	return address;
}



public void setAddress(String address) {
	this.address = address;
}



public String getAccountId() {
	return accountId;
}



public void setAccountId(String accountId) {
	this.accountId = accountId;
}



public int getAccountBalance() {
	return accountBalance;
}



public void setAccountBalance(int accountBalance) {
	this.accountBalance = accountBalance;
}



public int getUserId() {
	return userId;
}



public void setUserId(int userId) {
	this.userId = userId;
}



public BankAccountPojo(String firstName, String lastName, String phonenumber, String date, String aadharNumber,
		String iFSCcode, String address, String accountId, int accountBalance, int userId) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.phonenumber = phonenumber;
	this.date = date;
	this.aadharNumber = aadharNumber;
	this.iFSCcode = iFSCcode;
	this.address = address;
	this.accountId = accountId;
	this.accountBalance = accountBalance;
	this.userId = userId;
}
public BankAccountPojo(int userId,String firstName, String lastName, String phonenumber, String date, String aadharNumber,
		String iFSCcode, String address, String accountId, int accountBalance) {
	super();
	this.userId = userId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.phonenumber = phonenumber;
	this.date = date;
	this.aadharNumber = aadharNumber;
	this.iFSCcode = iFSCcode;
	this.address = address;
	this.accountBalance = accountBalance;
	this.accountId = accountId;
}
@Override
public String toString() {
	return "BankAccountPojo [FirstName=" + firstName + ", LastName=" + lastName + ", phonenumber=" + phonenumber
			+ ", date=" + date + ", aadharNumber=" + aadharNumber + ", IFSCcode=" + iFSCcode + ", address=" + address
			+ ", accountId=" + accountId + ", accountBalance=" + accountBalance + ", userId=" + userId + "]";
}



}
