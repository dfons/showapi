package com.example.showapi.booking.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.example.showapi.booking.BookingController;
import com.example.showapi.booking.domain.Ticket;

public class TicketResource extends RepresentationModel<TicketResource> {

	private String id;
	private String name;
	private String dni;
	private Date timestamp;
	private String showId;
	private String playId;
	List<Map<Integer, Float>> seats;
	Float total;

	public static TicketResource toResource(Ticket entity) {
		TicketResource resource = new TicketResource();
		resource.setId(entity.getId());
		resource.setName(entity.getName());
		resource.setDni(entity.getDni());
		resource.setTimestamp(entity.getTimestamp());
		resource.setShowId(entity.getShowId());
		resource.setPlayId(entity.getPlayId());
		resource.setSeats(entity.getSeats());
		resource.setTotal(entity.getTotal());

		Link selfRel = linkTo(BookingController.class).slash(entity.getId()).withSelfRel();
		resource.add(selfRel);
		return resource;
	}

	public static Collection<TicketResource> toResource(List<Ticket> entities) {
		List<TicketResource> resources = new ArrayList<>();
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public String getPlayId() {
		return playId;
	}

	public void setPlayId(String playId) {
		this.playId = playId;
	}

	public List<Map<Integer, Float>> getSeats() {
		return seats;
	}

	public void setSeats(List<Map<Integer, Float>> seats) {
		this.seats = seats;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "TicketResource [id=" + id + ", name=" + name + ", dni=" + dni + ", timestamp=" + timestamp + ", showId="
				+ showId + ", playId=" + playId + ", seats=" + seats + ", total=" + total + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((playId == null) ? 0 : playId.hashCode());
		result = prime * result + ((seats == null) ? 0 : seats.hashCode());
		result = prime * result + ((showId == null) ? 0 : showId.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		TicketResource other = (TicketResource) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
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
		if (playId == null) {
			if (other.playId != null)
				return false;
		} else if (!playId.equals(other.playId))
			return false;
		if (seats == null) {
			if (other.seats != null)
				return false;
		} else if (!seats.equals(other.seats))
			return false;
		if (showId == null) {
			if (other.showId != null)
				return false;
		} else if (!showId.equals(other.showId))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

}
