package se.gozacke.console;

import java.util.Scanner;

import se.gozacke.actor.Actor;
import se.gozacke.actor.ActorRepository;
import se.gozacke.author.AuthorRepository;
import se.gozacke.category.CategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.product.ProductRepository;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.UserRepository;

public class ActorConsole {
	public static void insertActor(Scanner sc, ActorRepository acr, ProductRepository pr, CategoryRepository cr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String firstName = "";
		String surName = "";
		String dob = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
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
				
				System.out.println("Insert dob: ");
				
				dob = sc.nextLine();
				
				if(dob.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value for dob yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			System.out.println("This is the values inserted for the new actor:");
			System.out.println("Firstname: " + firstName);
			System.out.println("Surname: " + surName);
			System.out.println("Dob: " + dob);
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				
				System.out.println("Do you want to insert this actor, press Y");
				System.out.println("If you don't want to insert this actor, press N");
				
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
					Actor actorToInsert = new Actor();
					
					actorToInsert.setFirstName(firstName);
					actorToInsert.setSurName(surName);
					actorToInsert.setDob(dob);
					
					acr.insertActor(actorToInsert);
					
					System.out.println();
					System.out.println("The actor is now inserted");
				} else if(keyboardChoise.equals("n")) {
					System.out.println();
					System.out.println("The actor is cancelled");
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				
				System.out.println("Do you want to make another actor, press Y");
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
	
	public static void updateActor(Scanner sc, ActorRepository acr, ProductRepository pr, CategoryRepository cr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String strActorId = "";
		String firstName = "";
		String surName = "";
		String dob = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			for(Actor a : acr.getAllActors()) {
				System.out.println(a);
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("Choose which actor you want to update (write actorId)");
				
				strActorId = sc.nextLine().trim();
				
				System.out.println();
				
				if(strActorId.equals("")) {
					checkValidInfo = true;
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(!Check.isNumeric(strActorId)) {
					checkValidInfo = true;
					System.out.println("You must insert a numeric value for actorId");
					System.out.println();
				} else if(acr.getActorOnActorId(Integer.parseInt(strActorId)).size() == 0) {
					checkValidInfo = true;
					System.out.println("The actor isn't in the repository");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("What do you want to change (write 1 - 3):");
				System.out.println("1 = FirstName");
				System.out.println("2 = SurName");
				System.out.println("3 = Dob");
				
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
						System.out.println("You haven't used a numeric value, press 1 - 3");
						System.out.println();
					} else if(Integer.parseInt(keyboardChoise) < 1 || Integer.parseInt(keyboardChoise) > 3) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't inserted 1 - 3");
						System.out.println();
					}
				} while(checkValidInfo);
				
				do{
					checkValidInfo = false;
					
					if(keyboardChoise.equals("1")) {
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
								Actor actorToUpdate = acr.getActorOnActorId(Integer.parseInt(strActorId)).get(0);
								
								actorToUpdate.setFirstName(firstName);
								
								acr.updateActor(actorToUpdate);
								
								System.out.println();
								System.out.println("The actor is updated and look like this now");
								
								System.out.println(acr.getActorOnActorId(Integer.parseInt(strActorId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("2")) {
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
								Actor actorToUpdate = acr.getActorOnActorId(Integer.parseInt(strActorId)).get(0);
								
								actorToUpdate.setSurName(surName);
								
								acr.updateActor(actorToUpdate);
								
								System.out.println();
								System.out.println("The actor is updated and look like this now");
								
								System.out.println(acr.getActorOnActorId(Integer.parseInt(strActorId)).get(0));
							}
						} while(checkValidInfo);
					} else if(keyboardChoise.equals("3")) {
						do{
							checkValidInfo = false;
							
							System.out.println();
							
							System.out.println("Dob:");
							
							dob = sc.nextLine().trim();
							
							if(dob.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value for dob yet");
								System.out.println();
							} else {
								Actor actorToUpdate = acr.getActorOnActorId(Integer.parseInt(strActorId)).get(0);
								
								actorToUpdate.setDob(dob);
								
								acr.updateActor(actorToUpdate);
								
								System.out.println();
								System.out.println("The actor is updated and look like this now");
								
								System.out.println(acr.getActorOnActorId(Integer.parseInt(strActorId)).get(0));
							}
						} while(checkValidInfo);
					}
					
					do{
						checkValidInfo = false;
						
						System.out.println("If you want to change something else on this actor, press Y");
						System.out.println("If you want to change something else on another actor, press X");
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
	
	public static void deleteActor(Scanner sc, ActorRepository acr, ProductRepository pr, CategoryRepository cr, AuthorRepository aur, StaffRepository sr, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String strActorId = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			for(Actor a : acr.getAllActors()) {
				System.out.println(a);
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert the actor you want to delete (write actorId):");
				
				strActorId = sc.nextLine().trim();
				
				if(strActorId.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(!Check.isNumeric(strActorId)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't used a numeric value for actorId");
					System.out.println();
				} else if(acr.getActorOnActorId(Integer.parseInt(strActorId)).size() == 0) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("Your choice of actor isn't in the repository");
					System.out.println();
				} else {
					System.out.println();
					
					Actor actorToDelete = acr.getActorOnActorId(Integer.parseInt(strActorId)).get(0);
					
					acr.deleteActor(actorToDelete, acr, pr);
					
					System.out.println("Now the actor is deleted");
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Do you want to delete another actor, press Y");
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
}