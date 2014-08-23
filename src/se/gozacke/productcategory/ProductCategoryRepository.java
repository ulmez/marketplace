package se.gozacke.productcategory;

import java.util.List;

import se.gozacke.data.StorageException;

public interface ProductCategoryRepository {
	public List<ProductCategory> getAllProductCategories() throws StorageException;
	public void insertProductCategory(ProductCategory productCategory) throws StorageException;
	public void updateProductCategory(ProductCategory productCategory) throws StorageException;
	public void deleteProductCategory(ProductCategory productCategory) throws StorageException;
}