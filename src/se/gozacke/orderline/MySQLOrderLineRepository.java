package se.gozacke.orderline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.actor.Actor;
import se.gozacke.data.StorageException;
import se.gozacke.database.Database;

public class MySQLOrderLineRepository implements OrderLineRepository {
private List<OrderLine> orderLines;
	
	public MySQLOrderLineRepository() {
		orderLines = new ArrayList<>();
	}
	
	@Override
	public List<OrderLine> getAllOrderLines() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    orderLines.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM orderlines;";
		
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				OrderLine tempOrderLine = new OrderLine(rs.getInt("id"));
				tempOrderLine.setOrderId(rs.getInt("orders_id"));
				tempOrderLine.setQuantity(rs.getInt("quantity"));
				tempOrderLine.setUserId(rs.getInt("users_id"));
				tempOrderLine.setProductId(rs.getInt("products_id"));
				
				orderLines.add(tempOrderLine);
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
	    
	    return orderLines;
	}

	@Override
	public List<OrderLine> getOrderLinesOnUserId(int userId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    orderLines.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM orderlines WHERE users_id = ?;";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				OrderLine tempOrderLine = new OrderLine(rs.getInt("id"));
				tempOrderLine.setOrderId(rs.getInt("orders_id"));
				tempOrderLine.setQuantity(rs.getInt("quantity"));
				tempOrderLine.setUserId(rs.getInt("users_id"));
				tempOrderLine.setProductId(rs.getInt("products_id"));
				
				orderLines.add(tempOrderLine);
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
	    
	    return orderLines;
	}

	@Override
	public List<OrderLine> getOrderLinesOnOrderIdAndProductId(int orderId, int productId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    orderLines.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM orderlines WHERE orderlines.orders_id = ? AND orderlines.products_id = ?;";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, productId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				OrderLine tempOrderLine = new OrderLine(rs.getInt("id"));
				tempOrderLine.setOrderId(rs.getInt("orders_id"));
				tempOrderLine.setQuantity(rs.getInt("quantity"));
				tempOrderLine.setUserId(rs.getInt("users_id"));
				tempOrderLine.setProductId(rs.getInt("products_id"));
				
				orderLines.add(tempOrderLine);
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
	    
	    return orderLines;
	}

	/*@Override
	public List<OrderLine> getOrderLinesOnUserIdAndTimeStamp(int userId, String timeStamp) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    orderLines.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM orderlines WHERE users_id = ? AND orderdate = ?;";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, userId);
			pstmt.setString(2, timeStamp);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				OrderLine tempOrderLine = new OrderLine(rs.getInt("id"));
				tempOrderLine.setOrderId(rs.getInt("orders_id"));
				tempOrderLine.setQuantity(rs.getInt("quantity"));
				tempOrderLine.setUserId(rs.getInt("users_id"));
				tempOrderLine.setProductId(rs.getInt("products_id"));
				
				orderLines.add(tempOrderLine);
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
	    
	    return orderLines;
	}*/
}