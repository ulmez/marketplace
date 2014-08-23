package se.gozacke.product;

import java.util.List;
import java.util.Set;

import se.gozacke.data.StorageException;

public interface ProductRepository {
	public List<Product> getAllProducts() throws StorageException;
	public void insertProduct(Product product, Set<Integer> listOfCategories) throws StorageException;
	public void updateProduct(Product product, Set<Integer> listOfCategories) throws StorageException;
	public void deleteProduct(Product product) throws StorageException;
	public List<Product> getProductsFromCategoryName(String categoryName) throws StorageException;
	public List<Product> getProductFromProductName(String productName) throws StorageException;
	public List<Product> getProductFromProductId(int productId) throws StorageException;
	public List<Product> getProductsFromOrderId(int orderId) throws StorageException;
	public List<Product> getProductsLessThenXInStock(int stock) throws StorageException;
	public List<Film> getAllFilms() throws StorageException;
	public List<Film> getFilmsOnActorId(int actorId) throws StorageException;
	public List<Film> getFilmsOnActorFirstNameAndSurName(String firstName, String surName) throws StorageException;
	public List<Film> getFilmOnProductId(int productId) throws StorageException;
	public List<Film> getFilmOnProductName(String productName) throws StorageException;
	public int getProductIdOnFilmId(int filmId) throws StorageException;
	public int getProductIdOnBookId(int bookId) throws StorageException;
	public List<Book> getAllBooks() throws StorageException;
	public List<Book> getBooksOnAuthorId(int authorId) throws StorageException;
	public List<Book> getBooksOnAuthorFirstNameAndSurName(String firstName, String surName) throws StorageException;
	public List<Book> getBookOnProductId(int productId) throws StorageException;
	public List<Book> getBookOnProductName(String productName) throws StorageException;
	public void insertFilm(Film film, Set<Integer> listOfCategories, Set<Integer> listOfActors) throws StorageException;
	public void updateFilm(Film film, Set<Integer> listOfCategories, Set<Integer> listOfActors) throws StorageException;
	public void deleteFilm(Film film) throws StorageException;
	public void insertBook(Book book, Set<Integer> listOfCategories, Set<Integer> listOfAuthors) throws StorageException;
	public void updateBook(Book book, Set<Integer> listOfCategories, Set<Integer> listOfAuthors) throws StorageException;
	public void deleteBook(Book book) throws StorageException;
}