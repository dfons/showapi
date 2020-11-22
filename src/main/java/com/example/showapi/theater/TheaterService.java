package com.example.showapi.theater;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.showapi.theater.domain.Section2;
import com.example.showapi.theater.domain.Theater;
import com.example.showapi.theater.request.SectionBookingRequest;

public interface TheaterService {

	// Theater related methods.
	@Cacheable("theaterById")
	public Theater getById( String id );
	
	public Page<Theater> findAll( Pageable paging );
	
	// Section related methods.
	@Cacheable("sectionById")
	public Section2 getSection2ById( String sectionId );
	
	public Page<Section2> findAllSections2( Pageable paging );
	
	@CacheEvict(cacheNames = "sectionById", allEntries = true)
	public Boolean updateSections(List<SectionBookingRequest> requests);
	
}
