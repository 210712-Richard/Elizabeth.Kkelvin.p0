package com.revature.service;
import java.time.LocalDate;

import com.revature.userdef.UserDef;
import com.revature.userdef.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.data.UserOps;
import com.revature.data.UserOpsFile;
import com.revature.factory1.BeanFactory;
import com.revature.factory1.Log;

@Log
public class ServiceImpt implements Service {
		private Logger log = LogManager.getLogger(ServiceImpt.class);
		public UserOps ud = (UserOps) BeanFactory.getFactory1().get(UserOps.class, UserOpsFile.class);

		private UserOpsFile uop = new UserOpsFile();
		
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
		
		public UserDef logout(UserDef ud, String accntType)
		{
			return null;
		}
		
}

