package se.gozacke.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import se.gozacke.author.Author;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.gozacke.actor.Actor;
import se.gozacke.actor.ActorRepository;
import se.gozacke.actor.MySQLActorRepository;
import se.gozacke.author.Author;
import se.gozacke.author.AuthorRepository;
import se.gozacke.author.MySQLAuthorRepository;
import se.gozacke.category.Category;
import se.gozacke.category.CategoryRepository;
import se.gozacke.category.MySQLCategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.ecommerce.ECommerce;
import se.gozacke.product.MySQLProductRepository;
import se.gozacke.product.ProductRepository;
import se.gozacke.productcategory.MySQLProductCategoryRepository;
import se.gozacke.productcategory.ProductCategoryRepository;
import se.gozacke.shoppingbasket.MySQLShoppingBasketRepository;
import se.gozacke.shoppingbasket.ShoppingBasketRepository;
import se.gozacke.shoppingbasket.ShoppingBasket;
import se.gozacke.staff.MySQLStaffRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.MySQLUserRepository;
import se.gozacke.user.User;
import se.gozacke.user.UserRepository;
public class JUnitshoppingcart {
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
	
	@Test
	public void shoppingbasketStoreSizeTest() {

		try {
			List<ShoppingBasket> shoppingbasketTest = eCommerceShoppingBasket.getAllShoppingBaskets();

			int shoppingbasketInStoreSizeTest = shoppingbasketTest.size();

			assertEquals(5, shoppingbasketInStoreSizeTest);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void shoppingbasketidExistsTest() {
		try {
			List<ShoppingBasket> shoppingbasketTest = eCommerceShoppingBasket.getAllShoppingBaskets();

			boolean shoppingbasketIdExists = false;

			for ( ShoppingBasket s : shoppingbasketTest ) {
				if ( s.getShoppingBasketId() == 3) {
					shoppingbasketIdExists = true;
				}
			}

			assertTrue(shoppingbasketIdExists);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void ShoppingbasketupdateTest(){
		try {
			ShoppingBasket shoppingbasketupdateupdate =  eCommerceShoppingBasket.getAllShoppingBaskets().get(4);
			boolean shoppingbasketupdatevalid = false;
			
				if(shoppingbasketupdateupdate.getProductId() == 2 && shoppingbasketupdateupdate.getUserId() == 2 && shoppingbasketupdateupdate.getQuantity() == 5){
					shoppingbasketupdatevalid = true;
				}
				assertTrue(shoppingbasketupdatevalid);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	@Test
	public void ShoppingBasketInsertTest() {
		try {
			List<ShoppingBasket> shoppinginsert = eCommerceShoppingBasket.getAllShoppingBaskets();
			
			
			int shoppingbasketsize = shoppinginsert.size();
			
			assertTrue(shoppingbasketsize < 6);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void AuthorDeleteTest() {
		try {
			List<ShoppingBasket>shoppingbasketrdelete = eCommerceShoppingBasket.getAllShoppingBaskets();
			
			int shoppingbasketsize = shoppingbasketrdelete.size();

			assertTrue(shoppingbasketsize > 4);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}