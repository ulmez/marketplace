package se.gozacke.shoppingbasket;

import java.util.List;

import se.gozacke.data.StorageException;

public interface ShoppingBasketRepository {
	public List<ShoppingBasket> getAllShoppingBaskets() throws StorageException;
	public void insertOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException;
	public void updateOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException;
	public void deleteOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException;
	public List<ShoppingBasket> getShoppingBasketOnUsername(String username) throws StorageException;
	public List<ShoppingBasket> getShoppingBasketOnUserId(int userId) throws StorageException;
	public List<ShoppingBasket> getShoppingBasketOnUserIdAndProductId(int userId, int productId) throws StorageException;
}