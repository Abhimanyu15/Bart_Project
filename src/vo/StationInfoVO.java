package vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StationListDetails", propOrder = {"station_name","abbr","address", "city", "county", "zip_code","intro"})

@XmlRootElement(name = "stationListInfo")
public class StationInfoVO {

	@XmlElement(required = false)
	private String station_name;
	@XmlElement(required = false)
	private String address;
	@XmlElement(required = false)
	private String city;
	@XmlElement(required = false)
	private String county;
	@XmlElement(required = false)
	private String zip_code;
	@XmlElement(required = false)
	private String abbr;
	@XmlElement(required = false)
	private String intro;
	
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
}

