package se.gozacke.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.database.Database;

public class MySQLUserRepository implements UserRepository {
	private List<User> users;
	
	public MySQLUserRepository() {
		users = new ArrayList<>();
	}
	
	@Override
	public List<User> getAllUsers() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    users.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM users;";
		
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				User tempUser = new User(rs.getInt("id"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setPassword(rs.getString("password"));
				tempUser.setFirstName(rs.getString("firstname"));
				tempUser.setSurName(rs.getString("surname"));
				tempUser.setStreetAddress(rs.getString("street_address"));
				tempUser.setPostCode(rs.getString("post_code"));
				tempUser.setTown(rs.getString("town"));
				tempUser.setTelephone(rs.getString("telephone"));
				
				users.add(tempUser);
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
	    
	    return users;
	}

	@Override
	public void insertUser(User user) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO users VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?);";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getSurName());
			pstmt.setString(5, user.getStreetAddress());
			pstmt.setString(6, user.getPostCode());
			pstmt.setString(7, user.getTown());
			pstmt.setString(8, user.getTelephone());
			
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
	public void updateUser(User user) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "UPDATE users "
						 + "SET email = ?, "
						 + "password = ?, "
						 + "firstname = ?, "
						 + "surname = ?, "
						 + "street_address = ?, "
						 + "post_code = ?, "
						 + "town = ?, "
						 + "telephone = ? "
						 + "WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getSurName());
			pstmt.setString(5, user.getStreetAddress());
			pstmt.setString(6, user.getPostCode());
			pstmt.setString(7, user.getTown());
			pstmt.setString(8, user.getTelephone());
			pstmt.setInt(9, user.getUserId());
			
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
	public void deleteUser(User user) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "DELETE FROM shoppingbasket WHERE shoppingbasket.users_id = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, user.getUserId());
			pstmt.executeUpdate();
			
			query = "DELETE FROM users WHERE id = ?;";
			pstmt.close();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, user.getUserId());
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
	public boolean validateUsernameAndPassword(String username, String password) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    boolean validUser = false;
	    int checkUser = 0;
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT count(*) as valid_user "
						 + "FROM users "
						 + "WHERE users.email = ? "
						 + "AND users.password = ?;";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				checkUser = rs.getInt("valid_user");
				
				if(checkUser > 0) {
					validUser = true;
				} else {
					validUser = false;
				}
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
	    
	    return validUser;
	}

	@Override
	public List<User> getUserOnUserId(int userId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    users.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM users WHERE users.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				User tempUser = new User(rs.getInt("id"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setPassword(rs.getString("password"));
				tempUser.setFirstName(rs.getString("firstname"));
				tempUser.setSurName(rs.getString("surname"));
				tempUser.setStreetAddress(rs.getString("street_address"));
				tempUser.setPostCode(rs.getString("post_code"));
				tempUser.setTown(rs.getString("town"));
				tempUser.setTelephone(rs.getString("telephone"));
				
				users.add(tempUser);
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
	    
	    return users;
	}

	@Override
	public List<User> getUserOnUsernameAndPassword(String username, String password) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    users.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM users WHERE users.email = ? AND users.password = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				User tempUser = new User(rs.getInt("id"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setPassword(rs.getString("password"));
				tempUser.setFirstName(rs.getString("firstname"));
				tempUser.setSurName(rs.getString("surname"));
				tempUser.setStreetAddress(rs.getString("street_address"));
				tempUser.setPostCode(rs.getString("post_code"));
				tempUser.setTown(rs.getString("town"));
				tempUser.setTelephone(rs.getString("telephone"));
				
				users.add(tempUser);
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
	    
	    return users;
	}

	@Override
	public List<User> getUserOnOrderId(int orderId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    users.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT users.id as user_id, users.firstname, users.surname, users.street_address, "
								+ "users.post_code, users.email, users.password, users.town, users.telephone "
						 + "FROM users "
						 + "INNER JOIN orderlines "
						 + "ON users.id = orderlines.users_id "
						 + "INNER JOIN orders "
						 + "ON orderlines.orders_id = orders.id "
						 + "WHERE orders.id = ? "
						 + "GROUP BY user_id;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, orderId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				User tempUser = new User(rs.getInt("user_id"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setPassword(rs.getString("password"));
				tempUser.setFirstName(rs.getString("firstname"));
				tempUser.setSurName(rs.getString("surname"));
				tempUser.setStreetAddress(rs.getString("street_address"));
				tempUser.setPostCode(rs.getString("post_code"));
				tempUser.setTown(rs.getString("town"));
				tempUser.setTelephone(rs.getString("telephone"));
				
				users.add(tempUser);
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
	    
	    return users;
	}
}