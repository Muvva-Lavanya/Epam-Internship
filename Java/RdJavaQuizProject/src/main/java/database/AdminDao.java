package database;
import java.util.Map;

import model.User;

public class AdminDao {
	public void setAdminData(User user)
	{
		Data.adminData.put(user.getUserName(), user.getPassword());
	}
	public Map<String, String> getAdminData()
	{
		 Map<String, String> adminData = Data.adminData;
		 return adminData;
	}
}
