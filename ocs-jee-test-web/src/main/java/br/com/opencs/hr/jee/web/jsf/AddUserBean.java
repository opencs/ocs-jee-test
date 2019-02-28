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
	
	
	public String doClear() {
		
		this.email = "";
		this.name = "";
		return null;
	}
	
	public String doAdd() {
		
		if (!UserValidator.isValidEMail(this.getEmail())) {
			this.showMessage(FacesMessage.SEVERITY_ERROR, "Invalid email.");
			return null;
		}
		if (!UserValidator.isValidName(this.getName())) {
			this.showMessage(FacesMessage.SEVERITY_ERROR, "Invalid name.");
			return null;
		}
		
		UserDTO user = new UserDTO();
		user.setName(getName());
		user.setEmail(getEmail());
		try {
			userService.addUser(user);
			this.doClear();
			this.showMessage(FacesMessage.SEVERITY_INFO, "User added.");
		} catch (ServiceException e) {
			this.showMessage(FacesMessage.SEVERITY_ERROR, e.getError().name());
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
