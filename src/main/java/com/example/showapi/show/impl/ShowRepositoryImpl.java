package com.example.showapi.show.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public Page<Show> findByDate(Pageable paging, Date dateFrom, Date dateTo, Float priceFrom, Float priceTo,
			String sortBy, String order) {
		Query query = new Query();
		Criteria criteria = new Criteria();
        
        List<Criteria> criterias = new ArrayList<>();
        if(dateFrom != null && dateTo != null) {
        	criterias.add(Criteria.where("plays.schedule").gte(dateFrom).lte(dateTo));
        }
        if(priceFrom != null && priceTo != null) {
        	criterias.add(Criteria.where("plays.prices.price").gte(priceFrom).lte(priceTo));
        }
        if(criterias.size() > 0) {
        	criteria.andOperator(criterias.toArray(new Criteria[criterias.size()]));
        }
        
        query.addCriteria(criteria);
        
		// If sortBy is defined, order is assumed ASC in case it is not defined.
		if (sortBy != null && !sortBy.isEmpty()) {
			query.with(Sort.by((order != null && order.equals("desc")) ? Direction.DESC : Direction.ASC, sortBy));
        }
        
        List<Show> shows = mongoTemplate.find(query, Show.class, "shows");
        
        int start = (int)paging.getOffset();
        int end = (start + paging.getPageSize()) > shows.size() ? shows.size() : (start + paging.getPageSize());
        Page<Show> pages = new PageImpl<Show>(shows.subList(start, end), paging, shows.size());
        
        return pages;
	}

}
