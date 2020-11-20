package com.example.showapi.booking;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.showapi.BaseIntegrationTest;
import com.example.showapi.booking.request.BookingRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookingControllerTest extends BaseIntegrationTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetAllEmpty() throws Exception {
		mvc.perform(get("/api/bookings")).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray()).andExpect(jsonPath("$.content.length()").value(0));
	}

	@Test
	public void testBookShow() throws Exception {
		// Build the seats.
		List<Integer> seats = new ArrayList<>();
		seats.add(1);
		seats.add(2);
		Map<String, List<Integer>> sectionAndSeats = new HashMap<>();
		sectionAndSeats.put("1", seats);
		
		// Build the request.
		BookingRequest request = new BookingRequest();
		request.setDni("28142035");
		request.setName("Diego Fons");
		request.setPlayId("1");
		request.setShowId("1");
		request.setTimestamp(new Date());
		request.setSeats(sectionAndSeats);
		
		mvc.perform(post("/api/bookings").content(objectMapper.writeValueAsString(request))
				.accept(BookingController.MediaType.TICKET_RESPONSE)
				.contentType(BookingController.MediaType.BOOKING_REQUEST))
				.andExpect(status().isCreated());
	}

}
