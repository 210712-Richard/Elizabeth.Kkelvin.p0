package com.revature.service;

import java.time.LocalDate;

import com.revature.userdef.UserDef;
import com.revature.userdef.UserType;


//import com.revature.beans.User;

public interface Service {

	UserDef login(String name);

	UserDef register(String username, String email, LocalDate birthday,  UserType userType, String AccType, Double Ba);

	UserDef createAccnt(UserDef ud, String accntType);

	UserDef accntTally(UserDef ud, Double amnt);


}