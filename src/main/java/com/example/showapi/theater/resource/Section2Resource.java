package com.example.showapi.theater.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.example.showapi.theater.SectionController;
import com.example.showapi.theater.domain.Seat;
import com.example.showapi.theater.domain.Section;
import com.example.showapi.theater.domain.Section2;

public class Section2Resource extends RepresentationModel<Section2Resource> {

	private String id;
    private HashMap<String, Boolean> seats;
    
    public static Section2Resource toResource(Section2 entity) {
		Section2Resource resource = new Section2Resource();
		resource.setId(entity.getId());
		resource.setSeats(new HashMap<>(entity.getSeats()));
		
		Link selfRel = linkTo(SectionController.class).slash(entity.getId()).withSelfRel();
		resource.add(selfRel);
		return resource;
	}
	public static Collection<Section2Resource> toResource(List<Section2> entities) {
		List<Section2Resource> resources = new ArrayList<>();
		entities.forEach(entity -> resources.add(toResource(entity)));
		return resources;
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HashMap<String, Boolean> getSeats() {
		return seats;
	}
	public void setSeats(HashMap<String, Boolean> seats) {
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
		Section2Resource other = (Section2Resource) obj;
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
