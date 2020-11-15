package com.example.showapi.theater.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.example.showapi.theater.SectionController;
import com.example.showapi.theater.domain.Seat;
import com.example.showapi.theater.domain.Section;

public class SectionResource extends RepresentationModel<SectionResource> {

	private String id;
    private List<Seat> seats;
    
    public static SectionResource toResource(Section entity) {
		SectionResource resource = new SectionResource();
		resource.setId(entity.getId());
		resource.setSeats(new ArrayList<>(entity.getSeats()));
		
		Link selfRel = linkTo(SectionController.class).slash(entity.getId()).withSelfRel();
		resource.add(selfRel);
		return resource;
	}
	public static Collection<SectionResource> toResource(List<Section> entities) {
		List<SectionResource> resources = new ArrayList<>();
		entities.forEach(entity -> resources.add(toResource(entity)));
		return resources;
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	@Override
	public String toString() {
		return "SectionResource [id=" + id + ", seats=" + seats + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((seats == null) ? 0 : seats.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SectionResource other = (SectionResource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (seats == null) {
			if (other.seats != null)
				return false;
		} else if (!seats.equals(other.seats))
			return false;
		return true;
	}
	
}
