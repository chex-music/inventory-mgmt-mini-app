package com.threewd.soft.interfaces;


import java.util.List;

import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Supplier;

public interface SupplierDAO {
	OperationResult addSupplier(Supplier supplier);
	List<Supplier> getAllSuppliers();
	Supplier getSuplierById(int supId);
	OperationResult updateSupplier(Supplier supplier);
	int deleteSupplier(int supId);
}

////Correct Use Case | Reason : DAO = RAW data & Service = RESULT + MESSAGE
//public interface SupplierDAO {
//
//    int addSupplier(Supplier supplier);
//
//    List<Supplier> getAllSuppliers();
//
//    Supplier getSupplierById(int supId);
//
//    int updateSupplier(Supplier supplier);
//
//    int deleteSupplier(int supId);
//}
