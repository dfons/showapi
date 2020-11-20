package com.example.showapi.show;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.example.showapi.BaseIntegrationTest;

public class ShowControllerTest extends BaseIntegrationTest {

	@Test
	public void testGetByIdOk() throws Exception {
		mvc.perform(get("/api/shows/{id}", "1")).andExpect(status().isOk());
	}

}
