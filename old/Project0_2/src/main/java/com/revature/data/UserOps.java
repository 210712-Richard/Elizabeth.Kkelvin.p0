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
			UserDef u = new UserDef(users.size(), "eliana", "eliana@gmail.com", LocalDate.of(2021, 4, 18), UserType.ADMIN, "checkin", 0.0);
			//u.setType(UserType.ADMIN);
			users.add(u);
		}
	}
	
	public UserDef getUser(String name) {
		for(UserDef user : users) {
			if(user.getUsername().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	public UserDef setUser(String username, String email, LocalDate birthday, UserType userType, String AccType, Double Bal) {

		UserDef u = new UserDef(users.size(), username, email, birthday, userType, null, 0.0);
		users.add(u);
        this.writeToFile();
		return u;
	}
	
	public UserDef modAccnt(UserDef u, String AccType) {
		u.setAccntType(AccType);
		users.add(u);
        this.writeToFile();
		return u;
	}

	public UserDef modBalance(UserDef u, Double Bal) {
				
		if (Bal > 0) {
			u.setBalance(Bal);
		}
		else {
			u.setBalance(0.0);
		}
		users.add(u);
        this.writeToFile();
		return u;
	}
	
	public void writeToFile() {
		new DataSerializer<UserDef>().writeObjectsToFile(users, "users.dat");
	}

}
