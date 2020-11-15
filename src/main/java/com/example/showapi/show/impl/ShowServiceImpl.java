package com.example.showapi.show.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.showapi.show.ShowRepository;
import com.example.showapi.show.ShowService;
import com.example.showapi.show.domain.Show;

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

}
