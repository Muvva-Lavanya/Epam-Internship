package service;

import database.AdminDao;
import database.UserDao;
import model.User;

public class SignUpService {
	public boolean signUpService(String userType,User user) {
		boolean result=false;
		if (userType.equals("admin")) {
			AdminDao adminDao=new AdminDao();
			adminDao.setAdminData(user);
			result=true;
		} else {
			UserDao userDao=new UserDao();
			userDao.setUserData(user);
			result=true;
		}
		return result;

	}

}
