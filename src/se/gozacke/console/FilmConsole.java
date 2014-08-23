package se.gozacke.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import se.gozacke.actor.Actor;
import se.gozacke.actor.ActorRepository;
import se.gozacke.author.AuthorRepository;
import se.gozacke.category.Category;
import se.gozacke.category.CategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.product.Film;
import se.gozacke.product.ProductRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.UserRepository;

public class FilmConsole {
	public static void insertFilm(Scanner sc, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String productName = "";
		String description = "";
		String cost = "";
		String rrp = "";
		String rating = "";
		String agelimit = "";
		String release = "";
		String actorsChoise = "";
		String categoryChoise = "";
		
		List<String> categoriesToAdd;
		Set<Integer> addCategoriesWithoutDoubles;
		
		List<String> actorsToAdd;
		Set<Integer> addActorsWithoutDoubles;
		
		Set<Integer> listOfCategoryIds;
		
		do{
			do{
				checkValidInfo = false;
				
				System.out.println("Insert film name:");
				productName = sc.nextLine();
				System.out.println();
				
				if(Check.isNumeric(productName)) {
					checkValidInfo = true;
					System.out.println("You can't have a numeric film name");
					System.out.println();
				} else if(productName.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted any film name yet");
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
					System.out.println("You can't have a numeric film description");
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
				
				System.out.println("Insert rating:");
				rating = sc.nextLine();
				System.out.println();
				
				if(!Check.isNumeric(rating)) {
					checkValidInfo = true;
					System.out.println("You haven't used a numeric value for rating");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert agelimit:");
				agelimit = sc.nextLine();
				System.out.println();
				
				if(!Check.isNumeric(agelimit)) {
					checkValidInfo = true;
					System.out.println("You haven't used a numeric value for agelimit");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert release year:");
				release = sc.nextLine();
				System.out.println();
				
				if(!Check.isNumeric(release)) {
					checkValidInfo = true;
					System.out.println("You haven't used a numeric value for release year");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				for(Actor a : acr.getAllActors()) {
					System.out.println("ActorId: " + a.getActorId());
					System.out.println("Actor name: " + a.getFirstName() + " " + a.getSurName());
					System.out.println();
				}
				
				System.out.println("Insert actors in the film (with actorId):");
				actorsChoise = sc.nextLine();
				
				actorsToAdd = Arrays.asList(actorsChoise.split(","));
				addActorsWithoutDoubles = new TreeSet<>();
				
				for(String s : actorsToAdd) {
					if(!Check.isNumeric(s.trim())) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You have inserted invalid actorId");
						break;
					} else if(acr.getActorOnActorId(Integer.parseInt(s.trim())).size() == 0) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You have inserted an actorId that is not in the repository");
						break;
					}
					addActorsWithoutDoubles.add(Integer.parseInt(s.trim()));
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
						
						System.out.println("Insert the categories you want the film to be associated with:");
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
				
				actorsChoise = "";
				
				for(int i : addActorsWithoutDoubles) {
					actorsChoise += acr.getActorOnActorId(i).get(0).getFirstName() + " " + acr.getActorOnActorId(i).get(0).getSurName() + ", ";
				}
				
				actorsChoise = actorsChoise.substring(0, actorsChoise.length() - 2);
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("You have inserted following values to your new film:");
				System.out.println("Product name: " + productName);
				System.out.println("Description: " + description);
				System.out.println("Cost: " + cost);
				System.out.println("Rrp: " + rrp);
				System.out.println("Rating: " + rating);
				System.out.println("Agelimit: " + agelimit);
				System.out.println("Release: " + release);
				System.out.println("Actors: " + actorsChoise);
				System.out.println("Categories: " + categoryChoise);
				
				System.out.println();
				
				System.out.println("If you want to save this film, and exit to main menu, press Y.");
				System.out.println("If you want to save this film, and make another, press A.");
				System.out.println("If you don't want to save this film, but make another, press X,");
				System.out.println("If you don't want to save this film, and exit to main menu, press Z,");
				
				String choises = sc.nextLine();
				choises = choises.toLowerCase();
				
				if(choises.equals("y")) {
					checkValidInfo = false;
					
					double doubleCost = Double.parseDouble(cost);
					double doubleRrp = Double.parseDouble(rrp);
					int intRating = Integer.parseInt(rating);
					int intAgelimit = Integer.parseInt(agelimit);
					
					Film f = new Film();
					
					f.setProductName(productName);
					f.setDescription(description);
					f.setCost(doubleCost);
					f.setRrp(doubleRrp);
					f.setRating(intRating);
					f.setAgelimit(intAgelimit);
					f.setReleaseYear(release);
					
					pr.insertFilm(f, listOfCategoryIds, addActorsWithoutDoubles);
					
					System.out.println();
				} else if(choises.equals("a")) {
					checkValidInfo = true;
					
					double doubleCost = Double.parseDouble(cost);
					double doubleRrp = Double.parseDouble(rrp);
					int intRating = Integer.parseInt(rating);
					int intAgelimit = Integer.parseInt(agelimit);
					
					Film f = new Film();
					
					f.setProductName(productName);
					f.setDescription(description);
					f.setCost(doubleCost);
					f.setRrp(doubleRrp);
					f.setRating(intRating);
					f.setAgelimit(intAgelimit);
					f.setReleaseYear(release);
					
					pr.insertFilm(f, listOfCategoryIds, addActorsWithoutDoubles);
					
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
	
	public static void updateFilm(Scanner sc, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = false;
		Film productToBeUpdated = null;
		String strProductId = "";
		String WhatToChange = "";
		String productName = "";
		String description = "";
		String cost = "";
		String rrp = "";
		String rating = "";
		String agelimit = "";
		String release = "";
		String categories = "";
		String actors = "";
		String keyboardChoise = "";
		List<String> actorsToAdd;
		List<Integer> productsAvailable;
		List<String> categoriesToAdd;
		Set<Integer> addCategoriesWithoutDoubles;
		Set<Integer> addActorsWithoutDoubles;
		
		do{
			checkValidInfo = false;
			int counter = 0;
			productsAvailable = new ArrayList<>();
			
			for(Film f : pr.getAllFilms()) {
				productsAvailable.add(f.getProductId());
				counter++;
				String tempString = "";
				tempString += counter + ".)\tProductId: " + f.getProductId() + "\n";
				tempString += "\tProduct name: " + f.getProductName() + "\n";
				tempString += "\tDescription: " + f.getDescription() + "\n";
				tempString += "\tCost: " + f.getCost() + "\n";
				tempString += "\tRrp: " + f.getRrp() + "\n";
				tempString += "\tRating: " + f.getRating() + "\n";
				tempString += "\tAgelimit: " + f.getAgelimit() + "\n";
				tempString += "\tRelease: " + f.getReleaseYear() + "\n";
				
				tempString += "\tActors: ";
				
				for(int a : acr.getActorsOnProductId(f.getProductId())) {
					Actor ac = acr.getActorOnActorId(a).get(0);
					
					tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
				}
				
				tempString = tempString.substring(0, tempString.length() - 2);
				
				tempString += "\n";
				tempString += "\tCategories: ";
				
				for(Category c : cr.getCategoriesFromProductId(f.getProductId())) {
					tempString += c.getCategoryName() + ", ";
				}
				
				tempString = tempString.substring(0, tempString.length() - 2);
				tempString += "\n";
				
				System.out.println(tempString);
			}
			
			do{
				checkValidInfo = false;
				boolean available = false;
				
				System.out.println("Choose the film you want to update (write productId):");
				
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
						System.out.println("You haven't choosen a film that is available in the repository");
						System.out.println();
					} else {
						productToBeUpdated = pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0);
					}
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				System.out.println("This is the film you wanted to change:");
				System.out.print(productToBeUpdated);
				
				String showActors = "";
				
				for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
					Actor ac = acr.getActorOnActorId(a).get(0);
					showActors += ac.getFirstName() + " " + ac.getSurName() + ", ";
				}
				
				showActors = showActors.substring(0, showActors.length() - 2);
				
				System.out.println("actors: " + showActors);
				
				String showCategories = "";
				
				for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
					showCategories += c.getCategoryName() + ", ";
				}
				
				showCategories = showCategories.substring(0, showCategories.length() - 2);
				
				System.out.println("categories: " + showCategories);
				System.out.println();
				
				do{
					checkValidInfo = false;
					
					System.out.println("What do you want to change (Write 1 - 9):");
					System.out.println("1 = Film name");
					System.out.println("2 = Description");
					System.out.println("3 = Cost");
					System.out.println("4 = Rrp");
					System.out.println("5 = Rating");
					System.out.println("6 = Agelimit");
					System.out.println("7 = Release");
					System.out.println("8 = Actors");
					System.out.println("9 = Categories");
					
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
					} else if(Integer.parseInt(WhatToChange) < 1 || Integer.parseInt(WhatToChange) > 9) {
						checkValidInfo = true;
						System.out.println("You haven't entered a number between 1 - 9");
						System.out.println();
					}
				} while(checkValidInfo);
				
				if(WhatToChange.equals("1")) {
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new film name:");
							
							productName = sc.nextLine();
							
							if(productName.equals("")) {
								checkValidInfo = true;
								System.out.println("You haven't inserted any film name yet");
								System.out.println();
							} else if(Check.isNumeric(productName)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric film name");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyActorList = new TreeSet<>();
						
						productToBeUpdated.setProductName(productName);
						pr.updateFilm(productToBeUpdated, emptyCategoryList, emptyActorList);
						
						System.out.println("You have changed the film name. Your film look like this now:");
						System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "actors: ";
						
						for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
							Actor ac = acr.getActorOnActorId(a).get(0);
							tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
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
						Set<Integer> emptyActorList = new TreeSet<>();
						
						productToBeUpdated.setDescription(description);
						pr.updateFilm(productToBeUpdated, emptyCategoryList, emptyActorList);
						
						
						System.out.println("You have changed the description. Your film look like this now:");
						System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "actors: ";
						
						for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
							Actor ac = acr.getActorOnActorId(a).get(0);
							tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
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
						Set<Integer> emptyActorList = new TreeSet<>();
						
						productToBeUpdated.setCost(Double.parseDouble(cost));
						pr.updateFilm(productToBeUpdated, emptyCategoryList, emptyActorList);
						
						System.out.println("You have changed the cost. Your film look like this now:");
						System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "actors: ";
						
						for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
							Actor ac = acr.getActorOnActorId(a).get(0);
							tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
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
						Set<Integer> emptyActorList = new TreeSet<>();
						
						productToBeUpdated.setRrp(Double.parseDouble(rrp));
						pr.updateFilm(productToBeUpdated, emptyCategoryList, emptyActorList);
						
						System.out.println("You have changed the rrp. Your film look like this now:");
						System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "actors: ";
						
						for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
							Actor ac = acr.getActorOnActorId(a).get(0);
							tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
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
							
							System.out.println("Enter new rating:");
							
							rating = sc.nextLine();
							
							if(!Check.isNumeric(rating)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You must insert a numeric value for rating");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyActorList = new TreeSet<>();
						
						productToBeUpdated.setRating(Integer.parseInt(rating));
						pr.updateFilm(productToBeUpdated, emptyCategoryList, emptyActorList);
						
						System.out.println("You have changed the rating. Your film look like this now:");
						System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "actors: ";
						
						for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
							Actor ac = acr.getActorOnActorId(a).get(0);
							tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
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
							
							System.out.println("Enter new agelimit:");
							
							agelimit = sc.nextLine();
							
							if(!Check.isNumeric(agelimit)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You must insert a numeric value for agelimit");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyActorList = new TreeSet<>();
						
						productToBeUpdated.setAgelimit(Integer.parseInt(agelimit));
						pr.updateFilm(productToBeUpdated, emptyCategoryList, emptyActorList);
						
						System.out.println("You have changed the agelimit. Your film look like this now:");
						System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "actors: ";
						
						for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
							Actor ac = acr.getActorOnActorId(a).get(0);
							tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
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
					do{
						checkValidInfo = false;
						
						do{
							checkValidInfo = false;
							
							System.out.println("Enter new release:");
							
							release = sc.nextLine();
							
							if(release.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any release year yet");
								System.out.println();
							} else if(!Check.isNumeric(release)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You must insert a numeric value for release year");
								System.out.println();
							}
						} while(checkValidInfo);
						
						System.out.println();
						
						Set<Integer> emptyCategoryList = new TreeSet<>();
						Set<Integer> emptyActorList = new TreeSet<>();
						
						productToBeUpdated.setReleaseYear(release);
						pr.updateFilm(productToBeUpdated, emptyCategoryList, emptyActorList);
						
						System.out.println("You have changed the release year. Your film look like this now:");
						System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
						
						String tempString = "actors: ";
						
						for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
							Actor ac = acr.getActorOnActorId(a).get(0);
							tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2) + "\n";
						tempString += "categories: ";
						
						for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
							tempString += c.getCategoryName() + ", ";
						}
						
						tempString = tempString.substring(0, tempString.length() - 2);
						
						System.out.println(tempString);
					} while(checkValidInfo);
				} else if(WhatToChange.equals("8")) {
					counter = 0;
					
					for(Actor a : acr.getAllActors()) {
						counter++;
						System.out.println(counter + ".) " + a.getFirstName() + " " + a.getSurName());
					}
					
					do{
						checkValidInfo = false;
						
						System.out.println();
						System.out.println("Enter which actors you want to use:");
						actors = sc.nextLine();
						
						actorsToAdd = Arrays.asList(actors.split(","));
						addActorsWithoutDoubles = new TreeSet<>();
						
						for(String s : actorsToAdd) {
							if(!Check.isNumeric(s)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You have inserted invalid actor");
							} else if(Integer.parseInt(s.trim()) > acr.getAllActors().size() || Integer.parseInt(s.trim()) < 1) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You have inserted out of bounds actors");
							} else {
								addActorsWithoutDoubles.add(acr.getAllActors().get(Integer.parseInt(s.trim()) - 1).getActorId());
							}
						}
					} while(checkValidInfo);
					Set<Integer> emptyCategoryList = new TreeSet<>();
					
					pr.updateFilm(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0) ,emptyCategoryList , addActorsWithoutDoubles);
					
					System.out.println();
					System.out.println("You have changed the actors. Your film look like this now:");
					System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
					
					String tempString = "actors: ";
					
					for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
						Actor ac = acr.getActorOnActorId(a).get(0);
						tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
					}
					
					tempString = tempString.substring(0, tempString.length() - 2) + "\n";
					tempString += "categories: ";
					
					for(Category c : cr.getCategoriesFromProductId(Integer.parseInt(strProductId))) {
						tempString += c.getCategoryName() + ", ";
					}
					
					tempString = tempString.substring(0, tempString.length() - 2);
					
					System.out.println(tempString);
				} else if(WhatToChange.equals("9")) {
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
					Set<Integer> emptyActorList = new TreeSet<>();
					
					pr.updateFilm(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0), addCategoriesWithoutDoubles, emptyActorList);
					
					System.out.println();
					System.out.println("You have changed the categories. Your film look like this now:");
					System.out.print(pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0));
					
					String tempString = "actors: ";
					
					for(int a : acr.getActorsOnProductId(Integer.parseInt(strProductId))) {
						Actor ac = acr.getActorOnActorId(a).get(0);
						tempString += ac.getFirstName() + " " + ac.getSurName() + ", ";
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
					
					System.out.println("Do you want to change something else in this film, press Y.");
					System.out.println("Do you want to change another film, press X");
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
	
	public static void deleteFilm(Scanner sc, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = false;
		String keyboardChoise = "";
		String strProductId = "";
		
		do{
			checkValidInfo = false;
			
			for(Film f : pr.getAllFilms()) {
				System.out.print(f);
				String categoriesTemp = "";
				for(Category c : cr.getCategoriesFromProductId(f.getProductId())) {
					categoriesTemp += c.getCategoryName() + ", ";
				}
				categoriesTemp = "categories: " + categoriesTemp.substring(0, categoriesTemp.length() - 2);
				
				categoriesTemp += "\nactors: ";
				
				for(int i : acr.getActorsOnProductId(f.getProductId())) {
					categoriesTemp += acr.getActorOnActorId(i).get(0).getFirstName() + " " + acr.getActorOnActorId(i).get(0).getSurName() + ", ";
				}
				
				categoriesTemp = categoriesTemp.substring(0, categoriesTemp.length() - 2);
				
				System.out.println(categoriesTemp);
				System.out.println();
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("What film do you want to delete? (write productId):");
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
				} else if(pr.getFilmOnProductId(Integer.parseInt(strProductId)).size() == 0) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("The film isn't in the repository");
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Now the film is deleted");
					Film filmToDelete = pr.getFilmOnProductId(Integer.parseInt(strProductId)).get(0);
					pr.deleteFilm(filmToDelete);
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Do you want to delete another film, press X");
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