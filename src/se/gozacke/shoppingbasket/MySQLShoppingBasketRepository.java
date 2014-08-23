package se.gozacke.shoppingbasket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.database.Database;

public class MySQLShoppingBasketRepository implements ShoppingBasketRepository {
	private List<ShoppingBasket> shoppingBaskets;
	
	public MySQLShoppingBasketRepository() {
		shoppingBaskets = new ArrayList<>();
	}
	
	@Override
	public List<ShoppingBasket> getAllShoppingBaskets() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM shoppingbasket;";
		
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				ShoppingBasket tempShoppingbasket = new ShoppingBasket(rs.getInt("id"));
				tempShoppingbasket.setUserId(rs.getInt("users_id"));
				tempShoppingbasket.setProductId(rs.getInt("products_id"));
				tempShoppingbasket.setQuantity(rs.getInt("quantity"));
				
				shoppingBaskets.add(tempShoppingbasket);
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
	    
	    return shoppingBaskets;
	}

	@Override
	public void insertOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO shoppingbasket VALUES (null, ?, ?, ?);";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, shoppingBasket.getUserId());
			pstmt.setInt(2, shoppingBasket.getProductId());
			pstmt.setInt(3, shoppingBasket.getQuantity());
			
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
	public void updateOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "UPDATE shoppingbasket "
						 + "SET users_id = ?, "
						 + "products_id = ?, "
						 + "quantity = ? "
						 + "WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, shoppingBasket.getUserId());
			pstmt.setInt(2, shoppingBasket.getProductId());
			pstmt.setInt(3, shoppingBasket.getQuantity());
			pstmt.setInt(4, shoppingBasket.getShoppingBasketId());
			
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
	public void deleteOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "DELETE FROM shoppingbasket WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, shoppingBasket.getShoppingBasketId());
			
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
	public List<ShoppingBasket> getShoppingBasketOnUsername(String username) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    shoppingBaskets.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT shoppingbasket.id, shoppingbasket.users_id, "
								+ "shoppingbasket.products_id, shoppingbasket.quantity "
						 + "FROM users, shoppingbasket "
						 + "WHERE users.id = shoppingbasket.users_id "
						 + "AND users.email = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				ShoppingBasket tempShoppingbasket = new ShoppingBasket(rs.getInt("id"));
				tempShoppingbasket.setUserId(rs.getInt("users_id"));
				tempShoppingbasket.setProductId(rs.getInt("products_id"));
				tempShoppingbasket.setQuantity(rs.getInt("quantity"));
				
				shoppingBaskets.add(tempShoppingbasket);
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
	    
	    return shoppingBaskets;
	}

	@Override
	public List<ShoppingBasket> getShoppingBasketOnUserId(int userId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    shoppingBaskets.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT shoppingbasket.id, shoppingbasket.users_id, "
								+ "shoppingbasket.products_id, shoppingbasket.quantity "
						 + "FROM users, shoppingbasket "
						 + "WHERE users.id = shoppingbasket.users_id "
						 + "AND users.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				ShoppingBasket tempShoppingbasket = new ShoppingBasket(rs.getInt("id"));
				tempShoppingbasket.setUserId(rs.getInt("users_id"));
				tempShoppingbasket.setProductId(rs.getInt("products_id"));
				tempShoppingbasket.setQuantity(rs.getInt("quantity"));
				
				shoppingBaskets.add(tempShoppingbasket);
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
	    
	    return shoppingBaskets;
	}

	@Override
	public List<ShoppingBasket> getShoppingBasketOnUserIdAndProductId(int userId, int productId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    shoppingBaskets.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT shoppingbasket.id, shoppingbasket.users_id, "
								+ "shoppingbasket.products_id, shoppingbasket.quantity "
						 + "FROM users, shoppingbasket "
						 + "WHERE users.id = shoppingbasket.users_id "
						 + "AND  shoppingbasket.users_id = ? "
						 + "AND shoppingbasket.products_id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, userId);
			pstmt.setInt(2, productId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				ShoppingBasket tempShoppingbasket = new ShoppingBasket(rs.getInt("id"));
				tempShoppingbasket.setUserId(rs.getInt("users_id"));
				tempShoppingbasket.setProductId(rs.getInt("products_id"));
				tempShoppingbasket.setQuantity(rs.getInt("quantity"));
				
				shoppingBaskets.add(tempShoppingbasket);
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
	    
	    return shoppingBaskets;
	}
}