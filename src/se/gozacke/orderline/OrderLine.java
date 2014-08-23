package se.gozacke.orderline;

public class OrderLine {
	private final int orderLineId;
	private int orderId;
	private int userId;
	private int productId;
	private int quantity;
	
	public OrderLine() {
		orderLineId = 0;
		orderId = 0;
		userId = 0;
		productId = 0;
		quantity = 0;
	}
	
	public OrderLine(int orderLineId) {
		this.orderLineId = orderLineId;
		orderId = 0;
		userId = 0;
		productId = 0;
		quantity = 0;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderLineId() {
		return orderLineId;
	}
	
	@Override
	public String toString() {
		String orderLineInfo = "";
		
		orderLineInfo += "orderLineId: " + orderLineId + "\n";
		orderLineInfo += "orderId: " + orderId + "\n";
		orderLineInfo += "userId: " + userId + "\n";
		orderLineInfo += "productId: " + productId + "\n";
		orderLineInfo += "quantity: " + quantity + "\n";
		
		return orderLineInfo;
	}
}