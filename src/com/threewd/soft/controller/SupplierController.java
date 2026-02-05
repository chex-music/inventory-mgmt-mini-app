package com.threewd.soft.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private String action;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OperationResult result = new OperationResult();
		action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		SupplierService supplyService = new SupplierServiceImpl();
		List<Supplier> supplierList = new ArrayList<>();

		System.out.println("GET :" + action);
		switch (action) {
		case "display":
			List<Supplier> list = supplyService.getAllSuppliers();
			request.setAttribute("supplierList", list);

			// move message from session → request
			HttpSession session = request.getSession();

			request.setAttribute("success", session.getAttribute("success"));
			request.setAttribute("message", session.getAttribute("message"));

			// clear session message (VERY IMPORTANT)
			session.removeAttribute("success");
			session.removeAttribute("message");

			request.getRequestDispatcher("views/displaySuppliers.jsp").forward(request, response);
			return;
		case "edit":
			String supplierId = request.getParameter("supplierId");
			System.out.println("Supplier ID from controller : " + supplierId);
			Supplier supplier = new Supplier();

			supplier = supplyService.getSuplierById(Integer.parseInt(supplierId));
			System.out.println(supplier);

			request.setAttribute("supplier", supplier);
			request.getRequestDispatcher("views/updateSupplier.jsp").forward(request, response);
			return;

		default:
//			result = new OperationResult(false, "Invalid Operation");
			request.getRequestDispatcher("views/addSupplier.jsp").forward(request, response);
			break;
		}
		request.getRequestDispatcher("views/displaySuppliers.jsp").forward(request, response);
//		response.sendRedirect("views/addSupplier.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Supplier supplier = new Supplier();
		SupplierService supplyService = new SupplierServiceImpl();
		OperationResult result = new OperationResult();

		if (action == null) {
			action = "";
		}

		switch (action) {
		case "add":
//			List<Supplier> supplierList = new ArrayList<>();

			supplier.setSupplierName(request.getParameter("supplierName"));
			supplier.setContactNo(request.getParameter("contactNumber"));
			supplier.setEmail(request.getParameter("email"));
			result = supplyService.addSupplier(supplier);

			List<Supplier> supplierList = supplyService.getAllSuppliers();
			request.setAttribute("categoryList", supplierList);
			request.setAttribute("message", result.getMessage());
			request.setAttribute("success", result.isSuccess());
//			supplierList.add(supplier);
			break;
		case "update":
			String supplierId = request.getParameter("supplierId");

			supplier.setSupplierId(Integer.parseInt(supplierId));
			supplier.setSupplierName(request.getParameter("supplierName"));
			supplier.setContactNo(request.getParameter("contactNo"));
			supplier.setEmail(request.getParameter("email"));
			result = supplyService.updateSupplier(supplier);
			request.setAttribute("message", result.getMessage());
			request.setAttribute("success", result.isSuccess());
			System.out.println(result.getMessage());
			request.getRequestDispatcher("views/updateSupplier.jsp").forward(request, response);

			return;
//			break;

		case "delete":
			int supId = Integer.parseInt(request.getParameter("supplierId"));
			result = supplyService.deleteSupplier(supId);

			// store message in session (temporary)
			request.getSession().setAttribute("success", result.isSuccess());
			request.getSession().setAttribute("message", result.getMessage());

			response.sendRedirect("supplierController?action=display");
			break;

		default:
			result = new OperationResult(false, "Invalid Operation");
			break;

		}
		// redirect and STOP
		doGet(request, response);
		return; // VERY IMPORTANT
	}

}
