package com.revature.service;

import com.revature.userdef.UserDef;
import com.revature.data.UserOps;

public class Service {
	
	private UserOps ud = new UserOps();
	
	public UserDef login(String name) {
		UserDef u = ud.getUser(name);
		ud.writeToFile();
		return u;
	}
	public UserDef register(String name, String email) {
		UserDef u1 = ud.SetUser(name, email);
		//ud.writeToFile();
		return u1;
	}
}
