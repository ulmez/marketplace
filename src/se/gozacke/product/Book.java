package se.gozacke.product;

public class Book extends Product {
	private int bookId;
	private String isbn;
	private String published;
	private int productId;
	
	public Book() {
		bookId = 0;
		isbn = "";
		published = "";
		productId = 0;
	}
	
	public Book(int bookId, int productId) {
		this.bookId = bookId;
		isbn = "";
		published = "";
		this.productId = productId;
	}
	
	public Book(int bookId) {
		this.bookId = bookId;
		isbn = "";
		published = "";
		productId = 0;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public int getBookId() {
		return bookId;
	}

	public int getProductId() {
		return productId;
	}
	
	@Override
	public String toString() {
		String bookInfo = "";
		
		bookInfo += "bookId: " + this.getBookId() + "\n";
		bookInfo += "productId: " + this.getProductId() + "\n";
		bookInfo += "productName: " + this.getProductName() + "\n";
		bookInfo += "description: " + this.getDescription() + "\n";
		bookInfo += "cost: " + this.getCost() + "\n";
		bookInfo += "rrp: " + this.getRrp() + "\n";
		bookInfo += "isbn: " + this.getIsbn() + "\n";
		bookInfo += "published: " + this.getPublished() + "\n";
		
		return bookInfo;
	}
}