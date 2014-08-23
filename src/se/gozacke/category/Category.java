package se.gozacke.category;

public class Category {
	private int categoryId;
	private int staffId;
	private String categoryName;
	
	public Category() {
		
	}
	
	public Category(int categoryId) {
		this.categoryId = categoryId;
		staffId = 0;
		categoryName = null;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		String categoryInfo = "";
		
		categoryInfo += "categoryId: " + categoryId + "\n";
		categoryInfo += "staffId: " + staffId + "\n";
		categoryInfo += "categoryName: " + categoryName + "\n";
		
		return categoryInfo;
	}
}