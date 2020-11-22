package com.example.showapi.theater;

import java.util.List;

import com.example.showapi.theater.request.SectionBookingRequest;

public interface SectionRepositoryCustom {

	public Boolean updateSections(List<SectionBookingRequest> requests);

}
