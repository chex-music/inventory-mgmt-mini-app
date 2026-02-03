<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Category</title>

<style>
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f4f6f8;
}

.container {
	width: 400px;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
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
	border: 1px solid #ccc;
	border-radius: 5px;
}

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
</style>
</head>

<body>

	<div class="container">
		<h2>Update Category</h2>

		<form action="categoryController" method="post">

			<input type="hidden" name="action" value="update"> <input
				type="hidden" name="categoryId" value="${category.categoryId}">

			<label>Category Name</label> 
			<input type="text" name="categoryName"
			value="${category.categoryName}" required> 
				
			<label>Category Description</label> 
			<input name="categoryDesc" rows="3"
			value = "${category.categoryDesc}" required>

			<button type="submit">Update</button>
			<a href="categoryController?action=display">View Table</a>
			
			<c:choose>
				<c:when test="${success}">
				<p style="color : green">${message}</p>
				</c:when>
				<c:otherwise>
				<p style="color : red">${message}</p>
				</c:otherwise>
			</c:choose>
		</form>
	</div>

</body>
</html>
