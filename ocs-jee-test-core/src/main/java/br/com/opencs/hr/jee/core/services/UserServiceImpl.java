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
package br.com.opencs.hr.jee.core.services;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.opencs.hr.jee.core.dto.UserDTO;
import br.com.opencs.hr.jee.core.repositories.UserRepository;
import br.com.opencs.hr.jee.core.services.interfaces.UserService;
import br.com.opencs.hr.jee.db.entities.UserEntity;

@Stateless
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	
	@EJB
	private UserRepository userRepository;
	
	
	private UserDTO entityToDTO(UserEntity userEntity) {
		UserDTO dto = new UserDTO();

		dto.setUserId(userEntity.getUserId());
		dto.setEmail(userEntity.getEmail());
		dto.setName(userEntity.getName());
		dto.setUpdateDate(userEntity.getUpdateDate());
		dto.setCreationDate(userEntity.getCreationDate());
		return dto;
	}
	
	public UserDTO findUserByEmail(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			return null;
		} else {
			return entityToDTO(userEntity);
		}
	}
	
	public void addUser(UserDTO user) {
		
		// Check user entity
		
		
	}
	
	
	public List<UserDTO> listUsers() {
		return null;
	}
	
	public void updateUser(UserDTO user, UserDTO newUserValues) {
		
	}
}
