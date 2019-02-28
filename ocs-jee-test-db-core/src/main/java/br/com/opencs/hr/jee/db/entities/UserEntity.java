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

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * This class implements the user entity of this JPA unit.
 * 
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @since 2019.02.24
 */
@Entity
@Cacheable(true)
@Table(name="users_")
@NamedQueries({
		@NamedQuery(name=UserEntity.LIST_ALL_QUERY, query="select u from UserEntity u"),
		@NamedQuery(name=UserEntity.FIND_BY_EMAIL_QUERY, query="select u from UserEntity u where u.email=:email"),
		@NamedQuery(name=UserEntity.EMAIL_USED_QUERY, query="select count(u) from UserEntity u where u.email=:email"),
})
public class UserEntity {
	
	public static final String LIST_ALL_QUERY = "br.com.opencs.hr.jee.db.entities.UserEntity.LIST_ALL_QUERY";
	public static final String FIND_BY_EMAIL_QUERY = "br.com.opencs.hr.jee.db.entities.UserEntity.FIND_BY_EMAIL_QUERY";
	public static final String EMAIL_USED_QUERY  = "br.com.opencs.hr.jee.db.entities.UserEntity.EMAIL_USED_QUERY";
	
	@Id
	@TableGenerator(name="USERID_GEN", table="generators", 
		pkColumnName="name", valueColumnName="value", pkColumnValue="USERID", 
		initialValue=1, allocationSize=15)
	@GeneratedValue(generator="USERID_GEN", strategy=GenerationType.TABLE)
	@Column(name="userid", nullable=false, updatable=false)
	private long userId;
	
	@Column(name="email", nullable=false, unique=true, length=128, updatable=true)
	private String email;
	
	@Column(name="name", nullable=false, unique=false, length=256, updatable=true)
	private String name;
	
	@Column(name="creationdate", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name="updatedate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Version
	@Column(name="version", nullable=false, unique=false)
	private long version;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
