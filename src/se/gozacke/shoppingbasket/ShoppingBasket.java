package se.gozacke.shoppingbasket;

public class ShoppingBasket {
	private int shoppingBasketId;
	private int userId;
	private int productId;
	private int quantity;
	
	public ShoppingBasket() {
		
	}
	
	public ShoppingBasket(int shoppingBasketId) {
		this.shoppingBasketId = shoppingBasketId;
		userId = 0;
		productId = 0;
		quantity = 0;
	}
	
	public int getShoppingBasketId() {
		return shoppingBasketId;
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
	
	@Override
	public String toString() {
		String shoppingBasketInfo = "";
		
		shoppingBasketInfo += "shoppingBasketId: " + shoppingBasketId + "\n";
		shoppingBasketInfo += "userId: " + userId + "\n";
		shoppingBasketInfo += "productId: " + productId + "\n";
		shoppingBasketInfo += "quantity: " + quantity + "\n";
		
		return shoppingBasketInfo;
	}
}