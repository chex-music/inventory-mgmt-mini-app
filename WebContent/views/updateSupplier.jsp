<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Supplier</title>

<style>
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f6f8;
}

.container {
	width: 450px;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
}

h2 {
	text-align: center;
}

/* MESSAGE */
.msg {
	text-align: center;
	margin-bottom: 10px;
}

/* FORM */
label {
	display: block;
	margin-top: 10px;
}

input {
	width: 100%;
	padding: 8px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

/* BUTTON */
button {
	margin-top: 15px;
	width: 100%;
	padding: 10px;
	background: black;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background: #333;
}

/* LINKS */
a {
	display: inline-block;
	margin-top: 10px;
	margin-right: 10px;
	text-decoration: none;
	color: black;
	font-size: 14px;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>

<body>

	<div class="container">
		<h2>Update Supplier</h2>
		<div class="msg">
			<c:choose>
				<c:when test="${success}">
					<p style="color: green">${message}</p>
				</c:when>
				<c:otherwise>
					<p style="color: red">${message}</p>
				</c:otherwise>
			</c:choose>
		</div>
		<form action="supplierController" method="post">

			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="supplierId" value="${supplier.supplierId}">

			<label>Supplier Name</label> <input type="text" name="supplierName"
				value="${supplier.supplierName}" required> <label>Contact
				No</label> <input type="text" name="contactNo" value="${supplier.contactNo}"
				required> <label>Email</label> <input type="email"
				name="email" value="${supplier.email}">

			<button type="submit">Update Supplier</button>
			<a href="supplierController?action=display">View Table</a> <a
				href="index.jsp">Back to Dashboard</a>
		</form>
	</div>

</body>
</html>
