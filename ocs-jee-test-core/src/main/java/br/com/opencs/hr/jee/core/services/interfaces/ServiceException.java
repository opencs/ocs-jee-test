package br.com.opencs.hr.jee.core.services.interfaces;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private ServiceError error;
	
	public ServiceException(ServiceError error) {
		super(error.toString());
		this.error = error;
	}
	
	public ServiceError getError() {
		return this.error;
	}
}
