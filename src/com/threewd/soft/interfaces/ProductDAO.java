package com.threewd.soft.interfaces;

import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Product;

public interface ProductDAO {
	OperationResult addProduct(Product product);
}
