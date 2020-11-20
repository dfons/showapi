package com.example.showapi.booking;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.showapi.booking.domain.Ticket;
import com.example.showapi.booking.request.BookingRequest;
import com.example.showapi.booking.resource.TicketResource;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "/bookings", description = "Bookings API endpoints")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping(path = "/{ticketId}", produces = { MediaType.TICKET_RESPONSE })
	public ResponseEntity<TicketResource> getTicketById(@PathVariable String ticketId) throws Exception {
		Ticket ticket = this.bookingService.getById(ticketId);
		if (ticket == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		final TicketResource response = TicketResource.toResource(ticket);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = "", produces = { MediaType.PAGED_TICKET_RESPONSE })
	public ResponseEntity<PagedModel<TicketResource>> getAllShows(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) throws Exception {
		Pageable paging = PageRequest.of(page, size);

		// Get tickets
		Page<Ticket> pagedTickets = this.bookingService.findAll(paging);
		// Build Page Resources
		Link selfLink = linkTo(BookingController.class).withSelfRel();
		PageMetadata metadata = new PageMetadata(pagedTickets.getSize(), pagedTickets.getNumber(),
				pagedTickets.getTotalElements(), pagedTickets.getTotalPages());

		PagedModel<TicketResource> response = PagedModel.of(TicketResource.toResource(pagedTickets.getContent()),
				metadata, selfLink);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "", produces = { MediaType.TICKET_RESPONSE }, consumes = { MediaType.BOOKING_REQUEST })
	public ResponseEntity<TicketResource> bookShow(@RequestBody BookingRequest request) {
		Ticket ticket = bookingService.bookShow(request);
		if (ticket == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}

		final TicketResource response = TicketResource.toResource(ticket);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * The type MediaType.
	 */
	public final static class MediaType {
		public static final String TICKET_RESPONSE = "application/ticket-response-v1-hal+json";
		public static final String PAGED_TICKET_RESPONSE = "application/paged-ticket-response-v1-hal+json";
		public static final String BOOKING_REQUEST = "application/booking-request-v1-hal+json";
	}

}
