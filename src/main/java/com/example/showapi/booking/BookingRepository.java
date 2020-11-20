package com.example.showapi.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.showapi.booking.domain.Ticket;

@Repository
public interface BookingRepository extends MongoRepository<Ticket, String> {

	public Ticket getById(String ticketId);

	@Override
	public Page<Ticket> findAll(Pageable paging);

}
