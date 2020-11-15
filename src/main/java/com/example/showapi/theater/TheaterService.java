package com.example.showapi.theater;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.theater.domain.Seat;
import com.example.showapi.theater.domain.Section;
import com.example.showapi.theater.domain.Theater;
import com.example.showapi.theater.request.SectionPatchRequest;

public interface TheaterService {

	public Theater getById( String id );
	
	public Page<Theater> findAll( Pageable paging );
	
	public List<Seat> getSeatsBySection( String sectionId );
	
	public Section getSectionById( String sectionId );
	
	public Page<Section> findAllSections( Pageable paging );
	
	public Boolean updateSection( String sectionId, SectionPatchRequest request );
	
}
