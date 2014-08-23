package se.gozacke.author;

import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.product.ProductRepository;

public interface AuthorRepository {
	public List<Author> getAllAuthors() throws StorageException;
	public List<Author> getAuthorOnAuthorId(int authorId) throws StorageException;
	public List<Author> getAuthorOnFirstNameAndSurName(String firstName, String surName) throws StorageException;
	public List<Integer> getAuthorsBookIds(Author author) throws StorageException;
	public List<Integer> getAuthorsOnProductId(int productId) throws StorageException;
	public boolean getIfBookIdStillExistOnAnyAuthorId(int bookId) throws StorageException;
	public void insertAuthor(Author author) throws StorageException;
	public void updateAuthor(Author author) throws StorageException;
	public void deleteAuthor(Author author, AuthorRepository ar, ProductRepository pr) throws StorageException;
}