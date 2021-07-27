package com.revature.apisetup;

import io.javalin.http.Context;

public interface UserApiSetup {
	
	void login(Context ctx);

	void register(Context ctx);

	void logout(Context ctx);

	void deposit(Context ctx);

	void withdraw(Context ctx);
	
	void balance(Context ctx);


}
