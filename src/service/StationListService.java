package service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;



import org.json.JSONObject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import model.StationInfoModel;
import model.StationModel;
import model.TripModel;

public class StationListService {
	
	@GET
	@Path("/getStationList")
	@Transactional(readOnly = true)
	public @ResponseBody StationModel getStationList() {
		try {
			StationModel stationModel = new StationModel();
			return stationModel;
		} catch(Exception e) {
			e.printStackTrace();
		    return null;
		}
	}
	
	@GET
	@Path("/getStationInfo/{stationName}")
	@Transactional(readOnly = true)
	public @ResponseBody StationInfoModel getStationInfo(@PathParam("stationName") String stationName) {
		try {
		StationInfoModel stationInfoModel = new StationInfoModel(stationName);
		return stationInfoModel;
		} catch(Exception e) {
		e.printStackTrace();
		   return null;
		}
	}
	
	@GET
	@Path("/getTripsInfo/{source}/{destination}")
	@Transactional(readOnly = true)
	public @ResponseBody TripModel getTripList(
			@PathParam("source") String source,
			@PathParam("destination") String destination) {
		try {
		TripModel tripModel = new TripModel(source,destination);
		return tripModel;
		} catch(Exception e) {
		e.printStackTrace();
		   return null;
		}
	}

}
