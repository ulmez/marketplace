package se.gozacke.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.database.Database;

public class MySQLCategoryRepository implements CategoryRepository {
	private List<Category> categories;
	
	public MySQLCategoryRepository() {
		categories = new ArrayList<>();
	}

	@Override
	public List<Category> getAllCategories() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    categories.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM categories;";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Category tempCategory = new Category(rs.getInt("id"));
				tempCategory.setStaffId(rs.getInt("staff_Id"));
				tempCategory.setCategoryName(rs.getString("category_name"));
				
				categories.add(tempCategory);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return categories;
	}

	@Override
	public void insertCategory(Category category) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO categories VALUES (null, ?, ?);";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, category.getCategoryName());
			pstmt.setInt(2, category.getStaffId());
			
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public void updateCategory(Category category) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "UPDATE categories "
						 + "SET category_name = ?, "
						 + "staff_id = ? "
						 + "WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, category.getCategoryName());
			pstmt.setInt(2, category.getStaffId());
			pstmt.setInt(3, category.getCategoryId());
			
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public void deleteCategory(Category category) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			conn.setAutoCommit(false);
			
			String query = "DELETE FROM products_categories WHERE categories_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, category.getCategoryId());
			pstmt.executeUpdate();
			
			query = "DELETE FROM categories WHERE id = ?;";
			pstmt.close();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, category.getCategoryId());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public List<Category> getCategoryOnCategoryName(String categoryName) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    categories.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * "
						 + "FROM categories "
						 + "WHERE category_name = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, categoryName);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Category tempCategory = new Category(rs.getInt("id"));
				tempCategory.setStaffId(rs.getInt("staff_Id"));
				tempCategory.setCategoryName(rs.getString("category_name"));
				
				categories.add(tempCategory);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return categories;
	}

	@Override
	public void updateStaffMemberResponsibleForCategory(int staffId, int categoryId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "UPDATE categories "
						 + "SET staff_id = ? "
						 + "WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, staffId);
			pstmt.setInt(2, categoryId);
			
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public List<Category> getCategoriesFromProductName(String productName) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    categories.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT categories.id, categories.staff_id, categories.category_name "
						 + "FROM categories, products_categories, products "
						 + "WHERE categories.id = products_categories.categories_id "
						 + "AND products.id = products_categories.products_id "
						 + "AND products.product_name = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, productName);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Category tempCategory = new Category(rs.getInt("id"));
				tempCategory.setStaffId(rs.getInt("staff_id"));
				tempCategory.setCategoryName(rs.getString("category_name"));
				
				categories.add(tempCategory);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return categories;
	}

	@Override
	public List<Category> getCategoriesFromProductId(int productId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    categories.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT categories.id, categories.staff_id, categories.category_name "
						 + "FROM categories, products_categories, products "
						 + "WHERE categories.id = products_categories.categories_id "
						 + "AND products.id = products_categories.products_id "
						 + "AND products.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Category tempCategory = new Category(rs.getInt("id"));
				tempCategory.setStaffId(rs.getInt("staff_id"));
				tempCategory.setCategoryName(rs.getString("category_name"));
				
				categories.add(tempCategory);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return categories;
	}

	@Override
	public List<Category> getCategoryOnCategoryId(int categoryId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    categories.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * "
						 + "FROM categories "
						 + "WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, categoryId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Category tempCategory = new Category(rs.getInt("id"));
				tempCategory.setStaffId(rs.getInt("staff_Id"));
				tempCategory.setCategoryName(rs.getString("category_name"));
				
				categories.add(tempCategory);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return categories;
	}
}