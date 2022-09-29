package com.youtap.contacts.service.remote;

import com.youtap.contacts.service.dao.User;
import com.youtap.contacts.service.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRemoteServiceImpl implements UserRemoteService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
public static final String USER_NOT_FOUND = "No user contacts found";

	private final RestTemplate restTemplate;
	@Value("${youtap.contacts.service.url}")
	private       String       url;

	@Autowired
	public UserRemoteServiceImpl(RestTemplate restTemplate) {

		this.restTemplate = restTemplate;
	}

	@Override
	public List<User> listUsers() throws UserNotFoundException {

		try {

			List<User>	users = Arrays.stream(getListOfUsers()).collect(Collectors.toList());
			if (!users.isEmpty()) {
				return users;
			} else throw new UserNotFoundException(USER_NOT_FOUND);

		} catch (UserNotFoundException userNotFoundException) {
			logger.error(USER_NOT_FOUND , userNotFoundException.getMessage());
			throw new UserNotFoundException(USER_NOT_FOUND);

		}

	}

	private User[] getListOfUsers() {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

		HttpEntity<User[]> httpEntity = new HttpEntity<>(getHeaders());
		User[] userList = null;

		try {
			userList = restTemplate.exchange(builder.build().toUriString(), HttpMethod.GET,
					httpEntity, User[].class).getBody();

		} catch (UserNotFoundException userNotFoundException) {
			logger.error(USER_NOT_FOUND);
		}

		return userList;

	}

	private HttpHeaders getHeaders() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		return headers;
	}

}
