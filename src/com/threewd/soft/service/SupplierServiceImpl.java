package com.threewd.soft.service;

import java.util.List;

import com.threewd.soft.dao.SupplierDAOImpl;
import com.threewd.soft.interfaces.SupplierDAO;
import com.threewd.soft.interfaces.SupplierService;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Supplier;
import com.threewd.soft.util.SupplierUtil;

public class SupplierServiceImpl implements SupplierService{
	private SupplierDAO supplierDao= new SupplierDAOImpl();
	@Override
	public OperationResult addSupplier(Supplier supplier) {
		if(supplier == null) {
			return new OperationResult(false, "Supplier cannot be empty");
		}
		if(SupplierUtil.hasAnyEmptyField(supplier)) {
			return new OperationResult(false, "Supplier infomation fields cannot be empty");
		}
		return supplierDao.addSupplier(supplier);
	}
	@Override
	public List<Supplier> getAllSuppliers() {
		List<Supplier> suppliersList = supplierDao.getAllSuppliers();
		if(suppliersList.isEmpty()) {
			System.out.println("Supplier list is empty");
		}else {
			System.out.println("Supplier list is not empty");
		}
		return suppliersList;
	}
	@Override
	public Supplier getSuplierById(int supId) {
		Supplier supplierBean = supplierDao.getSuplierById(supId);
		if(supplierBean == null) {
			System.out.println("Supplier cannot be empty");
		}
		return supplierBean;
	}

}
