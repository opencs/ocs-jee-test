package br.com.opencs.hr.jee.core.validators;

/**
 * This class implements all user validators.
 * 
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @version 2019.02.27
 */
public class UserValidator {

	public static int MAX_NAME_LENGTH = 256;
	
	public static int MAX_EMAIL_LENGTH = 128;
	
	public static boolean isEmptyString(String s) {
		return (s == null) || (s.isEmpty());
	}
	
	public static boolean isValidEMail(String email) {
		// TODO Check if the email is a valid email pattern.
		return !isEmptyString(email) && (email.length() <= MAX_EMAIL_LENGTH);
	}
	
	public static boolean isValidName(String name) {
		return !isEmptyString(name) && (name.length() <= MAX_NAME_LENGTH);
	}
	
}
