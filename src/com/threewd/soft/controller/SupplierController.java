package com.threewd.soft.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import com.threewd.soft.interfaces.SupplierService;
import com.threewd.soft.model.Category;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Supplier;
import com.threewd.soft.service.SupplierServiceImpl;

/**
 * Servlet implementation class SupplierController
 */
@WebServlet("/supplierController")
public class SupplierController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/addSupplier.jsp").forward(request, response);
//		response.sendRedirect("views/addSupplier.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		SupplierService supplyService = new SupplierServiceImpl();
		OperationResult result = new OperationResult();

		if (action == null) {
			action = "";
		}

		switch (action) {
		case "add":
			Supplier supplier = new Supplier();
//			List<Supplier> supplierList = new ArrayList<>();

			supplier.setSupplierName(request.getParameter("supplierName"));
			supplier.setContactNo(request.getParameter("contactNumber"));
			supplier.setEmail(request.getParameter("email"));
			result = supplyService.addSupplier(supplier);

			List<Supplier> supplierList = supplyService.getAllSuppliers();
			request.setAttribute("categoryList", supplierList);

//			supplierList.add(supplier);
			break;
		default:
			result = new OperationResult(false, "Invalid Operation");
			break;

		}
		request.setAttribute("message", result.getMessage());
		request.setAttribute("success", result.isSuccess());
		request.getRequestDispatcher("views/addSupplier.jsp").forward(request, response);
	}

}
