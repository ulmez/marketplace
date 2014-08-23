package se.gozacke.productcategory;

public class ProductCategory {
	private int productCategoryId;
	private int productId;
	private int categoryId;
	
	public ProductCategory() {
		productId = 0;
		categoryId = 0;
	}
	
	public ProductCategory(int productCategoryId) {
		this.productCategoryId = productCategoryId;
		productId = 0;
		categoryId = 0;
	}
	
	public int getProductCategoryId() {
		return productCategoryId;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	@Override
	public String toString() {
		String productCategoryInfo = "";
		
		productCategoryInfo += "productCategoryId: " + productCategoryId + "\n";
		productCategoryInfo += "productId: " + productId + "\n";
		productCategoryInfo += "categoryId: " + categoryId + "\n";
		
		return productCategoryInfo;
	}
}