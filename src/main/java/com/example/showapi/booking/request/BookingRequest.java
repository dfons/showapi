package com.example.showapi.booking.request;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.hateoas.RepresentationModel;

public class BookingRequest extends RepresentationModel<BookingRequest> {

	private String name;
	private String dni;
	private Date timestamp;
	private String showId;
	private String playId;
	Map<String, List<Integer>> seats; // [section] = (seat, seat, seat)

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

	public Map<String, List<Integer>> getSeats() {
		return seats;
	}

	public void setSeats(Map<String, List<Integer>> seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "BookingRequest [name=" + name + ", dni=" + dni + ", timestamp=" + timestamp + ", showId=" + showId
				+ ", playId=" + playId + ", seats=" + seats + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((playId == null) ? 0 : playId.hashCode());
		result = prime * result + ((seats == null) ? 0 : seats.hashCode());
		result = prime * result + ((showId == null) ? 0 : showId.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		BookingRequest other = (BookingRequest) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
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
		return true;
	}

}
