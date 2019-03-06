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

import java.util.Date;

import br.com.opencs.hr.jee.core.dto.UserDTO;
import br.com.opencs.hr.jee.db.entities.UserEntity;

/**
 * This class implements the UserEntity tools.
 *  
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @version 2019.03.05
 */
public class UserEntityUtil {

	/**
	 * Creates a new UserEntity. The creation date and the update date will be set
	 * to the current time.
	 *  
	 * @param email The email.
	 * @param name The name.
	 * @param passwordHash The password hash. May be null.
	 * @return The new entity instance.
	 */
	public static UserEntity createUserEntity(String email, String name, String passwordHash) {
		UserEntity entity = new UserEntity();
		Date now = new Date();
		
		entity.setName(name);
		entity.setEmail(email);
		entity.setCreationDate(now);
		entity.setUpdateDate(now);
		entity.setPasswordHash(passwordHash);
		return entity;
	}
	
	/**
	 * Creates a new UserDTO from a given entity. 
	 * 
	 * @param src The source entity.
	 * @param includePrivate A flag that indicates if private fields must 
	 * be copied.
	 * @return The new DTO.
	 */
	public static UserDTO toDTO(UserEntity src, boolean includePrivate) {
		UserDTO dto = new UserDTO();
		
		dto.setEmail(src.getEmail());
		dto.setName(src.getName());
		dto.setCreationDate((Date)src.getCreationDate().clone());
		dto.setUpdateDate((Date)src.getUpdateDate().clone());
		if (includePrivate) {
			dto.setPasswordHash(src.getPasswordHash());
		}
		return dto;
	}
}
