package se.gozacke.warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.database.Database;
import se.gozacke.order.Order;
import se.gozacke.order.OrderRepository;
import se.gozacke.orderline.OrderLineRepository;
import se.gozacke.product.Product;
import se.gozacke.product.ProductRepository;
import se.gozacke.user.User;
import se.gozacke.user.UserRepository;

public class MySQLWarehouseRepository implements WarehouseRepository {
private List<Warehouse> warehouse;
	
	public MySQLWarehouseRepository() {
		warehouse = new ArrayList<>();
	}

	@Override
	public List<Warehouse> getAllWarehouse() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    warehouse.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM warehouse;";
		
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Warehouse tempWarehouse = new Warehouse(rs.getInt("id"));
				tempWarehouse.setStock(rs.getInt("stock"));
				tempWarehouse.setProductId(rs.getInt("products_id"));
				
				warehouse.add(tempWarehouse);
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
	    
	    return warehouse;
	}

	@Override
	public void insertWarehouse(Warehouse warehouse) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO warehouse VALUES (null, ?, ?);";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, warehouse.getStock());
			pstmt.setInt(2, warehouse.getProductId());
			
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
	public void updateWarehouse(Warehouse warehouse) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "UPDATE warehouse "
						 + "SET stock = ?, "
						 + "products_id = ? "
						 + "WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, warehouse.getStock());
			pstmt.setInt(2, warehouse.getProductId());
			pstmt.setInt(3, warehouse.getWarehouseId());
			
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
	public void deleteWarehouse(Warehouse warehouse) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "DELETE FROM warehouse WHERE warehouse.id = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, warehouse.getWarehouseId());
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
	public List<Warehouse> getWarehouseOnProductId(int productId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    warehouse.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM warehouse WHERE warehouse.products_id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Warehouse tempWarehouse = new Warehouse(rs.getInt("id"));
				tempWarehouse.setStock(rs.getInt("stock"));
				tempWarehouse.setProductId(rs.getInt("products_id"));
				
				warehouse.add(tempWarehouse);
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
	    
	    return warehouse;
	}

	@Override
	public void informationOfAllOrders(OrderRepository or, UserRepository ur, ProductRepository pr, OrderLineRepository olr) throws StorageException {
		double totalCost = 0.0;
		double totalRrp = 0.0;
		
		System.out.println("List of all orders:");
		System.out.println();
		
		for(Order o : or.getAllOrders()) {
			totalCost = 0.0;
			totalRrp = 0.0;
			
			System.out.println("******************************************************");
			System.out.println("OrderId: " + o.getOrderId());
			System.out.println("Order date: " + o.getOrderDate());
//			System.out.println();
			System.out.println("------------------------");
			System.out.println();
			
			System.out.println("Products list:");
//			System.out.println();
			
			for(Product p : pr.getProductsFromOrderId(o.getOrderId())) {
				System.out.println();
				System.out.println("ProductId: " + p.getProductId());
				System.out.println("Product name: " + p.getProductName());
				System.out.println("Description: " + p.getDescription());
				System.out.println("Cost: " + p.getCost());
				System.out.println("Rrp: " + p.getRrp());
				System.out.println("Quantity: " + olr.getOrderLinesOnOrderIdAndProductId(o.getOrderId(), p.getProductId()).get(0).getQuantity());
				
				int tempQuantity = olr.getOrderLinesOnOrderIdAndProductId(o.getOrderId(), p.getProductId()).get(0).getQuantity();
				totalCost += p.getCost() * tempQuantity;
				totalRrp += p.getRrp() * tempQuantity;
			}
			
			System.out.println("------------------------");
			System.out.println();
			System.out.println("User information:");
			System.out.println();
			
			for(User u : ur.getUserOnOrderId(o.getOrderId())) {
				System.out.println("UserId: " + u.getUserId());
				System.out.println("Firstname: " + u.getFirstName());
				System.out.println("Surname: " + u.getSurName());
				System.out.println("Street address: " + u.getStreetAddress());
				System.out.println("Postcode: " + u.getPostCode());
				System.out.println("Town: " + u.getTown());
				System.out.println("Telephone: " + u.getTelephone());
				System.out.println("Email: " + u.getEmail());
			}
			
			System.out.println("------------------------");
			
			System.out.println();
			
			System.out.println("Total cost from the order:");
			System.out.println(totalCost);
			
			System.out.println();
			
			System.out.println("Profit from the order:");
			System.out.println(totalRrp - totalCost);
			
			System.out.println("******************************************************");
			System.out.println();
			
//			System.out.println(ur.getUserOnOrderId(o.getOrderId()).get(0));
		}
	}
}