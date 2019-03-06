/*
 * BSD 3-Clause License
 * 
 * Copyright (c) 2019, Open Communications Security
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * 
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * 
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.opencs.hr.jee.core.validators;

/**
 * This class implements all user validators.
 * 
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @version 2019.02.27
 */
public class UserValidator {

	/**
	 * Maximum length of the name.
	 */
	public static int MAX_NAME_LENGTH = 256;
	
	/**
	 * Maximum length of an email. It is based on the formal definition
	 * found in RFC5322.
	 */
	public static int MAX_EMAIL_LENGTH = 64 + 1 + 255;
	
	/**
	 * A regular expressing that matches a string with at least one non-blank character.
	 */
	protected static String NAME_PATTERN = ".*\\S.*";
	
	/**
	 * A regular expressing that matches a common email syntax.
	 */
	protected static String EMAIL_PATTERN = ".+@.+";	
	
	/**
	 * Verifies if a given string is empty. Null values are also considered
	 * empty.
	 * 
	 * @param s The string to be tested.
	 * @return true if the string is empty or false otherwise.
	 */
	public static boolean isEmptyString(String s) {
		return (s == null) || (s.isEmpty());
	}
	
	private static boolean checkString(String s, int maxLength, String pattern) {
		
		if (isEmptyString(s)) {
			return false;
		}
		if (s.length() > maxLength) {
			return false;
		}
		return s.matches(pattern);
	}

	/**
	 * Verifies if a given email is valid. It must match the email syntax
	 * defined in the RFC5322.
	 * 
	 * @param email The email to be tested.
	 * @return True if it is a valid email or false otherwise.
	 */
	public static boolean isValidEMail(String email) {
		return checkString(email, MAX_EMAIL_LENGTH, EMAIL_PATTERN);
	}
	
	/**
	 * Verifies if a given name is valid. A name is considered valid if
	 * it has at least one non blank character.
	 * 
	 * @param name The name to be tested.
	 * @return true if the name is valid or false otherwise.
	 */
	public static boolean isValidName(String name) {
		return checkString(name, MAX_NAME_LENGTH, NAME_PATTERN);
	}
}
