package com.youtap.contacts.service.controller;

import com.youtap.contacts.service.business.UserService;
import com.youtap.contacts.service.dao.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping(value = "/getusercontacts/{usernameOrUserId}")
	public ResponseEntity<UserResponseDto> getUserContacts(@PathVariable final String usernameOrUserId) {

		return new ResponseEntity<>(userService.getUser(usernameOrUserId), HttpStatus.OK);
	}


}
