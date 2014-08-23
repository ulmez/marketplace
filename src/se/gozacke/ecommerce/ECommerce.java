package se.gozacke.ecommerce;

import java.util.List;
import java.util.Set;

import se.gozacke.actor.Actor;
import se.gozacke.actor.ActorRepository;
import se.gozacke.author.Author;
import se.gozacke.author.AuthorRepository;
import se.gozacke.category.Category;
import se.gozacke.category.CategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.order.Order;
import se.gozacke.order.OrderRepository;
import se.gozacke.orderline.OrderLine;
import se.gozacke.orderline.OrderLineRepository;
import se.gozacke.product.Book;
import se.gozacke.product.Film;
import se.gozacke.product.Product;
import se.gozacke.product.ProductRepository;
import se.gozacke.productcategory.ProductCategory;
import se.gozacke.productcategory.ProductCategoryRepository;
import se.gozacke.shoppingbasket.ShoppingBasket;
import se.gozacke.shoppingbasket.ShoppingBasketRepository;
import se.gozacke.staff.Staff;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.User;
import se.gozacke.user.UserRepository;
import se.gozacke.warehouse.Warehouse;
import se.gozacke.warehouse.WarehouseRepository;

public class ECommerce implements CategoryRepository, ProductRepository, UserRepository,
								  ShoppingBasketRepository, ProductCategoryRepository,
								  StaffRepository, ActorRepository, AuthorRepository,
								  OrderLineRepository, OrderRepository, WarehouseRepository {
	CategoryRepository categoryRepository;
	ProductRepository productRepository;
	UserRepository userRepository;
	ShoppingBasketRepository shoppingBasketRepository;
	ProductCategoryRepository productCategoryRepository;
	StaffRepository staffRepository;
	ActorRepository actorRepository;
	AuthorRepository authorRepository;
	OrderLineRepository orderLineRepository;
	OrderRepository orderRepository;
	WarehouseRepository warehouseRepository;
	
	public ECommerce(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public ECommerce(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public ECommerce(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public ECommerce(ShoppingBasketRepository shoppingBasketRepository) {
		this.shoppingBasketRepository = shoppingBasketRepository;
	}
	
	public ECommerce(ProductCategoryRepository productCategoryRepository) {
		this.productCategoryRepository = productCategoryRepository;
	}
	
	public ECommerce(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}
	
	public ECommerce(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}
	
	public ECommerce(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	public ECommerce(OrderLineRepository orderLineRepository) {
		this.orderLineRepository = orderLineRepository;
	}
	
	public ECommerce(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public ECommerce(WarehouseRepository warehouseRepository) {
		this.warehouseRepository = warehouseRepository;
	}
	
	// *******************************************************************
	@Override
	public List<Category> getAllCategories() throws StorageException {
		return categoryRepository.getAllCategories();
	}
	
	@Override
	public void insertCategory(Category category) throws StorageException {
		categoryRepository.insertCategory(category);
	}

	@Override
	public void updateCategory(Category category) throws StorageException {
		categoryRepository.updateCategory(category);
	}

	@Override
	public void deleteCategory(Category category) throws StorageException {
		categoryRepository.deleteCategory(category);
	}
	
	@Override
	public List<Category> getCategoryOnCategoryName(String categoryName) throws StorageException {
		return categoryRepository.getCategoryOnCategoryName(categoryName);
	}
	
	@Override
	public void updateStaffMemberResponsibleForCategory(int staffId, int categoryId) throws StorageException {
		categoryRepository.updateStaffMemberResponsibleForCategory(staffId, categoryId);
	}
	
	@Override
	public List<Category> getCategoriesFromProductName(String productName) throws StorageException {
		return categoryRepository.getCategoriesFromProductName(productName);
	}
	
	@Override
	public List<Category> getCategoriesFromProductId(int productId) throws StorageException {
		return categoryRepository.getCategoriesFromProductId(productId);
	}
	
	@Override
	public List<Category> getCategoryOnCategoryId(int categoryId) throws StorageException {
		return categoryRepository.getCategoryOnCategoryId(categoryId);
	}
	// *******************************************************************
	
	// *******************************************************************
	@Override
	public List<Product> getAllProducts() throws StorageException {
		return productRepository.getAllProducts();
	}
	
	@Override
	public void insertProduct(Product product, Set<Integer> listOfCategories) throws StorageException {
		productRepository.insertProduct(product, listOfCategories);
	}
	
	@Override
	public void updateProduct(Product product, Set<Integer> listOfCategories) throws StorageException {
		productRepository.updateProduct(product, listOfCategories);
	}
	
	@Override
	public void deleteProduct(Product product) throws StorageException {
		productRepository.deleteProduct(product);
	}
	
	@Override
	public List<Product> getProductsFromCategoryName(String categoryName) throws StorageException {
		return productRepository.getProductsFromCategoryName(categoryName);
	}
	
	@Override
	public List<Product> getProductFromProductName(String productName) throws StorageException {
		return productRepository.getProductFromProductName(productName);
	}
	
	@Override
	public List<Film> getAllFilms() throws StorageException {
		return productRepository.getAllFilms();
	}
	
	@Override
	public List<Film> getFilmsOnActorId(int actorId) throws StorageException {
		return productRepository.getFilmsOnActorId(actorId);
	}
	
	@Override
	public List<Film> getFilmsOnActorFirstNameAndSurName(String firstName, String surName) throws StorageException {
		return productRepository.getFilmsOnActorFirstNameAndSurName(firstName, surName);
	}
	
	@Override
	public List<Book> getAllBooks() throws StorageException {
		return productRepository.getAllBooks();
	}
	
	@Override
	public List<Book> getBooksOnAuthorId(int authorId) throws StorageException {
		return productRepository.getBooksOnAuthorId(authorId);
	}
	
	@Override
	public List<Book> getBooksOnAuthorFirstNameAndSurName(String firstName, String surName) throws StorageException {
		return productRepository.getBooksOnAuthorFirstNameAndSurName(firstName, surName);
	}
	
	@Override
	public void insertFilm(Film film, Set<Integer> listOfCategories, Set<Integer> listOfActors) throws StorageException {
		productRepository.insertFilm(film, listOfCategories, listOfActors);
	}
	
	@Override
	public void updateFilm(Film film, Set<Integer> listOfCategories, Set<Integer> listOfActors) throws StorageException {
		productRepository.updateFilm(film, listOfCategories, listOfActors);
	}
	
	@Override
	public void deleteFilm(Film film) throws StorageException {
		productRepository.deleteFilm(film);
	}
	
	@Override
	public void insertBook(Book book, Set<Integer> listOfCategories, Set<Integer> listOfAuthors) throws StorageException {
		productRepository.insertBook(book, listOfCategories, listOfAuthors);
	}
	
	@Override
	public List<Film> getFilmOnProductId(int productId) throws StorageException {
		return productRepository.getFilmOnProductId(productId);
	}
	
	@Override
	public List<Film> getFilmOnProductName(String productName) throws StorageException {
		return productRepository.getFilmOnProductName(productName);
	}
	
	@Override
	public void updateBook(Book book, Set<Integer> listOfCategories, Set<Integer> listOfAuthors) throws StorageException {
		productRepository.updateBook(book, listOfCategories, listOfAuthors);
	}
	
	@Override
	public List<Book> getBookOnProductId(int productId) throws StorageException {
		return productRepository.getBookOnProductId(productId);
	}
	
	@Override
	public List<Book> getBookOnProductName(String productName) throws StorageException {
		return productRepository.getBookOnProductName(productName);
	}
	
	@Override
	public void deleteBook(Book book) throws StorageException {
		productRepository.deleteBook(book);
	}
	
	@Override
	public int getProductIdOnFilmId(int filmId) throws StorageException {
		return productRepository.getProductIdOnFilmId(filmId);
	}
	
	@Override
	public int getProductIdOnBookId(int bookId) throws StorageException {
		return productRepository.getProductIdOnBookId(bookId);
	}
	
	@Override
	public List<Product> getProductFromProductId(int productId) throws StorageException {
		return productRepository.getProductFromProductId(productId);
	}
	
	@Override
	public List<Product> getProductsFromOrderId(int orderId) throws StorageException {
		return productRepository.getProductsFromOrderId(orderId);
	}
	
	@Override
	public List<Product> getProductsLessThenXInStock(int stock) throws StorageException {
		return productRepository.getProductsLessThenXInStock(stock);
	}
	// *******************************************************************
	
	// *******************************************************************
	@Override
	public List<User> getAllUsers() throws StorageException {
		return userRepository.getAllUsers();
	}
	
	@Override
	public void insertUser(User user) throws StorageException {
		userRepository.insertUser(user);
	}

	@Override
	public void updateUser(User user) throws StorageException {
		userRepository.updateUser(user);
	}

	@Override
	public void deleteUser(User user) throws StorageException {
		userRepository.deleteUser(user);
	}
	
	@Override
	public boolean validateUsernameAndPassword(String username, String password) throws StorageException {
		return userRepository.validateUsernameAndPassword(username, password);
	}
	
	@Override
	public List<User> getUserOnUserId(int userId) throws StorageException {
		return userRepository.getUserOnUserId(userId);
	}
	
	@Override
	public List<User> getUserOnUsernameAndPassword(String username, String password) throws StorageException {
		return userRepository.getUserOnUsernameAndPassword(username, password);
	}
	
	@Override
	public List<User> getUserOnOrderId(int orderId) throws StorageException {
		return userRepository.getUserOnOrderId(orderId);
	}
	// *******************************************************************
	
	// *******************************************************************
	@Override
	public List<ShoppingBasket> getAllShoppingBaskets() throws StorageException {
		return shoppingBasketRepository.getAllShoppingBaskets();
	}
	
	@Override
	public void insertOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException {
		shoppingBasketRepository.insertOrderInShoppingBasket(shoppingBasket);
	}
	
	@Override
	public void updateOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException {
		shoppingBasketRepository.updateOrderInShoppingBasket(shoppingBasket);
	}
	
	@Override
	public void deleteOrderInShoppingBasket(ShoppingBasket shoppingBasket) throws StorageException {
		shoppingBasketRepository.deleteOrderInShoppingBasket(shoppingBasket);
	}
	
	@Override
	public List<ShoppingBasket> getShoppingBasketOnUsername(String username) throws StorageException {
		return shoppingBasketRepository.getShoppingBasketOnUsername(username);
	}
	
	@Override
	public List<ShoppingBasket> getShoppingBasketOnUserId(int userId) throws StorageException {
		return shoppingBasketRepository.getShoppingBasketOnUserId(userId);
	}
	
	@Override
	public List<ShoppingBasket> getShoppingBasketOnUserIdAndProductId(int userId, int productId) throws StorageException {
		return shoppingBasketRepository.getShoppingBasketOnUserIdAndProductId(userId, productId);
	}
	// *******************************************************************
	
	// *******************************************************************
	@Override
	public List<ProductCategory> getAllProductCategories() throws StorageException {
		return productCategoryRepository.getAllProductCategories();
	}

	@Override
	public void insertProductCategory(ProductCategory productCategory) throws StorageException {
		productCategoryRepository.insertProductCategory(productCategory);
	}

	@Override
	public void updateProductCategory(ProductCategory productCategory) throws StorageException {
		productCategoryRepository.updateProductCategory(productCategory);
	}

	@Override
	public void deleteProductCategory(ProductCategory productCategory) throws StorageException {
		productCategoryRepository.deleteProductCategory(productCategory);
	}
	// *******************************************************************
	
	// *******************************************************************
	@Override
	public List<Staff> getStaffFromFirstNameAndSurName(String firstName, String surName) throws StorageException {
		return staffRepository.getStaffFromFirstNameAndSurName(firstName, surName);
	}
	
	@Override
	public List<Staff> getAllStaffMembers() throws StorageException {
		return staffRepository.getAllStaffMembers();
	}
	
	@Override
	public List<Staff> getStaffOnStaffId(int staffId) throws StorageException {
		return staffRepository.getStaffOnStaffId(staffId);
	}
	// *******************************************************************

	// *******************************************************************
	@Override
	public List<Actor> getAllActors() throws StorageException {
		return actorRepository.getAllActors();
	}
	
	@Override
	public List<Actor> getActorOnActorId(int actorId) throws StorageException {
		return actorRepository.getActorOnActorId(actorId);
	}
	
	@Override
	public List<Actor> getActorOnFirstNameAndSurName(String firstName, String surName) throws StorageException {
		return actorRepository.getActorOnFirstNameAndSurName(firstName, surName);
	}
	
	@Override
	public void insertActor(Actor actor) throws StorageException {
		actorRepository.insertActor(actor);
	}
	
	@Override
	public void updateActor(Actor actor) throws StorageException {
		actorRepository.updateActor(actor);
	}
	
	@Override
	public List<Integer> getActorsFilmIds(Actor actor) throws StorageException {
		return actorRepository.getActorsFilmIds(actor);
	}
	
	@Override
	public boolean getIfFilmIdStillExistOnAnyActorId(int filmId) throws StorageException {
		return actorRepository.getIfFilmIdStillExistOnAnyActorId(filmId);
	}
	
	@Override
	public void deleteActor(Actor actor, ActorRepository ar, ProductRepository pr) throws StorageException {
		actorRepository.deleteActor(actor, ar, pr);
	}
	
	@Override
	public List<Integer> getActorsOnProductId(int productId) throws StorageException {
		return actorRepository.getActorsOnProductId(productId);
	}
	// *******************************************************************
	
	// *******************************************************************
	@Override
	public List<Author> getAllAuthors() throws StorageException {
		return authorRepository.getAllAuthors();
	}
	
	@Override
	public List<Author> getAuthorOnAuthorId(int authorId) throws StorageException {
		return authorRepository.getAuthorOnAuthorId(authorId);
	}
	
	@Override
	public List<Author> getAuthorOnFirstNameAndSurName(String firstName, String surName) throws StorageException {
		return authorRepository.getAuthorOnFirstNameAndSurName(firstName, surName);
	}
	
	@Override
	public List<Integer> getAuthorsBookIds(Author author) throws StorageException {
		return authorRepository.getAuthorsBookIds(author);
	}
	
	@Override
	public boolean getIfBookIdStillExistOnAnyAuthorId(int bookId) throws StorageException {
		return authorRepository.getIfBookIdStillExistOnAnyAuthorId(bookId);
	}
	
	@Override
	public void insertAuthor(Author author) throws StorageException {
		authorRepository.insertAuthor(author);
	}
	
	@Override
	public void updateAuthor(Author author) throws StorageException {
		authorRepository.updateAuthor(author);
	}
	
	@Override
	public void deleteAuthor(Author author, AuthorRepository ar, ProductRepository pr) throws StorageException {
		authorRepository.deleteAuthor(author, ar, pr);
	}
	
	@Override
	public List<Integer> getAuthorsOnProductId(int productId) throws StorageException {
		return authorRepository.getAuthorsOnProductId(productId);
	}
	// *******************************************************************

	// *******************************************************************
	@Override
	public List<OrderLine> getAllOrderLines() throws StorageException {
		return orderLineRepository.getAllOrderLines();
	}
	
	@Override
	public List<OrderLine> getOrderLinesOnUserId(int userId) throws StorageException {
		return orderLineRepository.getOrderLinesOnUserId(userId);
	}
	
	@Override
	public List<OrderLine> getOrderLinesOnOrderIdAndProductId(int orderId, int productId) throws StorageException {
		return orderLineRepository.getOrderLinesOnOrderIdAndProductId(orderId, productId);
	}
	
//	@Override
//	public List<OrderLine> getOrderLinesOnUserIdAndTimeStamp(int userId, String timeStamp) throws StorageException {
//		return orderLineRepository.getOrderLinesOnUserIdAndTimeStamp(userId, timeStamp);
//	}
	// *******************************************************************
	
	// *******************************************************************
	@Override
	public List<Order> getAllOrders() throws StorageException {
		return orderRepository.getAllOrders();
	}
	
	@Override
	public void insertOrderFromShoppingBasket(List<ShoppingBasket> orderLine, WarehouseRepository wr) throws StorageException {
		orderRepository.insertOrderFromShoppingBasket(orderLine, wr);
	}
	// *******************************************************************

	// *******************************************************************
	@Override
	public List<Warehouse> getAllWarehouse() throws StorageException {
		return warehouseRepository.getAllWarehouse();
	}
	
	@Override
	public void insertWarehouse(Warehouse warehouse) throws StorageException {
		warehouseRepository.insertWarehouse(warehouse);
	}
	
	@Override
	public void updateWarehouse(Warehouse warehouse) throws StorageException {
		warehouseRepository.updateWarehouse(warehouse);
	}
	
	@Override
	public void deleteWarehouse(Warehouse warehouse) throws StorageException {
		warehouseRepository.deleteWarehouse(warehouse);
	}
	
	@Override
	public List<Warehouse> getWarehouseOnProductId(int productId) throws StorageException {
		return warehouseRepository.getWarehouseOnProductId(productId);
	}
	
	@Override
	public void informationOfAllOrders(OrderRepository or, UserRepository ur, ProductRepository pr, OrderLineRepository olr) throws StorageException {
		warehouseRepository.informationOfAllOrders(or, ur, pr, olr);
	}
	// *******************************************************************
}