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

import static org.junit.Assert.*;

import org.junit.Test;

public class UserValidatorTest {

	@Test
	public void testIsEmptyString() {
		
		assertTrue(UserValidator.isEmptyString(""));
		assertTrue(UserValidator.isEmptyString(null));
		assertFalse(UserValidator.isEmptyString(" "));
	}

	@Test
	public void testIsValidEMail() {
		
		assertTrue(UserValidator.isValidEMail("ur@d"));
		assertTrue(UserValidator.isValidEMail("user@domain"));
	}

	@Test
	public void testIsValidName() {

		assertTrue(UserValidator.isValidName("."));
		assertTrue(UserValidator.isValidName(" . "));
		assertTrue(UserValidator.isValidName("a"));
		assertFalse(UserValidator.isValidName(" "));
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < UserValidator.MAX_NAME_LENGTH; i++) {
			sb.append('a');
		}
		assertTrue(UserValidator.isValidName(sb.toString()));

		sb.append('a');
		assertFalse(UserValidator.isValidName(sb.toString()));
	}
}
