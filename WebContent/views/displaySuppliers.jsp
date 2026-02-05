<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Supplier List</title>

<style>
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f6f8;
}

.container {
	width: 700px;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
}

h2 {
	text-align: center;
}

.msg {
	text-align: center;
	margin-bottom: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
}

th {
	background: black;
	color: white;
}

tr:hover {
	background-color: #f2f2f2;
}
</style>
</head>

<body>

	<div class="container">
		<h2>Supplier List</h2>
		<table>
			<tr>
				<th>Supplier ID</th>
				<th>Supplier Name</th>
				<th>Contact No</th>
				<th>Email</th>
				<th>Action</th>
			</tr>

			<c:forEach var="sup" items="${supplierList}">
				<tr>
					<td>${sup.supplierId}</td>
					<td>${sup.supplierName}</td>
					<td>${sup.contactNo}</td>
					<td>${sup.email}</td>

					<td>
						<!-- UPDATE LINK --> <a
						href="supplierController?action=edit&supplierId=${sup.supplierId}"
						style="margin-right: 10px; text-decoration: none; color: blue;">
							Update </a> <!-- DELETE (POST using hidden form) -->
						<form id="deleteForm${sup.supplierId}" action="supplierController"
							method="post" style="display: inline;">

							<input type="hidden" name="action" value="delete"> <input
								type="hidden" name="supplierId" value="${sup.supplierId}">
							<a href="javascript:void(0);"
								onclick="if(confirm('Are you sure you want to delete this supplier?')) 
                            document.getElementById('deleteForm${sup.supplierId}').submit();"
								style="text-decoration: none; color: red;"> Delete </a>
						</form>
					</td>
				</tr>
			</c:forEach>


			<c:if test="${empty supplierList}">
				<tr>
					<td colspan="4" style="text-align: center;">No suppliers found
					</td>
				</tr>
			</c:if>
		</table>
		<!-- MESSAGE BLOCK -->
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
