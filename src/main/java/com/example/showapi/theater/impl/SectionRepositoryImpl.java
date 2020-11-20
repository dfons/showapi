package com.example.showapi.theater.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.showapi.theater.SectionRepositoryCustom;
import com.example.showapi.theater.request.SectionBookingRequest;
import com.example.showapi.theater.request.SectionPatchRequest;
import com.mongodb.client.result.UpdateResult;

public class SectionRepositoryImpl implements SectionRepositoryCustom {
	
	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public Boolean updateSection(String sectionId, SectionPatchRequest request) {
		Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(sectionId).and("seats.number").is(Integer.valueOf(request.getSeatNumber())));
        
        Update update = new Update();
        update.set("seats.$.available", request.getSeatStatus());
        
        UpdateResult updateResult = mongoTemplate.upsert(query, update, "sections");
        
        return (updateResult != null);
	}

	@Override
	public Boolean updateSections(List<SectionBookingRequest> requests) {
		return false;
	}

}
