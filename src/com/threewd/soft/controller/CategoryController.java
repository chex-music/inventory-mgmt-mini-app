package com.threewd.soft.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threewd.soft.interfaces.CategoryService;
import com.threewd.soft.model.Category;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.service.CategoryServiceImpl;

/**
 * Servlet implementation class AddCategoryController
 */
@WebServlet("/categoryController")
public class CategoryController extends HttpServlet {
	private String action;
	private CategoryService catService = new CategoryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action = request.getParameter("action");

		String categoryIdStr = request.getParameter("categoryId");
		System.out.println(action);

		if (action == null) {
			action = "display";
		}

		if ("display".equals(action)) {

			List<Category> categoryList = catService.getAllCategory();
			request.setAttribute("categoryList", categoryList);

			request.getRequestDispatcher("views/displayCategories.jsp").forward(request, response);
			return; // Imp
		} else if ("update".equals(action)) {
			Category category = new Category();
			int catId = Integer.parseInt(categoryIdStr);
			System.out.println("Cat id get req : " + catId);

			category = catService.getCategoryById(catId);
			System.out.println("Category obj : " + category);

			if (category == null) {
				response.sendRedirect("categoryController?action=display");
				return;
			}
			request.setAttribute("category", category);
			request.getRequestDispatcher("views/updateCategory.jsp").forward(request, response);
		} else if ("delete".equals(action)) {
			request.getRequestDispatcher("views/deleteCategory.jsp").forward(request, response);
		} else {

			request.getRequestDispatcher("views/addCategory.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action = request.getParameter("action");
		System.out.println(action);

		Category category = new Category();
		OperationResult operationResult = new OperationResult();

		switch (action) {
		case "add":
			String categoryName = request.getParameter("categoryName");
			String categoryDesc = request.getParameter("categoryDesc");

			category.setCategoryName(categoryName);
			category.setCategoryDesc(categoryDesc);

			operationResult = catService.addCategory(category);
			System.out.println("Affected rows : " + operationResult.getAffectedRows());
			request.setAttribute("message", operationResult.getMessage());
			request.setAttribute("success", operationResult.isSuccess());
			request.getRequestDispatcher("views/addCategory.jsp").forward(request, response);
			break;
		case "update":

			String categoryId = request.getParameter("categoryId");
			String categoryNameToUpdate = request.getParameter("categoryName");
			String categoryDescToUpdate = request.getParameter("categoryDesc");

			if (categoryId == "") {
				request.setAttribute("message", operationResult.getMessage());
			} else {
				category.setCategoryId(Integer.parseInt(categoryId));
			}
			category.setCategoryName(categoryNameToUpdate);
			category.setCategoryDesc(categoryDescToUpdate);

			operationResult = catService.updateCategory(category);
			System.out.println("Affected rows : " + operationResult.getAffectedRows());
			request.setAttribute("message", operationResult.getMessage());
			request.setAttribute("success", operationResult.isSuccess());
			request.getRequestDispatcher("views/updateCategory.jsp").forward(request, response);

			break;
		case "delete":
			String categoryIdStr = request.getParameter("categoryId");
			System.out.println(categoryIdStr);
			if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
				operationResult.setSuccess(false);
				operationResult.setMessage("Invalid Category Id");
				request.setAttribute("message", operationResult.getMessage());
				request.setAttribute("success", operationResult.isSuccess());
				doGet(request, response);
				break;
			}

			try {
				int categoryIdToDelete = Integer.parseInt(categoryIdStr);
				operationResult = catService.deleteCategoryById(categoryIdToDelete);

			} catch (NumberFormatException e) {
				operationResult.setSuccess(false);
				operationResult.setMessage("Category Id must be number");
			}

			request.setAttribute("message", operationResult.getMessage());
			request.setAttribute("success", operationResult.isSuccess());

			// Refresh list
			doGet(request, response);
			break;
		default:
		}
	}

}
