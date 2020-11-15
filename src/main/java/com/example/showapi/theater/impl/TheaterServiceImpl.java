package com.example.showapi.theater.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.showapi.theater.SectionRepository;
import com.example.showapi.theater.TheaterRepository;
import com.example.showapi.theater.TheaterService;
import com.example.showapi.theater.domain.Seat;
import com.example.showapi.theater.domain.Section;
import com.example.showapi.theater.domain.Theater;
import com.example.showapi.theater.request.SectionPatchRequest;

@Component
public class TheaterServiceImpl implements TheaterService {
	
	private static final Logger logger = LoggerFactory.getLogger(TheaterServiceImpl.class);

	@Autowired
	private TheaterRepository theaterRepository;
	
	@Autowired
	private SectionRepository sectionRepository;
	
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

	@Override
	public List<Seat> getSeatsBySection(String sectionId) {
		logger.info("Looking for Seats on Section with ID {}", sectionId);
		Section section = sectionRepository.getSectionById(sectionId);
		return section.getSeats();
	}

	@Override
	public Section getSectionById(String sectionId) {
		logger.info("Looking for Section with ID {}", sectionId);
		Section section = sectionRepository.getSectionById(sectionId);
		return section;
	}

	@Override
	public Page<Section> findAllSections(Pageable paging) {
		logger.info("Looking for all Sections");
		Page<Section> theaters= sectionRepository.findAll(paging);
		return theaters;
	}

	@Override
	public Boolean updateSection(String sectionId, SectionPatchRequest request) {
		logger.info("Updating Seat with ID {} to status {} in Section with ID {}", request.getSeatNumber(), request.getSeatStatus(), sectionId);
		Boolean result = sectionRepository.updateSection(sectionId, request);
		return result;
	}

}
