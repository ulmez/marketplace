package se.gozacke.author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.database.Database;
import se.gozacke.product.ProductRepository;

public class MySQLAuthorRepository implements AuthorRepository {
private List<Author> authors;
	
	public MySQLAuthorRepository() {
		authors = new ArrayList<>();
	}

	@Override
	public List<Author> getAllAuthors() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    authors.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM authors;";
		
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Author tempAuthor = new Author(rs.getInt("id"));
				tempAuthor.setFirstName(rs.getString("firstname"));
				tempAuthor.setSurName(rs.getString("surname"));
				tempAuthor.setDob(rs.getString("dob"));
				
				authors.add(tempAuthor);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return authors;
	}

	@Override
	public List<Author> getAuthorOnAuthorId(int authorId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    authors.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM authors WHERE authors.id = ?;";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, authorId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Author tempAuthor = new Author(rs.getInt("id"));
				tempAuthor.setFirstName(rs.getString("firstname"));
				tempAuthor.setSurName(rs.getString("surname"));
				tempAuthor.setDob(rs.getString("dob"));
				
				authors.add(tempAuthor);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return authors;
	}

	@Override
	public List<Author> getAuthorOnFirstNameAndSurName(String firstName, String surName) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    authors.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM authors WHERE authors.firstname = ? AND authors.surname = ?;";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, firstName);
			pstmt.setString(2, surName);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Author tempAuthor = new Author(rs.getInt("id"));
				tempAuthor.setFirstName(rs.getString("firstname"));
				tempAuthor.setSurName(rs.getString("surname"));
				tempAuthor.setDob(rs.getString("dob"));
				
				authors.add(tempAuthor);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return authors;
	}

	@Override
	public List<Integer> getAuthorsBookIds(Author author) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    List<Integer> authorsBookIds = new ArrayList<>();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT books_authors.books_id "
						 + "FROM books_authors, authors "
						 + "WHERE authors.id = books_authors.authors_id "
						 + "AND authors.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, author.getAuthorId());
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				int bookId = rs.getInt("books_id");
				
				authorsBookIds.add(bookId);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return authorsBookIds;
	}

	@Override
	public boolean getIfBookIdStillExistOnAnyAuthorId(int bookId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    boolean exists = true;
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT count(books_authors.books_id) as book_id_exists "
						 + "FROM books_authors "
						 + "WHERE books_authors.books_id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, bookId);
			
			rs = pstmt.executeQuery();
			
			int countBookId = 0;
			
			// Display all the data in the table.
			while (rs.next()) {
				countBookId = rs.getInt("book_id_exists");
	        }
			
			if(countBookId == 1) {
				exists = false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return exists;
	}

	@Override
	public void insertAuthor(Author author) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO authors VALUES (null, ?, ?, ?);";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, author.getFirstName());
			pstmt.setString(2, author.getSurName());
			pstmt.setString(3, author.getDob());
			
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public void updateAuthor(Author author) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "UPDATE authors "
						 + "SET authors.firstname = ?, "
						 + "authors.surname = ?, "
						 + "authors.dob = ? "
						 + "WHERE authors.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, author.getFirstName());
			pstmt.setString(2, author.getSurName());
			pstmt.setString(3, author.getDob());
			pstmt.setInt(4, author.getAuthorId());
			
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}
	
	@Override
	public void deleteAuthor(Author author, AuthorRepository ar, ProductRepository pr) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    List<Integer> bookIdsToCheckForDeletion = ar.getAuthorsBookIds(author);
	    
	    try {
			conn = Database.getConnection();
			conn.setAutoCommit(false);
			
			String query = "DELETE FROM books_authors WHERE authors_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, author.getAuthorId());
			pstmt.executeUpdate();
			
			query = "DELETE FROM authors WHERE authors.id = ?";
			pstmt.close();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, author.getAuthorId());
			pstmt.executeUpdate();
			
			for(int i : bookIdsToCheckForDeletion) {
				boolean exists = ar.getIfBookIdStillExistOnAnyAuthorId(i);
				
				if(!exists) {
					int productIdToDelete = pr.getProductIdOnBookId(i);
					
					query = "DELETE FROM products_categories WHERE products_categories.products_id = ?;";
					pstmt.close();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, productIdToDelete);
					pstmt.executeUpdate();
					
					query = "DELETE FROM books WHERE books.products_id = ?;";
					pstmt.close();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, productIdToDelete);
					pstmt.executeUpdate();
					
					query = "DELETE FROM shoppingbasket WHERE shoppingbasket.products_id = ?;";
					pstmt.close();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, productIdToDelete);
					pstmt.executeUpdate();
					
					query = "DELETE FROM products WHERE products.id = ?;";
					pstmt.close();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, productIdToDelete);
					pstmt.executeUpdate();
				}
			}
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public List<Integer> getAuthorsOnProductId(int productId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    List<Integer> authorsIds = new ArrayList<>();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT authors.id as author_id "
						 + "FROM authors, books_authors, books, products "
						 + "WHERE authors.id = books_authors.authors_id "
						 + "AND books.id = books_authors.books_id "
						 + "AND products.id = books.products_id "
						 + "AND products.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				
				authorsIds.add(authorId);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return authorsIds;
	}
}