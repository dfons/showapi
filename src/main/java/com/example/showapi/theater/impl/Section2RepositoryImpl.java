package com.example.showapi.theater.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.showapi.theater.SectionRepositoryCustom;
import com.example.showapi.theater.request.SectionPatchRequest;
import com.mongodb.client.result.UpdateResult;

public class Section2RepositoryImpl implements SectionRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(TheaterServiceImpl.class);
	
	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public Boolean updateSection(String sectionId, SectionPatchRequest request) {
		Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(sectionId).and("seats.1").is(!request.getSeatStatus()));
        
        Update update = new Update();
        update.set("seats.1", request.getSeatStatus());
        
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "sections2");
        
        if(updateResult != null) {
	        Boolean success = (updateResult.getMatchedCount() == 1) && (updateResult.getModifiedCount() == 1);
	        if(!success) {
	        	logger.info("Update failed, matched is {} and updated is {}", updateResult.getMatchedCount(), updateResult.getModifiedCount());
	        	return false;
	        }
        }
        else {
        	logger.error("Result is invalid!");
        	return false;
        }
        
        return true;
	}

}
