package database;

import java.util.Map;

import model.User;

public class UserDao {
	public void setUserData(User user)
	{
		Data.userData.put(user.getUserName(), user.getPassword());
	}
	public Map<String, String> getUserData()
	{
		 Map<String, String> userData = Data.userData;
		 return userData;
	}
}
