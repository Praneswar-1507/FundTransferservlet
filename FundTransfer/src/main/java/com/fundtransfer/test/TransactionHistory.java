package com.fundtransfer.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fundtransfer.model.TranferAmountPojo;
import com.fundtransfer.util.Jdbc;

/**
 * Servlet implementation class TransactionHistory
 */
@WebServlet("/TransactionHistory")
public class TransactionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Jdbc crud = new Jdbc();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int userId=Integer.parseInt(request.getParameter("userId"));
		System.out.println(userId);
		ArrayList<TranferAmountPojo> history=null;
		try {
			history=crud.TransactionDetails(userId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(history);
		request.setAttribute("usertransaction", history);
		 request.getRequestDispatcher("transactionhistory.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
