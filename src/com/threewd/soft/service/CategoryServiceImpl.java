package com.threewd.soft.service;

import java.util.List;

import com.threewd.soft.dao.CategoryDAOImpl;
import com.threewd.soft.interfaces.CategoryDAO;
import com.threewd.soft.interfaces.CategoryService;
import com.threewd.soft.model.Category;
import com.threewd.soft.model.OperationResult;

public class CategoryServiceImpl implements CategoryService {

	private final CategoryDAO categoryDao = new CategoryDAOImpl();
	OperationResult operationResult = new OperationResult();

	@Override
	public OperationResult addCategory(Category category) {
		System.out.println(category);
		if (category == null) {
			return new OperationResult(false, "Category data is missing");
		}
		if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
			return new OperationResult(false, "Category name cannot be empty");
		}
		return categoryDao.addCategory(category);
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> catList = categoryDao.getAllCategory();
		if (catList.isEmpty()) {
			System.out.println("empty list");
		} else {
			System.out.println("not empty list");
		}
		return catList;
	}

	@Override
	public Category getCategoryById(int catId) {
		Category categoryBean = categoryDao.getCategoryById(catId);
		if (catId < 0) {
			System.out.println("Invalid cat id");
		}
		if (categoryBean == null) {
			System.out.println("catageory cannot be empty");
		}
		return categoryBean;
	}

	@Override
	public OperationResult updateCategory(Category category) {
		Category dbCat = categoryDao.getCategoryById(category.getCategoryId());
		if(dbCat == null) {
			return new OperationResult(false, "Category not found");
		}
		if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
			return new OperationResult(false, "Category name cannot be empty");
		}
		if(category.getCategoryName().length() > 50) {
			return new OperationResult(false, "Category name is too loooooong");
		}
		return categoryDao.updateCategory(category);
	}

	@Override
	public OperationResult deleteCategoryById(int categoryIdToDelete) {
		    if (categoryIdToDelete <= 0) {
		        operationResult.setSuccess(false);
		        operationResult.setMessage("Invalid Category Id");
		        return operationResult;
		    }
		    return categoryDao.deleteCategoryById(categoryIdToDelete);
	}
	



}
