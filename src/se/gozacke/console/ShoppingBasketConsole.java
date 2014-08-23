package se.gozacke.console;

import java.util.Scanner;

import se.gozacke.data.StorageException;
import se.gozacke.order.OrderRepository;
import se.gozacke.product.Product;
import se.gozacke.product.ProductRepository;
import se.gozacke.shoppingbasket.ShoppingBasket;
import se.gozacke.shoppingbasket.ShoppingBasketRepository;
import se.gozacke.user.UserRepository;
import se.gozacke.warehouse.WarehouseRepository;

public class ShoppingBasketConsole {
	public static int loginUserToShoppingBasket(Scanner sc, UserRepository ur) throws StorageException {
		boolean checkValidInfo = true;
		String username = "";
		String password = "";
		int userId = 0;
		
		do {
			checkValidInfo = false;
			
			System.out.println("Loggin to users shoppingbasket");
			System.out.println("Insert username:");
			
			do {
				checkValidInfo = false;
				
				username = sc.nextLine();
				
				if(username.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any username yet");
					System.out.println();
				}
			} while(checkValidInfo);
			
			System.out.println();
			
			do {
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
		} while(checkValidInfo);
		
		System.out.println();
		
		if(ur.validateUsernameAndPassword(username, password)) {
			userId = ur.getUserOnUsernameAndPassword(username, password).get(0).getUserId();
		}
		
		return userId;
	}
	
	public static void insertProductsToShoppingBasket(Scanner sc, int userId, ShoppingBasketRepository sbr, ProductRepository pr, WarehouseRepository wr, OrderRepository or) throws StorageException {
		boolean checkValidInfo = true;
		String options = "";
		String strProductId = "";
		String quantity = "";
		
		do {
			checkValidInfo = false;
			
			System.out.println("User options (write 1 - 6):");
			System.out.println("1 = Inspect your shoppingbasket");
			System.out.println("2 = Insert product to shoppingbasket");
			System.out.println("3 = Update product in shoppingbasket");
			System.out.println("4 = Delete product in shoppingbasket");
			System.out.println("5 = Make order of shoppingbasket");
			System.out.println("6 = Quit");
			
			do {
				checkValidInfo = false;
				
				options = sc.nextLine().trim();
				
				if(options.equals("")) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted any option yet");
					System.out.println();
				} else if(!Check.isNumeric(options)) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't used a numeric value as your option, press 1 - 6");
					System.out.println();
				} else if(Integer.parseInt(options) < 1 || Integer.parseInt(options) > 6) {
					checkValidInfo = true;
					System.out.println();
					System.out.println("You haven't inserted a value between 1 - 6");
					System.out.println();
				} else if(options.equals("1")) {
					checkValidInfo = true;
					
					System.out.println();
					
					if(sbr.getShoppingBasketOnUserId(userId).size() > 0) {
						for(ShoppingBasket sb : sbr.getShoppingBasketOnUserId(userId)) {
							System.out.println("ShoppingbasketId: " + sb.getShoppingBasketId());
							System.out.println("UserId: " + sb.getUserId());
							System.out.println("ProductId: " + pr.getProductFromProductId(sb.getProductId()).get(0).getProductId());
							System.out.println("Product name: " + pr.getProductFromProductId(sb.getProductId()).get(0).getProductName());
							System.out.println("Cost: " + pr.getProductFromProductId(sb.getProductId()).get(0).getCost());
							System.out.println("Rrp: " + pr.getProductFromProductId(sb.getProductId()).get(0).getRrp());
							System.out.println("Quantity: " + sb.getQuantity());
							System.out.println();
						}
					} else {
						System.out.println("No products inserted yet");
						System.out.println();
					}
					break;
				} else if(options.equals("2")) {
					System.out.println();
					
					for(Product p : pr.getAllProducts()) {
						System.out.println(p);
					}
					
					System.out.println("Choose product you want in your shoppingbasket (write productId):");
					
					do {
						checkValidInfo = false;
						
						strProductId = sc.nextLine().trim();
						
						if(strProductId.equals("")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted any value yet");
							System.out.println();
						} else if(!Check.isNumeric(strProductId)) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You didn't insert a numeric value");
							System.out.println();
						} else if(pr.getProductFromProductId(Integer.parseInt(strProductId)).size() == 0) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't chosen a product in the repository");
							System.out.println();
						} else if(sbr.getShoppingBasketOnUserIdAndProductId(userId, Integer.parseInt(strProductId)).size() > 0) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You have aready this product in your shoppingbasket");
							System.out.println();
						} else {
							System.out.println();
							System.out.println("Insert quantity of the product: ");
							
							do {
								checkValidInfo = false;
								
								quantity = sc.nextLine().trim();
								
								if(quantity.equals("")) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted any quantity yet");
									System.out.println();
								} else if(!Check.isNumeric(quantity)) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted a numeric value for quantity");
									System.out.println();
								} else if(Integer.parseInt(quantity) < 1) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You must insert minimum 1 in quantity of the product");
									System.out.println();
								} else {
									System.out.println();
									System.out.println("This is the product you want to put in your shoppingbasket:");
									
									System.out.println("ProductId: " + strProductId);
									System.out.println("Product name: " + pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0).getProductName());
									System.out.println("Description: " + pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0).getDescription());
									System.out.println("Cost: " + pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0).getCost());
									System.out.println("Rrp: " + pr.getProductFromProductId(Integer.parseInt(strProductId)).get(0).getRrp());
									System.out.println("Quantity: " + quantity);
									
									System.out.println();
									System.out.println("If you want to insert it, press Y");
									System.out.println("If you don't want to insert it, press N");
									
									do {
										checkValidInfo = false;
										
										options = sc.nextLine().trim().toLowerCase();
										
										if(options.equals("")) {
											checkValidInfo = true;
											System.out.println();
											System.out.println("You haven't inserted any choise yet");
											System.out.println();
										} else if(Check.isNumeric(options)) {
											checkValidInfo = true;
											System.out.println();
											System.out.println("You have inserted a numeric value as your choise, press Y or N");
											System.out.println();
										} else if(!options.equals("y") && !options.equals("n")) {
											checkValidInfo = true;
											System.out.println();
											System.out.println("You didn't insert Y or N");
											System.out.println();
										} else if(options.equals("y")) {
											System.out.println();
											
											ShoppingBasket shoppingBasketToInsert = new ShoppingBasket();
											
											shoppingBasketToInsert.setUserId(userId);
											shoppingBasketToInsert.setProductId(Integer.parseInt(strProductId));
											shoppingBasketToInsert.setQuantity(Integer.parseInt(quantity));
											
											sbr.insertOrderInShoppingBasket(shoppingBasketToInsert);
											
											System.out.println("Saved");
										} else if(options.equals("n")) {
											System.out.println();
											System.out.println("Cancelled");
										}
									} while(checkValidInfo);
								}
							} while(checkValidInfo);
						}
					} while(checkValidInfo);
					
					System.out.println();
				} else if(options.equals("3")) {
					System.out.println();
					
					if(sbr.getShoppingBasketOnUserId(userId).size() > 0) {
						
						for(ShoppingBasket sb : sbr.getShoppingBasketOnUserId(userId)) {
							System.out.println("ShoppingbasketId: " + sb.getShoppingBasketId());
							System.out.println("UserId: " + sb.getUserId());
							System.out.println("ProductId: " + sb.getProductId());
							System.out.println("Product name: " + pr.getProductFromProductId(sb.getProductId()).get(0).getProductName());
							System.out.println("Description: " + pr.getProductFromProductId(sb.getProductId()).get(0).getDescription());
							System.out.println("Cost: " + pr.getProductFromProductId(sb.getProductId()).get(0).getCost());
							System.out.println("Rrp: " + pr.getProductFromProductId(sb.getProductId()).get(0).getRrp());
							System.out.println("Quantity: " + sb.getQuantity());
							System.out.println();
						}
						
						System.out.println("Which product do you want to edit (write productId):");
						
						do {
							checkValidInfo = false;
							
							strProductId = sc.nextLine().trim();
							
							if(strProductId.equals("")) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted any value yet");
								System.out.println();
							} else if(!Check.isNumeric(strProductId)) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You didn't insert a numeric value");
								System.out.println();
							} else if(sbr.getShoppingBasketOnUserIdAndProductId(userId, Integer.parseInt(strProductId)).size() == 0) {
								checkValidInfo = true;
								System.out.println();
								System.out.println("You haven't inserted a productId available in your shoppingbasket");
								System.out.println();
							} else {
								System.out.println();
								
								for(ShoppingBasket sb : sbr.getShoppingBasketOnUserIdAndProductId(userId, Integer.parseInt(strProductId))) {
									System.out.println("ShoppingbasketId: " + sb.getShoppingBasketId());
									System.out.println("UserId: " + sb.getUserId());
									System.out.println("ProductId: " + sb.getProductId());
									System.out.println("Product name: " + pr.getProductFromProductId(sb.getProductId()).get(0).getProductName());
									System.out.println("Description: " + pr.getProductFromProductId(sb.getProductId()).get(0).getDescription());
									System.out.println("Cost: " + pr.getProductFromProductId(sb.getProductId()).get(0).getCost());
									System.out.println("Rrp: " + pr.getProductFromProductId(sb.getProductId()).get(0).getRrp());
									System.out.println("Quantity: " + sb.getQuantity());
									System.out.println();
								}
								
								System.out.println("How much quantity do you want instead:");
								
								do {
									checkValidInfo = false;
									
									quantity = sc.nextLine().trim();
									
									if(quantity.equals("")) {
										checkValidInfo = true;
										System.out.println();
										System.out.println("You haven't inserted any quantity yet");
										System.out.println();
									} else if(!Check.isNumeric(quantity)) {
										checkValidInfo = true;
										System.out.println();
										System.out.println("You haven't inserted a numeric value for quantity");
										System.out.println();
									} else if(Integer.parseInt(quantity) < 1) {
										checkValidInfo = true;
										System.out.println();
										System.out.println("You must insert minimum 1 in quantity of the product");
										System.out.println();
									} else {
										System.out.println();
										System.out.println("If you want to update it, press Y");
										System.out.println("If you don't want to update it, press N");
										
										do {
											checkValidInfo = false;
											
											options = sc.nextLine().trim().toLowerCase();
											
											if(options.equals("")) {
												checkValidInfo = true;
												System.out.println();
												System.out.println("You haven't inserted any choise yet");
												System.out.println();
											} else if(Check.isNumeric(options)) {
												checkValidInfo = true;
												System.out.println();
												System.out.println("You have inserted a numeric value as your choise, press Y or N");
												System.out.println();
											} else if(!options.equals("y") && !options.equals("n")) {
												checkValidInfo = true;
												System.out.println();
												System.out.println("You didn't insert Y or N");
												System.out.println();
											} else if(options.equals("y")) {
												System.out.println();
												
												ShoppingBasket shoppingBasketToUpdate = sbr.getShoppingBasketOnUserIdAndProductId(userId, Integer.parseInt(strProductId)).get(0);
												
												shoppingBasketToUpdate.setQuantity(Integer.parseInt(quantity));
												
												sbr.updateOrderInShoppingBasket(shoppingBasketToUpdate);
												
												System.out.println("Saved");
											} else if(options.equals("n")) {
												System.out.println();
												System.out.println("Cancelled");
											}
											
											System.out.println();
										} while(checkValidInfo);
									}
								} while(checkValidInfo);
							}
						} while(checkValidInfo);
						
					} else {
						System.out.println("You don't have any product in you shoppingbasket to update");
						System.out.println();
					}
					
				} else if(options.equals("4")) {
					System.out.println();
					
					if(sbr.getShoppingBasketOnUserId(userId).size() > 0) {
					
					for(ShoppingBasket sb : sbr.getShoppingBasketOnUserId(userId)) {
						System.out.println("ShoppingbasketId: " + sb.getShoppingBasketId());
						System.out.println("UserId: " + sb.getUserId());
						System.out.println("ProductId: " + sb.getProductId());
						System.out.println("Product name: " + pr.getProductFromProductId(sb.getProductId()).get(0).getProductName());
						System.out.println("Description: " + pr.getProductFromProductId(sb.getProductId()).get(0).getDescription());
						System.out.println("Cost: " + pr.getProductFromProductId(sb.getProductId()).get(0).getCost());
						System.out.println("Rrp: " + pr.getProductFromProductId(sb.getProductId()).get(0).getRrp());
						System.out.println("Quantity: " + sb.getQuantity());
						System.out.println();
					}
					
					System.out.println("Which product do you want to delete (write productId):");
					
					do {
						checkValidInfo = false;
						
						strProductId = sc.nextLine().trim();
						
						if(strProductId.equals("")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted any value yet");
							System.out.println();
						} else if(!Check.isNumeric(strProductId)) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You didn't insert a numeric value");
							System.out.println();
						} else if(sbr.getShoppingBasketOnUserIdAndProductId(userId, Integer.parseInt(strProductId)).size() == 0) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted a productId available in your shoppingbasket");
							System.out.println();
						} else {
							System.out.println();
							
							System.out.println();
							System.out.println("If you want to delete it, press Y");
							System.out.println("If you don't want to delete it, press N");
							
							do {
								checkValidInfo = false;
								
								options = sc.nextLine().trim().toLowerCase();
								
								if(options.equals("")) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You haven't inserted any choise yet");
									System.out.println();
								} else if(Check.isNumeric(options)) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You have inserted a numeric value as your choise, press Y or N");
									System.out.println();
								} else if(!options.equals("y") && !options.equals("n")) {
									checkValidInfo = true;
									System.out.println();
									System.out.println("You didn't insert Y or N");
									System.out.println();
								} else if(options.equals("y")) {
									System.out.println();
									
									ShoppingBasket shoppingBasketToDelete = sbr.getShoppingBasketOnUserIdAndProductId(userId, Integer.parseInt(strProductId)).get(0);
									
									sbr.deleteOrderInShoppingBasket(shoppingBasketToDelete);
									
									System.out.println("Deleted");
								} else if(options.equals("n")) {
									System.out.println();
									System.out.println("Cancelled");
								}
								
								System.out.println();
							} while(checkValidInfo);
						}
					} while(checkValidInfo);
					
				} else {
					System.out.println("You don't have any product in you shoppingbasket to delete");
					System.out.println();
				}
				
				} else if(options.equals("5")) {
					System.out.println();
					
					if(sbr.getShoppingBasketOnUserId(userId).size() > 0) {
					
					for(ShoppingBasket sb : sbr.getShoppingBasketOnUserId(userId)) {
						System.out.println("ShoppingbasketId: " + sb.getShoppingBasketId());
						System.out.println("UserId: " + sb.getUserId());
						System.out.println("ProductId: " + sb.getProductId());
						System.out.println("Product name: " + pr.getProductFromProductId(sb.getProductId()).get(0).getProductName());
						System.out.println("Description: " + pr.getProductFromProductId(sb.getProductId()).get(0).getDescription());
						System.out.println("Cost: " + pr.getProductFromProductId(sb.getProductId()).get(0).getCost());
						System.out.println("Rrp: " + pr.getProductFromProductId(sb.getProductId()).get(0).getRrp());
						System.out.println("Quantity: " + sb.getQuantity());
						System.out.println();
					}
					
					System.out.println("If you want to make an order of this shoppingbasket, press Y:");
					System.out.println("If you don't want to make an order of this shoppingbasket, press N:");
					
					do {
						checkValidInfo = false;
						
						options = sc.nextLine().trim().toLowerCase();
						
						if(options.equals("")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You haven't inserted any choise yet");
							System.out.println();
						} else if(Check.isNumeric(options)) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You have inserted a numeric value as your choise, press Y or N");
							System.out.println();
						} else if(!options.equals("y") && !options.equals("n")) {
							checkValidInfo = true;
							System.out.println();
							System.out.println("You didn't insert Y or N");
							System.out.println();
						} else if(options.equals("y")) {
							System.out.println();
							
							int shortageAmount = 0;
							
							for(ShoppingBasket sb : sbr.getShoppingBasketOnUserId(userId)) {
								if(sb.getProductId() == wr.getWarehouseOnProductId(sb.getProductId()).get(0).getProductId() &&
								   sb.getQuantity() > wr.getWarehouseOnProductId(sb.getProductId()).get(0).getStock()) {
									shortageAmount = sb.getQuantity() - wr.getWarehouseOnProductId(sb.getProductId()).get(0).getStock();
									System.out.println("There isn't enough in stock for product " + sb.getProductId() + ". The amount of shortage in stock is " + shortageAmount);
								}
							}
							
//							ShoppingBasket shoppingBasketToDelete = sbr.getShoppingBasketOnUserIdAndProductId(userId, Integer.parseInt(strProductId)).get(0);
							
//							sbr.deleteOrderInShoppingBasket(shoppingBasketToDelete);
							
							if(shortageAmount == 0) {
								System.out.println();
								
								or.insertOrderFromShoppingBasket(sbr.getShoppingBasketOnUserId(userId), wr);
								
								System.out.println("Your order is now sent");
							} else {
								System.out.println();
								System.out.println("Your order is cancelled. Use less quantities on the products that had shortage in stock");
							}
						} else if(options.equals("n")) {
							System.out.println();
							System.out.println("Order cancelled");
						}
						
						System.out.println();
					} while(checkValidInfo);
					
				} else {
					System.out.println("You don't have any product in you shoppingbasket to make an order of");
					System.out.println();
				}
					
				} else if(options.equals("6")) {
					checkValidInfo = false;
					options = "";
				}
			} while(checkValidInfo);
		} while(checkValidInfo || !options.equals(""));
	}
}