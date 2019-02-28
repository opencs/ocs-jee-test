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

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the base class for all entity unit tests.
 * 
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @version 2019.02.24
 */
public abstract class BaseEntityTest {
	
	protected final Logger logger;
	
	/**
	 * The entity manager.
	 */
	protected EntityManager em;
	
	/**
	 * The value used to generate unique sequential numbers.
	 */
	private long sequence =  System.nanoTime();
	
	protected BaseEntityTest() {
		this.logger = LoggerFactory.getLogger(this.getClass());
	}
	
	/**
	 * Generates a unique long for each call.
	 * 
	 * @return The next long.
	 */
	protected synchronized long nextSequence() {
		sequence++;
		return sequence;
	}

	/**
	 * Verifies if the test must be executed.
	 */
	@BeforeClass
	public static void beforeClass() {
		org.junit.Assume.assumeTrue(DBStub.shoudRun());
	}
	
	/**
	 * Acquires an entity manager for each test.
	 */
	@Before
	public void before() {
		em = DBStub.getEntityManager();
	}
	
	/**
	 * Disposes the entity manager used by the tests.
	 */
	@After
	public void after() {
		if (em != null) {
			em.close();
		}
	}
}
