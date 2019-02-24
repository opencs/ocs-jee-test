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
package br.com.opencs.hr.jee.core.repositories;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.opencs.hr.jee.db.entities.UserEntity;

@Stateless
public class UserRepositoryImpl extends BaseRepositoryImpl<UserEntity, Long> implements UserRepository{

	public UserRepositoryImpl() {
		super(UserEntity.class, Long.class, Logger.getLogger(UserRepositoryImpl.class.getName()));
	}
	
	public UserEntity findByEmail(String email) {
		
		TypedQuery<UserEntity> query = em.createNamedQuery(UserEntity.FIND_BY_EMAIL_QUERY, UserEntity.class);
		query.setParameter("email", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<UserEntity> listAll() {
		TypedQuery<UserEntity> query;
		
		query = em.createNamedQuery(UserEntity.LIST_ALL_QUERY, UserEntity.class);
		return query.getResultList();
	}
}
