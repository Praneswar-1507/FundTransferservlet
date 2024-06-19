package com.fundtransfer.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;




import com.fundtransfer.model.BankAccountPojo;
import com.fundtransfer.model.Beneficiary;
import com.fundtransfer.model.FundTransferPojo;
import com.fundtransfer.model.TranferAmountPojo;

public class Jdbc {
    
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "Last_name";
    private static final String COLUMN_AADHAR_NUMBER = "aadhar_number";
    private static final String COLUMN_ACCOUNT_BALANCE = "account_Balance";
    private static final String COLUMN_ACCOUNT_ID = "account_ID";
    private static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
    private static final String COLUMN_PHONE_NUMBER = "phonenumber";
    private static final String COLUMN_RESIDENTIAL_ADDRESS = "residential_address";
    private static final String COLUMN_USER_ID = "user_ID";

    public static boolean register(FundTransferPojo transfer) throws ClassNotFoundException, SQLException {
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
        if(!result.next()) {
            return false;
        }
        return true;
    }

    public boolean bankAccountInsert(BankAccountPojo account) throws ClassNotFoundException, SQLException {
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
            p.setInt(1,account.getUserId());
            p.setString(2,account.getAccountId());
            p.setString(3,account.getFirstName());
            p.setString(4,account.getLastName());
            p.setString(5,account.getPhonenumber());
            Date currentDate = Date.valueOf(LocalDate.now());
            p.setString(6,account.getDate() );
            p.setString(7,account.getAadharNumber());
            p.setString(8,account.getiFSCcode());
            p.setString(9,account.getAddress());
            p.executeUpdate();
            return true;
        } else {
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
        p.setString(4,amount.getiFSC());
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
                int id = re.getInt(COLUMN_USER_ID);
                String name=re.getString("user_name");
                a.setId(id);
                a.setUsername(name);
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

    public BankAccountPojo getUserData(BankAccountPojo bankAccount) throws ClassNotFoundException, SQLException {
        Connection con = Util.getConnection();
        String query = "SELECT * FROM Accounts WHERE aadhar_number=?";
        BankAccountPojo getdata=new BankAccountPojo();
        try (PreparedStatement p = con.prepareStatement(query)) {
            p.setString(1,bankAccount.getAadharNumber());
            ResultSet re = p.executeQuery();
            
            if (re.next()) {
                String accountId = re.getString(COLUMN_ACCOUNT_ID);
                bankAccount.setAccountId(accountId);
               
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

    public ArrayList<BankAccountPojo> read() throws ClassNotFoundException, SQLException {
        ArrayList<BankAccountPojo> user = new ArrayList<>();
        Connection connection = Util.getConnection();
        String query = "Select * from accounts";
        PreparedStatement p = connection.prepareStatement(query);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            BankAccountPojo bankaccount = new BankAccountPojo();
            bankaccount.setUserId(rs.getInt(COLUMN_USER_ID));
            bankaccount.setFirstName(rs.getString(COLUMN_FIRST_NAME));
            bankaccount.setLastName(rs.getString(COLUMN_LAST_NAME));
            bankaccount.setPhonenumber(rs.getString(COLUMN_PHONE_NUMBER));
            bankaccount.setDate(rs.getString(COLUMN_DATE_OF_BIRTH));
            bankaccount.setAadharNumber(rs.getString(COLUMN_AADHAR_NUMBER));
            bankaccount.setiFSCcode(rs.getString("IFSC"));
            bankaccount.setAddress(rs.getString(COLUMN_RESIDENTIAL_ADDRESS));
            bankaccount.setAccountBalance(rs.getInt(COLUMN_ACCOUNT_BALANCE));
            bankaccount.setAccountId(rs.getString(COLUMN_ACCOUNT_ID));
            user.add(bankaccount);
        }
        return user;
    }

    public BankAccountPojo read1(int id) throws ClassNotFoundException, SQLException {
        BankAccountPojo bankaccount = new BankAccountPojo();
        Connection connection = Util.getConnection();
        String query = "Select * from accounts where user_ID=?";
        PreparedStatement p = connection.prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            
            bankaccount.setUserId(rs.getInt(COLUMN_USER_ID));
            bankaccount.setFirstName(rs.getString(COLUMN_FIRST_NAME));
            bankaccount.setLastName(rs.getString(COLUMN_LAST_NAME));
            bankaccount.setPhonenumber(rs.getString(COLUMN_PHONE_NUMBER));
            bankaccount.setDate(rs.getString(COLUMN_DATE_OF_BIRTH));
            bankaccount.setAadharNumber(rs.getString(COLUMN_AADHAR_NUMBER));
            bankaccount.setiFSCcode(rs.getString("IFSC"));
            bankaccount.setAddress(rs.getString(COLUMN_RESIDENTIAL_ADDRESS));
            bankaccount.setAccountBalance(rs.getInt(COLUMN_ACCOUNT_BALANCE));
            bankaccount.setAccountId(rs.getString(COLUMN_ACCOUNT_ID));
        }
        return bankaccount;
        
    }

    public ArrayList<TranferAmountPojo> transactionDetails(int id) throws ClassNotFoundException, SQLException {
        ArrayList<TranferAmountPojo> transactionList = new ArrayList<>();
        Connection connection = Util.getConnection();
        String query = "Select * from Transfers where user_ID=?";
        PreparedStatement p = connection.prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            TranferAmountPojo transaction=new TranferAmountPojo();
            transaction.setUserId(rs.getInt(COLUMN_USER_ID));
            transaction.setTransferId(rs.getInt("transfer_ID"));
            transaction.setSendAccountNo(rs.getString("sender_Account_ID"));
            transaction.setRecepientAccountNo(rs.getString("Recipient_ID"));
            transaction.setDate(rs.getString("transfer_Date"));
            transaction.setiFSC(rs.getString("IFSC_code"));
            transaction.setTransfertype(rs.getString("transfer_Type"));
            transaction.setAmount(rs.getInt("transfer_Amount"));
            transactionList.add(transaction);
        }
        return transactionList;
        
    }

    public void insertGeneratedIdForUser(int userId,BankAccountPojo account) throws Exception {
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
            
                BankAccountPojo approveddetails = new BankAccountPojo();
                approveddetails.setUserId(resultSet.getInt(COLUMN_USER_ID));
                approveddetails.setFirstName(resultSet.getString(COLUMN_FIRST_NAME));
                approveddetails.setLastName(resultSet.getString(COLUMN_LAST_NAME));
                approveddetails.setPhonenumber(resultSet.getString(COLUMN_PHONE_NUMBER));
                approveddetails.setDate(resultSet.getString(COLUMN_DATE_OF_BIRTH));
                approveddetails.setAadharNumber(resultSet.getString(COLUMN_AADHAR_NUMBER));
                approveddetails.setiFSCcode(resultSet.getString("IFSC"));
                approveddetails.setAddress(resultSet.getString(COLUMN_RESIDENTIAL_ADDRESS));
                approveddetails.setAccountBalance(resultSet.getInt(COLUMN_ACCOUNT_BALANCE));
                approveddetails.setAccountId(resultSet.getString(COLUMN_ACCOUNT_ID));
                approved.add(approveddetails);
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
    public void updateBeneficiaryAccountBalance(int beneficiaryAccountId,int balance) throws Exception {
        Connection connection = Util.getConnection(); 
        String query = "UPDATE beneficiary SET account_Balance=?  WHERE beneficiary_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,balance);
        preparedStatement.setInt(2, beneficiaryAccountId);
        preparedStatement.executeUpdate();
    }


    public void updatePhoneNumber(int userId, String phoneNumber) throws UpdatePhoneNumberException, ClassNotFoundException {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Accounts SET phonenumber=? WHERE user_ID = ?")) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdatePhoneNumberException("Error updating phone number for user ID: " + userId);
        }
    }

    public void updateAddress(int userId, String address) throws UpdateAddressException, ClassNotFoundException {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Accounts SET residential_address=?  WHERE user_ID = ?")) {
            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateAddressException("Error updating address for user ID: " + userId);
        }
    }

    public static class UpdateAddressException extends Exception {
        public UpdateAddressException(String message) {
            super(message);
        }
    }

    public static class UpdatePhoneNumberException extends Exception {
        public UpdatePhoneNumberException(String message) {
            super(message);
        }
    }
    public static void beneficiaryInsert(Beneficiary beneficiary) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = Util.getConnection();
        String query = "insert into beneficiary(user_ID,beneficiary_name,account_ID,ifsccode) values(?,?,?,?)";
        PreparedStatement p = connection.prepareStatement(query);
        p.setInt(1,beneficiary.getUserId());
        p.setString(2,beneficiary.getBeneficiaryName());
        p.setString(3,beneficiary.getBeneficiaryAccountId());
        p.setString(4,beneficiary.getIfsccode());
        p.executeUpdate();
    }
    public ArrayList<Beneficiary> beneficiaryDetails(int id) throws ClassNotFoundException, SQLException {
        ArrayList<Beneficiary> beneficiaryList = new ArrayList<>();
        Connection connection = Util.getConnection();
        String query = "Select beneficiary_ID,beneficiary_name,account_ID,ifsccode from beneficiary where user_ID=?";
        PreparedStatement p = connection.prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
        	Beneficiary beneficiary=new Beneficiary();
            beneficiary.setBeneficiaryId(rs.getInt("beneficiary_ID"));
           beneficiary.setBeneficiaryName(rs.getString("beneficiary_name"));
           beneficiary.setBeneficiaryAccountId(rs.getString("account_ID"));
           beneficiary.setIfsccode(rs.getString("ifsccode"));
            
           beneficiaryList.add(beneficiary);
        }
        return beneficiaryList;
        
    }
    public ArrayList<Beneficiary> getdetails(int id) throws ClassNotFoundException, SQLException {
        ArrayList<Beneficiary> beneficiaryList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = Util.getConnection();
            String query = "SELECT * FROM beneficiary WHERE user_ID=?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	
            	
                Beneficiary info = new Beneficiary();
                info.setBeneficiaryId(rs.getInt("beneficiary_ID"));
                info.setBeneficiaryAccountId(rs.getString("account_ID"));
                info.setBeneficiaryName(rs.getString("beneficiary_name"));
                info.setIfsccode(rs.getString("ifsccode"));
                info.setBeneficiaryBalance(rs.getInt("account_balance"));
                
                beneficiaryList.add(info);
                System.out.println(beneficiaryList);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return beneficiaryList;
    }
    public Beneficiary readBeneficiary(int id) throws ClassNotFoundException, SQLException {
    	Beneficiary beneficiaryList = new Beneficiary();
        Connection connection = Util.getConnection();
        String query = "Select * from beneficiary where beneficiary_ID=?";
        PreparedStatement p = connection.prepareStatement(query);
        p.setInt(1, id);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            
        	beneficiaryList.setBeneficiaryId(rs.getInt("beneficiary_ID"));
        	beneficiaryList.setBeneficiaryAccountId(rs.getString("account_ID"));
        	beneficiaryList.setBeneficiaryBalance(rs.getInt("account_balance"));
        }
        return beneficiaryList;
        
    }
    public void deleteUser(int deleteid) throws ClassNotFoundException, SQLException {
        
        
        Connection connection = Util.getConnection();
        String delete = "delete from beneficiary where beneficiary_ID=?";
        PreparedStatement prepareStatement = connection.prepareStatement(delete);
        prepareStatement.setInt(1,deleteid);
        prepareStatement.executeUpdate();
        
           
       
    }
    public static void updateUser(Beneficiary beneficiary) throws ClassNotFoundException, SQLException {
	
		Connection connection = Util.getConnection();
		String query = "update beneficiary set beneficiary_name=?,account_ID=?,ifsccode=? where beneficiary_ID=?";
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1,beneficiary.getBeneficiaryName());

		ps.setString(2,beneficiary.getBeneficiaryAccountId());
		ps.setString(3,beneficiary.getIfsccode());
		ps.setInt(4,beneficiary.getBeneficiaryId());
		int row = ps.executeUpdate();
		
	}
}
