package se.gozacke.warehouse;

import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.order.OrderRepository;
import se.gozacke.orderline.OrderLineRepository;
import se.gozacke.product.ProductRepository;
import se.gozacke.user.UserRepository;

public interface WarehouseRepository {
	public List<Warehouse> getAllWarehouse() throws StorageException;
	public List<Warehouse> getWarehouseOnProductId(int productId) throws StorageException;
	public void informationOfAllOrders(OrderRepository or, UserRepository ur, ProductRepository pr, OrderLineRepository olr) throws StorageException;
	public void insertWarehouse(Warehouse warehouse) throws StorageException;
	public void updateWarehouse(Warehouse warehouse) throws StorageException;
	public void deleteWarehouse(Warehouse warehouse) throws StorageException;
}