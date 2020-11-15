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

import com.example.showapi.theater.domain.Theater;
import com.example.showapi.theater.resource.TheaterResource;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/theaters")
@Tag(name = "/theaters", description = "Theaters API endpoints")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;
	
	@GetMapping(path = "/{theaterId}", produces = { MediaType.THEATER_RESPONSE })
	public ResponseEntity<TheaterResource> getShowById( @PathVariable String theaterId ) throws Exception {
		Theater theater = this.theaterService.getById(theaterId);
		if( (theater == null) || (theater.getId() != Integer.valueOf(theaterId) ) ) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		final TheaterResource response = TheaterResource.toResource(theater);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "", produces = { MediaType.PAGED_THEATER_RESPONSE })
	public ResponseEntity<PagedModel<TheaterResource>> getAllShows(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) throws Exception {
		Pageable paging = PageRequest.of(page, size);
		
		// Get shows
		Page<Theater> pagedTheaters = this.theaterService.findAll(paging);
	    // Build Page Resources
	    Link selfLink = linkTo(TheaterController.class).withSelfRel();
	    PageMetadata metadata = new PageMetadata(pagedTheaters.getSize(),
	    		pagedTheaters.getNumber(),
	    		pagedTheaters.getTotalElements(),
	    		pagedTheaters.getTotalPages());
	    
	    PagedModel<TheaterResource> response = PagedModel.of(TheaterResource.toResource(pagedTheaters.getContent()), metadata, selfLink);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * The type MediaType.
	 */
	public final static class MediaType {
		public static final String THEATER_RESPONSE = "application/theater-response-v1-hal+json";
		public static final String PAGED_THEATER_RESPONSE = "application/paged-theater-response-v1-hal+json";
		public static final String THEATER_REQUEST = "application/theater-request-v1-hal+json";
    }
	
}
