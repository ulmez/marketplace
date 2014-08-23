package se.gozacke.warehouse;

public class Warehouse {
	private final int warehouseId;
	private int productId;
	private int stock;
	
	public Warehouse() {
		warehouseId = 0;
		productId = 0;
		stock = 0;
	}
	
	public Warehouse(int warehouseId) {
		this.warehouseId = warehouseId;
		productId = 0;
		stock = 0;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getWarehouseId() {
		return warehouseId;
	}
	
	@Override
	public String toString() {
		String warehouseInfo = "";
		
		warehouseInfo += "warehouseId: " + warehouseId + "\n";
		warehouseInfo += "productId: " + productId + "\n";
		warehouseInfo += "stock: " + stock + "\n";
		
		return warehouseInfo;
	}
}