package com.example.showapi.show;

import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.show.domain.Show;

public interface ShowService {

	public Show getShowById( String id );
	
	public Page<Show> findAll( Pageable paging );
	
	public Page<Show> findAllByDate( Pageable paging, String dateFrom, String dateTo ) throws ParseException;
	
}
