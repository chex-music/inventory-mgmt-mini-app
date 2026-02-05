<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Supplier</title>

<style>
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4
}

.container {
	width: 400px;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	border-radius: 6px;
}

h2 {
	text-align: center;
}

label {
	display: block;
	margin-top: 10px;
}

input, textarea {
	width: 100%;
	padding: 8px;
	margin-top: 5px;
	border-radius: 5px;
}

button {
	margin-top: 15px;
	width: 100%;
	padding: 10px;
	background: #333;
	color: white;
	border: none;
	cursor: pointer;
	border-radius: 5px;
}

a {
	display: block;
	text-align: center;
	margin-top: 10px;
	text-decoration: none;
}
.msg {
	text-align: center;
}

button:hover {
	background: #0056b3;
}
</style>
</head>

<body>

	<div class="container">
		<h2>Add Supplier</h2>

		<form action="supplierController" method="post">
			<input type="hidden" name="action" value="add"> <label>Supplier
				Name</label> <input type="text" name="supplierName" required> <label>Contact
				Number</label> <input type="text" name="contactNumber"> <label>Email</label>
			<input type="email" name="email">

			<!--         <label>Address</label>
        <textarea name="address" rows="3"></textarea> -->

			<button type="submit">Add Supplier</button>
		</form>
		<a href="index.jsp">⬅ Back to Dashboard</a>
		<a href="supplierController?action=display">View Table</a>
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
	</div>

</body>
</html>
