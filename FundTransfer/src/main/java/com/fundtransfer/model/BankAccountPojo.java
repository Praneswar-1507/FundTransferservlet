package com.fundtransfer.model;

public class BankAccountPojo {
String FirstName,LastName,phonenumber,date,aadharNumber,IFSCcode,address,accountId;
int accountBalance,userId;
public BankAccountPojo() {

}

public String getFirstName() {
	return FirstName;
}

public void setFirstName(String firstName) {
	FirstName = firstName;
}

public String getLastName() {
	return LastName;
}

public void setLastName(String lastName) {
	LastName = lastName;
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

public String getIFSCcode() {
	return IFSCcode;
}

public void setIFSCcode(String iFSCcode) {
	IFSCcode = iFSCcode;
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
	FirstName = firstName;
	LastName = lastName;
	this.phonenumber = phonenumber;
	this.date = date;
	this.aadharNumber = aadharNumber;
	IFSCcode = iFSCcode;
	this.address = address;
	this.accountId = accountId;
	this.accountBalance = accountBalance;
	this.userId = userId;
}

@Override
public String toString() {
	return "BankAccountPojo [FirstName=" + FirstName + ", LastName=" + LastName + ", phonenumber=" + phonenumber
			+ ", date=" + date + ", aadharNumber=" + aadharNumber + ", IFSCcode=" + IFSCcode + ", address=" + address
			+ ", accountId=" + accountId + ", accountBalance=" + accountBalance + ", userId=" + userId + "]";
}



}
