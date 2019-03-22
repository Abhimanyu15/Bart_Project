package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import vo.TripModelVO;
import apiClient.ServiceClient;

@XmlRootElement(name = "tripInfo")
public class TripModel {
	@XmlElement(required = false)
	private List<TripModelVO> trip;
	
	public TripModel() {}
	
	public TripModel(String Source, String Destination) throws SQLException, JSONException {
		String ApiResponse=ServiceClient.serviceCall("http://api.bart.gov/api/sched.aspx?cmd=depart&orig="+Source+"&dest="+Destination+"&date=now&key=MW9S-E7SL-26DU-VV8V&b=0&a=3&json=y");
		trip=apiResponseParse_trip(ApiResponse);
	}
	
       
    public List<TripModelVO> apiResponseParse_trip(String responseData) throws JSONException {
   	 List<TripModelVO> list= new ArrayList<TripModelVO>(); 
   	 
   	 JSONObject scheduleobj = new JSONObject(new JSONObject(responseData).getString("root"));
		 String schedule = scheduleobj.getString("schedule");
		 JSONObject requestobj = new JSONObject(schedule);
		 String request = requestobj.getString("request");
		 JSONObject tripobj = new JSONObject(request);
		 String trip = tripobj.getString("trip");

        JSONArray trip_jsonarray = new JSONArray(trip);
        for (int j = 0; j < trip_jsonarray.length(); ++j) {
        	
            JSONObject fares_jsonobj = trip_jsonarray.getJSONObject(j);
            TripModelVO vo = new TripModelVO();
            vo.setOrigin(fares_jsonobj.getString("@origin"));
            vo.setDestination(fares_jsonobj.getString("@destination"));
            vo.setFare(fares_jsonobj.getString("@fare"));
            vo.setTripTime(fares_jsonobj.getString("@tripTime"));
            vo.setDestTimeMin(fares_jsonobj.getString("@destTimeMin"));
            
            System.out.println("fares  == "+fares_jsonobj.getString("fares"));
            String fare = fares_jsonobj.getString("fares");
            JSONObject fare_jsonobj = new JSONObject(fare);
           String amount= fare_jsonobj.getString("fare");
           JSONObject amount_jsonobj = new JSONObject(amount);
           System.out.println("schema_name  == "+amount_jsonobj.getString("@amount"));

		 //JSONObject requestobj = new JSONObject("request");
           list.add(vo);
        }
        return list;
   }

}
