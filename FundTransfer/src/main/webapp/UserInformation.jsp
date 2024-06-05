<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.fundtransfer.model.FundTransferPojo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.fundtransfer.util.Jdbc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
body {
	color: #404E67;
	background: #F5F7FA;
	font-family: 'Open Sans', sans-serif;
}

.table-wrapper {
	width: 100%;
	margin: 30px auto;
	background: #fff;
	padding: 20px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

.table-title {
	padding-bottom: 10px;
	margin: 0 0 10px;
}

.table-title h2 {
	margin: 6px 0 0;
	font-size: 22px;
}

.table-title .add-new {
	float: right;
	height: 30px;
	font-weight: bold;
	font-size: 12px;
	text-shadow: none;
	min-width: 100px;
	border-radius: 50px;
	line-height: 13px;
}

.table-title .add-new i {
	margin-right: 4px;
}

table.table {
	table-layout: fixed;
	width: 800px; /* Set the width of the table */
	margin: auto; /* Center the table horizontally */
}

table.table tr th, table.table tr td {
	border-color: #e9e9e9;
}

table.table th i {
	font-size: 5px;
	margin: 0 5px;
	cursor: pointer;
}

table.table td a {
	cursor: pointer;
	display: inline-block;
	margin: 0 5px;
	width: 150px;
}

table.table td a.add {
	/* Removed color property */
	
}

table.table td a.edit {
	/* Removed color property */
	
}

table.table td form.delete button {
	/* Remove custom styling */
	
}

table.table td i {
	font-size: 19px;
}

table.table td a.add i {
	font-size: 24px;
	margin-right: -1px;
	position: relative;
	top: 3px;
}

table.table .form-control {
	height: 32px;
	line-height: 32px;
	box-shadow: none;
	border-radius: 2px;
}

table.table .form-control.error {
	border-color: #f50000;
}

table.table td .add {
	display: none;
}
.search-container {
            float: right;
            margin-top: 20px;
        }

        .search-container input[type=text] {
            padding: 6px;
            margin-top: 8px;
            font-size: 17px;
            border: none;
            border-radius: 5px;
        }

        .search-container button {
            float: right;
            padding: 6px 10px;
            margin-top: 8px;
            margin-right: 16px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        .search-container button:hover {
            background: #ccc;
        }
</style>
</head>
<body>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8">
                        <h2>User <b>Details</b></h2>
                    </div>
                    <div class="col-sm-4">
                    <form action="TransferOnline" method="get">
                        <div class="search-container">
                            <input type="text" placeholder="Search..."name="search" >
                            <button type="submit"><i class="fa fa-search"></i></button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
            <form>
                <table border="1" class="table">
                    <tr>
                        <td style="width: 10%;">ID</td>
                        <td style="width: 20%;">UserName</td>
                        <td style="width: 20%;">Email ID</td>
                        <td style="width: 20%;">Password</td>
                    </tr>
                    <tbody>
                        <% 
                        Jdbc use = new Jdbc();
                        ArrayList<FundTransferPojo> array = (ArrayList<FundTransferPojo>)request.getAttribute("log");
                        for (FundTransferPojo view : array) { 
                        %>
                        <tr>
                            <td><%=view.getId() %></td>
                            <td><%=view.getUsername() %></td>
                             <td><%=view.getEmail() %></td>
                            <td><%=view.getPassword() %></td>
                           
                            <td><a href="update.jsp?id=<%=  %>"><i class="material-icons">&#xE254;</i></a></td>
                            <td>
                                <form action="Login" method="get">
                                    <input type="hidden" name="action" value="delete"> 
                                    <input type="hidden" name="deleteid" value="<%=%>">
                                    <button type="submit" title="delete"><i class="fa fa-trash"></i></button>
                                </form>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
    <form action="Logout" method="get">
<button type="submit" class="btn">logout</button> 
</form>
   
        
   
</body>
	
</body>
</html>