package com.revature.apisetup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.factory1.BeanFactory;
import com.revature.service.Service;
import com.revature.service.ServiceImpt;
import com.revature.userdef.UserDef;
import com.revature.userdef.UserType;
import com.revature.factory1.Log;
import io.javalin.http.Context;

@Log
public class UserApiSetupImpt implements UserApiSetup {
		private static Logger log = LogManager.getLogger(UserApiSetupImpt.class);
		private Service us = (Service) BeanFactory.getFactory1().get(Service.class, ServiceImpt.class);

	
		public void login(Context ctx) {
			log.trace("Login method called");
			log.debug(ctx.body());
			
			UserDef u = ctx.bodyAsClass(UserDef.class);
			log.debug(u);
			u = us.login(u.getUsername());
			log.debug(u);
			if(u != null) {
				ctx.sessionAttribute("CurrentUser", u);
				ctx.json(u);
				return;
			}
			ctx.status(401);
		}
			
		public void register(Context ctx) {
			UserDef u = ctx.bodyAsClass(UserDef.class);

			if(us.login(u.getUsername()) != null) {
				// should be passed from postman API
				UserDef newUser = us.register(u.getUsername(), u.getEmail(), u.getBirthday(), UserType.CUSTOMER, null, 0.0);
				ctx.status(201);
				ctx.json(newUser);
			} else {
				ctx.status(409);
				ctx.html("Username already taken.");
			}
			
		}
		
		public void logout(Context ctx) {
			ctx.req.getSession().invalidate();
			ctx.status(204);
		}
		
		public void deposit(Context ctx) {
			
			//Check that the user is logged in
			UserDef currentuser = ctx.sessionAttribute("currentUser");
			String username = ctx.pathParam("username");
			if(currentuser == null || !currentuser.getUsername().equals(username)) {
				ctx.status(403);
				return;
			}
			Double amount = (double) Integer.parseInt(ctx.pathParam("deposit"));
			UserDef ud = us.accntTally(currentuser, amount);
		}

		public void withdraw(Context ctx) {
				
				//Check that the user is logged in
				UserDef currentUser = ctx.sessionAttribute("currentUser");
				String username = ctx.pathParam("username");
				if(currentUser == null || !currentUser.getUsername().equals(username)) {
					ctx.status(403);
					return;
				}
				Double amnt = (double)  Integer.parseInt(ctx.pathParam("withdraw"));
				UserDef ud1 = us.accntTally(currentUser, amnt*-1);
		}
		
		public void balance(Context ctx) {
			ctx.req.getSession().invalidate();
			ctx.status(204);
		}
}
	
	
	
	
	