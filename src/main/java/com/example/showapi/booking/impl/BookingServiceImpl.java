package com.example.showapi.booking.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.showapi.booking.BookingRepository;
import com.example.showapi.booking.BookingService;
import com.example.showapi.booking.domain.Ticket;
import com.example.showapi.booking.request.BookingRequest;
import com.example.showapi.show.ShowRepository;
import com.example.showapi.show.domain.Play;
import com.example.showapi.show.domain.Play.Price;
import com.example.showapi.show.domain.Show;
import com.example.showapi.theater.Section2Repository;
import com.example.showapi.theater.request.SectionBookingRequest;

@Component
public class BookingServiceImpl implements BookingService {

	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private Section2Repository sectionRepository;

	@Override
	public Ticket getById(String ticketId) {
		logger.info("Looking for Booking with ID {}", ticketId);
		Ticket ticket = bookingRepository.getById(ticketId);
		return ticket;
	}

	@Override
	public Page<Ticket> findAll(Pageable paging) {
		logger.info("Looking for all Bookings");
		Page<Ticket> tickets = bookingRepository.findAll(paging);
		return tickets;
	}

	@Override
	public Ticket bookShow(BookingRequest request) {
		List<SectionBookingRequest> bookings = new ArrayList<>();
		for (Map.Entry<String, List<Integer>> entry : request.getSeats().entrySet()) {
			SectionBookingRequest bookingRequest = new SectionBookingRequest();
			bookingRequest.sectionId = entry.getKey();
			bookingRequest.seats = entry.getValue();
			bookingRequest.status = false;

			bookings.add(bookingRequest);
		}

		Boolean result = sectionRepository.updateSections(bookings);
		if (!result) {
			return null;
		}

		Ticket ticket = new Ticket();
		ticket.setDni(request.getDni());
		ticket.setName(request.getName());
		ticket.setTimestamp(request.getTimestamp());
		
		// Retrieve the show.
		Show show = showRepository.getShowById(request.getShowId());
		if (show == null) {
			return null;
		}
		ticket.setShowId(request.getShowId());

		// Retrieve the play so we can get the sections and seats.
		Play play = show.getPlays().stream().filter(item -> request.getPlayId().equals(item.getId())).findAny()
				.orElse(null);
		if (play == null) {
			return null;
		}
		ticket.setPlayId(request.getPlayId());
		
		// Map storing (section, price).
		Map<String, Float> prices = new HashMap<>();
		for (Price price : play.getPrices()) {
			// We don't have two sections with the same ID for a given play.
			prices.put(price.sectionId, price.price);
		}
		
		// Get the bought seats and their cost.
		List<Map<Integer, Float>> ticketSeats = new ArrayList<>();
		Float total = 0.0f;
		for (Map.Entry<String, List<Integer>> seat : request.getSeats().entrySet()) {
			Map<Integer, Float> ticketSeatPrices = new HashMap<>();

			String setionId = seat.getKey();
			List<Integer> seatsBySection = seat.getValue();
			Float priceBySection = prices.get(setionId);

			total += seatsBySection.size() * priceBySection;
			
			for (Integer item : seatsBySection) {
				ticketSeatPrices.put(item, priceBySection);
			}
			ticketSeats.add(ticketSeatPrices);
		}
		ticket.setSeats(ticketSeats);
		ticket.setTotal(total);

		return bookingRepository.save(ticket);
	}

}
