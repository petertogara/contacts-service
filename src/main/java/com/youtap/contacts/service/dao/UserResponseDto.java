package com.youtap.contacts.service.dao;

public class UserResponseDto {

	private Long   id;
	private String email;
	private String phone;

	public UserResponseDto() {

	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getPhone() {

		return phone;
	}

	public void setPhone(String phone) {

		this.phone = phone;
	}
}
