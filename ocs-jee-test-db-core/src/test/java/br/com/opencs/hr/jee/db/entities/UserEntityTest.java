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
package br.com.opencs.hr.jee.db.entities;

import java.util.Date;

import javax.persistence.Query;

import org.junit.Test;

public class UserEntityTest extends BaseEntityTest {
	
	public void deleteAll() {
		
		em.getTransaction().begin();
		Query query = em.createQuery("delete from UserEntity");
		query.executeUpdate();
		em.getTransaction().commit();
	}
	
	public void loadSample() {
		
		em.getTransaction().begin();
		for (int i = 0; i < 10; i++) {
			UserEntity user = new UserEntity();
			user.setCreationDate(new Date());
			user.setEmail("email" + i);
			user.setName("name" + i);
			em.persist(user);
		}
		em.getTransaction().commit();
	}


	@Test
	public void testInsert() {
	
		em.getTransaction().begin();
		UserEntity user = new UserEntity();
		user.setCreationDate(new Date());
		user.setEmail("email" + System.nanoTime());
		user.setName("name");
		em.persist(user);
		System.out.println(user.getUserId());
		em.getTransaction().commit();
	}
	
	@Test
	public void testQueryByEmail() {
	
		loadSample();

		
	
	
	}

}
