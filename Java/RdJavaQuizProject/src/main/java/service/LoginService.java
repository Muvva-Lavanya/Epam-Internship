package service;

import java.util.HashMap;
import java.util.Map;

import database.AdminDao;
import database.UserDao;
import model.User;

public class LoginService {
	public boolean validateUser(String userType,User user) {
		boolean result = false;
		Map<String, String> data = new HashMap<>();
		if (userType.equals("admin")) {
			AdminDao adminDao=new AdminDao();
			data = adminDao.getAdminData();
		} else if (userType.equals("user")) {
			UserDao userDao=new UserDao();
			data = userDao.getUserData();

		}
		if (data.containsKey(user.getUserName())) {
			result = data.get(user.getUserName()).equals(user.getPassword());
		}
		return result;

	}

}
