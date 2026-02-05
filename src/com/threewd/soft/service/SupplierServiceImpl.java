package com.threewd.soft.service;

import java.util.List;

import com.threewd.soft.dao.SupplierDAOImpl;
import com.threewd.soft.interfaces.SupplierDAO;
import com.threewd.soft.interfaces.SupplierService;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Supplier;
import com.threewd.soft.util.DBUtil;
import com.threewd.soft.util.SupplierUtil;

public class SupplierServiceImpl implements SupplierService{
	private SupplierDAO supplierDao= new SupplierDAOImpl();
	OperationResult oprationResult = new OperationResult();
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
//			System.out.println("Supplier cannot be empty");
			oprationResult.setSuccess(false);
			oprationResult.setMessage("Supplier Not Found");
			return null;
		}
		oprationResult.setSuccess(true);
		oprationResult.setMessage("Supplier Found");
		return supplierBean;
	}
	@Override
	public OperationResult updateSupplier(Supplier supplier) {

	    if (supplier == null) {
	        return new OperationResult(false, "Supplier data is missing");
	    }

	    if (supplier.getSupplierId() <= 0) {
	        return new OperationResult(false, "Invalid supplier ID");
	    }

	    if (supplier.getSupplierName() == null || supplier.getSupplierName().trim().isEmpty()) {
	        return new OperationResult(false, "Supplier name is required");
	    }

	    return supplierDao.updateSupplier(supplier);
	}
	@Override
	public OperationResult deleteSupplier(int supId) {

	    int rows = supplierDao.deleteSupplier(supId);

	    if (DBUtil.isRowAffected(rows)) {
	        return new OperationResult(true, "Supplier deleted successfully");
	    }

	    return new OperationResult(false, "Supplier not found");
	}



}
