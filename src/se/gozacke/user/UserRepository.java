package se.gozacke.user;

import java.util.List;

import se.gozacke.data.StorageException;

public interface UserRepository {
	public List<User> getAllUsers() throws StorageException;
	public List<User> getUserOnUserId(int userId) throws StorageException;
	public List<User> getUserOnUsernameAndPassword(String username, String password) throws StorageException;
	public List<User> getUserOnOrderId(int orderId) throws StorageException;
	public void insertUser(User user) throws StorageException;
	public void updateUser(User user) throws StorageException;
	public void deleteUser(User user) throws StorageException;
	public boolean validateUsernameAndPassword(String username, String password) throws StorageException;
}