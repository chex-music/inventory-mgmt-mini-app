package com.threewd.soft.interfaces;

import com.threewd.soft.model.Category;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Product;

public interface ProductService {
	OperationResult addProduct(Product product);
}
