package com.threewd.soft.service;

import com.threewd.soft.dao.ProductDAOimpl;
import com.threewd.soft.interfaces.ProductDAO;
import com.threewd.soft.interfaces.ProductService;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Product;

public class ProductServiceImpl implements ProductService {

	ProductDAO prodDao = new ProductDAOimpl();

	@Override
	public OperationResult addProduct(Product product) {
		if(product == null || product.getCategory() == null || product.getSupplier() == null) {
		    return new OperationResult(false, "Product, Category, or Supplier data missing");
		}

		if (product.getProductName().trim().isEmpty()) {
			return new OperationResult(false, "Product name cannot be empty");
		}
		return prodDao.addProduct(product);
	}

}
