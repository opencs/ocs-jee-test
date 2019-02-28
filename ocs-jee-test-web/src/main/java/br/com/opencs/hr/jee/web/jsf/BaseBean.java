package br.com.opencs.hr.jee.web.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class BaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected void showMessage(Severity severity, String message) {
		showMessage(severity, null, message);		
	}
	
	protected void showMessage(Severity severity, String clientId, String message) {
        FacesMessage msg = new FacesMessage(message);
        msg.setSeverity(severity);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form", msg);
	}
}
