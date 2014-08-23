package se.gozacke.console;

import java.util.Scanner;

import se.gozacke.actor.ActorRepository;
import se.gozacke.author.AuthorRepository;
import se.gozacke.category.Category;
import se.gozacke.category.CategoryRepository;
import se.gozacke.data.StorageException;
import se.gozacke.product.ProductRepository;
import se.gozacke.staff.Staff;
import se.gozacke.staff.StaffRepository;
import se.gozacke.user.UserRepository;

public class CategoryConsole {
	public static void insertCategory(Scanner sc, CategoryRepository cr, StaffRepository sr, ProductRepository pr, ActorRepository acr, AuthorRepository aur, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String keyboardChoise = "";
		String categoryName = "";
		String strStaffId = "";
		
		do{
			checkValidInfo = false;
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert category name:");
				categoryName = sc.nextLine();
				
				if(categoryName.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't entered any category name yet");
					System.out.println();
				} else if(Check.isNumeric(categoryName)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't have a numeric category name");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			for(Staff s : sr.getAllStaffMembers()) {
				System.out.println("StaffId: " + s.getStaffId() + " Name: " + s.getFirstName() + " " + s.getSurName());
			}
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Choose the staff members responsible for this category (write staffId):");
				
				strStaffId = sc.nextLine();
				
				if(strStaffId.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(!Check.isNumeric(strStaffId)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You must insert a numeric value for staffId");
					System.out.println();
				} else {
					for(Staff s : sr.getAllStaffMembers()) {
						if(s.getStaffId() == Integer.parseInt(strStaffId)) {
							checkValidInfo = false;
							break;
						} else {
							checkValidInfo = true;
						}
					}
					
					if(checkValidInfo) {
						System.out.println();
						System.out.println("You have inserted a staffId that's not in the repository");
						System.out.println();
					} else {
						Category categoryToInsert = new Category();
						categoryToInsert.setStaffId(Integer.parseInt(strStaffId));
						categoryToInsert.setCategoryName(categoryName);
						
						cr.insertCategory(categoryToInsert);
						
						System.out.println();
						System.out.println("The new category is now inserted");
						System.out.println();
					}
				}
				
				do{
					checkValidInfo = false;
					keyboardChoise = "";
					
					System.out.println("If you want to insert another category, press Y");
					System.out.println("If you want to quit to main menu, press Q");
					
					keyboardChoise = sc.nextLine().trim();
					
					if(keyboardChoise.equals("")) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't made a choise yet");
						System.out.println();
					} else if(Check.isNumeric(keyboardChoise)) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You have used a numeric value as your choise");
						System.out.println();
					} else if(!keyboardChoise.equals("y") && !keyboardChoise.equals("q")) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't pressed Y or Q");
						System.out.println();
					} else if(keyboardChoise.equals("y")) {
						checkValidInfo = true;
						System.out.println();
						break;
					} else if(keyboardChoise.equals("q")) {
						checkValidInfo = false;
						System.out.println();
						break;
					}
				} while(checkValidInfo);
				break;
			} while(checkValidInfo);
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
	
	public static void updateCategory(Scanner sc, CategoryRepository cr, StaffRepository sr, ProductRepository pr, ActorRepository acr, AuthorRepository aur, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String strCategoryId = "";
		String strStaffId = "";
		String categoryName = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			for(Category c : cr.getAllCategories()) {
				System.out.println("CategoryId: " + c.getCategoryId() + " StaffId: " + c.getStaffId() + " Category name: " + c.getCategoryName());
			}
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Choose the category you want to change (write categoryId):");
				
				strCategoryId = sc.nextLine().trim();
				
				if(strCategoryId.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(!Check.isNumeric(strCategoryId)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted a numeric value for categoryId");
					System.out.println();
				} else if(cr.getCategoryOnCategoryId(Integer.parseInt(strCategoryId)).size() == 0) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You havent used a categoryId in the repository");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Insert the new category name:");
				
				categoryName = sc.nextLine();
				
				if(categoryName.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any category name yet");
					System.out.println();
				} else if(Check.isNumeric(categoryName)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't have a numeric category name");
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Now your category name is updated");
					
					Category categoryToUpdate = cr.getCategoryOnCategoryId(Integer.parseInt(strCategoryId)).get(0);
					
					categoryToUpdate.setCategoryName(categoryName);
					
					cr.updateCategory(categoryToUpdate);
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println();
				
				System.out.println("If you want to change the staff member responsible for this category, press Y");
				System.out.println("If you don't want to change the staff member responsible for this category, press any key except Y");
				
				keyboardChoise = sc.nextLine().trim().toLowerCase();
				
				if(keyboardChoise.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(Check.isNumeric(keyboardChoise)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You have inserted a numeric value as your choise");
					System.out.println();
				} else if(!keyboardChoise.equals("y") && !keyboardChoise.equals("n")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't pressed Y or N");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			if(keyboardChoise.equals("y")) {
				for(Staff f : sr.getAllStaffMembers()) {
					System.out.println("staffId: " + f.getStaffId() + " name: " + f.getFirstName() + " " + f.getSurName());
				}
				
				do{
					checkValidInfo = false;
					
					System.out.println();
					
					System.out.println("Choose the staff member responsible for this category (write staffId):");
					
					strStaffId = sc.nextLine().trim().toLowerCase();
					
					if(strStaffId.equals("")) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't inserted any value yet");
						System.out.println();
					} else if(!Check.isNumeric(strStaffId)) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("You haven't used a numeric value for staffId");
						System.out.println();
					} else if(sr.getStaffOnStaffId(Integer.parseInt(strStaffId)).size() == 0) {
						checkValidInfo = true;
						System.out.println();
						System.out.println("This staff member isn't in the repository");
						System.out.println();
					} else {
						cr.updateStaffMemberResponsibleForCategory(Integer.parseInt(strStaffId), Integer.parseInt(strCategoryId));
						System.out.println();
						System.out.println("Now your have changed the staff member responsible for the category");
						System.out.println();
					}
				} while(checkValidInfo);
			}
			
			do{
				checkValidInfo = false;
				
				System.out.println("If you want to update another category, press Y");
				System.out.println("If you want to quit to main menu, press Q");
				
				keyboardChoise = sc.nextLine().trim().toLowerCase();
				
				if(keyboardChoise.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any choice yet");
					System.out.println();
				} else if(Check.isNumeric(keyboardChoise)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You can't use a numeric value");
					System.out.println();
				} else if(!keyboardChoise.equals("y") && !keyboardChoise.equals("q")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't pressed on Y or Q");
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
	
	public static void deleteCategory(Scanner sc, CategoryRepository cr, StaffRepository sr, ProductRepository pr, ActorRepository acr, AuthorRepository aur, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String strCategoryId = "";
		String keyboardChoise = "";
		
		do{
			checkValidInfo = false;
			
			for(Category c : cr.getAllCategories()) {
				System.out.println("CategoryId: " + c.getCategoryId() + " Category name: " + c.getCategoryName());
			}
			
			System.out.println();
			
			do{
				checkValidInfo = false;
				
				System.out.println("Choose the category you want to delete (write categoryId):");
				
				strCategoryId = sc.nextLine().trim().toLowerCase();
				
				if(strCategoryId.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any value yet");
					System.out.println();
				} else if(!Check.isNumeric(strCategoryId)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted a numeric value for categoryId");
					System.out.println();
				} else if(cr.getCategoryOnCategoryId(Integer.parseInt(strCategoryId)).size() == 0) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("This category you have inserted isn't in the repository");
					System.out.println();
				} else {
					Category categoryToDelete = cr.getCategoryOnCategoryId(Integer.parseInt(strCategoryId)).get(0);
					
					cr.deleteCategory(categoryToDelete);
					
					System.out.println();
					System.out.println("The category is deleted now");
					System.out.println();
				}
			} while(checkValidInfo);
			
			do{
				checkValidInfo = false;
				
				System.out.println("If you want to delete another category, press Y");
				System.out.println("If you want to return to main menu, press Q");
				
				keyboardChoise = sc.nextLine().trim().toLowerCase();
				
				if(keyboardChoise.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't made any choise yet");
					System.out.println();
				} else if(Check.isNumeric(keyboardChoise)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You have inserted a numeric value");
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
			} else {
				checkValidInfo = false;
				System.out.println();
			}
		} while(checkValidInfo);
		
		MainConsole.mainWindow(pr, cr, acr, aur, sr, ur);
	}
}