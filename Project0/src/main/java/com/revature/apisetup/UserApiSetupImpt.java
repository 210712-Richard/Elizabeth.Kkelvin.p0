package com.revature.apisetup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.factory1.BeanFactory;
import com.revature.service.Service;
import com.revature.service.ServiceImpt;
import com.revature.userdef.UserDef;
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
				log.trace(u);
				
				u = us.login(u.getUsername());
				log.debug(u.getUsername());
				if(u != null) {
						ctx.sessionAttribute("currentUser", u);
						ctx.json(u);
						return;
				}
				ctx.status(401);
		}
			
		public void register(Context ctx) {
				System.out.println(ctx.body());
				UserDef u = ctx.bodyAsClass(UserDef.class);
	
				if(us.login(u.getUsername()) == null) {
						// should be passed from postman API
						UserDef newUser = us.register(u.getUsername(), u.getEmail(), u.getBirthday(), u.getType(), null, 0.0);
						ctx.status(201);
						ctx.json(newUser);
				} else {
					ctx.status(409);
					ctx.html("Username already taken.");
				}
		}
		

		public void deposit(Context ctx) {
				UserDef currentuser = ctx.sessionAttribute("currentUser");
				String username = ctx.pathParam("username");
				if(currentuser == null || !currentuser.getUsername().equals(username)) {
						ctx.status(403);
						return;
				} else {
						Double amount = Double.parseDouble(ctx.req.getParameter("deposit"));
						UserDef ud = us.accntTally(currentuser, amount);
						ctx.json(ud.getBalance());	
				}
			
		}

		
		public void withdraw(Context ctx) {
				UserDef currentuser = ctx.sessionAttribute("currentUser");
				String username = ctx.pathParam("username");
				if(currentuser == null || !currentuser.getUsername().equals(username)) {
						ctx.status(403);
						return;
				}else {
						Double amnt =  Double.parseDouble(ctx.req.getParameter("withdraw"));
						UserDef ud1 = us.accntTally(currentuser, amnt*-1);
						ctx.json(ud1.getBalance());
				}
		}

		
		public void balance(Context ctx) {
				UserDef currentUser = ctx.sessionAttribute("currentUser");
				String username = ctx.pathParam("username");
				if(currentUser == null || !currentUser.getUsername().equals(username)) {
						ctx.status(403);
						return;
				}else {
						log.trace(username);
						ctx.json(currentUser.getBalance());
				}
		}
		
		
		public void logout(Context ctx) {
			ctx.req.getSession().invalidate();
			ctx.status(204);
		}
	
}

	
	
	
	
	