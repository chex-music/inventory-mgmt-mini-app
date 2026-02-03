package com.threewd.soft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.threewd.soft.interfaces.SupplierDAO;
import com.threewd.soft.model.Category;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Supplier;
import com.threewd.soft.util.DBConnection;
import com.threewd.soft.util.DBUtil;

public class SupplierDAOImpl implements SupplierDAO {

	@Override
	public OperationResult addSupplier(Supplier supplier) {
		String sql = "INSERT INTO SUPPLIER_MASTER (supplier_name, contact_no, email) VALUES(?,?,?)";
		int i = 1;
		int affectedRows = 0;
		String message = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.setString(i++, supplier.getSupplierName());
			pstm.setString(i++, supplier.getContactNo());
			pstm.setString(i++, supplier.getEmail());

			affectedRows = pstm.executeUpdate();

			if (DBUtil.isRowAffected(affectedRows)) {
				return new OperationResult(true, "Supplier " + supplier.getSupplierName() + " added successfully",
						affectedRows);
			}
		} catch (SQLException e) {

			switch (e.getErrorCode()) {

			case 1062:
				// Duplicate entry
				// Happens when same data is inserted again
				// Example: Same category name already exists
				message = "Supplier already exists";
				break;

			case 1049:
				// Database not found
				// Happens when database name is wrong or missing
				message = "Database not found";
				break;

			case 1146:
				// Table does not exist
				// Happens when table name is wrong or table not created
				message = "Table not found";
				break;

			case 1054:
				// Unknown column
				// Happens when column name is wrong in SQL query
				message = "Column not found";
				break;

			case 1451:
				// Cannot delete parent record
				// Happens when child data exists (foreign key constraint)
				message = "Cannot delete. Data is in use";
				break;

			case 1452:
				// Cannot add or update child record
				// Happens when parent data is missing (foreign key constraint)
				message = "Invalid reference data";
				break;

			default:
				// Any other database error
				message = "Database error occurred";
			}

			// Log error for developer (debugging)
			e.printStackTrace();

			// Return safe message to user
			return new OperationResult(false, message);
		}

		return new OperationResult(false, message, affectedRows);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		List<Supplier> supplierList = new ArrayList<>();
		String sql = "SELECT supplier_id, supplier_name FROM SUPPLIER_MASTER";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery()) {
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getInt("supplier_id"));
				supplier.setSupplierName(rs.getString("supplier_name"));
				supplierList.add(supplier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplierList;
	}

	public Supplier getSuplierById(int supId) {
		String sql = "SELECT supplier_name FROM SUPPLIER_MASTER WHERE supplier_id = ?";
		Supplier supplier = new Supplier();
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstm = con.prepareStatement(sql);) {
			pstm.setInt(1, supplier.getSupplierId());
			System.out.println("Supplier id :" + supplier.getSupplierId());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				supplier.setSupplierName(rs.getString("supplier_name"));
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplier;
	}

}
