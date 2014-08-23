package se.gozacke.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.gozacke.actor.ActorRepository;
import se.gozacke.actor.MySQLActorRepository;
import se.gozacke.author.AuthorRepository;
import se.gozacke.author.MySQLAuthorRepository;
import se.gozacke.category.CategoryRepository;
import se.gozacke.category.MySQLCategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.ecommerce.ECommerce;
import se.gozacke.product.MySQLProductRepository;
import se.gozacke.product.Product;
import se.gozacke.product.ProductRepository;
import se.gozacke.productcategory.MySQLProductCategoryRepository;
import se.gozacke.productcategory.ProductCategoryRepository;
import se.gozacke.shoppingbasket.MySQLShoppingBasketRepository;
import se.gozacke.shoppingbasket.ShoppingBasketRepository;
import se.gozacke.staff.MySQLStaffRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.User;
import se.gozacke.user.UserRepository;
import se.gozacke.user.MySQLUserRepository;

public class JUnitUserTest {

	UserRepository userRepository = new MySQLUserRepository();
	UserRepository eCommerceUser = new ECommerce(userRepository);

	ProductRepository productRepository = new MySQLProductRepository();
	ProductRepository eCommerceProduct = new ECommerce(productRepository);

	CategoryRepository categoryRepository = new MySQLCategoryRepository();
	CategoryRepository eCommerceCategory = new ECommerce(categoryRepository);

	ShoppingBasketRepository shoppingBasketRepository = new MySQLShoppingBasketRepository();
	ShoppingBasketRepository eCommerceShoppingBasket = new ECommerce(
			shoppingBasketRepository);

	ProductCategoryRepository productCategoryRepository = new MySQLProductCategoryRepository();
	ProductCategoryRepository eCommerceProductCategory = new ECommerce(
			productCategoryRepository);

	StaffRepository staffRepository = new MySQLStaffRepository();
	StaffRepository eCommerceStaff = new ECommerce(staffRepository);

	ActorRepository actorRepository = new MySQLActorRepository();
	ActorRepository eCommerceActor = new ECommerce(actorRepository);

	AuthorRepository authorRepository = new MySQLAuthorRepository();
	AuthorRepository eCommerceAuthor = new ECommerce(authorRepository);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void UserStoreSizeTest() {

		try {
			List<User> usersTest = userRepository.getAllUsers();

			int usersInStoreSizeTest = usersTest.size();

			assertEquals(4, usersInStoreSizeTest);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void UserIdExistsTest() {
		try {
			List<User> usersTest = userRepository.getAllUsers();
			boolean userIdExists = false;

			for (User u : usersTest) {
				if (u.getUserId() == 1) {
					userIdExists = true;
				}
			}

			assertTrue(userIdExists);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void UserupdateTest(){
		try {
			User userid = eCommerceUser.getAllUsers().get(3);
			boolean userinsertvalid = false;
			
			if(userid.getEmail().equals("batman@batmobile.com")){
				userinsertvalid = true;
			}
			assertTrue(userinsertvalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void UserInsertTest() {
		try {
			List<User> usersinsert = userRepository.getAllUsers();
			
			int usersize = usersinsert.size();

			assertTrue(usersize < 5);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void UserDeleteTest() {
		try {
			List<User> usersinsert = userRepository.getAllUsers();
			
			int usersize = usersinsert.size();

			assertTrue(usersize > 3);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void UserLoginTest(){
		try {
			boolean userlogvalid = false ;
			User userlog = eCommerceUser.getAllUsers().get(1);

			if(userlog.getEmail().equals("manne.banne@hotmail.com") && userlog.getPassword().equals("gneten")){
				userlogvalid = true;
			}
			
			assertTrue(userlogvalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
}