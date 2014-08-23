package se.gozacke.productcategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.database.Database;

public class MySQLProductCategoryRepository implements ProductCategoryRepository {
	private List<ProductCategory> productCategories;
	
	public MySQLProductCategoryRepository() {
		productCategories = new ArrayList<>();
	}

	@Override
	public List<ProductCategory> getAllProductCategories() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    productCategories.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM products_categories;";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				ProductCategory tempProductCategory = new ProductCategory(rs.getInt("id"));
				tempProductCategory.setProductId(rs.getInt("products_id"));
				tempProductCategory.setCategoryId(rs.getInt("categories_id"));
				
				productCategories.add(tempProductCategory);
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
	    
	    return productCategories;
	}

	@Override
	public void insertProductCategory(ProductCategory productCategory) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO products_categories VALUES (null, ?, ?);";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productCategory.getProductId());
			pstmt.setInt(2, productCategory.getCategoryId());
			
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
	public void updateProductCategory(ProductCategory productCategory) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "UPDATE products_categories "
						 + "SET products_id = ?, "
						 + "categories_id = ? "
						 + "WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productCategory.getProductId());
			pstmt.setInt(2, productCategory.getCategoryId());
			pstmt.setInt(3, productCategory.getProductCategoryId());
			
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
	public void deleteProductCategory(ProductCategory productCategory) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "DELETE FROM products_categories WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productCategory.getProductCategoryId());
			
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
}