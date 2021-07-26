package com.revature.data;

import java.time.LocalDate;
import java.util.List;

import com.revature.userdef.UserDef;
import com.revature.userdef.UserType;
import com.revature.userdef.UserDef;

public interface UserOps {

	UserDef setUser(String username, String email, LocalDate birthday, UserType userType, String AccType, Double Ba);

	//List<UserDef> getUsers();

	UserDef getUser(String username);


}