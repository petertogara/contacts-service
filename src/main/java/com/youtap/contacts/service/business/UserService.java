package com.youtap.contacts.service.business;

import com.youtap.contacts.service.dao.User;
import com.youtap.contacts.service.dao.UserResponseDto;

import java.util.List;

public interface UserService {

	UserResponseDto getUser(final String usernameOrUserId);

	List<User> listUsers();


}
