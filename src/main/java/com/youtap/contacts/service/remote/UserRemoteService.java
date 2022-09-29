package com.youtap.contacts.service.remote;

import com.youtap.contacts.service.dao.User;
import com.youtap.contacts.service.exception.UserNotFoundException;

import java.util.List;

public interface UserRemoteService {

	List<User> listUsers() throws UserNotFoundException;
}
