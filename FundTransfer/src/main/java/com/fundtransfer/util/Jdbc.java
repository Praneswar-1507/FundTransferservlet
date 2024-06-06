package com.fundtransfer.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import com.fundtransfer.model.BankAccountPojo;
import com.fundtransfer.model.FundTransferPojo;
import com.fundtransfer.model.TranferAmountPojo;
import com.fundtransfer.util.Util;

public class Jdbc {
public boolean register(FundTransferPojo transfer) throws ClassNotFoundException, SQLException {
        Connection connection = Util.getConnection();
        
        String query = "SELECT user_name FROM Users WHERE user_name=? and email=?";
        PreparedStatement prepare = connection.prepareStatement(query);
        prepare.setString(1, transfer.getUsername());
        prepare.setString(2, transfer.getEmail());
        ResultSet result = prepare.executeQuery();
        if (!result.next()) {
            String check ="insert into Users (user_name,email,user_Password)values(?,?,?)";
            PreparedStatement p = connection.prepareStatement(check);
            p.setString(1,transfer.getUsername() );
            p.setString(2,transfer.getEmail() );
            p.setString(3,transfer.getPassword());
            p.execute();
            System.out.println("registered  successfull");
            return true;
        } else {
            return false;

        }
}
public static boolean login(FundTransferPojo transfer) throws ClassNotFoundException, SQLException {
    Connection connection = Util.getConnection();
    String query = "SELECT email,user_Password FROM Users WHERE user_name=? AND user_password=?";
    PreparedStatement prepare = connection.prepareStatement(query);
    prepare.setString(1,transfer.getUsername());
    prepare.setString(2,transfer.getPassword());
    ResultSet result = prepare.executeQuery();
    if(!result.next())
    {
        
        return false;
    }
    
       
        return true;
        
    
}
public static void BankAccountInsert(BankAccountPojo account) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection = Util.getConnection();
	String query = "insert into Accounts(account_ID,first_name,Last_name,phonenumber,date_of_birth,aadhar_number,IFSC,residential_address) values(?,?,?,?,?,?,?,?,?)";
	PreparedStatement p = connection.prepareStatement(query);
	System.out.println();
	p.setInt(1,account.getUserId());
	p.setString(2,account.getAccountId());
	p.setString(3,account.getFirstName());
	p.setString(4,account.getLastName());
	p.setString(5,account.getPhonenumber());
	Date currentDate = Date.valueOf(LocalDate.now());
	p.setString(6,account.getDate() );
	p.setString(7,account.getAadharNumber());
	 p.setString(8,account.getIFSCcode());
	 p.setString(9,account.getAddress());
	 p.executeUpdate();
}

public static void insert(TranferAmountPojo amount) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection = Util.getConnection();
	String query = "insert into transfers(sender_Account_ID,Recipient_ID,IFSC_code,transfer_Type,transfer_Date,transfer_Amount) values(?,?,?,?,?,?)";
	PreparedStatement p = connection.prepareStatement(query);
	p.setLong(1,amount.getSendAccountNo());
	p.setLong(2,amount.getRecepientAccountNo());
	p.setString(3,amount.getIFSC());
	p.setString(4,amount.getTransfertype());
	Date currentDate = Date.valueOf(LocalDate.now());
	p.setDate(5,currentDate );
	p.setInt(6,amount.getAmount());
	 p.executeUpdate();
}
public FundTransferPojo getid(FundTransferPojo transfer)throws ClassNotFoundException,SQLException
{
    Connection con=Util.getConnection();
    String query="SELECT *FROM Users WHERE email=?";
    PreparedStatement p = con.prepareStatement(query);
    p.setString(1,transfer.getEmail());
    ResultSet re=p.executeQuery();
    while(re.next())
    {
        int id=re.getInt("user_ID");
        System.out.println(id);
        transfer.setId(id);
        System.out.println(id);
   
        return transfer;
    }
 return null;
}
}








