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
import com.fundtransfer.model.TranferAmountPojo;
import com.fundtransfer.util.Jdbc;

/**
 * Servlet implementation class AmountTransfer
 */
@WebServlet("/AmountTransfer")
public class AmountTransfer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static TranferAmountPojo fundTransfer = new TranferAmountPojo();
    static Jdbc crud = new Jdbc();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmountTransfer() {
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
        HttpSession session = request.getSession();
        
    
        ArrayList<Beneficiary> beneficiaryList = (ArrayList<Beneficiary>) session.getAttribute("beneficiarydetails");

   
        if (beneficiaryList == null || beneficiaryList.isEmpty()) {
            response.sendRedirect("home.jsp");
            return;
        }

        int userId = Integer.parseInt(request.getParameter("fundId"));
        String transferType = request.getParameter("type");
        String senderAccount = request.getParameter("senderAccount");
        String receiverAccount = request.getParameter("receiverAccount");
        System.out.println(receiverAccount);
        String iFSC = request.getParameter("ifsc");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int beneficiaryId = Integer.parseInt(request.getParameter("beneficiaryId"));

        System.out.println(beneficiaryId); 
        fundTransfer.setUserId(userId);
        fundTransfer.setSendAccountNo(senderAccount);
        fundTransfer.setRecepientAccountNo(receiverAccount);
        fundTransfer.setTransfertype(transferType);
        fundTransfer.setiFSC(iFSC);
        fundTransfer.setAmount(amount);
        int remainingAmount = 0;
        int remainingBeneficiaryAmount=0;

        try {
            Jdbc.insert(fundTransfer);

            
            BankAccountPojo userAccount = crud.read1(userId);
            Beneficiary beneficiary=crud.readBeneficiary(beneficiaryId);

          
            if (amount > userAccount.getAccountBalance()) {
                response.sendRedirect("fundtransfer.jsp?error=insufficientBalance");
                return;
            }

           
            remainingAmount = userAccount.getAccountBalance() - amount;
            remainingBeneficiaryAmount=beneficiary.getBeneficiaryBalance()+amount;
            System.out.println(remainingBeneficiaryAmount);

          
            crud.updatedAccountBalance(userId, remainingAmount);
            crud.updateBeneficiaryAccountBalance(beneficiaryId, remainingBeneficiaryAmount);

            
            response.sendRedirect("home1.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("fundtransfer.jsp?error=dbError");
        } catch (Exception e1) {
            e1.printStackTrace();
            response.sendRedirect("fundtransfer.jsp?error=unknownError");
        }
    }
}
