package com.example.showapi.theater;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.showapi.theater.domain.Theater;

public interface TheaterRepository extends MongoRepository<Theater, String> {
	
	public Theater getTheaterById( String id );
	
	public Page<Theater> findAll( Pageable paging );
	
}
