package vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StationListDetails", propOrder = {"origin","destination","fare", "tripTime", "destTimeMin"})

@XmlRootElement(name = "tripInfo")
public class TripModelVO {

	@XmlElement(required = false)
	private String origin;
	@XmlElement(required = false)
	private String destination;
	@XmlElement(required = false)
	private String fare;
	@XmlElement(required = false)
	private String tripTime;
	@XmlElement(required = false)
	private String destTimeMin;

	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getFare() {
		return fare;
	}
	
	public void setFare(String fare) {
		this.fare = fare;
	}
	
	public String getTripTime() {
		return tripTime;
	}
	
	public void setTripTime(String tripTime) {
		this.tripTime = tripTime;
	}
	public String getDestTimeMin() {
		return destTimeMin;
	}
	public void setDestTimeMin(String destTimeMin) {
		this.destTimeMin = destTimeMin;
	}
	
	
	
}

