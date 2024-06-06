package com.fundtransfer.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fundtransfer.model.BankAccountPojo;
import com.fundtransfer.model.TranferAmountPojo;
import com.fundtransfer.util.Jdbc;

/**
 * Servlet implementation class BankAccount
 */
@WebServlet("/BankAccount")
public class BankAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BankAccountPojo account = new BankAccountPojo();
	Jdbc crud = new Jdbc();
	randomGenerator generate=new randomGenerator(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int userid=Integer.parseInt(request.getParameter("id"));
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		String phoneNumber=request.getParameter("phonenumber");
		String dob = request.getParameter("dob");
	    String aadharNumber=request.getParameter("aadharnumber");
	    String address=request.getParameter("address");
	    account.setUserId(userid);
	    account.setFirstName(firstName);
	    account.setLastName(lastName);
	    account.setAccountId(generate.generateRandomAccountNumber());
	    account.setIFSCcode(generate.generateRandomIFSC());
	    account.setPhonenumber(phoneNumber);
	    account.setDate(dob);
	    account.setAadharNumber(aadharNumber);
	    account.setAddress(address);
	    try {
			crud.BankAccountInsert(account);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
