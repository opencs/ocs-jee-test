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
package br.com.opencs.hr.jee.core.repositories.utils;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import br.com.opencs.hr.jee.core.dto.UserDTO;
import br.com.opencs.hr.jee.db.entities.UserEntity;

public class UserEntityUtilTest {

	@Test
	public void testCreateUserEntity() {
		UserEntity e;
		
		e = UserEntityUtil.createUserEntity("email", "name", "passwordHash");
		assertNotNull(e);
		assertEquals("email", e.getEmail());
		assertEquals("name", e.getName());
		assertEquals("passwordHash", e.getPasswordHash());
		assertTrue(Math.abs(e.getCreationDate().getTime() - System.currentTimeMillis()) < 1);
		assertEquals(e.getCreationDate(), e.getUpdateDate());
		
		e = UserEntityUtil.createUserEntity("email", "name", null);
		assertNotNull(e);
		assertEquals("email", e.getEmail());
		assertEquals("name", e.getName());
		assertNull(e.getPasswordHash());
		assertTrue(Math.abs(e.getCreationDate().getTime() - System.currentTimeMillis()) < 1);
		assertEquals(e.getCreationDate(), e.getUpdateDate());		
	}

	@Test
	public void testToDTO() {
		UserEntity e;
		UserDTO d;
		
		e = UserEntityUtil.createUserEntity("email", "name", "passwordHash");
		e.setUpdateDate(new Date(System.currentTimeMillis() + 10));
		
		d = UserEntityUtil.toDTO(e, false);
		assertNotNull(d);
		assertEquals(e.getEmail(), d.getEmail());
		assertEquals(e.getName(), d.getName());
		assertEquals(e.getCreationDate(), d.getCreationDate());
		assertNotSame(e.getCreationDate(), d.getCreationDate());
		assertEquals(e.getUpdateDate(), d.getUpdateDate());
		assertNotSame(e.getUpdateDate(), d.getUpdateDate());
		assertNull(d.getPasswordHash());
		
		d = UserEntityUtil.toDTO(e, true);
		assertNotNull(d);
		assertEquals(e.getEmail(), d.getEmail());
		assertEquals(e.getName(), d.getName());
		assertEquals(e.getCreationDate(), d.getCreationDate());
		assertNotSame(e.getCreationDate(), d.getCreationDate());
		assertEquals(e.getUpdateDate(), d.getUpdateDate());
		assertNotSame(e.getUpdateDate(), d.getUpdateDate());
		assertEquals(e.getPasswordHash(), d.getPasswordHash());
	}
}
