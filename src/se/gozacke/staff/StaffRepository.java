package se.gozacke.staff;

import java.util.List;

import se.gozacke.data.StorageException;

public interface StaffRepository {
	public List<Staff> getStaffFromFirstNameAndSurName(String firstName, String surName) throws StorageException;
	public List<Staff> getAllStaffMembers() throws StorageException;
	public List<Staff> getStaffOnStaffId(int staffId) throws StorageException;
}