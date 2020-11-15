package com.example.showapi.show;

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

import com.example.showapi.show.domain.Show;
import com.example.showapi.show.resource.ShowResource;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/shows")
@Tag(name = "/shows", description = "Shows API endpoints")
public class ShowController {

	@Autowired
	private ShowService showService;
//	@Autowired
//	private TheaterService theaterService;
	
	@GetMapping(path = "/{showId}", produces = { MediaType.SHOW_RESPONSE })
	public ResponseEntity<ShowResource> getShowById( @PathVariable String showId ) throws Exception {
		Show show = this.showService.getShowById(showId);
		if( (show == null) || (show.getId() != Integer.valueOf(showId) ) ) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		final ShowResource response = ShowResource.toResource(show);
		
//		for (PlayResource play : response.getPlays()) {
//			for (PriceResource price : play.getPrices()) {
//				price.getSeats().addAll(theaterService.getSeatsBySection(price.getSectionId()));
//			}
//		}
		
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "", produces = { MediaType.PAGED_SHOW_RESPONSE })
	public ResponseEntity<PagedModel<ShowResource>> getAllShows(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) throws Exception {
		Pageable paging = PageRequest.of(page, size);
		
		// Get shows
		Page<Show> pagedShows = this.showService.findAll(paging);
	    // Build Page Resources
	    Link selfLink = linkTo(ShowController.class).withSelfRel();
	    PageMetadata metadata = new PageMetadata(pagedShows.getSize(),
	    		pagedShows.getNumber(),
	    		pagedShows.getTotalElements(),
	    		pagedShows.getTotalPages());
	    
	    PagedModel<ShowResource> response = PagedModel.of(ShowResource.toResource(pagedShows.getContent()), metadata, selfLink);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * The type MediaType.
	 */
	public final static class MediaType {
		public static final String SHOW_RESPONSE = "application/show-response-v1-hal+json";
		public static final String PAGED_SHOW_RESPONSE = "application/paged-show-response-v1-hal+json";
		public static final String SHOW_REQUEST = "application/show-request-v1-hal+json";
    }
}
