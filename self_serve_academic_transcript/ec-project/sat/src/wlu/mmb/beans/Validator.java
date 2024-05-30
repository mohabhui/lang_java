package wlu.mmb.beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// validate name, email and password
// name, email and password should be at least 4 character long
// email should be valid
// name and email should not be used as password
public class Validator {

	public Validator() {}
	
	public static boolean isValid(String name, String email, String password) {
	
		if(name != "name" && name.length()>3 && email !="email" && email.length()>3 && password !="password" && password.length()>3 && password.compareTo(name) != 0 && password.compareTo(email) != 0) {
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher matcher = pattern.matcher(email);
			boolean matches = matcher.matches();
			if(matches) {
				return true;
				//out.print("<h4 style='color:green'>SUCCESS!</h4>");
			}else {
				return false;
				//out.print("<h4 style='color:red'>Invalid Email; Nothing Changed!</h4>");
			}
			
		}else {
			return false;
			//out.print("<h4 style='color:red'>Invalid Name, Email or Password</h4>");
			//out.print("<h4 style='color:red'>Min 4-character is required in each field</h4>");
			//out.print("<h4 style='color:red'>Nothing Changed!</h4>");
		}
		
		
		
		
		
	}// function end




}// class end
