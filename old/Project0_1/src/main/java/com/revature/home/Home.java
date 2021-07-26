package com.revature.home;

import java.util.Scanner;
import com.revature.userdef.UserDef;
import com.revature.util.MyScanner;
import com.revature.service.Service;

public class Home {
    // --- Class Variables --
	private Service userService = new Service();
	private UserDef loggedUser = null;
	private Scanner scan = MyScanner.getScanner().getScan();
	
	// ---- Start function, Handle User operations --
	public void start() {
		
		while(true) {
			switch(startMenu()) {
				case 1:
					// login
					System.out.println("Please enter your username: \n");
					String username = scan.nextLine();
					UserDef uname   = userService.login(username);
					if(uname == null) {
						System.out.println("Please enter a valid username.\n");
					} else {
						loggedUser = uname;
						System.out.println("Welcome back: "+uname.getUsername());					
						switch(loggedUser.getType()) {
							case ADMIN:
								admin();
								break;
							case CUSTOMER:
								customer();
								break;
						}
					}
					break;
				case 2:
					// register
					System.out.println("Coming here\n");
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
	
	public int startMenu() {
		//log
		System.out.println("What would you like to do?");
		System.out.println("\t1. Login");
		System.out.println("\t2. Register");
		System.out.println("\t3. Quit");
		System.out.println("\n");

		int selection;
		try {
			selection = Integer.parseInt(scan.nextLine());
		} catch(Exception e) {
			selection = -1;
		}
		return selection;
	}
	
	public void customer() {
		
	}
	public void admin() {
		
	}
	public void newUser() {
		System.out.println("Please enter your username: \n");
		String username;
		String email;
		while (true) {
			username = scan.nextLine();
			UserDef uname   = userService.login(username);
			if(uname != null) {
				System.out.println("Username Already exists. Try a different one.\n");
			} else {
				System.out.println("Please enter your emailId.\n");
				email =scan.nextLine();
				break;
			}	
		}
		userService.register(username, email);
	}
}
