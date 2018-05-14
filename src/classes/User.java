package classes;

import java.util.List;

public class User extends DataObject{
	public static User loged_in_user = null;
	
	public User(){
		super("users");
	}
	
	public static String log_in_user(String username, String password){
		User_factory user_factory = new User_factory();
		if (username != null){
			List<User> users = user_factory.getByColumn("username", username);
			if (users != null){
				User user = users.get(0);
				if (user != null && user.get_data("password").equals(password)){
					loged_in_user = user;
					return user.get_data("id");
				}
			}
		}
		return "";
	}
}
