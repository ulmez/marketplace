package se.gozacke.main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import se.gozacke.actor.Actor;
import se.gozacke.actor.ActorRepository;
import se.gozacke.actor.MySQLActorRepository;
import se.gozacke.author.Author;
import se.gozacke.author.AuthorRepository;
import se.gozacke.author.MySQLAuthorRepository;
import se.gozacke.category.Category;
import se.gozacke.category.CategoryRepository;
import se.gozacke.category.MySQLCategoryRepository;
import se.gozacke.console.ActorConsole;
import se.gozacke.console.AuthorConsole;
import se.gozacke.console.BookConsole;
import se.gozacke.console.CategoryConsole;
import se.gozacke.console.FilmConsole;
import se.gozacke.console.MainConsole;
import se.gozacke.console.ProductConsole;
import se.gozacke.console.ShoppingBasketConsole;
import se.gozacke.console.UserConsole;
import se.gozacke.console.WarehouseConsole;
import se.gozacke.data.StorageException;
import se.gozacke.ecommerce.ECommerce;
import se.gozacke.order.MySQLOrderRepository;
import se.gozacke.order.OrderRepository;
import se.gozacke.orderline.MySQLOrderLineRepository;
import se.gozacke.orderline.OrderLineRepository;
import se.gozacke.product.Book;
import se.gozacke.product.Film;
import se.gozacke.product.MySQLProductRepository;
import se.gozacke.product.Product;
import se.gozacke.product.ProductRepository;
import se.gozacke.productcategory.MySQLProductCategoryRepository;
import se.gozacke.productcategory.ProductCategory;
import se.gozacke.productcategory.ProductCategoryRepository;
import se.gozacke.shoppingbasket.MySQLShoppingBasketRepository;
import se.gozacke.shoppingbasket.ShoppingBasket;
import se.gozacke.shoppingbasket.ShoppingBasketRepository;
import se.gozacke.staff.MySQLStaffRepository;
import se.gozacke.staff.Staff;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.MySQLUserRepository;
import se.gozacke.user.User;
import se.gozacke.user.UserRepository;
import se.gozacke.warehouse.MySQLWarehouseRepository;
import se.gozacke.warehouse.Warehouse;
import se.gozacke.warehouse.WarehouseRepository;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		UserRepository userRepository = new MySQLUserRepository();
		UserRepository eCommerceUser = new ECommerce(userRepository);
		
		ProductRepository productRepository = new MySQLProductRepository();
		ProductRepository eCommerceProduct = new ECommerce(productRepository);
		
		CategoryRepository categoryRepository = new MySQLCategoryRepository();
		CategoryRepository eCommerceCategory = new ECommerce(categoryRepository);
		
		ShoppingBasketRepository shoppingBasketRepository = new MySQLShoppingBasketRepository();
		ShoppingBasketRepository eCommerceShoppingBasket = new ECommerce(shoppingBasketRepository);
		
		ProductCategoryRepository productCategoryRepository = new MySQLProductCategoryRepository();
		ProductCategoryRepository eCommerceProductCategory = new ECommerce(productCategoryRepository);
		
		StaffRepository staffRepository = new MySQLStaffRepository();
		StaffRepository eCommerceStaff = new ECommerce(staffRepository);
		
		ActorRepository actorRepository = new MySQLActorRepository();
		ActorRepository eCommerceActor = new ECommerce(actorRepository);
		
		AuthorRepository authorRepository = new MySQLAuthorRepository();
		AuthorRepository eCommerceAuthor = new ECommerce(authorRepository);
		
		OrderLineRepository orderLineRepository = new MySQLOrderLineRepository();
		OrderLineRepository eCommerceOrderLine = new ECommerce(orderLineRepository);
		
		OrderRepository orderRepository = new MySQLOrderRepository();
		OrderRepository eCommerceOrder = new ECommerce(orderRepository);
		
		WarehouseRepository warehouseRepository = new MySQLWarehouseRepository();
		WarehouseRepository eCommerceWarehouse = new ECommerce(warehouseRepository);
		
//		try {
//			new DataOutputStream(new ServerSocket(8080).accept().getOutputStream()).writeBytes(eCommerceProduct.getAllProducts().toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (StorageException e) {
//			e.printStackTrace();
//		}
		
		try {
			// The VG assignment blocks is block 1 and 2 directly under here. ***************
			// Block 1 **********************************************************************
			// ******************************************************************************
			// * login with user to the shoppingbasket. *************************************
			// * Insert, update and delete to users shoppingbasket. *************************
			// * Making order of users shoppingbasket. **************************************
			// ******************************************************************************
			Scanner sc = new Scanner(System.in);
			int userId = 0;
			
			if((userId = ShoppingBasketConsole.loginUserToShoppingBasket(sc, eCommerceUser)) > 0) {
				ShoppingBasketConsole.insertProductsToShoppingBasket(sc, userId, eCommerceShoppingBasket, eCommerceProduct, eCommerceWarehouse, eCommerceOrder);
			} else {
				System.out.println("Unvalid");
			}
			
			sc.close();
			// ******************************************************************************
			
			// Block 2 **********************************************************************
			// * List all orders ************************************************************
			// * List products that are low in stock ****************************************
			// ******************************************************************************
//			Scanner sc = new Scanner(System.in);
//			
//			WarehouseConsole.listings(sc, eCommerceWarehouse, eCommerceOrder, eCommerceUser, eCommerceProduct, eCommerceOrderLine);
//			
//			sc.close();
			// ******************************************************************************
			
			// Block 3 **********************************************************************
			// * Insert product, category or user
			// * Update product, category or user
			// * Delete product, category or user
			// * Test login for a user
			// * Get list of products in a category
			// * Search a product by name
			// ******************************************************************************
//			MainConsole.mainWindow(eCommerceProduct, eCommerceCategory, eCommerceActor, eCommerceAuthor, eCommerceStaff, eCommerceUser);
			// ******************************************************************************
		} catch (StorageException e) {
			log.error(e);
			e.printStackTrace();
		}
	}
}