package com.example.showapi.show.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.showapi.show.domain.Play.Price;
import com.example.showapi.theater.domain.Seat;

public class PriceResource {

	private String sectionId;
	private Float price;
	private List<Seat> seats;
	
	public static PriceResource toResource(Price entity) {
		PriceResource resource = new PriceResource();
		resource.setPrice(entity.price);
		resource.setSectionId(entity.sectionId);
		resource.setSeats(new ArrayList<>());
		return resource;
	}
	public static Collection<PriceResource> toResource(List<Price> entities) {
		List<PriceResource> resources = new ArrayList<>();
		entities.forEach(entity -> resources.add(toResource(entity)));
		return resources;
	}
	
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	@Override
	public String toString() {
		return "PriceResource [sectionId=" + sectionId + ", price=" + price + ", seats=" + seats + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((seats == null) ? 0 : seats.hashCode());
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
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
		PriceResource other = (PriceResource) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (seats == null) {
			if (other.seats != null)
				return false;
		} else if (!seats.equals(other.seats))
			return false;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		return true;
	}
	
	
}
