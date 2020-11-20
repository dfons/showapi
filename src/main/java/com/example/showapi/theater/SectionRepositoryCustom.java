package com.example.showapi.theater;

import java.util.List;

import com.example.showapi.theater.request.SectionBookingRequest;
import com.example.showapi.theater.request.SectionPatchRequest;

public interface SectionRepositoryCustom {

	public Boolean updateSection( String SectionId, SectionPatchRequest request );
	
	public Boolean updateSections(List<SectionBookingRequest> requests);

}
