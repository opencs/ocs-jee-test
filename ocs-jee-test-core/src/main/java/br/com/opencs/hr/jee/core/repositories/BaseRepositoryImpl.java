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

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

public class BaseRepositoryImpl<EntityClass, KeyClass> implements BaseRepository<EntityClass, KeyClass> {
	
	protected final Logger logger;
	
	protected final Class<EntityClass> entityClass;
	
	protected final Class<KeyClass> keyClass;
	
	@PersistenceContext(name="ocs-jee-test")
	protected EntityManager em;
	
	protected BaseRepositoryImpl(Class<EntityClass> entityClass, Class<KeyClass> keyClass, Logger logger) {
		this.entityClass = entityClass;
		this.keyClass = keyClass;
		this.logger = logger;
	}

	@Override
	public void flush() {
		em.flush();
	}

	@Override
	public EntityClass find(KeyClass key) {
		return find(key, LockModeType.NONE);
	}
	
	@Override
	public EntityClass find(KeyClass key, LockModeType lockMode) {
		return em.find(this.entityClass, key, lockMode);
	}
	
	@Override
	public void persist(EntityClass entity) {
		em.persist(entity);
	}
	
	@Override
	public void reload(EntityClass entity) {
		this.reload(entity, LockModeType.NONE);
	}
	
	@Override
	public void reload(EntityClass entity, LockModeType lockMode) {
		em.refresh(entity, lockMode);
	}
	
	@Override
	public void delete(EntityClass entity) {
		em.remove(entity);
	}
}
