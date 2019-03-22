package model;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vo.StationListVO;
import apiClient.ServiceClient;

@XmlRootElement(name = "stations")
public class StationModel {
	@XmlElement(required = false)
	private List<StationListVO> station;
	
	public StationModel() throws SQLException, JSONException {
		String ApiResponse=ServiceClient.serviceCall("http://api.bart.gov/api/stn.aspx?cmd=stns&key=MW9S-E7SL-26DU-VV8V&json=y");
		station=apiResponseParse_stationList(ApiResponse);
	}
	
    public List<StationListVO> apiResponseParse_stationList(String responseData) throws JSONException {
    	 List<StationListVO> list= new ArrayList<StationListVO>(); 
    	 JSONObject scheduleobj = new JSONObject(new JSONObject(responseData).getString("root"));
		 String schedule = scheduleobj.getString("stations");
		 JSONObject requestobj = new JSONObject(schedule);
		 String station = requestobj.getString("station");
		 JSONArray station_jsonarray = new JSONArray(station);

         for (int j = 0; j < station_jsonarray.length(); ++j) {
        	 StationListVO vo= new StationListVO();
             JSONObject station_jsonobj = station_jsonarray.getJSONObject(j);
             vo.setStation_name(station_jsonobj.getString("name"));
             vo.setAbbr(station_jsonobj.getString("abbr"));
             vo.setCity(station_jsonobj.getString("city"));
             vo.setCounty(station_jsonobj.getString("county"));
             vo.setZip_code(station_jsonobj.getString("zipcode"));
             vo.setAddress(station_jsonobj.getString("address"));
             list.add(vo);
             

		 //JSONObject requestobj = new JSONObject("request");
         }
         return list;
    }
    
    
    public List<StationListVO> apiResponseParse(String responseData) throws JSONException {
   	 List<StationListVO> list= new ArrayList<StationListVO>(); 
   	 JSONObject scheduleobj = new JSONObject(new JSONObject(responseData).getString("root"));
		 String schedule = scheduleobj.getString("schedule");
		 JSONObject requestobj = new JSONObject(schedule);
		 String request = requestobj.getString("request");
		 JSONObject tripobj = new JSONObject(request);
		 String trip = tripobj.getString("trip");

        JSONArray trip_jsonarray = new JSONArray(trip);
        for (int j = 0; j < trip_jsonarray.length(); ++j) {
            JSONObject fares_jsonobj = trip_jsonarray.getJSONObject(j);
            System.out.println("fares  == "+fares_jsonobj.getString("fares"));
            String fare = fares_jsonobj.getString("fares");
            JSONObject fare_jsonobj = new JSONObject(fare);
           String amount= fare_jsonobj.getString("fare");
           JSONObject amount_jsonobj = new JSONObject(amount);
           System.out.println("schema_name  == "+amount_jsonobj.getString("@amount"));

		 //JSONObject requestobj = new JSONObject("request");
        }
        return list;
   }
}
