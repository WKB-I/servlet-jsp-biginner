package model;

public class LoginLogic {
	public boolean exucute(User user) {
		if(user.getPass().equals("1234")) {return true;}
		return false;
	}

}
