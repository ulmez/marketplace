package se.gozacke.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import se.gozacke.staff.MySQLStaffRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.MySQLUserRepository;
import se.gozacke.user.UserRepository;

public class JunitCategoryTest {

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
	public void CategoryStoreSizeTest() {

		try {
			List<Category> categorysTest = eCommerceCategory.getAllCategories();

			int categorysInStoreSizeTest = categorysTest.size();

			assertEquals(11, categorysInStoreSizeTest);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void CategoryidExistsTest() {
		try {
			List<Category> categorysTest = eCommerceCategory.getAllCategories();

			boolean categoryIdExists = false;

			for (Category c : categorysTest) {
				if ( c.getCategoryId() == 1) {
					categoryIdExists = true;
				}
			}

			assertTrue(categoryIdExists);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void CategoryupdateTest(){
		try {
			Category categoryupdate = eCommerceCategory.getAllCategories().get(10);
			
			boolean categoryupdatevalid = false;
			
			if(categoryupdate.getCategoryName().equals("Films")){
				categoryupdatevalid = true;
			}
			assertTrue(categoryupdatevalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void CategoryupdatestaffTest(){
		try {
			Category categorysupdatestaff = eCommerceCategory.getAllCategories().get(10);

			boolean categoryupdatestaffvalid = false;
			
			if(categorysupdatestaff.getStaffId() == 27){
				categoryupdatestaffvalid = true;
			}
			assertTrue(categoryupdatestaffvalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void CategoryInsertTest() {
		try {
			List<Category> usersinsert = eCommerceCategory.getAllCategories();
			
			int categorysize = usersinsert.size();

			assertTrue(categorysize < 12);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void CategoryDeleteTest() {
		try {
			List<Category> categorysinsert = eCommerceCategory.getAllCategories();
			
			int categorysize = categorysinsert.size();

			assertTrue(categorysize > 10);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void  getCategoriesFromProductNameTest() {
		try {
			List<Category> CategoriesFromProductName = eCommerceCategory.getCategoriesFromProductName("Bike");
			List<Category> OwnCategoryList = new ArrayList<>();
			
			Category c1 = new Category();
			c1.setCategoryName("Books");
			
			Category c2 = new Category();
			c2.setCategoryName("Computing");
			
			Category c3 = new Category();
			c3.setCategoryName("Toys");
			
			Category c4 = new Category();
			c4.setCategoryName("Mens");
			
			Category c5 = new Category();
			c5.setCategoryName("Films");
			
			OwnCategoryList.add(c1);
			OwnCategoryList.add(c2);
			OwnCategoryList.add(c3);
			OwnCategoryList.add(c4);
			OwnCategoryList.add(c5);
			
			boolean CategoriesFromProductNamevalid = true;
			
			int counter = 0;
			for(Category c : CategoriesFromProductName) {
				if(!c.getCategoryName().equals(OwnCategoryList.get(counter).getCategoryName())) {
					CategoriesFromProductNamevalid = false;
					break;
				}
				counter++;
			}
			
			assertTrue(CategoriesFromProductNamevalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCategoriesFromProductIdTest() {
		try {
			List<Category> getCategoriesFromProductId = eCommerceCategory.getCategoriesFromProductId(2);
			List<Category> OwnCategoryList2 = new ArrayList<>();
			
			
			Category c1 = new Category(3);
			
			Category c2 = new Category(8);
			
			Category c3 = new Category(11);
			
			OwnCategoryList2.add(c1);
			OwnCategoryList2.add(c2);
			OwnCategoryList2.add(c3);
			
			boolean CategoriesFromProductIdvalid = true;
			
			int counter = 0;
			for(Category c :getCategoriesFromProductId) {
				if((!(c.getCategoryId() == (OwnCategoryList2.get(counter).getCategoryId())))){
					CategoriesFromProductIdvalid = false;
					break;
				}
				counter++;
			}
			
			assertTrue(CategoriesFromProductIdvalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
}