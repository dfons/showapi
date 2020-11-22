package com.example.showapi.theater.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.showapi.theater.SectionRepositoryCustom;
import com.example.showapi.theater.domain.Section2;
import com.example.showapi.theater.request.SectionBookingRequest;
import com.mongodb.bulk.BulkWriteResult;

public class Section2RepositoryImpl implements SectionRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(TheaterServiceImpl.class);
	
	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public Boolean updateSections(List<SectionBookingRequest> requests) {
		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Section2.class);
		for (SectionBookingRequest request : requests) {
			Query query = new Query();
			Update update = new Update();

			Criteria criteria = Criteria.where("_id").is(request.sectionId);
			for (Integer seat : request.seats) {
				String key = "seats." + seat.toString();
				criteria.and(key).is(!request.status);

				update.set(key, request.status);
			}

			query.addCriteria(criteria);

			// Each update operation has the form:
			// db.sections2.update(
			// {"_id":"1", "seats.1":true, "seats.2":true},
			// {"$set":{"seats.1":false, "seats.2":false}}
			// );
			bulkOps.updateOne(query, update);
		}

		BulkWriteResult result = bulkOps.execute();

		if (result != null) {
			Boolean success = (result.getMatchedCount() == requests.size())
					&& (result.getModifiedCount() == requests.size());
			if (!success) {
				logger.info("Update failed, matched is {} and updated is {}", result.getMatchedCount(),
						result.getModifiedCount());
				return false;
			}
		} else {
			logger.error("Result is invalid!");
			return false;
		}

		return true;
	}

}
