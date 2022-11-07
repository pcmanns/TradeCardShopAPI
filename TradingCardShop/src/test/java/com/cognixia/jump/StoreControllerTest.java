package com.cognixia.jump;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.controller.StoreController;
import com.cognixia.jump.model.Cards;
import com.cognixia.jump.repository.StoreRepository;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.StoreService;
import com.cognixia.jump.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers =StoreController.class)
@ContextConfiguration
@WithMockUser(value="pcmanns",roles = {"ADMIN"})
public class StoreControllerTest {

	@MockBean
	StoreService service;
	
	@MockBean
	StoreRepository repo;
	
	@MockBean
	MyUserDetailsService myUserDetailsService;
	
	@MockBean
	JwtUtil jwrUtil;
	
	@InjectMocks
	StoreController controller;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testGetAllStuedents() throws Exception {
		String uri = "/api/store";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status =mvcResult.getResponse().getStatus();
		assertEquals(200,status);
		
	}
	
	@Test
	public void testAddCard() throws Exception {
		String uri = "/api/store/card/add";
		Cards card= new Cards("test","manaCost", "keywords", "power", "toughness", 5,5.2);
		String store="store";
		
		when(service.addCard(card,store)).thenReturn(card);
		mockMvc.perform(post(uri).content(asJsonString(card))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isForbidden());
	
		
	}

	// converts any object to a JSON string
		public static String asJsonString(final Object obj) {
			
			try {
				return new ObjectMapper().writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			
		}
}
