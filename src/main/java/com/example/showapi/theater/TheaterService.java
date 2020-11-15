package com.example.showapi.theater;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.theater.domain.Seat;
import com.example.showapi.theater.domain.Theater;

public interface TheaterService {

	public Theater getById( String id );
	
	public Page<Theater> findAll( Pageable paging );
	
	public List<Seat> getSeatsBySection( String sectionId );
}
