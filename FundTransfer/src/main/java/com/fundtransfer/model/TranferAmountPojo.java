package com.fundtransfer.model;

import java.time.LocalDate;

public class TranferAmountPojo {
String SendAccountNo,RecepientAccountNo;
int Amount,transferId,userId;
String IFSC,date,transfertype;







public String getSendAccountNo() {
	return SendAccountNo;
}

public void setSendAccountNo(String sendAccountNo) {
	SendAccountNo = sendAccountNo;
}

public String getRecepientAccountNo() {
	return RecepientAccountNo;
}

public void setRecepientAccountNo(String recepientAccountNo) {
	RecepientAccountNo = recepientAccountNo;
}

public int getAmount() {
	return Amount;
}

public void setAmount(int amount) {
	Amount = amount;
}

public int getTransferId() {
	return transferId;
}

public void setTransferId(int transferId) {
	this.transferId = transferId;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getIFSC() {
	return IFSC;
}

public void setIFSC(String iFSC) {
	IFSC = iFSC;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getTransfertype() {
	return transfertype;
}

public void setTransfertype(String transfertype) {
	this.transfertype = transfertype;
}



public TranferAmountPojo(String sendAccountNo, String recepientAccountNo, int amount, int transferId, int userId,
		String iFSC, String date, String transfertype) {
	super();
	SendAccountNo = sendAccountNo;
	RecepientAccountNo = recepientAccountNo;
	Amount = amount;
	this.transferId = transferId;
	this.userId = userId;
	IFSC = iFSC;
	this.date = date;
	this.transfertype = transfertype;
}

public TranferAmountPojo() {
	
}

@Override
public String toString() {
	return "TranferAmountPojo [SendAccountNo=" + SendAccountNo + ", RecepientAccountNo=" + RecepientAccountNo
			+ ", Amount=" + Amount + ", transferId=" + transferId + ", userId=" + userId + ", IFSC=" + IFSC + ", date="
			+ date + ", transfertype=" + transfertype + "]";
}



}
