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
 * Servlet implementation class AmountTransfer
 */
@WebServlet("/AmountTransfer")
public class AmountTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TranferAmountPojo fundTransfer=new TranferAmountPojo();
	Jdbc crud=new Jdbc();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmountTransfer() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
		int userid=Integer.parseInt(request.getParameter("fundId"));
		System.out.println(userid);
		String transferType=request.getParameter("transferType");
		String senderAccount = request.getParameter("senderAccount");
		String receiverAccount = request.getParameter("receiverAccount");
		String IFSC = request.getParameter("ifsc");
		int amount =Integer.parseInt( request.getParameter("amount"));
		fundTransfer.setUserId(userid);
		fundTransfer.setSendAccountNo(senderAccount);
		fundTransfer.setRecepientAccountNo(receiverAccount);
		fundTransfer.setTransfertype(transferType);
		fundTransfer.setIFSC(IFSC);
		fundTransfer.setAmount(amount);
		int remainingAmount=0;
		try {
			crud.insert(fundTransfer);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BankAccountPojo userAccount = crud.Read1(userid);
			if(amount<userAccount.getAccountBalance())
			{
			 remainingAmount=userAccount.getAccountBalance()-amount;
			System.out.println(remainingAmount);
			}
			else
			{
				response.sendRedirect("fundtransfer.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			crud.updatedAccountBalance(userid, remainingAmount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
