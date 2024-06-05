package com.fundtransfer.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


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
            String check ="insert into Users (user_name,phonenumber,email,user_Password)values(?,?,?,?)";

            PreparedStatement p = connection.prepareStatement(check);
            p.setString(1,transfer.getUsername() );
            p.setString(2,transfer.getPhonenumber());
            p.setString(3,transfer.getEmail() );
            p.setString(4,transfer.getPassword());
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





}
