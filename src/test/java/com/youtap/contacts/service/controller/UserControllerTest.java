package com.youtap.contacts.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtap.contacts.service.business.UserService;
import com.youtap.contacts.service.dao.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URL;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
 class UserControllerTest {

	@Autowired
	private UserController userController;
	@Autowired
	private MockMvc        mockMvc;
	@MockBean
	private UserService userService;

	@Test
	 void getUserByIdExistsSuccessTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		 UserResponseDto userResponse =
				objectMapper.readValue(new URL("file:src/test/resources/simulate/sample_user_by_userid.json"),
						UserResponseDto.class);

		when(userService.getUser(anyString())).thenReturn(userResponse);
		mockMvc.perform(MockMvcRequestBuilders.get("/getusercontacts/5"))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("id").value(5))
				.andExpect(MockMvcResultMatchers.jsonPath("email").value("Lucio_Hettinger@annie.ca"))
				.andExpect(MockMvcResultMatchers.jsonPath("phone").value("(254)954-1289"))
				.andExpect(status().isOk());

	}

	@Test
	 void getUserByUsernameExistsSuccessTest() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		 UserResponseDto userResponse =
				objectMapper.readValue(new URL("file:src/test/resources/simulate/sample_user_by_username.json"),
						UserResponseDto.class);

		when(userService.getUser(anyString())).thenReturn(userResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/getusercontacts/Leopoldo_Corkery"))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("id").value(6))
				.andExpect(MockMvcResultMatchers.jsonPath("email").value("Karley_Dach@jasper.info"))
				.andExpect(MockMvcResultMatchers.jsonPath("phone").value("1-477-935-8478 x6430"))
				.andExpect(status().isOk());

	}

	@Test
	 void getUserByIdNotFoundTest() throws Exception {

		UserResponseDto userResponse = new UserResponseDto();

		userResponse.setId((long) -1);

		when(userService.getUser(anyString())).thenReturn(userResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/getusercontacts/16"))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("id").value(-1))
				.andExpect(status().isOk());

	}

	@Test
	 void getUserByUsernameNotFoundTest() throws Exception {

		UserResponseDto userResponse = new UserResponseDto();

		userResponse.setId((long) -1);

		when(userService.getUser(anyString())).thenReturn(userResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/getusercontacts/Peter_Togara"))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("id").value(-1))
				.andExpect(status().isOk());

	}
}
