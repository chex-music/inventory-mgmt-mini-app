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
		String sql = "SELECT supplier_id, supplier_name, contact_no, email FROM SUPPLIER_MASTER";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery()) {
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getInt("supplier_id"));
				supplier.setSupplierName(rs.getString("supplier_name"));
				supplier.setContactNo(rs.getString("contact_no"));
				supplier.setEmail(rs.getString("email"));
				supplierList.add(supplier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplierList;
	}

	public Supplier getSuplierById(int supId) {
		String sql = "SELECT supplier_id, supplier_name, contact_no, email "
				+ "FROM SUPPLIER_MASTER WHERE supplier_id = ?";
		Supplier supplier = null;
		int i = 1;
		try (Connection con = DBConnection.getConnection(); 
			PreparedStatement pstm = con.prepareStatement(sql);) {
			pstm.setInt(i++, supId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				supplier = new Supplier();
				supplier.setSupplierId(rs.getInt("supplier_id"));
				supplier.setSupplierName(rs.getString("supplier_name"));
				supplier.setContactNo(rs.getString("contact_no"));
				supplier.setEmail(rs.getString("email"));
			} else {
				return null;
			}
			System.out.println("Supplier id :" + supplier.getSupplierId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplier;
	}

	public OperationResult updateSupplier(Supplier supplier) {

	    String sql = "UPDATE SUPPLIER_MASTER "
	               + "SET supplier_name = ?, contact_no = ?, email = ? "
	               + "WHERE supplier_id = ?";

	    int affectedRows = 0;
	    int i = 1;
	    String message;

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement pstm = con.prepareStatement(sql)) {

	        pstm.setString(i++, supplier.getSupplierName());
	        pstm.setString(i++, supplier.getContactNo());
	        pstm.setString(i++, supplier.getEmail());
	        pstm.setInt(i++, supplier.getSupplierId());

	        affectedRows = pstm.executeUpdate();
	        System.out.println("Affected rows in DAO " + affectedRows);

	        if (DBUtil.isRowAffected(affectedRows)) {
	            return new OperationResult(
	                true,
	                "Supplier " + supplier.getSupplierName() + " updated successfully"
	            );
	        }

	    } catch (SQLException e) {

	        switch (e.getErrorCode()) {

	            case 1049:
	                message = "Database not found";
	                break;

	            case 1146:
	                message = "Table not found";
	                break;

	            case 1054:
	                message = "Column not found";
	                break;

	            case 1451:
	                message = "Cannot update. Data is in use";
	                break;

	            case 1452:
	                message = "Invalid reference data";
	                break;

	            default:
	                message = "Database error occurred";
	        }

	        e.printStackTrace();
	        return new OperationResult(false, message);
	    }

	    return new OperationResult(false, "Something went wrong");
	}

	@Override
	public int deleteSupplier(int supId) {

	    String sql = "DELETE FROM SUPPLIER_MASTER WHERE supplier_id = ?";
	    int affectedRows = 0;

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, supId);
	        affectedRows = ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return affectedRows;
	}



}
