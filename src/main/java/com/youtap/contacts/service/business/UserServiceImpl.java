package com.youtap.contacts.service.business;

import com.youtap.contacts.service.exception.UserNotFoundException;
import com.youtap.contacts.service.dao.User;
import com.youtap.contacts.service.dao.UserResponseDto;
import com.youtap.contacts.service.remote.UserRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {


	private final Logger     logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final      UserRemoteService userRemoteService;

	@Autowired
	public UserServiceImpl(UserRemoteService userRemoteService) {

		this.userRemoteService = userRemoteService;
	}

	@Override
	public UserResponseDto getUser(String usernameOrUserId) {

		List<User> userList = listUsers();

		User userResponseObject;
		UserResponseDto userResponse = new UserResponseDto();

		if (userList != null || !userList.isEmpty()) {
			if (isNumber(usernameOrUserId)) {
				userResponseObject =
						userList.stream().filter(user -> user.getId() == Long.parseLong(usernameOrUserId)).findFirst().orElse(null);

			} else {
				userResponseObject =
						userList.stream().filter(user -> user.getUsername().equalsIgnoreCase(usernameOrUserId)).findFirst().orElse(null);

			}

			if (userResponseObject != null) {

				userResponse.setId(userResponseObject.getId());
				userResponse.setEmail(userResponseObject.getEmail());
				userResponse.setPhone(userResponseObject.getPhone());

				return userResponse;
			}
			userResponse.setId((long) -1);

		}

		return userResponse;
	}

	@Override
	public List<User> listUsers() {


		List<User> userDetails = null;
		try {
			userDetails = userRemoteService.listUsers();
		} catch (UserNotFoundException userNotFoundException) {
			logger.error("Exception while trying to process user contact request {} ",
					userNotFoundException.getMessage());
		}


		return userDetails;
	}

	private boolean isNumber(String value) {

		try {
			Long.parseLong(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


}
