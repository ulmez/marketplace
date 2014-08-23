package se.gozacke.console;

import java.util.Scanner;

import se.gozacke.data.StorageException;
import se.gozacke.order.OrderRepository;
import se.gozacke.orderline.OrderLineRepository;
import se.gozacke.product.Product;
import se.gozacke.product.ProductRepository;
import se.gozacke.user.UserRepository;
import se.gozacke.warehouse.WarehouseRepository;

public class WarehouseConsole {
	public static void listings(Scanner sc, WarehouseRepository wr, OrderRepository or, UserRepository ur, ProductRepository pr, OrderLineRepository olr) throws StorageException {
		boolean checkValidInfo = false;
		String options = "";
		String amount = "";
		
		do {
			checkValidInfo = false;
			
			System.out.println("Warehouse listings menu (write 1 - 3):");
			System.out.println("1 = List all orders");
			System.out.println("2 = List products that are low in stock");
			System.out.println("3 = Quit");
			
			do {
				checkValidInfo = false;
				
				options = sc.nextLine().trim();
				
				if(options.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any choise yet");
					System.out.println();
				} else if(!Check.isNumeric(options)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted a numeric value as your choise, press 1 - 3");
					System.out.println();
				} else if(Integer.parseInt(options) < 1 || Integer.parseInt(options) > 3) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You didn't insert number 1 - 3");
					System.out.println();
				} else if(options.equals("1")) {
					checkValidInfo = true;
					System.out.println();
					wr.informationOfAllOrders(or, ur, pr, olr);
					break;
				} else if(options.equals("2")) {
					System.out.println();
					System.out.println("Insert the amount of stock to list less then:");
					
					do {
						checkValidInfo = false;
						
						amount = sc.nextLine().trim();
						
						if(amount.equals("")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted any amount yet");
							System.out.println();
						} else if(!Check.isNumeric(amount)) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted a numeric value for the amount");
							System.out.println();
						} else {
							System.out.println();
							
							for(Product p : pr.getProductsLessThenXInStock(Integer.parseInt(amount))) {
								System.out.println("ProductId: " + p.getProductId());
								System.out.println("Product name: " + p.getProductName());
								System.out.println("Description: " + p.getDescription());
								System.out.println("Cost: " + p.getCost());
								System.out.println("Rrp: " + p.getRrp());
								System.out.println("Stock left in warehouse: " + wr.getWarehouseOnProductId(p.getProductId()).get(0).getStock());
								System.out.println();
							}
						}
					} while(checkValidInfo);
				} else if(options.equals("3")) {
					checkValidInfo = false;
					amount = "";
				}
			} while(checkValidInfo);
		} while(checkValidInfo || !amount.equals(""));
	}
}