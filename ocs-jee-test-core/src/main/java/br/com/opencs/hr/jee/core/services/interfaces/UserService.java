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
package br.com.opencs.hr.jee.core.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.opencs.hr.jee.core.dto.UserDTO;

/**
 * This is the local interface of the UserService EJB.
 * 
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @since 2019.02.24
 */
@Local
public interface UserService {

	/**
	 * Finds the user by his/her email.
	 * @param email The email.
	 * @return The user found or null if the user does not exist.
	 */
	public UserDTO findUserByEmail(String email);
	
	/**
	 * Adds a new user.
	 * 
	 * @param user The user data to be added.
	 * @exception ServiceException If the user could not be added.
	 */
	public void addUser(UserDTO user) throws ServiceException;
	
	/**
	 * Lists all users inside the database.
	 * 
	 * @return A list of all users inside the database.
	 */
	public List<UserDTO> listUsers();
	
	/**
	 * Updates a given user.
	 * 
	 * @param user The user to be updated.
	 * @param newUserValues The new user data.
	 * throws ServiceException If the user cannot be updated.
	 */
	public void updateUser(UserDTO user, UserDTO newUserValues) throws ServiceException;
}
