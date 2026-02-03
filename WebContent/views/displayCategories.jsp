<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List</title>

<style>
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f6f8;
}

.container {
	width: 600px;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
}

h2 {
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 15px;
}

th, td, button {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
}

.opration {
	display: flex;
	justify-content: center;
}

a {
/* 	border: 1px solid red; */
	padding: 1vh;
	margin-left: 0.5vh;
	text-decoration: none;
	color: white;
	background-color: black;
	border-radius: 5px;
}

th {
	background: #000;
	color: #fff;
}
</style>
</head>

<body>
	<div class="container">

		<h2>Category List</h2>
		<c:choose>
			<c:when test="${success}">
				<p style="color: green">${message}</p>
			</c:when>
			<c:otherwise>
				<p style="color: red">${message}</p>
			</c:otherwise>
		</c:choose>
		<table>
			<tr>
				<th>ID</th>
				<th>Category Name</th>
				<th>Description</th>
				<th>Action</th>
			</tr>

			<c:forEach var="cat" items="${categoryList}">
				<tr>
					<td>${cat.categoryId}</td>
					<td>${cat.categoryName}</td>
					<td>${cat.categoryDesc}</td>
					<td>
						<div class="opration">
							<a
								href="categoryController?action=update&categoryId=${cat.categoryId}">
								Update </a> <a
								href="categoryController?action=delete&categoryId=${cat.categoryId}"
								value="delete">Delete</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
