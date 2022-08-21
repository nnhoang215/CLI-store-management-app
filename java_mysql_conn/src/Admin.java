package src;

import java.util.Map;

public class Admin extends Person{
	public Admin(Map user){
		super(
			(Integer) user.get("userID"),
			(String) user.get("username"),
			(String) user.get("name"),
			(String) user.get("age"),
			(String) user.get("email"),
			(String) user.get("phone"),
			(Boolean) user.get("isAdmin")
		);
	}
}
