package com.example.showapi.show.resource;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.example.showapi.theater.domain.Seat;

public class PlayResource extends RepresentationModel<PlayResource> {

	private String id;
	private Date schedule;
	private String theaterId;
	private String roomId;
	private List<PriceResource> prices;
	
	private class PriceResource {
		public String sectionId;
		public Float price;
		public List<Seat> seats;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public String getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public List<PriceResource> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceResource> prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		return "PlayResource [id=" + id + ", schedule=" + schedule + ", theaterId=" + theaterId + ", roomId=" + roomId
				+ ", prices=" + prices + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prices == null) ? 0 : prices.hashCode());
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		result = prime * result + ((theaterId == null) ? 0 : theaterId.hashCode());
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
		PlayResource other = (PlayResource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prices == null) {
			if (other.prices != null)
				return false;
		} else if (!prices.equals(other.prices))
			return false;
		if (roomId == null) {
			if (other.roomId != null)
				return false;
		} else if (!roomId.equals(other.roomId))
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (theaterId == null) {
			if (other.theaterId != null)
				return false;
		} else if (!theaterId.equals(other.theaterId))
			return false;
		return true;
	}
	
	
	
}
