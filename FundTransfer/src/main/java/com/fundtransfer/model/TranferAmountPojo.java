package com.fundtransfer.model;

import java.time.LocalDate;

public class TranferAmountPojo {
long SendAccountNo,RecepientAccountNo;
int Amount,transferId;
String IFSC,date,transfertype;


public long getSendAccountNo() {
	return SendAccountNo;
}

public void setSendAccountNo(long sendAccountNo) {
	SendAccountNo = sendAccountNo;
}

public long getRecepientAccountNo() {
	return RecepientAccountNo;
}

public void setRecepientAccountNo(long recepientAccountNo) {
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



public TranferAmountPojo(long sendAccountNo, long recepientAccountNo, int amount, int transferId, String iFSC,
		String date, String transfertype) {
	super();
	SendAccountNo = sendAccountNo;
	RecepientAccountNo = recepientAccountNo;
	Amount = amount;
	this.transferId = transferId;
	IFSC = iFSC;
	this.date = date;
	this.transfertype = transfertype;
}

public TranferAmountPojo() {
	
}
}
