package com.threewd.soft.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threewd.soft.dao.CategoryDAOImpl;
import com.threewd.soft.dao.SupplierDAOImpl;
import com.threewd.soft.interfaces.ProductService;
import com.threewd.soft.model.Category;
import com.threewd.soft.model.Supplier;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Product;
import com.threewd.soft.service.CategoryServiceImpl;
import com.threewd.soft.service.ProductServiceImpl;
import com.threewd.soft.service.SupplierServiceImpl;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/productController")
public class ProductController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categoryList = new CategoryServiceImpl().getAllCategory();
		List<Supplier> supplierList = new SupplierServiceImpl().getAllSuppliers();
		System.out.println(categoryList);
		request.setAttribute("allCategories", categoryList);
		request.setAttribute("allSuppliers", supplierList);
		request.getRequestDispatcher("views/addProduct.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Product action :"+action);
		Product product = new Product();
		ProductService prodService = new ProductServiceImpl();
		OperationResult operationResult = new OperationResult();

		switch (action) {
		case "add":
			Category category = new Category();
			Supplier supplier = new Supplier();
			String prodName = request.getParameter("productName");
			int catId = Integer.parseInt(request.getParameter("categoryId"));
			int supId = Integer.parseInt(request.getParameter("supplierId"));
			int price = Integer.parseInt(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));		
		
			category.setCategoryId(catId);
			supplier.setSupplierId(supId);
			
			product.setProductName(prodName);
			product.setCategory(category);
			product.setSupplier(supplier);
			product.setPrice(price);
			product.setQuantity(quantity);
			
			operationResult = prodService.addProduct(product);
			break;

		default:
			break;
		}
		System.out.println("Affected rows : "+operationResult.getAffectedRows());
		request.setAttribute("message", operationResult.getMessage());
		request.setAttribute("success", operationResult.isSuccess());
		doGet(request, response);
	}

}
