package com.example.showapi.theater;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.showapi.theater.domain.Section;
import com.example.showapi.theater.domain.Section2;

public interface SectionRepository extends MongoRepository<Section, String>, SectionRepositoryCustom {

	public Section getSectionById( String id );
	
	public Page<Section> findAll( Pageable paging );
	
}
