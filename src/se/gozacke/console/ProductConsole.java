package se.gozacke.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import se.gozacke.actor.ActorRepository;
import se.gozacke.author.AuthorRepository;
import se.gozacke.category.Category;
import se.gozacke.category.CategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.product.Product;
import se.gozacke.product.ProductRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.UserRepository;

public class ProductConsole {
	public static void insertProduct(Scanner sc, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String productName = "";
		String description = "";
		String cost = "";
		String rrp = "";
		String categoryChoise = "";
		List<String> categoriesToAdd;
		Set<Integer> addCategoriesWithoutDoubles;
		Set<Integer> listOfCategoryIds;
		
		do{
			do{
				checkValidInfo = false;
				
				System.out.println("Insert product name:");
				productName = sc.nextLine();
				System.out.println();
				
				if(Check.isNumeric(productName)) {
					checkValidInfo = true;
					System.out.println("You can't have a numeric product name");
					System.out.println();
				} else if(productName.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted any product name yet");
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
					System.out.println("You can't have a numeric product description");
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
						
						System.out.println("Insert the categories you want the product to be associated with:");
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
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("You have inserted following values to your new product:");
				System.out.println("Product name: " + productName);
				System.out.println("Description: " + description);
				System.out.println("Cost: " + cost);
				System.out.println("Rrp: " + rrp);
				System.out.println("Categories: " + categoryChoise);
				
				System.out.println();
				System.out.println("If you want to save this product, and exit to main menu, press Y.");
				System.out.println("If you want to save this product, and make another, press A.");
				System.out.println("If you don't want to save this product, but make another, press X,");
				System.out.println("If you don't want to save this product, and exit to main menu, press Z,");
				
				String choises = sc.nextLine();
				choises = choises.toLowerCase();
				
				if(choises.equals("y")) {
					checkValidInfo = false;
					
					double doubleCost = Double.parseDouble(cost);
					double doubleRrp = Double.parseDouble(rrp);
					
					Product p = new Product();
					
					p.setProductName(productName);
					p.setDescription(description);
					p.setCost(doubleCost);
					p.setRrp(doubleRrp);
					
					pr.insertProduct(p, listOfCategoryIds);
					
					System.out.println();
				} else if(choises.equals("a")) {
					checkValidInfo = true;
					
					double doubleCost = Double.parseDouble(cost);
					double doubleRrp = Double.parseDouble(rrp);
					
					Product p = new Product();
					
					p.setProductName(productName);
					p.setDescription(description);
					p.setCost(doubleCost);
					p.setRrp(doubleRrp);
					
					pr.insertProduct(p, listOfCategoryIds);
					
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
	
	public static void updateProduct(Scanner sc, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = false;
		Product productToBeUpdated = null;
		String strProductId = "";
		String WhatToChange = "";
		String productName = "";
		String description = "";
		String cost = "";
		String rrp = "";
		String categories = "";
		String keyboardChoise = "";
		List<Integer> productsAvailable;
		List<String> categoriesToAdd;
		Set<Integer> addCategoriesWithoutDoubles;
		
		do{
			checkValidInfo = false;
			int counter = 0;
			productsAvailable = new ArrayList<>();
			
			for(Product p : pr.getAllProducts()) {
				if(pr.getFilmOnProductId(p.getProductId()).size() == 0 && pr.getBookOnProductId(p.getProductId()).size() == 0) {
					productsAvailable.add(p.getProductId());
					counter++;
					String tempString = "";
					tempString += counter + ".)\tProductId: " + p.getProductId() + "\n";
					tempString += "\tProduct name: " + p.getProductName() + "\n";
					tempString += "\tDescription: " + p.getDescription() + "\n";
					tempString += "\tCost: " + p.getCost() + "\n";
					tempString += "\tRrp: " + p.getRrp() + "\n";
					
					tempString += "\tCategories: ";
					
					for(Category c : cr.getCategoriesFromProductId(p.getProductId())) {
						tempString += c.getCategoryName() + ", ";
					}
					
					tempString = tempString.substring(0, tempString.length() - 2);
					tempString += "\n";
					
					System.out.println(tempString);
				}
			}
			
			do{
				checkValidInfo = false;
				boolean available = false;
				
				System.out.println("Choose the product you want to update (write productId):");
				
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
						System.out.println("You haven't choosen a product that is available in the repository");
						System.out.println();
					} else {
						productToBeUpdated = pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0);
					}
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				System.out.println("This is the product you wanted to change:");
				System.out.print(productToBeUpdated);
				
				String showCategories = "";
				
				for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
					showCategories += c.getCategoryName() + ", ";
				}
				
				showCategories = showCategories.substring(0, showCategories.length() - 2);
				
				System.out.println("categories: " + showCategories);
				System.out.println();
				
				System.out.println("What do you want to change (Write 1 - 5):");
				System.out.println("1 = Product name");
				System.out.println("2 = Description");
				System.out.println("3 = Cost");
				System.out.println("4 = Rrp");
				System.out.println("5 = Categories");
				
				System.out.println();
				WhatToChange = sc.nextLine();
				System.out.println();
				if(WhatToChange.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted a choise yet");
				} else if(!Check.isNumeric(WhatToChange)) {
					checkValidInfo = true;
					System.out.println("You haven't inserted a numeric value for your choise");
				} else if(Integer.parseInt(WhatToChange) < 1 || Integer.parseInt(WhatToChange) > 5) {
					checkValidInfo = true;
					System.out.println("You haven't entered a number between 1 - 5");
				} else if(WhatToChange.equals("1")) {
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new product name:");
							
							productName = sc.nextLine();
							
							if(productName.equals("")) {
								checkValidInfo = true;
								System.out.println("You haven't inserted any product name yet");
								System.out.println();
							} else if(Check.isNumeric(productName)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric product name");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						
						productToBeUpdated.setProductName(productName);
						pr.updateProduct(productToBeUpdated, emptyCategoryList);
						
						System.out.println("You have changed the product name. Your product look like this now:");
						System.out.print(pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println("Categories: " + tempString);
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
						
						productToBeUpdated.setDescription(description);
						pr.updateProduct(productToBeUpdated, emptyCategoryList);
						
						System.out.println("You have changed the description. Your product look like this now:");
						System.out.print(pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println("Categories: " + tempString);
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
						
						productToBeUpdated.setCost(Double.parseDouble(cost));
						pr.updateProduct(productToBeUpdated, emptyCategoryList);
						
						System.out.println("You have changed the cost. Your product look like this now:");
						System.out.print(pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println("Categories: " + tempString);
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
						
						productToBeUpdated.setRrp(Double.parseDouble(rrp));
						pr.updateProduct(productToBeUpdated, emptyCategoryList);
						
						System.out.println("You have changed the rrp. Your product look like this now:");
						System.out.print(pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println("Categories: " + tempString);
					} while(checkValidInfo);
				} else if(WhatToChange.equals("5")) {
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
					
					pr.updateProduct(pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0), addCategoriesWithoutDoubles);
					
					System.out.println();
					System.out.println("You have changed the categories. Your product look like this now:");
					System.out.print(pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0));
					
					String tempString = "";
					
					for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
						tempString += c.getCategoryName() + ", ";
					}
					
					tempString = tempString.substring(0, tempString.length() - 2);
					
					System.out.println("Categories: " + tempString);
				}
				
				System.out.println();
				
				do{
					checkValidInfo = false;
					
					System.out.println("Do you want to change something else in this product, press Y.");
					System.out.println("Do you want to change another product, press X");
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
	
	public static void deleteProduct(Scanner sc, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = false;
		String keyboardChoise = "";
		String strProductId = "";
		List<Integer> productsAvailable;
		
		do{
			checkValidInfo = false;
			productsAvailable = new ArrayList<>();
			
			for(Product p : pr.getAllProducts()) {
				if(pr.getFilmOnProductId(p.getProductId()).size() == 0 && pr.getBookOnProductId(p.getProductId()).size() == 0) {
					productsAvailable.add(p.getProductId());
					System.out.print(p);
					String categoriesTemp = "";
					for(Category c : cr.getCategoriesFromProductId(p.getProductId())) {
						categoriesTemp += c.getCategoryName() + ", ";
					}
					categoriesTemp = "categories: " + categoriesTemp.substring(0, categoriesTemp.length() - 2);
					System.out.println(categoriesTemp);
					System.out.println();
				}
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("What product do you want to delete? (write productId):");
				strProductId = sc.nextLine();
				strProductId = strProductId.trim();
				boolean available = false;
				
				System.out.println();
				
				if(strProductId.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted productId yet");
					System.out.println();
				} else if(!Check.isNumeric(strProductId)) {
					checkValidInfo = true;
					System.out.println("You haven't inserted a numeric value for productId");
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
						System.out.println("The product isn't in the repository");
						System.out.println();
					} else {
						System.out.println("Now the product is deleted");
						Product productToDelete = pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0);
						pr.deleteProduct(productToDelete);
					}
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Do you want to delete another product, press X");
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
	
	public static void getProductsOnCategoryName(Scanner sc, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = false;
		String categoryName = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			for(Category c : cr.getAllCategories()) {
				System.out.println("CategoryId: " + c.getCategoryId() + " Category name: " + c.getCategoryName());
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				
				System.out.println("Choose category to get the list of products under it (write category name):");
			
				categoryName = sc.nextLine();
				
				if(categoryName.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any category name yet");
				} else if(Check.isNumeric(categoryName)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't use a numeric value as category name");
				} else if(cr.getCategoryOnCategoryName(categoryName).size() == 0) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't used a category name available in the repository");
				} else {
					System.out.println();
					
					if(pr.getProductsFromCategoryName(categoryName).size() > 0) {
						for(Product p : pr.getProductsFromCategoryName(categoryName)) {
							System.out.println(p);
						}
					} else {
						System.out.println("There is no products under this category at the moment");
						System.out.println();
					}
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("If you want to see more of which products are under a specific category name, press Y");
				System.out.println("If you want to quit to main menu, press Q");
				
				keyboardChoise = sc.nextLine().trim().toLowerCase();
				
				if(keyboardChoise.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted a value yet");
					System.out.println();
				} else if(Check.isNumeric(keyboardChoise)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You have inserted a numeric value, press Y or Q");
					System.out.println();
				} else if(!keyboardChoise.equals("y") && !keyboardChoise.equals("q")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You didn't insert Y or Q");
					System.out.println();
				}
			} while(checkValidInfo);
			
			if(keyboardChoise.equals("y")) {
				checkValidInfo = true;
				System.out.println();
			} else if(keyboardChoise.equals("q")) {
				checkValidInfo = false;
				System.out.println();
			}
			
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
	
	public static void getProductOnProductName(Scanner sc, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = false;
		String productName = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert the product name:");
				
				productName = sc.nextLine().trim();
				
				if(productName.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any product name yet");
					System.out.println();
				} else if(Check.isNumeric(productName)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't insert a numeric value for a product name");
					System.out.println();
				} else {
					System.out.println();
					
					if(pr.getProductFromProductName(productName).size() > 0) {
						for(Product p : pr.getProductFromProductName(productName)) {
							System.out.println(p);
						}
					} else {
						System.out.println("There isn't any product in the repository with that name");
					}
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("If you want to see another product, press Y");
				System.out.println("If you want to quit to main menu, press Q");
				
				keyboardChoise = sc.nextLine().trim().toLowerCase();
				
				if(keyboardChoise.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(Check.isNumeric(keyboardChoise)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You have inserted a numeric value, press Y or Q");
					System.out.println();
				} else if(!keyboardChoise.equals("y") && !keyboardChoise.equals("q")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted Y or Q");
					System.out.println();
				}
			} while(checkValidInfo);
			
			if(keyboardChoise.equals("y")) {
				checkValidInfo = true;
				System.out.println();
			} else if(keyboardChoise.equals("q")) {
				checkValidInfo = false;
				System.out.println();
			}
			
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
}