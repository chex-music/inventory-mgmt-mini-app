<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>

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
	border-radius: 5px;
	border: 1px solid #ddd;
}

h2 {
	text-align: center;
}

label {
	display: block;
	margin-top: 10px;
}

input, textarea, select {
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
		<h2>Add Product</h2>

		<form action="productController" method="post">
			<input type="hidden" name="action" value="add"> 
			<label>Product Name</label> 
			<input type="text" name="productName" required>
			
			<label>Category</label>
			<select name="categoryId" required>
			<option value="">-- Select Category --</option>
			
				<c:forEach var="cat" items="${allCategories}">
					<option value="${cat.categoryId}">${cat.categoryName}</option>
				</c:forEach>

			</select> 
			<label>Supplier</label> 
			<select name="supplierId" required>
				<option value="">-- Select Supplier --</option>
				<c:forEach var="sup" items="${allSuppliers}">
				<option value="${sup.supplierId}">${sup.supplierName}</option>
				</c:forEach>
				
			</select> 
			<label>Price</label> 
			<input type="number" name="price" step="0.01">
			<label>Quantity</label> 
			<input type="number" name="quantity">

			<button type="submit">Add Product</button>
		</form>
				<c:choose>
			<c:when test="${success}">
				<p style="color: green">${message}</p>
			</c:when>
			<c:otherwise>
				<p style="color: red">${message}</p>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>
