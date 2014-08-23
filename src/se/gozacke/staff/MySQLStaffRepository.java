package se.gozacke.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.database.Database;

public class MySQLStaffRepository implements StaffRepository {
	private List<Staff> staffs;
	
	public MySQLStaffRepository() {
		staffs = new ArrayList<>();
	}

	@Override
	public List<Staff> getStaffFromFirstNameAndSurName(String firstName, String surName) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    staffs.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * "
						 + "FROM staff "
						 + "WHERE firstname = ? "
						 + "AND surname = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, firstName);
			pstmt.setString(2, surName);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Staff tempStaff = new Staff(rs.getInt("id"));
				tempStaff.setSalary(rs.getInt("salary"));
				tempStaff.setFirstName(rs.getString("firstname"));
				tempStaff.setSurName(rs.getString("surname"));
				tempStaff.setDateOfBirth(rs.getString("dob"));
				tempStaff.setStreetAddress(rs.getString("street_address"));
				tempStaff.setTown(rs.getString("town"));
				tempStaff.setPostCode(rs.getString("postcode"));
				tempStaff.setMobile(rs.getString("mobile"));
				tempStaff.setEmail(rs.getString("email"));

				staffs.add(tempStaff);
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
	    
	    return staffs;
	}

	@Override
	public List<Staff> getAllStaffMembers() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    staffs.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM staff;";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Staff tempStaff = new Staff(rs.getInt("id"));
				tempStaff.setSalary(rs.getInt("salary"));
				tempStaff.setFirstName(rs.getString("firstname"));
				tempStaff.setSurName(rs.getString("surname"));
				tempStaff.setDateOfBirth(rs.getString("dob"));
				tempStaff.setStreetAddress(rs.getString("street_address"));
				tempStaff.setTown(rs.getString("town"));
				tempStaff.setPostCode(rs.getString("postcode"));
				tempStaff.setMobile(rs.getString("mobile"));
				tempStaff.setEmail(rs.getString("email"));

				staffs.add(tempStaff);
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
	    
	    return staffs;
	}

	@Override
	public List<Staff> getStaffOnStaffId(int staffId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    staffs.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * "
						 + "FROM staff "
						 + "WHERE id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, staffId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Staff tempStaff = new Staff(rs.getInt("id"));
				tempStaff.setSalary(rs.getInt("salary"));
				tempStaff.setFirstName(rs.getString("firstname"));
				tempStaff.setSurName(rs.getString("surname"));
				tempStaff.setDateOfBirth(rs.getString("dob"));
				tempStaff.setStreetAddress(rs.getString("street_address"));
				tempStaff.setTown(rs.getString("town"));
				tempStaff.setPostCode(rs.getString("postcode"));
				tempStaff.setMobile(rs.getString("mobile"));
				tempStaff.setEmail(rs.getString("email"));

				staffs.add(tempStaff);
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
	    
	    return staffs;
	}
}