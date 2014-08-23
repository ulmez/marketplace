package se.gozacke.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import se.gozacke.actor.ActorRepository;
import se.gozacke.author.Author;
import se.gozacke.author.AuthorRepository;
import se.gozacke.category.Category;
import se.gozacke.category.CategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.product.Book;
import se.gozacke.product.ProductRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.UserRepository;

public class BookConsole {
	public static void insertBook(Scanner sc, ProductRepository pr, CategoryRepository cr, AuthorRepository aur, ActorRepository acr, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String productName = "";
		String description = "";
		String cost = "";
		String rrp = "";
		String isbn = "";
		String published = "";
		String authorsChoise = "";
		String categoryChoise = "";
		
		List<String> categoriesToAdd;
		Set<Integer> addCategoriesWithoutDoubles;
		
		List<String> authorsToAdd;
		Set<Integer> addAuthorsWithoutDoubles;
		
		Set<Integer> listOfCategoryIds;
		
		do{
			do{
				checkValidInfo = false;
				
				System.out.println("Insert book name:");
				productName = sc.nextLine();
				System.out.println();
				
				if(Check.isNumeric(productName)) {
					checkValidInfo = true;
					System.out.println("You can't have a numeric book name");
					System.out.println();
				} else if(productName.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted any book name yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert description:");
				description = sc.nextLine();
				System.out.println();
				
				if(Check.isNumeric(description)) {
					checkValidInfo = true;
					System.out.println("You can't have a numeric book description");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert cost:");
				cost = sc.nextLine();
				System.out.println();
				
				if(!Check.isNumeric(cost)) {
					checkValidInfo = true;
					System.out.println("You haven't used a numeric value for cost");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert rrp:");
				rrp = sc.nextLine();
				System.out.println();
				
				if(!Check.isNumeric(rrp)) {
					checkValidInfo = true;
					System.out.println("You haven't used a numeric value for rrp");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert isbn:");
				isbn = sc.nextLine();
				System.out.println();
				
				if(isbn.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted any value for isbn yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert published:");
				published = sc.nextLine();
				System.out.println();
				
				if(published.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted any value for published yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				for(Author a : aur.getAllAuthors()) {
					System.out.println("AuthorId: " + a.getAuthorId());
					System.out.println("Author name: " + a.getFirstName() + " " + a.getSurName());
					System.out.println();
				}
				
				System.out.println("Insert authors in the book (with authorId):");
				authorsChoise = sc.nextLine();
				
				authorsToAdd = Arrays.asList(authorsChoise.split(","));
				addAuthorsWithoutDoubles = new TreeSet<>();
				
				for(String s : authorsToAdd) {
					if(!Check.isNumeric(s.trim())) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You have inserted invalid authorId");
						break;
					} else if(aur.getAuthorOnAuthorId(Integer.parseInt(s.trim())).size() == 0) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You have inserted an authorId that is not in the repository");
						break;
					}
					addAuthorsWithoutDoubles.add(Integer.parseInt(s.trim()));
				}
				
				System.out.println();
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				do{
					checkValidInfo = false;
					int counter = 0;
					
					do{
						for(Category c : cr.getAllCategories()) {
							counter++;
							System.out.println(counter + ": " + c.getCategoryName());
						}
						
						checkValidInfo = false;
						
						System.out.println();
						
						System.out.println("Insert the categories you want the book to be associated with:");
						categoryChoise = sc.nextLine();
						System.out.println();
						
						if(categoryChoise.equals("")) {
							checkValidInfo = true;
							System.out.println("You haven't inserted any category yet");
							System.out.println();
						}
					} while(checkValidInfo);
					
					categoriesToAdd = Arrays.asList(categoryChoise.split(","));
					addCategoriesWithoutDoubles = new TreeSet<>();
					
					for(String s : categoriesToAdd) {
						String tempS = s.trim();
						
						if(Check.isNumeric(tempS)) {
							int i = Integer.parseInt(tempS);
							addCategoriesWithoutDoubles.add(i - 1);
						} else {
							checkValidInfo = true;
							System.out.println("You inserted some invalid data when trying to generate categories");
							break;
						}
					}
				} while(checkValidInfo);
				
				categoryChoise = "";
				listOfCategoryIds = new TreeSet<>();
				
				for(int i : addCategoriesWithoutDoubles) {
					listOfCategoryIds.add(cr.getAllCategories().get(i).getCategoryId());
					categoryChoise += cr.getAllCategories().get(i).getCategoryName() + ", ";
				}
				
				categoryChoise = categoryChoise.substring(0, categoryChoise.length() - 2);
				
				authorsChoise = "";
				
				for(int i : addAuthorsWithoutDoubles) {
					authorsChoise += aur.getAuthorOnAuthorId(i).get(0).getFirstName() + " " + aur.getAuthorOnAuthorId(i).get(0).getSurName() + ", ";
				}
				
				authorsChoise = authorsChoise.substring(0, authorsChoise.length() - 2);
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("You have inserted following values to your new book:");
				System.out.println("Product name: " + productName);
				System.out.println("Description: " + description);
				System.out.println("Cost: " + cost);
				System.out.println("Rrp: " + rrp);
				System.out.println("Isbn: " + isbn);
				System.out.println("Published: " + published);
				System.out.println("Authors: " + authorsChoise);
				System.out.println("Categories: " + categoryChoise);
				
				System.out.println();
				
				System.out.println("If you want to save this book, and exit to main menu, press Y.");
				System.out.println("If you want to save this book, and make another, press A.");
				System.out.println("If you don't want to save this book, but make another, press X,");
				System.out.println("If you don't want to save this book, and exit to main menu, press Z,");
				
				String choises = sc.nextLine();
				choises = choises.toLowerCase();
				
				if(choises.equals("y")) {
					checkValidInfo = false;
					
					double doubleCost = Double.parseDouble(cost);
					double doubleRrp = Double.parseDouble(rrp);
					
					Book b = new Book();
					
					b.setProductName(productName);
					b.setDescription(description);
					b.setCost(doubleCost);
					b.setRrp(doubleRrp);
					b.setIsbn(isbn);
					b.setPublished(published);
					
					pr.insertBook(b, listOfCategoryIds, addAuthorsWithoutDoubles);
					
					System.out.println();
				} else if(choises.equals("a")) {
					checkValidInfo = true;
					
					double doubleCost = Double.parseDouble(cost);
					double doubleRrp = Double.parseDouble(rrp);
					
					Book b = new Book();
					
					b.setProductName(productName);
					b.setDescription(description);
					b.setCost(doubleCost);
					b.setRrp(doubleRrp);
					b.setIsbn(isbn);
					b.setPublished(published);
					
					pr.insertBook(b, listOfCategoryIds, addAuthorsWithoutDoubles);
					
					System.out.println();
					
					break;
				} else if(choises.equals("x")) {
					checkValidInfo = true;
					
					System.out.println();
					
					break;
				} else if(choises.equals("z")) {
					checkValidInfo = false;
					
					System.out.println();
				} else {
					checkValidInfo = true;
					
					System.out.println("You haven't pressed Y, A, X or Z");
					System.out.println();
				}
			} while(checkValidInfo);
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
	
	public static void updateBook(Scanner sc, ProductRepository pr, CategoryRepository cr, AuthorRepository aur, ActorRepository acr, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = false;
		Book productToBeUpdated = null;
		String strProductId = "";
		String WhatToChange = "";
		String productName = "";
		String description = "";
		String cost = "";
		String rrp = "";
		String isbn = "";
		String published = "";
		String categories = "";
		String authors = "";
		String keyboardChoise = "";
		List<String> authorsToAdd;
		List<Integer> productsAvailable;
		List<String> categoriesToAdd;
		Set<Integer> addCategoriesWithoutDoubles;
		Set<Integer> addAuthorsWithoutDoubles;
		
		do{
			checkValidInfo = false;
			int counter = 0;
			productsAvailable = new ArrayList<>();
			
			for(Book b : pr.getAllBooks()) {
				productsAvailable.add(b.getProductId());
				counter++;
				String tempString = "";
				tempString += counter + ".)\tProductId: " + b.getProductId() + "\n";
				tempString += "\tProduct name: " + b.getProductName() + "\n";
				tempString += "\tDescription: " + b.getDescription() + "\n";
				tempString += "\tCost: " + b.getCost() + "\n";
				tempString += "\tRrp: " + b.getRrp() + "\n";
				tempString += "\tIsbn: " + b.getIsbn() + "\n";
				tempString += "\tPublished: " + b.getPublished() + "\n";
				
				tempString += "\tAuthors: ";
				
				for(int a : aur.getAuthorsOnProductId(b.getProductId())) {
					Author au = aur.getAuthorOnAuthorId(a).get(0);
					
					tempString += au.getFirstName() + " " + au.getSurName() + ", ";
				}
				
				tempString = tempString.substring(0, tempString.length() - 2);
				
				tempString += "\n";
				tempString += "\tCategories: ";
				
				for(Category c : cr.getCategoriesFromProductId(b.getProductId())) {
					tempString += c.getCategoryName() + ", ";
				}
				
				tempString = tempString.substring(0, tempString.length() - 2);
				tempString += "\n";
				
				System.out.println(tempString);
			}
			
			do{
				checkValidInfo = false;
				boolean available = false;
				
				System.out.println("Choose the book you want to update (write productId):");
				
				strProductId = sc.nextLine();
				if(strProductId.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted a productId yet");
					System.out.println();
				} else if(!Check.isNumeric(strProductId)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't used a numeric value for productId");
					System.out.println();
				} else {
					for(int i : productsAvailable) {
						if(i == Integer.parseInt(strProductId)) {
							available = true;
							break;
						}
					}
					
					if(!available) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't choosen a book that is available in the repository");
						System.out.println();
					} else {
						productToBeUpdated = pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0);
					}
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				System.out.println("This is the book you wanted to change:");
				System.out.print(productToBeUpdated);
				
				String showAuthors = "";
				
				for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
					Author au = aur.getAuthorOnAuthorId(a).get(0);
					showAuthors += au.getFirstName() + " " + au.getSurName() + ", ";
				}
				
				showAuthors = showAuthors.substring(0, showAuthors.length() - 2);
				
				System.out.println("authors: " + showAuthors);
				
				String showCategories = "";
				
				for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
					showCategories += c.getCategoryName() + ", ";
				}
				
				showCategories = showCategories.substring(0, showCategories.length() - 2);
				
				System.out.println("categories: " + showCategories);
				System.out.println();
				
				do{
					checkValidInfo = false;
					
					System.out.println("What do you want to change (Write 1 - 8):");
					System.out.println("1 = Book name");
					System.out.println("2 = Description");
					System.out.println("3 = Cost");
					System.out.println("4 = Rrp");
					System.out.println("5 = Isbn");
					System.out.println("6 = Published");
					System.out.println("7 = Authors");
					System.out.println("8 = Categories");
					
					System.out.println();
					WhatToChange = sc.nextLine();
					System.out.println();
					if(WhatToChange.equals("")) {
						checkValidInfo = true;
						System.out.println("You haven't inserted a choise yet");
						System.out.println();
					} else if(!Check.isNumeric(WhatToChange)) {
						checkValidInfo = true;
						System.out.println("You haven't inserted a numeric value for your choise");
						System.out.println();
					} else if(Integer.parseInt(WhatToChange) < 1 || Integer.parseInt(WhatToChange) > 8) {
						checkValidInfo = true;
						System.out.println("You haven't entered a number between 1 - 8");
						System.out.println();
					}
				} while(checkValidInfo);
				
				if(WhatToChange.equals("1")) {
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new book name:");
							
							productName = sc.nextLine();
							
							if(productName.equals("")) {
								checkValidInfo = true;
								System.out.println("You haven't inserted any book name yet");
								System.out.println();
							} else if(Check.isNumeric(productName)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric book name");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyAuthorList = new TreeSet<>();
						
						productToBeUpdated.setProductName(productName);
						pr.updateBook(productToBeUpdated, emptyCategoryList, emptyAuthorList);
						
						System.out.println("You have changed the book name. Your book look like this now:");
						System.out.print(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "authors: ";
						
						for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
							Author au = aur.getAuthorOnAuthorId(a).get(0);
							tempString += au.getFirstName() + " " + au.getSurName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2) + "\n";
						tempString += "categories: ";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println(tempString);
					} while(checkValidInfo);
				} else if(WhatToChange.equals("2")) {
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new description:");
							
							description = sc.nextLine();
							
							if(Check.isNumeric(description)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric description");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyAuthorList = new TreeSet<>();
						
						productToBeUpdated.setDescription(description);
						pr.updateBook(productToBeUpdated, emptyCategoryList, emptyAuthorList);
						
						System.out.println("You have changed the description. Your book look like this now:");
						System.out.print(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "authors: ";
						
						for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
							Author au = aur.getAuthorOnAuthorId(a).get(0);
							tempString += au.getFirstName() + " " + au.getSurName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2) + "\n";
						tempString += "categories: ";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println(tempString);
					} while(checkValidInfo);
				} else if(WhatToChange.equals("3")) {
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new cost:");
							
							cost = sc.nextLine();
							
							if(!Check.isNumeric(cost)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You must insert a numeric value for cost");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyAuthorList = new TreeSet<>();
						
						productToBeUpdated.setCost(Double.parseDouble(cost));
						pr.updateBook(productToBeUpdated, emptyCategoryList, emptyAuthorList);
						
						System.out.println("You have changed the cost. Your film look like this now:");
						System.out.print(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "authors: ";
						
						for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
							Author au = aur.getAuthorOnAuthorId(a).get(0);
							tempString += au.getFirstName() + " " + au.getSurName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2) + "\n";
						tempString += "categories: ";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println(tempString);
					} while(checkValidInfo);
				} else if(WhatToChange.equals("4")) {
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new rrp:");
							
							rrp = sc.nextLine();
							
							if(!Check.isNumeric(rrp)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You must insert a numeric value for rrp");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyAuthorList = new TreeSet<>();
						
						productToBeUpdated.setRrp(Double.parseDouble(rrp));
						pr.updateBook(productToBeUpdated, emptyCategoryList, emptyAuthorList);
						
						System.out.println("You have changed the rrp. Your book look like this now:");
						System.out.print(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "authors: ";
						
						for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
							Author au = aur.getAuthorOnAuthorId(a).get(0);
							tempString += au.getFirstName() + " " + au.getSurName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2) + "\n";
						tempString += "categories: ";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println(tempString);
					} while(checkValidInfo);
				} else if(WhatToChange.equals("5")) {
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new isbn:");
							
							isbn = sc.nextLine();
							
							if(isbn.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for isbn yet");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyAuthorList = new TreeSet<>();
						
						productToBeUpdated.setIsbn(isbn);
						pr.updateBook(productToBeUpdated, emptyCategoryList, emptyAuthorList);
						
						System.out.println("You have changed the isbn. Your book look like this now:");
						System.out.print(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "authors: ";
						
						for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
							Author au = aur.getAuthorOnAuthorId(a).get(0);
							tempString += au.getFirstName() + " " + au.getSurName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2) + "\n";
						tempString += "categories: ";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println(tempString);
					} while(checkValidInfo);
				} else if(WhatToChange.equals("6")) {
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new published:");
							
							published = sc.nextLine();
							
							if(published.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for published yet");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyAuthorList = new TreeSet<>();
						
						productToBeUpdated.setPublished(published);
						pr.updateBook(productToBeUpdated, emptyCategoryList, emptyAuthorList);
						
						System.out.println("You have changed the published. Your book look like this now:");
						System.out.print(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "authors: ";
						
						for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
							Author au = aur.getAuthorOnAuthorId(a).get(0);
							tempString += au.getFirstName() + " " + au.getSurName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2) + "\n";
						tempString += "categories: ";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println(tempString);
					} while(checkValidInfo);
				} else if(WhatToChange.equals("7")) {
					counter = 0;
					
					for(Author a : aur.getAllAuthors()) {
						counter++;
						System.out.println(counter + ".) " + a.getFirstName() + " " + a.getSurName());
					}
					
					do{
						checkValidInfo = false;
						
						System.out.println();
						System.out.println("Enter which authors you want to use:");
						authors = sc.nextLine();
						
						authorsToAdd = Arrays.asList(authors.split(","));
						addAuthorsWithoutDoubles = new TreeSet<>();
						
						for(String s : authorsToAdd) {
							if(!Check.isNumeric(s)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You have inserted invalid author");
							} else if(Integer.parseInt(s.trim()) > aur.getAllAuthors().size() || Integer.parseInt(s.trim()) < 1) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You have inserted out of bounds authors");
							} else {
								addAuthorsWithoutDoubles.add(aur.getAllAuthors().get(Integer.parseInt(s.trim()) - 1).getAuthorId());
							}
						}
					} while(checkValidInfo);
					Set<Integer> emptyCategoryList = new TreeSet<>();
					
					pr.updateBook(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0) ,emptyCategoryList , addAuthorsWithoutDoubles);
					
					System.out.println();
					System.out.println("You have changed the authors. Your book look like this now:");
					System.out.print(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0));
					
					String tempString = "authors: ";
					
					for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
						Author au = aur.getAuthorOnAuthorId(a).get(0);
						tempString += au.getFirstName() + " " + au.getSurName() + ", ";
					}
					
					tempString = tempString.substring(0, tempString.length() - 2) + "\n";
					tempString += "categories: ";
					
					for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
						tempString += c.getCategoryName() + ", ";
					}
					
					tempString = tempString.substring(0, tempString.length() - 2);
					
					System.out.println(tempString);
				} else if(WhatToChange.equals("8")) {
					counter = 0;
					
					for(Category c : cr.getAllCategories()) {
						counter++;
						System.out.println(counter + ".) " + c.getCategoryName());
					}
					
					do{
						checkValidInfo = false;
						
						System.out.println();
						System.out.println("Enter which categories you want to use:");
						categories = sc.nextLine();
						
						categoriesToAdd = Arrays.asList(categories.split(","));
						addCategoriesWithoutDoubles = new TreeSet<>();
						
						for(String s : categoriesToAdd) {
							if(!Check.isNumeric(s)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You have inserted invalid category");
							} else if(Integer.parseInt(s.trim()) > cr.getAllCategories().size() || Integer.parseInt(s.trim()) < 1) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You have inserted out of bounds categories");
							} else {
								addCategoriesWithoutDoubles.add(cr.getAllCategories().get(Integer.parseInt(s.trim()) - 1).getCategoryId());
							}
						}
					} while(checkValidInfo);
					Set<Integer> emptyAuthorList = new TreeSet<>();
					
					pr.updateBook(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0), addCategoriesWithoutDoubles, emptyAuthorList);
					
					System.out.println();
					System.out.println("You have changed the categories. Your book look like this now:");
					System.out.print(pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0));
					
					String tempString = "authors: ";
					
					for(int a : aur.getAuthorsOnProductId(Integer.parseInt(strProductId))) {
						Author au = aur.getAuthorOnAuthorId(a).get(0);
						tempString += au.getFirstName() + " " + au.getSurName() + ", ";
					}
					
					tempString = tempString.substring(0, tempString.length() - 2) + "\n";
					tempString += "categories: ";
					
					for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
						tempString += c.getCategoryName() + ", ";
					}
					
					tempString = tempString.substring(0, tempString.length() - 2);
					
					System.out.println(tempString);
				}
				
				System.out.println();
				
				do{
					checkValidInfo = false;
					
					System.out.println("Do you want to change something else in this book, press Y.");
					System.out.println("Do you want to change another book, press X");
					System.out.println("Do you want to quit to main menu, press Q");
					
					keyboardChoise = sc.nextLine();
					keyboardChoise = keyboardChoise.toLowerCase();
					
					System.out.println();
					
					if(keyboardChoise.equals("")) {
						checkValidInfo = true;
						System.out.println("You haven't made any choise yet");
						System.out.println();
					} else if(keyboardChoise.equals("y")) {
						checkValidInfo = true;
						break;
					} else if(keyboardChoise.equals("x")) {
						break;
					} else if(keyboardChoise.equals("q")) {
						System.out.println();
						break;
					} else {
						checkValidInfo = true;
						System.out.println("You haven't pressed Y, X or Q");
						System.out.println();
					}
				} while(checkValidInfo);
			} while(checkValidInfo);
		} while(checkValidInfo || keyboardChoise.equals("x"));
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
	
	public static void deleteBook(Scanner sc, ProductRepository pr, CategoryRepository cr, AuthorRepository aur, ActorRepository acr, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = false;
		String keyboardChoise = "";
		String strProductId = "";
		
		do{
			checkValidInfo = false;
			
			for(Book b : pr.getAllBooks()) {
				System.out.print(b);
				String categoriesTemp = "";
				for(Category c : cr.getCategoriesFromProductId(b.getProductId())) {
					categoriesTemp += c.getCategoryName() + ", ";
				}
				categoriesTemp = "categories: " + categoriesTemp.substring(0, categoriesTemp.length() - 2);
				
				categoriesTemp += "\nauthors: ";
				
				for(int i : aur.getAuthorsOnProductId(b.getProductId())) {
					categoriesTemp += aur.getAuthorOnAuthorId(i).get(0).getFirstName() + " " + aur.getAuthorOnAuthorId(i).get(0).getSurName() + ", ";
				}
				
				categoriesTemp = categoriesTemp.substring(0, categoriesTemp.length() - 2);
				
				System.out.println(categoriesTemp);
				System.out.println();
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("What book do you want to delete? (write productId):");
				strProductId = sc.nextLine();
				strProductId = strProductId.trim();
				
				if(strProductId.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted productId yet");
					System.out.println();
				} else if(!Check.isNumeric(strProductId)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted a numeric value for productId");
					System.out.println();
				} else if(pr.getBookOnProductId(Integer.parseInt(strProductId)).size() == 0) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("The book isn't in the repository");
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Now the book is deleted");
					Book bookToDelete = pr.getBookOnProductId(Integer.parseInt(strProductId)).get(0);
					pr.deleteBook(bookToDelete);
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Do you want to delete another book, press X");
				System.out.println("Do you want to quit to main menu, press Q");
				
				keyboardChoise = sc.nextLine();
				keyboardChoise = keyboardChoise.toLowerCase();
				
				System.out.println();
				
				if(keyboardChoise.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't made any choise yet");
					System.out.println();
				} else if(keyboardChoise.equals("x")) {
					checkValidInfo = true;
					break;
				} else if(keyboardChoise.equals("q")) {
					System.out.println();
					break;
				} else {
					checkValidInfo = true;
					System.out.println("You haven't pressed X or Q");
					System.out.println();
				}
			} while(checkValidInfo);
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
}