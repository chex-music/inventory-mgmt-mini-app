<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
        }
        .container {
            width: 400px;
            margin: 80px auto;
            padding: 20px;
            background: white;
            text-align: center;
            border-radius: 8px;
        }
        a {
            display: block;
            margin: 10px 0;
            padding: 10px;
            background: #333;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background: #555;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>📦 Inventory Management System</h2>

    <a href="categoryController">➕ Add Category</a>
    <a href="supplierController">➕ Add Supplier</a>
    <a href="productController">➕ Add Product</a>
</div>

</body>
</html>
