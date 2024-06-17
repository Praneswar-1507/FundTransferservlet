package com.fundtransfer.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fundtransfer.model.Beneficiary;
import com.fundtransfer.util.Jdbc;

/**
 * Servlet implementation class BeneficiaryEdit
 */
@WebServlet("/BeneficiaryEdit")
public class BeneficiaryEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Jdbc crud = new Jdbc();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeneficiaryEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
		int id=Integer.parseInt(request.getParameter("deleteid"));
		try {
			crud.deleteUser(id);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		int iD = Integer.parseInt(request.getParameter("viewid"));
		System.out.println(iD);
		ArrayList<Beneficiary> beneficiaryList = null;
		try {
			beneficiaryList=crud.beneficiaryDetails(iD);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		request.setAttribute("userbeneficiary", beneficiaryList);
		request.getRequestDispatcher("viewbeneficiary.jsp").forward(request, response);
		
		
	}

}
