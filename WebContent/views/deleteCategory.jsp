<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Category</title>

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
    text-align: center;
}

button {
    width: 100%;
    padding: 10px;
    margin-top: 10px;
    background: black;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button.cancel {
    background: #777;
}

button:hover {
    opacity: 0.9;
}

a {
    display: inline-block;
    margin-top: 10px;
    text-decoration: none;
}
</style>
</head>

<body>

<div class="container">
    <h2>Delete Category</h2>

    <p>Are you sure you want to delete this category?</p>

    <form action="categoryController" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="categoryId" value="${param.categoryId}">
        <button type="submit">Yes, Delete</button>
    </form>

    <!-- CANCEL -->
    <form action="categoryController" method="get">
        <button type="submit" class="cancel">Cancel</button>
    </form>

    <a href="categoryController?action=display">View Table</a>

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
