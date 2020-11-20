package com.example.showapi.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.booking.domain.Ticket;
import com.example.showapi.booking.request.BookingRequest;

public interface BookingService {

	public Ticket getById(String ticketId);

	public Page<Ticket> findAll(Pageable paging);

	public Ticket bookShow(BookingRequest request);

}
