package com.example.showapi.theater.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.showapi.theater.Section2Repository;
import com.example.showapi.theater.TheaterRepository;
import com.example.showapi.theater.TheaterService;
import com.example.showapi.theater.domain.Section2;
import com.example.showapi.theater.domain.Theater;
import com.example.showapi.theater.request.SectionBookingRequest;

@Component
public class TheaterServiceImpl implements TheaterService {
	
	private static final Logger logger = LoggerFactory.getLogger(TheaterServiceImpl.class);

	@Autowired
	private TheaterRepository theaterRepository;
	
	@Autowired
	private Section2Repository section2Repository;

	// Theater related methods.
	@Override
	public Theater getById(String id) {
		logger.info("Looking for Theater with ID {}", id);
		Theater theater = theaterRepository.getTheaterById(id);
		return theater;
	}

	@Override
	public Page<Theater> findAll(Pageable paging) {
		logger.info("Looking for all Theaters");
		Page<Theater> theaters= theaterRepository.findAll(paging);
		return theaters;
	}

	// Section related methods.
	@Override
	public Section2 getSection2ById(String sectionId) {
		logger.info("Looking for Section2 with ID {}", sectionId);
		Section2 section = section2Repository.getSectionById(sectionId);
		return section;
	}

	@Override
	public Page<Section2> findAllSections2(Pageable paging) {
		logger.info("Looking for all Sections2");
		Page<Section2> sections= section2Repository.findAll(paging);
		return sections;
	}

	@Override
	public Boolean updateSections(List<SectionBookingRequest> requests) {
		logger.info("Updating Sections");
		Boolean result = section2Repository.updateSections(requests);
		return result;
	}

}
