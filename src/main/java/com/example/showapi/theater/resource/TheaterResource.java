package com.example.showapi.theater.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.example.showapi.theater.TheaterController;
import com.example.showapi.theater.domain.Theater;

public class TheaterResource extends RepresentationModel<TheaterResource> {

	private String id;
    private String name;
    private String address;
    private List<RoomResource> rooms;
    
    public static TheaterResource toResource(Theater entity) {
		TheaterResource resource = new TheaterResource();
		resource.setId(entity.getId().toString());
		resource.setName(entity.getName());
		resource.setAddress(entity.getAddress());
		resource.setRooms(new ArrayList<>(RoomResource.toResource(entity.getRooms())));
		
		Link selfRel = linkTo(TheaterController.class).slash(entity.getId()).withSelfRel();
		resource.add(selfRel);
		return resource;
	}
	public static Collection<TheaterResource> toResource(List<Theater> entities) {
		List<TheaterResource> resources = new ArrayList<>();
		entities.forEach(entity -> resources.add(toResource(entity)));
		return resources;
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<RoomResource> getRooms() {
		return rooms;
	}
	public void setRooms(List<RoomResource> rooms) {
		this.rooms = rooms;
	}
	
	@Override
	public String toString() {
		return "TheaterResource [id=" + id + ", name=" + name + ", address=" + address + ", rooms=" + rooms + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rooms == null) ? 0 : rooms.hashCode());
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
		TheaterResource other = (TheaterResource) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		return true;
	}
    
    
	
}
