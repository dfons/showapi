package com.example.showapi.theater.request;

import org.springframework.hateoas.RepresentationModel;

public class SectionPatchRequest extends RepresentationModel<SectionPatchRequest> {
	
	private String seatNumber;
	private Boolean seatStatus;
	
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public Boolean getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(Boolean seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	@Override
	public String toString() {
		return "SectionPatchRequest [seatNumber=" + seatNumber + ", seatStatus=" + seatStatus + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((seatNumber == null) ? 0 : seatNumber.hashCode());
		result = prime * result + ((seatStatus == null) ? 0 : seatStatus.hashCode());
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
		SectionPatchRequest other = (SectionPatchRequest) obj;
		if (seatNumber == null) {
			if (other.seatNumber != null)
				return false;
		} else if (!seatNumber.equals(other.seatNumber))
			return false;
		if (seatStatus == null) {
			if (other.seatStatus != null)
				return false;
		} else if (!seatStatus.equals(other.seatStatus))
			return false;
		return true;
	}
	
}
