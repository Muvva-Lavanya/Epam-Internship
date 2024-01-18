package ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserSelection {
	private static final Map<Integer, DefaultMenu> UserType;
	static {
		final Map<Integer, DefaultMenu> userType = new HashMap<>();
		userType.put(1, new DefaultMenu() {
			@Override
			public void selectType() {
				AdminUI adminUI = new AdminUI();
				adminUI.adminService();
			}
		});
		userType.put(2, new DefaultMenu() {
			@Override
			public void selectType() {
				UserUI userUI = new UserUI();
				userUI.userService();
			}
		});
		userType.put(3, new DefaultMenu() {
			@Override
			public void selectType() {
				System.exit(0);
			}
		});
		UserType = Collections.unmodifiableMap(userType);
		
	}

	public void createUser(int userType) {
		DefaultMenu command = UserType.get(userType);
		if (command == null) {
			System.out.println("Enter proper option");
		}
		try
		{
		command.selectType();
		}
		catch(NullPointerException e)
		{
			System.out.println(e);
		}
	}
}
