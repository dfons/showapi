package com.example.showapi.show.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import com.example.showapi.show.ShowRepository;
import com.example.showapi.show.ShowService;
import com.example.showapi.show.domain.Show;
import com.fasterxml.jackson.databind.util.StdDateFormat;

@Component
public class ShowServiceImpl implements ShowService {
	
	private static final Logger logger = LoggerFactory.getLogger(ShowServiceImpl.class);

	@Autowired
	private ShowRepository showRepository;
	
	@Override
	public Show getShowById(String id) {
		logger.info("Looking for Show with ID {}", id);
		Show show = showRepository.getShowById(id);
		return show;
	}
	
	@Override
	public Page<Show> findAll( Pageable paging ) {
		logger.info("Looking for all Shows");
		Page<Show> shows = showRepository.findAll(paging);
		return shows;
	}

	@Override
	public Page<Show> findAllByDate(Pageable paging, String dateFrom, String dateTo, String priceFrom, String priceTo) throws ParseException {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    
	    logger.info("The date strings: {} and {}", dateFrom, dateTo);
		Date from = (dateFrom != null && !dateFrom.isEmpty()) ? inputFormat.parse(dateFrom) : null;
		Date to = (dateFrom != null && !dateFrom.isEmpty()) ? inputFormat.parse(dateTo) : null;
		logger.info("The dates: {} and {}", from, to);
		
		logger.info("The price strings: {} and {}", priceFrom, priceTo);
		Float priceA = (priceFrom != null && !priceFrom.isEmpty()) ? Float.valueOf(priceFrom) : null;
		Float priceB = (priceTo != null && !priceTo.isEmpty()) ? Float.valueOf(priceTo) : null;
		logger.info("The prices: {} and {}", from, to);
		
		Page<Show> shows = showRepository.findByDate(paging, from, to, priceA, priceB);
		
		return shows;
	}

}
