package com.example.showapi.show;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.showapi.show.domain.Show;

public interface ShowRepository extends MongoRepository<Show, String>, ShowRepositoryCustom {
	
	public Show getShowById( String id );
	
	public Page<Show> findAll( Pageable paging );
	
}
