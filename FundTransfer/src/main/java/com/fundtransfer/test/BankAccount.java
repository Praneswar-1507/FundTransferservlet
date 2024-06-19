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

import com.fundtransfer.model.BankAccountPojo;
import com.fundtransfer.model.Beneficiary;
import com.fundtransfer.model.FundTransferPojo;
import com.fundtransfer.model.TranferAmountPojo;
import com.fundtransfer.util.Jdbc;

@WebServlet("/BankAccount")
public class BankAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static BankAccountPojo account = new BankAccountPojo();
	static Jdbc crud = new Jdbc();
	static RandomGenerator generate = new RandomGenerator();
	Beneficiary beneficiary=new Beneficiary();
	private static final String USER_DATA_ATTRIBUTE = "userData";
	private static final String USER_PROFILE_PAGE = "userprofile.jsp";

	public BankAccount() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt(request.getParameter("userId"));
		ArrayList<Beneficiary> beneficiaryList = null;
		try {
			beneficiaryList=crud.beneficiaryDetails(id);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		request.setAttribute("userbeneficiary", beneficiaryList);
		request.getRequestDispatcher("viewbeneficiary.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userid = request.getParameter("id");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String phoneNumber = request.getParameter("phonenum");
		String dob = request.getParameter("dob");
		String aadharNumber = request.getParameter("aadharnumber");
		String address = request.getParameter("address");
		if (userid != null && !userid.isEmpty() && userid.matches("\\d+")) {
			int userId = Integer.parseInt(userid);
			account.setUserId(userId);
		}

		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setiFSCcode(RandomGenerator.generateRandomIFSC());
		account.setPhonenumber(phoneNumber);
		account.setDate(dob);
		account.setAadharNumber(aadharNumber);
		account.setAddress(address);
		String action = request.getParameter("action");

		switch (action) {
		case "registered":

			try {
				if (crud.bankAccountInsert(account)) {
					response.sendRedirect("home1.jsp");
				} else {
					response.sendRedirect("home1.jsp");
				}

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			break;

		case "generateID":
			int generateid = Integer.parseInt(request.getParameter("generateid"));
			account.setUserId(generateid);
			String generateID = RandomGenerator.generateRandomAccountNumber();
			account.setAccountId(generateID);
			try {
				crud.insertGeneratedIdForUser(generateid, account);
			} catch (Exception e) {

				e.printStackTrace();
			}
			Jdbc viewApproved = new Jdbc();

			ArrayList<BankAccountPojo> approvedUsers = null;

			try {
				approvedUsers = viewApproved.getApprovedUsers();

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}

			response.sendRedirect("admin.jsp");
			break;

		case "login2":
			int userId = Integer.parseInt(request.getParameter("id"));
			try {
				request.setAttribute(USER_DATA_ATTRIBUTE, crud.read1(userId));
				request.getRequestDispatcher(USER_PROFILE_PAGE).forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			break;

		case "deposit":
			int userID = Integer.parseInt(request.getParameter("accountId"));
			int depositAmount = Integer.parseInt(request.getParameter("amount"));
			int balance = 0;
			try {
				BankAccountPojo userAccount = crud.read1(userID);
				balance = userAccount.getAccountBalance() + depositAmount;

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			try {
				crud.updatedAccountBalance(userID, balance);
			} catch (Exception e) {

				e.printStackTrace();
			}
			try {
				request.setAttribute(USER_DATA_ATTRIBUTE, crud.read1(userID));
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}

			request.getRequestDispatcher(USER_PROFILE_PAGE).forward(request, response);
			break;

		case "updatePhoneNumber":
			int id = Integer.parseInt(request.getParameter("phonenumber"));
			String updatedNumber = request.getParameter("phoneNumber");
			try {
				crud.updatePhoneNumber(id, updatedNumber);
			} catch (Exception e) {

				e.printStackTrace();
			}
			try {
				request.setAttribute(USER_DATA_ATTRIBUTE, crud.read1(id));
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			request.getRequestDispatcher(USER_PROFILE_PAGE).forward(request, response);
			break;

		case "updateAddress":
			int idAddress = Integer.parseInt(request.getParameter("address"));
			String updatedAddress = request.getParameter("addressValue");
			try {
				crud.updateAddress(idAddress, updatedAddress);
			} catch (Exception e) {

				e.printStackTrace();
			}
			try {
				request.setAttribute(USER_DATA_ATTRIBUTE, crud.read1(idAddress));
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			request.getRequestDispatcher(USER_PROFILE_PAGE).forward(request, response);
			break;
		case "beneficiary":
			int userBeneficiary = Integer.parseInt(request.getParameter("userId"));
			String beneficiaryName=request.getParameter("beneficiaryName");
			String beneficiaryAccountId=request.getParameter("accountID");
			String beneficiaryIfscCode=request.getParameter("ifscCode");
			beneficiary.setBeneficiaryName(beneficiaryName);
			beneficiary.setBeneficiaryAccountId(beneficiaryAccountId);
			beneficiary.setIfsccode(beneficiaryIfscCode);
			beneficiary.setUserId(userBeneficiary);
			try {
				crud.beneficiaryInsert(beneficiary);
			} catch (ClassNotFoundException | SQLException e) {
			
				e.printStackTrace();
			}
			ArrayList<Beneficiary> details=null;
			try {
				details = crud.getdetails(userBeneficiary);
				HttpSession session = request.getSession();
				session.setAttribute("beneficiarydetails", details);
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			
		
		
		default:
			return;
		}
	}
}
