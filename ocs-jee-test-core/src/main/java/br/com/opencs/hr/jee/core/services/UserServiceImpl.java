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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.opencs.hr.jee.core.dto.UserDTO;
import br.com.opencs.hr.jee.core.repositories.UserRepository;
import br.com.opencs.hr.jee.core.repositories.utils.UserEntityUtil;
import br.com.opencs.hr.jee.core.services.interfaces.ServiceError;
import br.com.opencs.hr.jee.core.services.interfaces.ServiceException;
import br.com.opencs.hr.jee.core.services.interfaces.UserService;
import br.com.opencs.hr.jee.core.validators.UserValidator;
import br.com.opencs.hr.jee.db.entities.UserEntity;

@Stateless
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@EJB
	private UserRepository userRepository;
	
	@Override
	public UserDTO findUserByEmail(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			logger.info("User with email %1$s not found.", email);
			return null;
		} else {
			return UserEntityUtil.toDTO(userEntity, false);
		}
	}
	
	@Override
	public UserDTO findUserByID(long userId) {

		UserEntity userEntity = userRepository.find(userId);
		if (userEntity == null) {
			logger.info("User with id %1$d not found.", userId);
			return null;
		} else {
			return UserEntityUtil.toDTO(userEntity, false);
		}
	}

	@Override
	public UserDTO addUser(UserDTO user) throws ServiceException {
		
		// Check user entity
		if (!UserValidator.isValidName(user.getName()))  {
			throw new ServiceException(ServiceError.USER_INVALID_NAME);
		}
		if (!UserValidator.isValidEMail(user.getEmail()))  {
			throw new ServiceException(ServiceError.USER_INVALID_EMAIL);
		}
		
		// Look for duplicates
		UserEntity userEntity;
		userEntity = userRepository.findByEmail(user.getEmail());
		if (userEntity != null) {
			logger.info("Email %1$s already used.", user.getEmail());
			throw new ServiceException(ServiceError.USER_EMAIL_ALREADY_USED);
		} else {
			userEntity = UserEntityUtil.createUserEntity(
					user.getEmail(), user.getName(), null);
			userRepository.persist(userEntity);
			logger.info("User %1$s with email %2$s added with ID %3%d.", userEntity.getName(),
					userEntity.getEmail(), userEntity.getUserId());
			user.setUserId(userEntity.getUserId());
			return user;
		}
	}
	
	@Override
	public List<UserDTO> listUsers() {
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
	
		for (UserEntity userEntity: userRepository.listAll()) {
			users.add(UserEntityUtil.toDTO(userEntity, false));
		}
		return users;
	}
	
	public void updateUser(UserDTO user, UserDTO newUserValues) {
		throw new UnsupportedOperationException();
	}
}
