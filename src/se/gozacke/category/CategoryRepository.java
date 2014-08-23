package se.gozacke.category;

import java.util.List;

import se.gozacke.data.StorageException;

public interface CategoryRepository {
	public List<Category> getAllCategories() throws StorageException;
	public void insertCategory(Category category) throws StorageException;
	public void updateCategory(Category category) throws StorageException;
	public void deleteCategory(Category category) throws StorageException;
	public List<Category> getCategoryOnCategoryName(String categoryName) throws StorageException;
	public void updateStaffMemberResponsibleForCategory(int staffId, int categoryId) throws StorageException;
	public List<Category> getCategoriesFromProductName(String productName) throws StorageException;
	public List<Category> getCategoriesFromProductId(int productId) throws StorageException;
	public List<Category> getCategoryOnCategoryId(int categoryId) throws StorageException;
}