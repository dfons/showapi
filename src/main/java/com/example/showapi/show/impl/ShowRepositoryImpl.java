package com.example.showapi.show.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.showapi.show.ShowRepositoryCustom;
import com.example.showapi.show.domain.Show;
import com.example.showapi.theater.impl.TheaterServiceImpl;

public class ShowRepositoryImpl implements ShowRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(TheaterServiceImpl.class);
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Override
	public Page<Show> findByDate(Pageable paging, Date fromDate, Date dateTo) {
		Query query = new Query();
        query.addCriteria(Criteria.where("plays.schedule").gte(fromDate).lte(dateTo));
        
        List<Show> shows = mongoTemplate.find(query, Show.class, "shows");
        
        int start = (int)paging.getOffset();
        int end = (start + paging.getPageSize()) > shows.size() ? shows.size() : (start + paging.getPageSize());
        Page<Show> pages = new PageImpl<Show>(shows.subList(start, end), paging, shows.size());
        
        return pages;
	}

}
