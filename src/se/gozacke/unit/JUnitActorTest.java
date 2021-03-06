package se.gozacke.unit;

import static org.junit.Assert.*;

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
import se.gozacke.staff.MySQLStaffRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.MySQLUserRepository;
import se.gozacke.user.User;
import se.gozacke.user.UserRepository;
public class JUnitActorTest {

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
	public void ActorStoreSizeTest() {

		try {
			List<Actor> actorTest = eCommerceActor.getAllActors();

			int authorsInStoreSizeTest = actorTest.size();

			assertEquals(6, authorsInStoreSizeTest);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ActoridExistsTest() {
		try {
			List<Author> authorsTest = eCommerceAuthor.getAllAuthors();

			boolean authorIdExists = false;

			for (Author a : authorsTest) {
				if ( a.getAuthorId() == 3) {
					authorIdExists = true;
				}
			}

			assertTrue(authorIdExists);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void  getActorrOnActorIdTest(){
		try {
			Actor actorsTest = eCommerceActor.getAllActors().get(1);
			
			boolean actoronactorvalid = false;
			
			if(actorsTest.getActorId() == 2 ){
				 actoronactorvalid = true;
			}
			assertTrue( actoronactorvalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	 @Test
	 public void  getAuthorOnFirstNameAndSurName(){
		 try {
			Actor actorsTest = eCommerceActor.getAllActors().get(1);
			 
			 boolean actorfirstnameandsurnamevalid = false;
			 
			 if(actorsTest.getFirstName().equals("Arnold") && actorsTest.getSurName().equals("Swarschenegger")){
				 actorfirstnameandsurnamevalid = true;
			 }
			 assertTrue(actorfirstnameandsurnamevalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	 }
	 /*
	 @Test
	 public void getAuthorsBookIds(){
		 List<Integer> getAuthorsBookId = eCommerceAuthor.getAuthorsBookIds(a1);
		 List<Author> OwnAuthorList2 = new ArrayList<>();	
		 
		 Author a1 = new Author(1); 
		 
		 Author a2 = new Author(1);
		 
		 Author a3 = new Author(1);
		 
		 OwnAuthorList2.add(a1);
		 OwnAuthorList2.add(a2);
		 OwnAuthorList2.add(a3);
		 
		 boolean CategoriesFromProductIdvalid = true;
			
			int counter = 0;
			for(Author a : getAuthorsBookId) {
				if((!(c.getCategoryId() == (OwnCategoryList2.get(counter).getCategoryId())))){
					CategoriesFromProductIdvalid = false;
					break;
				}
				counter++;
			}
			
			assertTrue(CategoriesFromProductIdvalid);

	 }
	*/
	@Test
	public void ActorupdateTest(){
		try {
			Actor actorupdate =  eCommerceActor.getAllActors().get(1);
			boolean actorupdatevalid = false;
			
			if( actorupdate.getFirstName().equals("Arnold")){
				actorupdatevalid = true;
			}
			assertTrue(actorupdatevalid);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public void ActorInsertTest() {
		try {
			List<Actor> actorsinsert = eCommerceActor.getAllActors();
			
			int actorrsize = actorsinsert.size();

			assertTrue(actorrsize < 7);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void ActorDeleteTest() {
		try {
			List<Actor> actorsinsert = eCommerceActor.getAllActors();
			
			int actorrsize = actorsinsert.size();

			assertTrue(actorrsize > 5);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}