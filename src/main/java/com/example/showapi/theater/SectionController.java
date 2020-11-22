package com.example.showapi.theater;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.showapi.theater.domain.Section2;
import com.example.showapi.theater.resource.Section2Resource;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/sections")
@Tag(name = "/sections", description = "Sections API endpoints")
public class SectionController {

	@Autowired
	private TheaterService theaterService;
	
	@GetMapping(path = "/{sectionId}", produces = { MediaType.SECTION_RESPONSE })
	public ResponseEntity<Section2Resource> getSection2ById( @PathVariable String sectionId ) throws Exception {
		Section2 section = this.theaterService.getSection2ById(sectionId);
		if( section == null ) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		final Section2Resource response = Section2Resource.toResource(section);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "", produces = { MediaType.PAGED_SECTION_RESPONSE })
	public ResponseEntity<PagedModel<Section2Resource>> getAllSections2(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) throws Exception {
		Pageable paging = PageRequest.of(page, size);
		
		// Get shows
		Page<Section2> pagedSections = this.theaterService.findAllSections2(paging);
	    // Build Page Resources
	    Link selfLink = linkTo(SectionController.class).withSelfRel();
	    PageMetadata metadata = new PageMetadata(pagedSections.getSize(),
	    		pagedSections.getNumber(),
	    		pagedSections.getTotalElements(),
	    		pagedSections.getTotalPages());
	    
	    PagedModel<Section2Resource> response = PagedModel.of(Section2Resource.toResource(pagedSections.getContent()), metadata, selfLink);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * The type MediaType.
	 */
	public final static class MediaType {
		public static final String SECTION_RESPONSE = "application/section-response-v1-hal+json";
		public static final String PAGED_SECTION_RESPONSE = "application/paged-section-response-v1-hal+json";
		public static final String SECTION_REQUEST = "application/section-requesst-v1-hal+json";
    }
	
}
