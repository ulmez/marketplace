package se.gozacke.console;

import java.util.Scanner;

import se.gozacke.actor.ActorRepository;
import se.gozacke.author.AuthorRepository;
import se.gozacke.category.CategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.product.ProductRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.UserRepository;

public class MainConsole {
	public static void mainWindow(ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		Scanner sc = new Scanner(System.in);
		String insertedChoise = "";
		boolean checkValidInfo = true;
		
		do {
			checkValidInfo = false;
			
			System.out.println("Choose one of the following options (write 1 - 7):");
			System.out.println("1 = Insert product, category or user");
			System.out.println("2 = Update product, category or user");
			System.out.println("3 = Delete product, category or user");
			System.out.println("4 = Test login for a user");
			System.out.println("5 = Get list of products in a category");
			System.out.println("6 = Search a product by name");
			System.out.println("7 = Quit");
			
			do {
				checkValidInfo = false;
				
				insertedChoise = sc.nextLine();
				
				if(insertedChoise.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(!Check.isNumeric(insertedChoise)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted a numeric value as choise");
					System.out.println();
				} else if(Integer.parseInt(insertedChoise) < 1 || Integer.parseInt(insertedChoise) > 7) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't made a choise between 1 - 7");
					System.out.println();
				} else if(insertedChoise.equals("1")) {
					System.out.println();
					
					System.out.println("What do you like to insert (write 1 - 3):");
					System.out.println("1 = Product");
					System.out.println("2 = Category");
					System.out.println("3 = User");
					
					do{
						checkValidInfo = false;
						
						insertedChoise = sc.nextLine();
						
						if(insertedChoise.equals("")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted any value yet");
							System.out.println();
						} else if(!Check.isNumeric(insertedChoise)) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted a numeric value as choise");
							System.out.println();
						} else if(Integer.parseInt(insertedChoise) < 1 || Integer.parseInt(insertedChoise) > 3) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't made a choise between 1 - 3");
							System.out.println();
						} else if(insertedChoise.equals("1")) {
							System.out.println();
							System.out.println("What type of product do you like to insert:");
							System.out.println("1 = Main article");
							System.out.println("2 = Film");
							System.out.println("3 = Book");
							System.out.println("4 = Actor");
							System.out.println("5 = Author");
							
							do{
								checkValidInfo = false;
								
								insertedChoise = sc.nextLine();
								
								if(insertedChoise.equals("")) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted any value yet");
									System.out.println();
								} else if(!Check.isNumeric(insertedChoise)) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted a numeric value as choise");
									System.out.println();
								} else if(Integer.parseInt(insertedChoise) < 1 || Integer.parseInt(insertedChoise) > 5) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't made a choise between 1 - 5");
									System.out.println();
								} else if(insertedChoise.equals("1")) {
									System.out.println();
									ProductConsole.insertProduct(sc, pr, cr, acr, aur, sr, ur);
								} else if(insertedChoise.equals("2")) {
									System.out.println();
									FilmConsole.insertFilm(sc, pr, cr, acr, aur, sr, ur);
								} else if(insertedChoise.equals("3")) {
									System.out.println();
									BookConsole.insertBook(sc, pr, cr, aur, acr, sr, ur);
								} else if(insertedChoise.equals("4")) {
									System.out.println();
									ActorConsole.insertActor(sc, acr, pr, cr, aur, sr, ur);
								} else if(insertedChoise.equals("5")) {
									System.out.println();
									AuthorConsole.insertAuthor(sc, aur, acr, pr, cr, sr, ur);
								}
							} while(checkValidInfo);
						} else if(insertedChoise.equals("2")) {
							System.out.println();
							CategoryConsole.insertCategory(sc, cr, sr, pr, acr, aur, ur);
						} else if(insertedChoise.equals("3")) {
							System.out.println();
							UserConsole.insertUser(sc, ur, pr, cr, acr, aur, sr);
						}
					} while(checkValidInfo);
				} else if(insertedChoise.equals("2")) {
					System.out.println();
					
					System.out.println("What do you like to update (write 1 - 3):");
					System.out.println("1 = Product");
					System.out.println("2 = Category");
					System.out.println("3 = User");
					
					do{
						checkValidInfo = false;
						
						insertedChoise = sc.nextLine();
						
						if(insertedChoise.equals("")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted any value yet");
							System.out.println();
						} else if(!Check.isNumeric(insertedChoise)) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted a numeric value as choise");
							System.out.println();
						} else if(Integer.parseInt(insertedChoise) < 1 || Integer.parseInt(insertedChoise) > 3) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't made a choise between 1 - 3");
							System.out.println();
						} else if(insertedChoise.equals("1")) {
							System.out.println();
							System.out.println("What type of product do you like to update:");
							System.out.println("1 = Main article");
							System.out.println("2 = Film");
							System.out.println("3 = Book");
							System.out.println("4 = Actor");
							System.out.println("5 = Author");
							
							do{
								checkValidInfo = false;
								
								insertedChoise = sc.nextLine();
								
								if(insertedChoise.equals("")) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted any value yet");
									System.out.println();
								} else if(!Check.isNumeric(insertedChoise)) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted a numeric value as choise");
									System.out.println();
								} else if(Integer.parseInt(insertedChoise) < 1 || Integer.parseInt(insertedChoise) > 5) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't made a choise between 1 - 5");
									System.out.println();
								} else if(insertedChoise.equals("1")) {
									System.out.println();
									ProductConsole.updateProduct(sc, pr, cr, acr, aur, sr, ur);
								} else if(insertedChoise.equals("2")) {
									System.out.println();
									FilmConsole.updateFilm(sc, pr, cr, acr, aur, sr, ur);
								} else if(insertedChoise.equals("3")) {
									System.out.println();
									BookConsole.updateBook(sc, pr, cr, aur, acr, sr, ur);
								} else if(insertedChoise.equals("4")) {
									System.out.println();
									ActorConsole.updateActor(sc, acr, pr, cr, aur, sr, ur);
								} else if(insertedChoise.equals("5")) {
									System.out.println();
									AuthorConsole.updateAuthor(sc, aur, acr, pr, cr, sr, ur);
								}
							} while(checkValidInfo);
						} else if(insertedChoise.equals("2")) {
							System.out.println();
							CategoryConsole.updateCategory(sc, cr, sr, pr, acr, aur, ur);
						} else if(insertedChoise.equals("3")) {
							System.out.println();
							UserConsole.updateUser(sc, ur, pr, cr, acr, aur, sr);
						}
					} while(checkValidInfo);
				} else if(insertedChoise.equals("3")) {
					System.out.println();
					
					System.out.println("What do you like to delete (write 1 - 3):");
					System.out.println("1 = Product");
					System.out.println("2 = Category");
					System.out.println("3 = User");
					
					do{
						checkValidInfo = false;
						
						insertedChoise = sc.nextLine();
						
						if(insertedChoise.equals("")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted any value yet");
							System.out.println();
						} else if(!Check.isNumeric(insertedChoise)) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted a numeric value as choise");
							System.out.println();
						} else if(Integer.parseInt(insertedChoise) < 1 || Integer.parseInt(insertedChoise) > 3) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't made a choise between 1 - 3");
							System.out.println();
						} else if(insertedChoise.equals("1")) {
							System.out.println();
							System.out.println("What type of product do you like to delete:");
							System.out.println("1 = Main article");
							System.out.println("2 = Film");
							System.out.println("3 = Book");
							System.out.println("4 = Actor");
							System.out.println("5 = Author");
							
							do{
								checkValidInfo = false;
								
								insertedChoise = sc.nextLine();
								
								if(insertedChoise.equals("")) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted any value yet");
									System.out.println();
								} else if(!Check.isNumeric(insertedChoise)) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted a numeric value as choise");
									System.out.println();
								} else if(Integer.parseInt(insertedChoise) < 1 || Integer.parseInt(insertedChoise) > 5) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't made a choise between 1 - 5");
									System.out.println();
								} else if(insertedChoise.equals("1")) {
									System.out.println();
									ProductConsole.deleteProduct(sc, pr, cr, acr, aur, sr, ur);
								} else if(insertedChoise.equals("2")) {
									System.out.println();
									FilmConsole.deleteFilm(sc, pr, cr, acr, aur, sr, ur);
								} else if(insertedChoise.equals("3")) {
									System.out.println();
									BookConsole.deleteBook(sc, pr, cr, aur, acr, sr, ur);
								} else if(insertedChoise.equals("4")) {
									System.out.println();
									ActorConsole.deleteActor(sc, acr, pr, cr, aur, sr, ur);
								} else if(insertedChoise.equals("5")) {
									System.out.println();
									AuthorConsole.deleteAuthor(sc, aur, acr, pr, cr, sr, ur);
								}
							} while(checkValidInfo);
						} else if(insertedChoise.equals("2")) {
							System.out.println();
							CategoryConsole.deleteCategory(sc, cr, sr, pr, acr, aur, ur);
						} else if(insertedChoise.equals("3")) {
							System.out.println();
							UserConsole.deleteUser(sc, ur, pr, cr, acr, aur, sr);
						}
					} while(checkValidInfo);
				} else if(insertedChoise.equals("4")) {
					System.out.println();
					UserConsole.validateUser(sc, ur, pr, cr, acr, aur, sr);
				} else if(insertedChoise.equals("5")) {
					System.out.println();
					ProductConsole.getProductsOnCategoryName(sc, pr, cr, acr, aur, sr, ur);
				} else if(insertedChoise.equals("6")) {
					System.out.println();
					ProductConsole.getProductOnProductName(sc, pr, cr, acr, aur, sr, ur);
				}
			} while(checkValidInfo);
		} while(checkValidInfo);
		
		sc.close();
	}
}