package com.threewd.soft.interfaces;

import java.util.List;

import com.threewd.soft.model.Category;
import com.threewd.soft.model.OperationResult;

public interface CategoryService {
	OperationResult addCategory(Category category);
	List<Category> getAllCategory();
	Category getCategoryById(int catId);
	OperationResult updateCategory(Category category);
	OperationResult deleteCategoryById(int categoryIdToDelete);
}
