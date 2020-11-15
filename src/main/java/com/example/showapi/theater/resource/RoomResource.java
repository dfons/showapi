package com.example.showapi.theater.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.showapi.theater.domain.Room;

public class RoomResource {

	private String id;
    private String name;
    private Integer capacity;
    
    public static RoomResource toResource(Room entity) {
		RoomResource resource = new RoomResource();
		resource.setId(entity.getId().toString());
		resource.setName(entity.getName());
		resource.setCapacity(entity.getCapacity());
		
		return resource;
	}
	public static Collection<RoomResource> toResource(List<Room> entities) {
		List<RoomResource> resources = new ArrayList<>();
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
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		return "RoomResource [id=" + id + ", name=" + name + ", capacity=" + capacity + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capacity == null) ? 0 : capacity.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomResource other = (RoomResource) obj;
		if (capacity == null) {
			if (other.capacity != null)
				return false;
		} else if (!capacity.equals(other.capacity))
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
		return true;
	}
	
}
