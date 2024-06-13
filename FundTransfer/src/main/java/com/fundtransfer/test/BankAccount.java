package com.fundtransfer.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fundtransfer.model.BankAccountPojo;

import com.fundtransfer.util.Jdbc;

@WebServlet("/BankAccount")
public class BankAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 BankAccountPojo account = new BankAccountPojo();
	 Jdbc crud = new Jdbc();
    RandomGenerator generate=new RandomGenerator(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccount() {
        super();
        
    }



    @Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid=request.getParameter("id");
		
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		String phoneNumber=request.getParameter("phonenum");
		String dob = request.getParameter("dob");
	    String aadharNumber=request.getParameter("aadharnumber");
	    String address=request.getParameter("address");
	    if (userid != null && !userid.isEmpty() && userid.matches("\\d+")) {
	        int userId = Integer.parseInt(userid);
	        account.setUserId(userId);
	    }
	  
	    account.setFirstName(firstName);
	    account.setLastName(lastName);
	    account.setiFSCcode(generate.generateRandomIFSC());
	    account.setPhonenumber(phoneNumber);
	    account.setDate(dob);
	    account.setAadharNumber(aadharNumber);
	    account.setAddress(address);
	    String action=request.getParameter("action");
	   

	    switch(action)
	    {
	    case "registered" :
	    
	    try {
			if(crud.bankAccountInsert(account))
			{
				response.sendRedirect("home1.jsp");
			}
			else
			{
				response.sendRedirect("home1.jsp");
			}
				
		} catch (ClassNotFoundException | SQLException e) {
	
			e.printStackTrace();
		}
	    break;
	    
	    case "generateID":
	    	int generateid=Integer.parseInt(request.getParameter("generateid"));
	    	account.setUserId(generateid);
	    	String generateID=generate.generateRandomAccountNumber();
	    	account.setAccountId(generateID);
	    	try {
				crud.insertGeneratedIdForUser(generateid, account);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    	  Jdbc viewApproved  = new Jdbc();     
			     
		        ArrayList<BankAccountPojo> approvedUsers = null;
		       
		            try {
						approvedUsers =viewApproved.getApprovedUsers();
						
					} catch (ClassNotFoundException | SQLException e) {
						
						e.printStackTrace();
					}
		       

		        request.setAttribute("approveduserdetails", approvedUsers);
		        
		        
		        
		        request.getRequestDispatcher("approvedusers.jsp").forward(request, response);
	    break;
	    case "login2":
	    	 int userId = Integer.parseInt(request.getParameter("id"));
	    	try {
	    	
	    		
	    		request.setAttribute("userData", crud.read1(userId));
	    		request.getRequestDispatcher("userprofile.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
	    	break;
	    case "deposit":
	    	 int userID = Integer.parseInt(request.getParameter("accountId"));
	    	 int depositAmount=Integer.parseInt(request.getParameter("amount"));
	    	 int balance = 0;
	    	 try {
				BankAccountPojo userAccount = crud.read1(userID);
				balance=userAccount.getAccountBalance()+depositAmount;
				
		
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
	    	 try {
					crud.updatedAccountBalance(userID, balance);
				} catch (Exception e) {
				
					e.printStackTrace();
				}
	    	 try {
				request.setAttribute("userData", crud.read1(userID));
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
	    	 
	    	 request.getRequestDispatcher("userprofile.jsp").forward(request, response);
	    	break;
	    case "updatePhoneNumber":
	    	int id = Integer.parseInt(request.getParameter("phonenumber"));
	    	String updatedNumber=request.getParameter("phoneNumber");
	    	try {
				crud.updatePhoneNumber(id, updatedNumber);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    	try {
				request.setAttribute("userData", crud.read1(id));
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
	    	 request.getRequestDispatcher("userprofile.jsp").forward(request, response);
		    break;
	    case "updateAddress":
	    	int idAddress = Integer.parseInt(request.getParameter("address"));
	    	String updatedAddress=request.getParameter("addressValue");
	    	try {
				crud.updateAddress(idAddress, updatedAddress);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    	try {
				request.setAttribute("userData", crud.read1(idAddress));
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
	    	request.getRequestDispatcher("userprofile.jsp").forward(request, response);
	    	break;
	      default:
	    	  return;
	     
	    
	    }
	 
	  
	     
	

}
}
