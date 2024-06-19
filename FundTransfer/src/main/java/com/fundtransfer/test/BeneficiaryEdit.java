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
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action=request.getParameter("action");
	switch(action) {
	case "deletebeneficiary":
		doGet(request, response);
		int id=Integer.parseInt(request.getParameter("deleteid"));
		try {
			crud.deleteUser(id);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		int iD = Integer.parseInt(request.getParameter("viewid"));
		ArrayList<Beneficiary> beneficiaryList = null;
		try {
			beneficiaryList=crud.beneficiaryDetails(iD);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		request.setAttribute("userbeneficiary", beneficiaryList);
		request.getRequestDispatcher("viewbeneficiary.jsp").forward(request, response);
		break;
	case "editbeneficiary":
		Beneficiary beneficiary = new Beneficiary();
		int userupdateid = Integer.parseInt(request.getParameter("editBeneficiaryId"));
		String name=request.getParameter("editBeneficiaryName");
		String accountId=request.getParameter("editBeneficiaryAccountId");
		String ifsc=request.getParameter("editBeneficiaryIfscCode");
		beneficiary.setBeneficiaryId(userupdateid);
		beneficiary.setBeneficiaryName(name);
		beneficiary.setBeneficiaryAccountId(accountId);
		beneficiary.setIfsccode(ifsc);
		try {
			crud.updateUser(beneficiary);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		int userid = Integer.parseInt(request.getParameter("id"));
		
		ArrayList<Beneficiary> beneficiaryList1 = null;
		try {
			beneficiaryList1=crud.beneficiaryDetails(userid);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		request.setAttribute("userbeneficiary", beneficiaryList1);
		request.getRequestDispatcher("viewbeneficiary.jsp").forward(request, response);
		break;
	}
    }
}
