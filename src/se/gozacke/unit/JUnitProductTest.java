package se.gozacke.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
import se.gozacke.product.Film;
import se.gozacke.product.MySQLProductRepository;
import se.gozacke.product.Product;
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

public class JUnitProductTest {
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
	public void ProductStoreSizeTest() {
		try {
			List<Product> productsTest = productRepository.getAllProducts();

			int productsInStoreSizeTest = productsTest.size();

			assertEquals(16, productsInStoreSizeTest);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ProductIdExistsTest() {
		try {
			List<Product> productsTest = productRepository.getAllProducts();
			boolean productIdExists = false;

			for (Product p : productsTest) {
				if (p.getProductId() == 1) {
					productIdExists = true;
				}
			}

			assertTrue(productIdExists);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ProductInsertTest() {
		try {
			List<Product> productinsertTest = eCommerceProduct.getAllProducts();
			
			int productssize =  productinsertTest.size();

			assertTrue(productssize < 17);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ProductupdateTest() {
		try {
			Product productupdate = eCommerceProduct.getAllProducts().get(12);
			
			boolean productupdatevalid = false;
			
			if(productupdate.getProductName().equals("Ball")){
				productupdatevalid  = true;
			}
			
			assertTrue(productupdatevalid );
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void ProductDeleteTest() {
		try {
			List<Product> productinsertTest = eCommerceProduct.getAllProducts();
			
			int productssize =  productinsertTest.size();

			assertTrue(productssize > 15);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	/*
	@Test
	public void getProductsFromCategoryNameTest(){
		List<Product> ProductsFromCategoryName = eCommerceProduct.getProductsFromCategoryName("bike bmx");
		List<Integer> OwnparoductList = new ArrayList<>();
		
		
		Product p1 = new Product(3);
		
		Product p2 = new Product(8);
		
		 OwnparoductList.add(p1);
		 OwnparoductList.add(p2);
		
		boolean ProductsFromCategoryNamevalid = true;
		
		int counter = 0;
		for(Product p :ProductsFromCategoryName) {
			if(!p.getProductName().equals(OwnparoductList.get(counter).getProductName())) {
				ProductsFromCategoryNamevalid = false;
				break;
			}
			counter++;
		}
		
		assertTrue(ProductsFromCategoryNamevalid);
		

	}
	*/
	@Test
	public void getProductFromProductNameTest(){
		
	}
	
	@Test
	public void getProductFromProductId(){
		
	}
	
	@Test
	public void FilmsstoresizeTest() {
		try {
			List<Film> filmsTest = productRepository.getAllFilms();
			
			int filmsInStoreSizeTest =filmsTest.size();

			assertEquals(3, filmsInStoreSizeTest);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void FilmsIdExistsTest() {
		try {
			List<Film> filmsTest = productRepository.getAllFilms();
				
			boolean filmsIdExists = false;

			for (Film f: filmsTest) {
				if (f.getFilmId() == 15) {
					filmsIdExists = true;
				}
				
				assertTrue(filmsIdExists);
			}
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
}