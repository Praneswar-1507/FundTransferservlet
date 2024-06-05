<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fund Transfer Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            width: 80%;
            margin: 0 auto;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="text"][readonly] {
            background-color: #f2f2f2; /* Gray out the input field */
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 15px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Fund Transfer Form</h2>
        <form action="TransferAmount" method="get">
        
            <!-- Made transferType input read-only -->
            <label for="transferType">Transfer Type:</label>
            <input type="text" id="transferType" name="transferType" value="<%= request.getParameter("transferType") %>" readonly>
            
            <label for="senderAccount">Sender Account:</label>
            <input type="text" id="senderAccount" pattern="^[0-9]{12,13}$" name="senderAccount">
            
            <label for="receiverAccount">Receiver Account:</label>
            <input type="text" id="receiverAccount" pattern="^[0-9]{12,13}$" name="receiverAccount">
            
            <label for="ifsc">IFSC Code:</label>
            <input type="text" id="ifsc" pattern="^([A-Z]{4}[0][A-Z0-9]{6})$" name="ifsc">
            
            <label for="amount">Amount:</label>
            <input type="text" id="amount" name="amount">
            
            <input type="submit" value="Transfer">
        </form>
    </div>
</body>
</html>
