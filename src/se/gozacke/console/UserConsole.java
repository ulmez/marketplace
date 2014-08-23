package se.gozacke.console;

import java.util.Scanner;

import se.gozacke.actor.ActorRepository;
import se.gozacke.author.AuthorRepository;
import se.gozacke.category.CategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.product.ProductRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.User;
import se.gozacke.user.UserRepository;

public class UserConsole {
	public static void insertUser(Scanner sc, UserRepository ur, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr) throws StorageException {
		boolean checkValidInfo = true;
		String email = "";
		String password = "";
		String firstName = "";
		String surName = "";
		String streetAddress = "";
		String postCode = "";
		String town = "";
		String telephone = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert email: ");
				
				email = sc.nextLine();
				
				if(email.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for email yet");
					System.out.println();
				} else if(Check.isNumeric(email)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't have a numeric value as email");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert password: ");
				
				password = sc.nextLine();
				
				if(password.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for password yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert firstname: ");
				
				firstName = sc.nextLine();
				
				if(firstName.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for firstname yet");
					System.out.println();
				} else if(Check.isNumeric(firstName)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't have a numeric value as firstname");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert surname: ");
				
				surName = sc.nextLine();
				
				if(surName.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for surname yet");
					System.out.println();
				} else if(Check.isNumeric(surName)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't have a numeric value as surname");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert street address: ");
				
				streetAddress = sc.nextLine();
				
				if(streetAddress.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for street address yet");
					System.out.println();
				} else if(Check.isNumeric(streetAddress)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't have a numeric value as street address");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert post code: ");
				
				postCode = sc.nextLine();
				
				if(postCode.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for post code yet");
					System.out.println();
				} else if(!Check.isNumeric(postCode)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You must insert a numeric value as post code");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert town: ");
				
				town = sc.nextLine();
				
				if(town.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for town yet");
					System.out.println();
				} else if(Check.isNumeric(town)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't have a numeric value as town");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert telephone: ");
				
				telephone = sc.nextLine();
				
				if(telephone.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for telephone yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			System.out.println("This is the values inserted for the new user:");
			System.out.println("Email: " + email);
			System.out.println("Password: " + password);
			System.out.println("firstname: " + firstName);
			System.out.println("surname: " + surName);
			System.out.println("Street address: " + streetAddress);
			System.out.println("Post code: " + postCode);
			System.out.println("Town: " + town);
			System.out.println("Telephone: " + telephone);
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				
				System.out.println("Do you want to insert this user, press Y");
				System.out.println("If you don't want to insert this user, press N");
				
				keyboardChoise = sc.nextLine().trim().toLowerCase();
				
				if(keyboardChoise.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
				} else if(Check.isNumeric(keyboardChoise)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You have inserted a numeric value, press Y or N");
				} else if(!keyboardChoise.equals("y") && !keyboardChoise.equals("n")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't pressed Y or N");
				} else if(keyboardChoise.equals("y")) {
					User userToInsert = new User();
					
					userToInsert.setEmail(email);
					userToInsert.setPassword(password);
					userToInsert.setFirstName(firstName);
					userToInsert.setSurName(surName);
					userToInsert.setStreetAddress(streetAddress);
					userToInsert.setPostCode(postCode);
					userToInsert.setTown(town);
					userToInsert.setTelephone(telephone);
					
					ur.insertUser(userToInsert);
					
					System.out.println();
					System.out.println("The user is now inserted");
				} else if(keyboardChoise.equals("n")) {
					System.out.println();
					System.out.println("The user is cancelled");
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				
				System.out.println("Do you want to make another user, press Y");
				System.out.println("Do you want to quit to main menu, press Q");
				
				keyboardChoise = sc.nextLine().trim().toLowerCase();
				
				if(keyboardChoise.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
				} else if(Check.isNumeric(keyboardChoise)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You have inserted a numeric value, press Y or Q");
				} else if(!keyboardChoise.equals("y") && !keyboardChoise.equals("q")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't pressed Y or Q");
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
	
	public static void updateUser(Scanner sc, UserRepository ur, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr) throws StorageException {
		boolean checkValidInfo = true;
		String strUserId = "";
		String email = "";
		String password = "";
		String firstName = "";
		String surName = "";
		String streetAddress = "";
		String postCode = "";
		String town = "";
		String telephone = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			for(User u : ur.getAllUsers()) {
				System.out.println(u);
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("Choose which user you want to update (write userId)");
				
				strUserId = sc.nextLine().trim();
				
				System.out.println();
				
				if(strUserId.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(!Check.isNumeric(strUserId)) {
					checkValidInfo = true;
					System.out.println("You must insert a numeric value for userId");
					System.out.println();
				} else if(ur.getUserOnUserId(Integer.parseInt(strUserId)).size() == 0) {
					checkValidInfo = true;
					System.out.println("The user isn't in the repository");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("What do you want to change (write 1 - 8):");
				System.out.println("1 = Email");
				System.out.println("2 = Password");
				System.out.println("3 = FirstName");
				System.out.println("4 = SurName");
				System.out.println("5 = Street address");
				System.out.println("6 = Post code");
				System.out.println("7 = Town");
				System.out.println("8 = Telephone");
				
				do{
					checkValidInfo = false;
					
					keyboardChoise = sc.nextLine().trim();
					
					if(keyboardChoise.equals("")) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't inserted any value yet");
						System.out.println();
					} else if(!Check.isNumeric(keyboardChoise)) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't used a numeric value, press 1 - 8");
						System.out.println();
					} else if(Integer.parseInt(keyboardChoise) < 1 || Integer.parseInt(keyboardChoise) > 8) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't inserted 1 - 8");
						System.out.println();
					}
				} while(checkValidInfo);
				
				do{
					checkValidInfo = false;
					
					if(keyboardChoise.equals("1")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Email:");
							
							email = sc.nextLine().trim();
							
							if(email.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for email yet");
								System.out.println();
							} else if(Check.isNumeric(email)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric email");
								System.out.println();
							} else {
								User userToUpdate = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
								
								userToUpdate.setEmail(email);
								
								ur.updateUser(userToUpdate);
								
								System.out.println();
								System.out.println("The user is updated and look like this now");
								
								System.out.println(ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("2")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Password:");
							
							password = sc.nextLine().trim();
							
							if(password.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for password yet");
								System.out.println();
							} else {
								User userToUpdate = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
								
								userToUpdate.setPassword(password);
								
								ur.updateUser(userToUpdate);
								
								System.out.println();
								System.out.println("The user is updated and look like this now");
								
								System.out.println(ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("3")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Firstname:");
							
							firstName = sc.nextLine().trim();
							
							if(firstName.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for firstname yet");
								System.out.println();
							} else if(Check.isNumeric(firstName)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric firstname");
								System.out.println();
							} else {
								User userToUpdate = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
								
								userToUpdate.setFirstName(firstName);
								
								ur.updateUser(userToUpdate);
								
								System.out.println();
								System.out.println("The user is updated and look like this now");
								
								System.out.println(ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("4")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Surname:");
							
							surName = sc.nextLine().trim();
							
							if(surName.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for surname yet");
								System.out.println();
							} else if(Check.isNumeric(surName)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric surname");
								System.out.println();
							} else {
								User userToUpdate = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
								
								userToUpdate.setSurName(surName);
								
								ur.updateUser(userToUpdate);
								
								System.out.println();
								System.out.println("The user is updated and look like this now");
								
								System.out.println(ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("5")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Street address:");
							
							streetAddress = sc.nextLine().trim();
							
							if(streetAddress.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for street address yet");
								System.out.println();
							} else if(Check.isNumeric(streetAddress)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric street address");
								System.out.println();
							} else {
								User userToUpdate = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
								
								userToUpdate.setStreetAddress(streetAddress);
								
								ur.updateUser(userToUpdate);
								
								System.out.println();
								System.out.println("The user is updated and look like this now");
								
								System.out.println(ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("6")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Post code:");
							
							postCode = sc.nextLine().trim();
							
							if(postCode.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for post code yet");
								System.out.println();
							} else if(!Check.isNumeric(postCode)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted a numeric value for post code");
								System.out.println();
							} else {
								User userToUpdate = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
								
								userToUpdate.setPostCode(postCode);
								
								ur.updateUser(userToUpdate);
								
								System.out.println();
								System.out.println("The user is updated and look like this now");
								
								System.out.println(ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("7")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Town:");
							
							town = sc.nextLine().trim();
							
							if(town.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for town yet");
								System.out.println();
							} else if(Check.isNumeric(town)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You can't have a numeric value as town");
								System.out.println();
							} else {
								User userToUpdate = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
								
								userToUpdate.setTown(town);
								
								ur.updateUser(userToUpdate);
								
								System.out.println();
								System.out.println("The user is updated and look like this now");
								
								System.out.println(ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("8")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Telephone:");
							
							telephone = sc.nextLine().trim();
							
							if(telephone.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for telephone yet");
								System.out.println();
							} else {
								User userToUpdate = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
								
								userToUpdate.setTelephone(telephone);
								
								ur.updateUser(userToUpdate);
								
								System.out.println();
								System.out.println("The user is updated and look like this now");
								
								System.out.println(ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0));
							}
						} while(checkValidInfo);
					}
					
					do{
						checkValidInfo = false;
						
						System.out.println("If you want to change something else on this user, press Y");
						System.out.println("If you want to change something else on another user, press X");
						System.out.println("If you don't want to change something else and quit to main menu, press Q");
						
						keyboardChoise = sc.nextLine().trim().toLowerCase();
						
						if(keyboardChoise.equals("")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted anything yet");
							System.out.println();
						} else if(Check.isNumeric(keyboardChoise)) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You inserted a numeric value, press Y, X or Q");
							System.out.println();
						} else if(!keyboardChoise.equals("y") && !keyboardChoise.equals("x") && !keyboardChoise.equals("q")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You didn't insert Y, X or Q");
							System.out.println();
						}
					} while(checkValidInfo);
				} while(checkValidInfo);
				
				if(keyboardChoise.equals("y")) {
					checkValidInfo = true;
					System.out.println();
				} else if(keyboardChoise.equals("x")) {
					checkValidInfo = true;
					System.out.println();
					break;
				} else if(keyboardChoise.equals("q")) {
					checkValidInfo = false;
					System.out.println();
				}
			} while(checkValidInfo);
			
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
	
	public static void deleteUser(Scanner sc, UserRepository ur, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr) throws StorageException {
		boolean checkValidInfo = true;
		String strUserId = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			for(User u : ur.getAllUsers()) {
				System.out.println(u);
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert the user you want to delete (write userId):");
				
				strUserId = sc.nextLine().trim();
				
				if(strUserId.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(!Check.isNumeric(strUserId)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't used a numeric value for userId");
					System.out.println();
				} else if(ur.getUserOnUserId(Integer.parseInt(strUserId)).size() == 0) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("Your choice of user isn't in the repository");
					System.out.println();
				} else {
					System.out.println();
					
					User userToDelete = ur.getUserOnUserId(Integer.parseInt(strUserId)).get(0);
					
					ur.deleteUser(userToDelete);
					
					System.out.println("Now the user is deleted");
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Do you want to delete another user, press Y");
				System.out.println("Do you want to quit and return to main menu, press Q");
				
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
					System.out.println("You didn't insert Y or Q");
					System.out.println();
				}
			} while(checkValidInfo);
			
			if(keyboardChoise.equals("y")) {
				checkValidInfo = true;
			} else if(keyboardChoise.equals("q")) {
				checkValidInfo = false;
				System.out.println();
			}
			
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
	
	public static void validateUser(Scanner sc, UserRepository ur, ProductRepository pr, CategoryRepository cr, ActorRepository acr, AuthorRepository aur, StaffRepository sr) throws StorageException {
		boolean checkValidInfo = true;
		String email = "";
		String password = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert username (email):");
				
				email = sc.nextLine();
				
				if(email.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any username yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert password:");
				
				password = sc.nextLine();
				
				if(password.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any password yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			if(ur.validateUsernameAndPassword(email, password)) {
				System.out.println();
				System.out.println("The user is valid");
				System.out.println();
			} else {
				System.out.println();
				System.out.println("The user isn't valid");
				System.out.println();
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("Do you want to test another user, press Y");
				System.out.println("Do you want to quit to main menu, press Q");
				
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
					System.out.println("You didn't press Y or Q");
					System.out.println();
				}
			} while(checkValidInfo);
			
			if(keyboardChoise.equals("y")) {
				checkValidInfo = true;
			} else if(keyboardChoise.equals("q")) {
				checkValidInfo = false;
				System.out.println();
			}
			
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
}