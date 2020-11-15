package com.example.showapi.theater.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.showapi.theater.domain.Seat;

public class SeatResource {

	private Integer number;
	private Boolean available;
	
	public static SeatResource toResource(Seat entity) {
		SeatResource resource = new SeatResource();
		resource.setNumber(entity.getNumber());
		resource.setAvailable(entity.getAvailable());
		
		return resource;
	}
	public static Collection<SeatResource> toResource(List<Seat> entities) {
		List<SeatResource> resources = new ArrayList<>();
		entities.forEach(entity -> resources.add(toResource(entity)));
		return resources;
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	@Override
	public String toString() {
		return "SeatResource [number=" + number + ", available=" + available + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		SeatResource other = (SeatResource) obj;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
}
