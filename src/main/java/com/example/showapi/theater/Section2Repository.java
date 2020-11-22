package com.example.showapi.theater;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.showapi.theater.domain.Section2;

public interface Section2Repository extends MongoRepository<Section2, String>, SectionRepositoryCustom {

	public Section2 getSectionById(String id);

	@Override
	public Page<Section2> findAll(Pageable paging);

}
