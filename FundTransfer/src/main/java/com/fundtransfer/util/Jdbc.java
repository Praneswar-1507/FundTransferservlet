package com.fundtransfer.util;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpSession;

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
    String query = "SELECT email,user_Password FROM Users WHERE email=? AND user_password=?";
    PreparedStatement prepare = connection.prepareStatement(query);
    prepare.setString(1,transfer.getEmail());
    prepare.setString(2,transfer.getPassword());
    ResultSet result = prepare.executeQuery();
    if(!result.next())
    {
        
        return false;
    }
    
       
        return true;
        
    
}
public boolean BankAccountInsert(BankAccountPojo account) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection = Util.getConnection();
	 String query = "SELECT user_ID FROM Accounts WHERE user_ID=? and aadhar_number=?";
     PreparedStatement prepare = connection.prepareStatement(query);
     prepare.setInt(1, account.getUserId());
     prepare.setString(2,account.getAadharNumber());
     ResultSet result = prepare.executeQuery();
     if (!result.next()) {
	String check = "insert into Accounts(user_ID,account_ID,first_name,Last_name,phonenumber,date_of_birth,aadhar_number,IFSC,residential_address) values(?,?,?,?,?,?,?,?,?)";
	PreparedStatement p = connection.prepareStatement(check);
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
	 return true;
     }
     else {
    	 System.out.println("Already have an account ");
         return false;
        

     }
}

public static void insert(TranferAmountPojo amount) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection = Util.getConnection();
	String query = "insert into transfers(user_ID,sender_Account_ID,Recipient_ID,IFSC_code,transfer_Type,transfer_Date,transfer_Amount) values(?,?,?,?,?,?,?)";
	PreparedStatement p = connection.prepareStatement(query);
	p.setInt(1,amount.getUserId());
	p.setString(2,amount.getSendAccountNo());
	p.setString(3,amount.getRecepientAccountNo());
	p.setString(4,amount.getIFSC());
	p.setString(5,amount.getTransfertype());
	Date currentDate = Date.valueOf(LocalDate.now());
	p.setDate(6,currentDate );
	p.setInt(7,amount.getAmount());
	 p.executeUpdate();
}
public FundTransferPojo getid(FundTransferPojo transfer) throws ClassNotFoundException, SQLException {
    Connection con = Util.getConnection();
    String query = "SELECT * FROM Users WHERE email=?";
    FundTransferPojo a=new FundTransferPojo();
    try (PreparedStatement p = con.prepareStatement(query)) {
        p.setString(1, transfer.getEmail());
        ResultSet re = p.executeQuery();
        
        if (re.next()) {
            int id = re.getInt("user_ID");
            String name=re.getString("user_name");
            a.setId(id);
            a.setUsername(name);
            System.out.println("User ID retrieved: " + id);
            System.out.println("name:"+name);
            return a;
        }
    } catch (SQLException e) {
        e.printStackTrace();
      
    } finally {
       
        if (con != null) {
            con.close();
        }
    }
    return null;
}
public BankAccountPojo getUserData(BankAccountPojo BankAccount) throws ClassNotFoundException, SQLException {
    Connection con = Util.getConnection();
    String query = "SELECT * FROM Accounts WHERE aadhar_number=?";
    BankAccountPojo getdata=new BankAccountPojo();
    try (PreparedStatement p = con.prepareStatement(query)) {
        p.setString(1,BankAccount.getAadharNumber());
        ResultSet re = p.executeQuery();
        
        if (re.next()) {
            String accountId = re.getString("account_ID");
            BankAccount.setAccountId(accountId);
            System.out.println("User ID retrieved: " + accountId);
           
            return getdata;
        }
    } catch (SQLException e) {
        e.printStackTrace();
      
    } finally {
       
        if (con != null) {
            con.close();
        }
    }
    return null;
}
public ArrayList<BankAccountPojo> Read() throws ClassNotFoundException, SQLException {
	ArrayList<BankAccountPojo> user = new ArrayList<BankAccountPojo>();
	Connection connection = Util.getConnection();
	String query = "Select * from accounts";
	PreparedStatement p = connection.prepareStatement(query);
	ResultSet rs = p.executeQuery();
	while (rs.next()) {
		BankAccountPojo bankaccount = new BankAccountPojo();
		bankaccount.setUserId(rs.getInt("user_ID"));
		bankaccount.setFirstName(rs.getString("first_name"));
		bankaccount.setLastName(rs.getString("Last_name"));
		bankaccount.setPhonenumber(rs.getString("phonenumber"));
		bankaccount.setDate(rs.getString("date_of_birth"));
		bankaccount.setAadharNumber(rs.getString("aadhar_number"));
		bankaccount.setIFSCcode(rs.getString("IFSC"));
		bankaccount.setAddress(rs.getString("residential_address"));
		bankaccount.setAccountBalance(rs.getInt("account_Balance"));
		bankaccount.setAccountId(rs.getString("account_ID"));
		user.add(bankaccount);
	}

	return user;
}
public BankAccountPojo Read1(int id) throws ClassNotFoundException, SQLException {
	BankAccountPojo bankaccount = new BankAccountPojo();
	Connection connection = Util.getConnection();
	String query = "Select * from accounts where user_ID=?";
	PreparedStatement p = connection.prepareStatement(query);
	p.setInt(1, id);
	ResultSet rs = p.executeQuery();
	while (rs.next()) {
		
		bankaccount.setUserId(rs.getInt("user_ID"));
		bankaccount.setFirstName(rs.getString("first_name"));
		bankaccount.setLastName(rs.getString("Last_name"));
		bankaccount.setPhonenumber(rs.getString("phonenumber"));
		bankaccount.setDate(rs.getString("date_of_birth"));
		bankaccount.setAadharNumber(rs.getString("aadhar_number"));
		bankaccount.setIFSCcode(rs.getString("IFSC"));
		bankaccount.setAddress(rs.getString("residential_address"));
		bankaccount.setAccountBalance(rs.getInt("account_Balance"));
		bankaccount.setAccountId(rs.getString("account_ID"));
	}
	return bankaccount;
	
}
public ArrayList<TranferAmountPojo> TransactionDetails(int id) throws ClassNotFoundException, SQLException {
	System.out.println(id);
	ArrayList<TranferAmountPojo> transactionList = new ArrayList<TranferAmountPojo>();
	Connection connection = Util.getConnection();
	String query = "Select * from Transfers where user_ID=?";
	PreparedStatement p = connection.prepareStatement(query);
	p.setInt(1, id);
	ResultSet rs = p.executeQuery();
	while (rs.next()) {
		TranferAmountPojo transaction=new TranferAmountPojo();
		transaction.setUserId(rs.getInt("user_ID"));
		transaction.setTransferId(rs.getInt("transfer_ID"));
		transaction.setSendAccountNo(rs.getString("sender_Account_ID"));
		transaction.setRecepientAccountNo(rs.getString("Recipient_ID"));
		transaction.setDate(rs.getString("transfer_Date"));
		transaction.setIFSC(rs.getString("IFSC_code"));
		transaction.setTransfertype(rs.getString("transfer_Type"));
	    transaction.setAmount(rs.getInt("transfer_Amount"));
	    transactionList.add(transaction);
	}
	return transactionList;
	
}
public void insertGeneratedIdForUser(int userId,BankAccountPojo account) throws Exception {
	System.out.println(userId);
	System.out.println(account.getAccountId());
    Connection connection = Util.getConnection(); 
    String query = "UPDATE Accounts SET account_ID=?,is_approval =true  WHERE user_ID = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1,account.getAccountId());
    preparedStatement.setInt(2, userId);
    preparedStatement.executeUpdate();
}
public boolean bankAccount(FundTransferPojo transfer) throws ClassNotFoundException, SQLException {
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
public ArrayList<BankAccountPojo> getApprovedUsers() throws ClassNotFoundException, SQLException {
    ArrayList<BankAccountPojo> approved = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        connection = Util.getConnection();
        String query = "SELECT * FROM Accounts WHERE is_approval = true";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        
        	BankAccountPojo Approveddetails = new BankAccountPojo();
        	Approveddetails.setUserId(resultSet.getInt("user_ID"));
        	Approveddetails.setFirstName(resultSet.getString("first_name"));
        	Approveddetails.setLastName(resultSet.getString("Last_name"));
        	Approveddetails.setPhonenumber(resultSet.getString("phonenumber"));
        	Approveddetails.setDate(resultSet.getString("date_of_birth"));
        	Approveddetails.setAadharNumber(resultSet.getString("aadhar_number"));
        	Approveddetails.setIFSCcode(resultSet.getString("IFSC"));
        	Approveddetails.setAddress(resultSet.getString("residential_address"));
        	Approveddetails.setAccountBalance(resultSet.getInt("account_Balance"));
        	Approveddetails.setAccountId(resultSet.getString("account_ID"));
            approved.add(Approveddetails);
        }
    } finally {
        
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    return approved;
}
public void updatedAccountBalance(int userId,int balance) throws Exception {
    Connection connection = Util.getConnection(); 
    String query = "UPDATE Accounts SET account_Balance=?  WHERE user_ID = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1,balance);
    preparedStatement.setInt(2, userId);
    preparedStatement.executeUpdate();
}
public void updatePhoneNumber(int userId,String phoneNumber) throws Exception {
    Connection connection = Util.getConnection(); 
    String query = "UPDATE Accounts SET phonenumber=?  WHERE user_ID = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1,phoneNumber);
    preparedStatement.setInt(2, userId);
    preparedStatement.executeUpdate();
}
public void updateAddress(int userId,String address) throws Exception {
	System.out.println(address);
	System.out.println(userId);
    Connection connection = Util.getConnection(); 
    String query = "UPDATE Accounts SET residential_address=?  WHERE user_ID = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1,address);
    preparedStatement.setInt(2, userId);
    preparedStatement.executeUpdate();
}


}








