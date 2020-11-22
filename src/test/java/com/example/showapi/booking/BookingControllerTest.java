package com.example.showapi.booking;

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
	public void testBookShow() throws Exception {
		// Build the seats.
		List<Integer> seats = new ArrayList<>();
		// Seats 1 and 2 belong to section 1.
		seats.add(1);
		seats.add(2);
		Map<String, List<Integer>> sectionAndSeats = new HashMap<>();
		sectionAndSeats.put("1", seats);
		// Seat 6 belongs to section 2.
		seats = new ArrayList<>();
		seats.add(6);
		sectionAndSeats.put("2", seats);

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
				.andExpect(status().isCreated()).andExpect(jsonPath("$.name").value(request.getName()))
				.andExpect(jsonPath("$.dni").value(request.getDni()))
				.andExpect(jsonPath("$.showId").value(request.getShowId()))
				.andExpect(jsonPath("$.playId").value(request.getPlayId())).andExpect(jsonPath("$.seats").isArray())
				.andExpect(jsonPath("$.seats.length()").value(3)).andExpect(jsonPath("$.total").value(290.0f));

		// Expected ticket as result:
		//	    {
		//		  "id": "5fb92df194c7a65a30beb46d",
		//		  "name": "Diego Fons",
		//		  "dni": "28142035",
		//		  "timestamp": "2020-11-21T15:10:41.456+00:00",
		//		  "showId": "1",
		//		  "playId": "1",
		//		  "seats": [
		//		    {
		//		      "1": 100
		//		    },
		//		    {
		//		      "2": 100
		//		    },
		//		    {
		//		      "6": 90
		//		    }
		//		  ],
		//		  "total": 290,
		//		  "links": [
		//		    {
		//		      "rel": "self",
		//		      "href": "http://localhost/api/bookings/5fb92df194c7a65a30beb46d"
		//		    }
		//		  ]
		//		}

		// If we perform the same booking, it must fail.
		mvc.perform(post("/api/bookings").content(objectMapper.writeValueAsString(request))
				.accept(BookingController.MediaType.TICKET_RESPONSE)
				.contentType(BookingController.MediaType.BOOKING_REQUEST)).andExpect(status().isNotAcceptable());

	}

}
