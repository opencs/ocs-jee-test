package br.com.opencs.hr.jee.web.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.opencs.hr.jee.core.dto.UserDTO;
import br.com.opencs.hr.jee.core.services.interfaces.UserService;

@ManagedBean(name="listUsersBean")
@ViewScoped
public class ListUsersBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;
	
	private List<UserDTO> users;
	
	@PostConstruct
	public void postConstrut() {
		doUpdate();
	}
	
	public void doUpdate() {
		this.users = userService.listUsers();
	}

	public List<UserDTO> getUsers() {
		return users;
	}

}
