package com.cmcc.bean;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;

	public UserNotFoundException(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
