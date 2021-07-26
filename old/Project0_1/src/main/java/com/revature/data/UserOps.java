package com.revature.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.revature.userdef.*;

public class UserOps {
	// DAO = Database Access Object
	// This is a class that is dedicated to accessing data from persistence.
	
	private static List<UserDef> users;
	
	static {
		DataSerializer<UserDef> ds = new DataSerializer<UserDef>();
		users = ds.readObjectsFromFile("users.dat");
		
		// Helper for myself. If no users exist in the users.dat file (first startup) than I should create a few
		if(users == null) {
			users = new ArrayList<UserDef>();
			users.add(new UserDef(users.size(), "Alby", "alby@alby.alby", LocalDate.of(1900, 1, 1), 1000l));
			users.add(new UserDef(users.size(), "William", "will@will.will", LocalDate.of(1950, 5, 1), 3000l));
			users.add(new UserDef(users.size(), "Jaclyn", "jaclyn@jaclyn.jaclyn", LocalDate.of(2021, 1, 1), 2000l));
			UserDef u = new UserDef(users.size(), "richard", "richard.orr@revature.com", LocalDate.of(1900, 1, 1), 1000l);
			u.setType(UserType.ADMIN);
			users.add(u);
		}
	}
	
	public UserDef getUser(String username) {
		
		for(UserDef user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
    // id , username, email, bday, currancy 
	public UserDef SetUser(String username, String email) {
		UserDef u = new UserDef(users.size(), username, email, LocalDate.of(1900, 1, 1), 1000l);
		u.setType(UserType.CUSTOMER);
		users.add(u);
        this.writeToFile();
		return null;
	}
	
	public void writeToFile() {
		new DataSerializer<UserDef>().writeObjectsToFile(users, "users.dat");
	}
}
