package com.fundtransfer.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fundtransfer.model.FundTransferPojo;
import com.fundtransfer.model.TranferAmountPojo;
import com.fundtransfer.util.Jdbc;

/**
 * Servlet implementation class TransferAmount
 */
@WebServlet("/TransferAmount")
public class TransferAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TranferAmountPojo amount = new TranferAmountPojo();
	Jdbc crud = new Jdbc();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferAmount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String senderAccNo=request.getParameter("senderAccount");
		String recepientAccNo=request.getParameter("receiverAccount");
		String userIFSC=request.getParameter("ifsc");
		String transferType = request.getParameter("transferType");
	    String amountSend=request.getParameter("amount");
	    if (senderAccNo != null && !senderAccNo.isEmpty() && senderAccNo.matches("\\d+")) {
	        long senderAccNum = Long.parseLong(senderAccNo);
	        amount.setSendAccountNo(senderAccNum);
	    }

	   
	    if (recepientAccNo != null && !recepientAccNo.isEmpty() && recepientAccNo.matches("\\d+")) {
	        long recepientAccNum = Long.parseLong(recepientAccNo);
	        amount.setRecepientAccountNo(recepientAccNum);
	    }

	    if (amountSend != null && !amountSend.isEmpty() && amountSend.matches("\\d+")) {
	        int amountSent = Integer.parseInt(amountSend);
	        amount.setAmount(amountSent);
	    }
	   
	   
	   amount.setIFSC(userIFSC);
	   amount.setTransfertype(transferType);
	   try {
		crud.insert(amount);
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
