package com.threewd.soft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.threewd.soft.interfaces.ProductDAO;
import com.threewd.soft.model.Category;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Product;
import com.threewd.soft.model.Supplier;
import com.threewd.soft.util.DBConnection;
import com.threewd.soft.util.DBUtil;

public class ProductDAOimpl implements ProductDAO{	
	private String sql ="INSERT INTO PRODUCT_INVENTORY "
			+ "(product_name,category_id,supplier_id,quantity,price)"
			+ "VALUES (?,?,?,?,?)" ;
	@Override
	public OperationResult addProduct(Product product) {
		int affectedRows = 0;
		int i=1;
		String message = null;
		boolean success = false;
		
		try(Connection con = DBConnection.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql)) {
			
			pstm.setString(i++, product.getProductName());
			pstm.setInt(i++, product.getCategory().getCategoryId());
			pstm.setInt(i++, product.getSupplier().getSupplierId());
			pstm.setInt(i++, product.getQuantity());
			pstm.setDouble(i++, product.getPrice());
			
			affectedRows = pstm.executeUpdate();
			
			if(DBUtil.isRowAffected(affectedRows)) {
				success = true;
				message="Product "+product.getProductName()+" added successfully";
			}
		} catch (SQLException e) {
			switch (e.getErrorCode()) {

			case 1062:
				// Duplicate entry
				// Happens when same data is inserted again
				// Example: Same category name already exists
				message = "Product already exists";
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

		return new OperationResult(success,message,affectedRows);
	}

}
