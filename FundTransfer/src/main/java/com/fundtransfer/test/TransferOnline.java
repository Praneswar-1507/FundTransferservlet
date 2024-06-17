package com.fundtransfer.test;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fundtransfer.model.Beneficiary;
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
	Beneficiary beneficiary=new Beneficiary();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferOnline() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		String username = request.getParameter("username");
		String phonenumber = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		transfer.setUsername(username);
		transfer.setPhonenumber(phonenumber);
		transfer.setEmail(email);
		transfer.setPassword(password);
		String action = request.getParameter("action");

		switch (action) {
		case "register":
			try {
				if (Jdbc.register(transfer)) {
					response.sendRedirect("Login.jsp");
				} else {
					response.sendRedirect("Login.jsp");
				}

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "login1":
			try {
				if (Jdbc.login(transfer)) {

					FundTransferPojo id = crud.getid(transfer);
					HttpSession session = request.getSession();
					session.setAttribute("user", id);
				
					ArrayList<Beneficiary> details=null;
					try {
						details = crud.getdetails(id.getId());
						session.setAttribute("beneficiarydetails", details);
						System.out.println(details);
					} catch (ClassNotFoundException | SQLException e) {
						
						e.printStackTrace();
					}

					if (email.endsWith("@fastpay.com")) {
						response.sendRedirect("admin.jsp");

					} else {

						response.sendRedirect("home1.jsp");
					}

				} else {
					response.sendRedirect("signup.jsp");
				}
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			return;

		}
	}

}
