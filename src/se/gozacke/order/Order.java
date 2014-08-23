package se.gozacke.order;

public class Order {
	private final int orderId;
	private String orderDate;
	
	public Order() {
		orderId = 0;
		orderDate = "";
	}
	
	public Order(int orderId) {
		this.orderId = orderId;
		orderDate = "";
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}
	
	@Override
	public String toString() {
		String orderInfo = "";
		
		orderInfo += "orderId: " + orderId + "\n";
		orderInfo += "orderDate: " + orderDate + "\n";
		
		return orderInfo;
	}
}