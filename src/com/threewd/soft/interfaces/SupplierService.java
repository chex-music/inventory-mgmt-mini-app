package com.threewd.soft.interfaces;

import java.util.List;

import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Supplier;

public interface SupplierService {
	OperationResult addSupplier(Supplier supplier);
	List<Supplier> getAllSuppliers();
	Supplier getSuplierById(int supId);
	OperationResult updateSupplier(Supplier supplier);
	OperationResult deleteSupplier(int supId);
}
////Correct use case
//public interface SupplierService {
//
//    OperationResult addSupplier(Supplier supplier);
//
//    List<Supplier> getAllSuppliers();
//
//    Supplier getSupplierById(int supId);
//
//    OperationResult updateSupplier(Supplier supplier);
//
//    OperationResult deleteSupplier(int supId);
//}
