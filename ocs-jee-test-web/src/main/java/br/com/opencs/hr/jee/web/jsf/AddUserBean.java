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
package br.com.opencs.hr.jee.web.jsf;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.opencs.hr.jee.core.dto.UserDTO;
import br.com.opencs.hr.jee.core.services.interfaces.ServiceException;
import br.com.opencs.hr.jee.core.services.interfaces.UserService;
import br.com.opencs.hr.jee.core.validators.UserValidator;

/**
 * Implementation of the addUserBean.
 * 
 * @author Fabio Jun Takada Chino <fjtc@users.noreply.github.com>
 * @version 2019.02.27
 */
@ManagedBean(name="addUserBean")
@ViewScoped
public class AddUserBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	private String name;
	 
	private String email;

	@EJB
	private UserService userService;
	
	/**
	 * Clears the fields.
	 * 
	 * @return The next action.
	 */	
	public String doClear() {
		
		this.email = "";
		this.name = "";
		return null;
	}

	/**
	 * Adds the new user.
	 * 
	 * @return The next action.
	 */
	public String doAdd() {
		
		if (!UserValidator.isValidEMail(this.getEmail())) {
			this.showMessage(FacesMessage.SEVERITY_ERROR, "form", "Invalid email.");
			return null; 
		}
		if (!UserValidator.isValidName(this.getName())) {
			this.showMessage(FacesMessage.SEVERITY_ERROR, "form", "Invalid name.");
			return null;
		}
		
		UserDTO user = new UserDTO(); 
		user.setName(getName());
		user.setEmail(getEmail());
		try {
			userService.addUser(user);
			this.doClear();
			this.showMessage(FacesMessage.SEVERITY_INFO, "form", "User added.");
		} catch (ServiceException e) {
			this.showMessage(FacesMessage.SEVERITY_ERROR, "form", e.getError().name());
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}
}
