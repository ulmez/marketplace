package se.gozacke.product;

public class Film extends Product {
	private int filmId;
	private int rating;
	private int agelimit;
	private String releaseYear;
	private int productId;
	
	public Film() {
		filmId = 0;
		rating = 0;
		agelimit = 0;
		releaseYear = "";
		productId = 0;
	}
	
	public Film(int filmId, int productId) {
		this.filmId = filmId;
		rating = 0;
		agelimit = 0;
		releaseYear = "";
		this.productId = productId;
	}
	
	public Film(int filmId) {
		this.filmId = filmId;
		rating = 0;
		agelimit = 0;
		releaseYear = "";
		productId = 0;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getAgelimit() {
		return agelimit;
	}

	public void setAgelimit(int agelimit) {
		this.agelimit = agelimit;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getFilmId() {
		return filmId;
	}

	public int getProductId() {
		return productId;
	}
	
	@Override
	public String toString() {
		String filmInfo = "";
		
		filmInfo += "filmId: " + this.getFilmId() + "\n";
		filmInfo += "productId: " + this.getProductId() + "\n";
		filmInfo += "productName: " + this.getProductName() + "\n";
		filmInfo += "description: " + this.getDescription() + "\n";
		filmInfo += "cost: " + this.getCost() + "\n";
		filmInfo += "rrp: " + this.getRrp() + "\n";
		filmInfo += "rating: " + this.getRating() + "\n";
		filmInfo += "agelimit: " + this.getAgelimit() + "\n";
		filmInfo += "releaseYear: " + this.getReleaseYear() + "\n";
		
		return filmInfo;
	}
}