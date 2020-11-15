package com.example.showapi.show;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.show.domain.Show;

public interface ShowService {

	public Show getShowById( String id );
	
	public Page<Show> findAll( Pageable paging );
	
}
