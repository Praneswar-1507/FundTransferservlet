package com.fundtransfer.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fundtransfer.model.FundTransferPojo;
import com.fundtransfer.util.Jdbc;

/**
 * Servlet implementation class TransferOnline
 */
@WebServlet("/TransferOnline")
public class TransferOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FundTransferPojo transfer = new FundTransferPojo();
	Jdbc crud = new Jdbc();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferOnline() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		   System.out.println("get");
			String username=request.getParameter("username");
			String phonenumber=request.getParameter("phone");
			String email=request.getParameter("email");
		    String password=request.getParameter("password");
		    transfer.setUsername(username);
		    transfer.setPhonenumber(phonenumber);
		    transfer.setEmail(email);
		    transfer.setPassword(password);
		    String action=request.getParameter("action");
		    System.out.println(action);
//		    HttpSession session = request.getSession();		    if(action!=null)
		    {
		    	switch(action)
		    	{
		    		case "register":
		    try {
				if(crud.register(transfer))
				{
					response.sendRedirect("Login.jsp");
				}
				else
				{
					response.sendRedirect("Login.jsp");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    break;
		    		case "login":
					try {
						if(crud.login(transfer))
		    			{   
							
							  FundTransferPojo id=crud.getid(transfer); 
							  HttpSession session =request.getSession(); session.setAttribute("user",id);
							   	 
							  
							 
		    				response.sendRedirect("bankaccount.jsp");
		    			}
		    			else
						{
							response.sendRedirect("signup.jsp");
						}
					} catch (ClassNotFoundException | SQLException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    }
		    			
		    			
		    			
		    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
