package se.gozacke.order;

import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.shoppingbasket.ShoppingBasket;
import se.gozacke.warehouse.WarehouseRepository;

public interface OrderRepository {
	public List<Order> getAllOrders() throws StorageException;
	public void insertOrderFromShoppingBasket(List<ShoppingBasket> orderLine, WarehouseRepository wr) throws StorageException;
}