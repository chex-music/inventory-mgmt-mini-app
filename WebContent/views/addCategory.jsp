<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Add Category</title>
<style>
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background: #f4f4f4;
}

.container {
	width: 400px;
	margin: 80px auto;
	padding: 20px;
	background: white;
	border-radius: 5px;
}

input, textarea {
	width: 100%;
	padding: 8px;
	margin: 8px 0;
	border-radius: 5px;
}

button {
	width: 100%;
	padding: 10px;
	background: #333;
	color: white;
	border: none;
	cursor: pointer;
	border-radius: 5px;
}

button:hover {
	background: #555;
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
</style>
</head>
<body>

	<div class="container">
		<h2>➕ Add Category</h2>

		<form action="categoryController" method="post">
			<label>Category Name</label> <input type="text" name="categoryName"
				required> <label>Category Description</label>
			<textarea name="categoryDesc" rows="3"></textarea>
			<input type="hidden" name="action" value="add">
			<button type="submit">Save Category</button>
		</form>
		<a href="categoryController?action=display">View Table</a>
		<a href="index.jsp">Back to Dashboard</a>
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
