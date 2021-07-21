package com.revature.home;
import java.time.LocalDate;
import java.util.Scanner;
import com.revature.userdef.UserDef;
import com.revature.userdef.UserType;
import com.revature.util.MyScanner;
import com.revature.service.Service;

public class Home {
    // --- Class Variables --
	private Service userService = new Service();
	private UserDef currentUser = null;
	private Scanner scan = MyScanner.getScanner().getScan();

	
	// ---- Start function, Handle User operations --
	public void start() {
		
		while(true) {
			switch(startMenu()) {
				case 1:
					// login
					this.userLogin();
					break;
				case 2:
					// register
					this.newUser();
					break;
				case 3:
					// quit
					System.out.println("Quitting... \n Goodbye! \n");
					break;
				default:
					// invalid selection
					System.out.println("Not a valid selection, please try again. \n");
			}
		}
	}
	
	private void customer() {
		while(true) {
			switch(this.customerMenu()) {
			case 1:
				// Create account
				if(this.currentUser.getAccntType() != null) {
					System.out.println("You AccountType: " +this.currentUser.getAccntType() );
					break;
				}
				else {
					while(true) {
						System.out.println("Please enter Account type.\n\t1. Ceckin \n\t2. Saving");
						Integer selection = Integer.parseInt(scan.nextLine());
						if(selection == 1) {
							UserDef ud = userService.createAccnt(this.currentUser, "checkin");
							System.out.println("Your Account Type is set as : " +ud.getAccntType());
							break;
						}
						else if(selection == 2){
							UserDef ud = userService.createAccnt(this.currentUser, "savings");
							System.out.println("Your Account Type is set as : " +ud.getAccntType());
							break;
						}
						else {
							System.out.println("Invalid Selection. Please try again.\n");
						}
					}
				    break;
				}
			case 2:
				// deposit
				System.out.println("\t Deposit Amount: \n");
				Double amount = (double) select();
				UserDef ud = userService.accntTally(this.currentUser, amount);
				System.out.println("New Account Balnce: " + ud.getBalance() );
				break;
			case 3:
				// spend currency
				System.out.println("\t Withdraw Amount: \n");
				Double amnt = (double) select();
				UserDef ud1 = userService.accntTally(this.currentUser, amnt*-1);
				if(ud1 == null) {
					System.out.println("Withdrawble Balnce: " + this.currentUser.getBalance() );
				}
				System.out.println("New Account Balnce: " + ud1.getBalance() );
				break;
			case 4:
				break ;
			default:
			}
		}
		
	}
	
	private int customerMenu() {
		System.out.println("Please select your Operation.\n");
		System.out.println("\t1. Create Checkin or Savings Account");
		System.out.println("\t2. Deposit");
		System.out.println("\t3. Withdraw");
		System.out.println("\t4. Logout");
		return select();
	}
	
/*
	private void admin() {
		Admin: while(true) {
			switch(AdminMenu()) {
			case 1:
				// Create account
				if(us.hasCheckedIn(uname)) {
					System.out.println("AccountType");
					break;
				}
				us.doCheckIn(loggedUser);
				System.out.println("Your Account "+AccountType);
				System.out.println("Your balance is "+loggedUser.getOpeningBalance());
				break;
			case 2:
				// deposit
				System.out.println("Deposit "+loggedUser.getOpeningDeposit());
				break;
			case 3:
				// view and delete customer
				System.out.println("Update Customer List "+loggedUser.getOpeningDeposit());
				break;
			case 4:
				loggedUser = null;
				break customer;
			default:
		}
	}

		private int AdminMenu() {
			System.out.println("Options");
			System.out.println("\t1. Create Checking Account");
			System.out.println("\t2. Deposit");
			System.out.println("\t3. Add/Delete Customers");
			System.out.println("\t4. Logout");
			return select();
		}
		
	}*/

	public int startMenu() {
		//log
		System.out.println("What would you like to do?");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Logout");
		return select();
	}

	private int select() {
		int selection;
		try {
				selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
				selection = -1;
		}
		return selection;
	}
	
	public void newUser() {
		String username;
		String email;
		LocalDate birthDay;
		UserType userType = UserType.CUSTOMER;
		
		System.out.println("Please enter your username: \n");
		while (true) {
			username = scan.nextLine();
			UserDef uname   = userService.login(username);
			if(uname != null) {
				System.out.println("Username Already exists. Try a different one.\n");
			} else  {
				System.out.println("Please enter your emailId.\n");
				email =scan.nextLine();
				System.out.println("enter your birthday (YYYY/MM/DD): ");
				String[] bday = scan.nextLine().split("/");			
				birthDay = LocalDate.of(Integer.parseInt(bday[0]), Integer.parseInt(bday[1]),Integer.parseInt(bday[2]));
				System.out.println("Are you an Admin or Customer?");
				System.out.println("\t1. Admin");
				System.out.println("\t2. Customer");
				switch(select()) {
					case 1:
						// Admin
						userType = UserType.ADMIN;
						break;
					case 2:
						// Customer
						userType = UserType.CUSTOMER;
						break;
					default:
						// invalid selection
						System.out.println("Not a valid selection, please try again. \n");
				}
				userService.register(username, email, birthDay, userType, null, 0.0);
				break;
			}	
		}
	}
	
	public void userLogin() {
		System.out.println("Please enter your username: \n");
		String username = scan.nextLine();
		UserDef uname   = userService.login(username);
		if(uname == null) {
			System.out.println("Please enter a valid username.\n");
		} else {
			System.out.println("Welcome: "+uname.getUsername());	
			this.currentUser = uname;
			if (uname.getType() == null) {
				System.out.println("Account type is not set for this user.");	
			}
			else {
				switch(uname.getType()) {
					case ADMIN:
						break;
					case CUSTOMER:
						this.customer();
						break;
				}
			}
		}
	}
}


	




	



