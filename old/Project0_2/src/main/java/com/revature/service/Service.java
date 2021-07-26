package com.revature.service;

import com.revature.userdef.UserDef;
import com.revature.userdef.UserType;

import java.time.LocalDate;

import com.revature.data.UserOps;

public class Service {
	
	private UserOps uop = new UserOps();
	
	public UserDef login(String name) {
		UserDef u = uop.getUser(name);
		return u;
	}
	
	public UserDef register(String name, String email, LocalDate birthday, UserType userType, String AccType, Double Bal) {
		UserDef u = uop.setUser(name, email, birthday, userType, null, 0.0);
		return u;
	}
	public UserDef createAccnt(UserDef ud, String accntType)
	{
		UserDef u = uop.modAccnt(ud, accntType);
		return u;
	}
	public UserDef accntTally(UserDef ud, Double amnt)
	{
		Double balance = ud.getBalance();
		if (balance + amnt < 0) {
			return null;
		}
		else {
			balance = balance + amnt;
		}
		UserDef u = uop.modBalance(ud, balance);
		return u;
	}
}
