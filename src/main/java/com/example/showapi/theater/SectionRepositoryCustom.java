package com.example.showapi.theater;

import com.example.showapi.theater.request.SectionPatchRequest;

public interface SectionRepositoryCustom {

	public Boolean updateSection( String SectionId, SectionPatchRequest request );
	
}
