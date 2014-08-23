package se.gozacke.orderline;

import java.util.List;

import se.gozacke.data.StorageException;

public interface OrderLineRepository {
	public List<OrderLine> getAllOrderLines() throws StorageException;
	public List<OrderLine> getOrderLinesOnUserId(int userId) throws StorageException;
	public List<OrderLine> getOrderLinesOnOrderIdAndProductId(int orderId, int productId) throws StorageException;
//	public List<OrderLine> getOrderLineOnOrderLineId(int orderLineId) throws StorageException;
//	public List<OrderLine> getOrderLinesOnUserIdAndTimeStamp(int userId, String timeStamp) throws StorageException;
}