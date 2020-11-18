package com.example.showapi.theater;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.theater.domain.Seat;
import com.example.showapi.theater.domain.Section;
import com.example.showapi.theater.domain.Section2;
import com.example.showapi.theater.domain.Theater;
import com.example.showapi.theater.request.SectionPatchRequest;

public interface TheaterService {

	@Cacheable("theaterById")
	public Theater getById( String id );
	
	public Page<Theater> findAll( Pageable paging );
	
	public List<Seat> getSeatsBySection( String sectionId );
	
	@Cacheable("sectionById")
	public Section getSectionById( String sectionId );
	
	public Page<Section> findAllSections( Pageable paging );
	
	@CacheEvict(cacheNames = "sectionById", allEntries = true)
	public Boolean updateSection( String sectionId, SectionPatchRequest request );
	
	// New Section2.
	@Cacheable("section2ById")
	public Section2 getSection2ById( String sectionId );
	
	public Page<Section2> findAllSections2( Pageable paging );
	
	@CacheEvict(cacheNames = "section2ById", allEntries = true)
	public Boolean updateSection2( String sectionId, SectionPatchRequest request );
	
}
