package com.threewd.soft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.threewd.soft.interfaces.CategoryDAO;
import com.threewd.soft.model.Category;
import com.threewd.soft.model.OperationResult;
import com.threewd.soft.model.Supplier;
import com.threewd.soft.util.DBConnection;
import com.threewd.soft.util.DBUtil;

public class CategoryDAOImpl implements CategoryDAO {
	private final String INSERT_QUERY = "INSERT INTO CATEGORY_MASTER (category_name, category_desc) VALUES (?, ?)";

	@Override
	public OperationResult addCategory(Category category) {

		boolean success = false;
		int affectedRows = 0;
		String message = "Category not added";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstm = con.prepareStatement(INSERT_QUERY)) {
			int i = 1;
			pstm.setString(i++, category.getCategoryName());
			pstm.setString(i++, category.getCategoryDesc());

			affectedRows = pstm.executeUpdate();

			if (affectedRows > 0) {
				success = true;
				message = category.getCategoryName() + " category added successfully";
			} else {
				message = "Unable to add category: " + category.getCategoryName();
			}

		} catch (SQLException e) {
			switch (e.getErrorCode()) {

			case 1062:
				// Duplicate entry
				// Happens when same data is inserted again
				// Example: Same category name already exists
				message = "Category already exists";
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

		return new OperationResult(success, message);
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categoryList = new ArrayList<Category>();
		String sql = "SELECT category_id, category_name,category_desc "
				+ "FROM CATEGORY_MASTER "
				+ "ORDER BY category_id";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery()) {

			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setCategoryDesc(rs.getString("category_desc"));
				categoryList.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@Override
	public Category getCategoryById(int catId) {
		Category category = null;
		String sql = "SELECT category_id,category_name,category_desc FROM CATEGORY_MASTER WHERE category_id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstm = con.prepareStatement(sql);) {
//			pstm.setInt(1,category.getCategoryId());
			pstm.setInt(1, catId);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setCategoryDesc(rs.getString("category_desc"));
			} else {
				return null;
			}
			System.out.println("Cat id from dao:" + category.getCategoryId());

		} catch (SQLException e) {
			e.printStackTrace();
			;
		}
		return category;
	}

	@Override
	public OperationResult updateCategory(Category category) {
		String sql = "UPDATE CATEGORY_MASTER " + "SET category_name =?, category_desc = ? " + "WHERE category_id = ?";
		int affectedRows = 0;
		int i = 1;
		String message;
		try (Connection con = DBConnection.getConnection(); PreparedStatement pstm = con.prepareStatement(sql);) {
			pstm.setString(i++, category.getCategoryName());
			pstm.setString(i++, category.getCategoryDesc());
			pstm.setInt(i++, category.getCategoryId());

			affectedRows = pstm.executeUpdate();
			System.out.println("Affected rows in DAO " + affectedRows);
			if (DBUtil.isRowAffected(affectedRows)) {
				return new OperationResult(true, "Category " + category.getCategoryName() + " Updated Successfully");
			}
		} catch (SQLException e) {
			switch (e.getErrorCode()) {

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

		return new OperationResult(false, "Somthing Went Wrong");
	}

	@Override
	public OperationResult deleteCategoryById(int categoryId) {

		String sql = "DELETE FROM CATEGORY_MASTER WHERE category_id = ?";
		int affectedRows = 0;
		String message;

		try (Connection con = DBConnection.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {

			pstm.setInt(1, categoryId);

			affectedRows = pstm.executeUpdate();
			System.out.println("Affected rows in DAO : " + affectedRows);

			if (DBUtil.isRowAffected(affectedRows)) {
				return new OperationResult(true, "Category deleted successfully");
			}

		} catch (SQLException e) {

			switch (e.getErrorCode()) {

			case 1049:
				// Database not found
				message = "Database not found";
				break;

			case 1146:
				// Table does not exist
				message = "Table not found";
				break;

			case 1451:
				// Cannot delete parent record (FK constraint)
				message = "Cannot delete. Category is in use";
				break;

			default:
				// Any other DB error
				message = "Database error occurred";
			}

			// Log for developer
			e.printStackTrace();

			// Safe message for UI
			return new OperationResult(false, message);
		}

		return new OperationResult(false, "Something went wrong");
	}

}
