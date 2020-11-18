package com.example.showapi.show;

import java.text.ParseException;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.show.domain.Show;

public interface ShowService {

	@Cacheable("showById")
	public Show getShowById( String id );
	
	public Page<Show> findAll( Pageable paging );
	
	public Page<Show> findAllByDate(Pageable paging, String dateFrom, String dateTo, String priceFrom, String priceTo,
			String sortBy, String order) throws ParseException;
	
}
