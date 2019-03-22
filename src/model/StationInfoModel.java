package model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vo.StationInfoVO;
import apiClient.ServiceClient;

@XmlRootElement(name = "stationListInfo")
public class StationInfoModel {
	@XmlElement(required = false)
	private List<StationInfoVO> station;
	
	
	public StationInfoModel() {}
	
	
	public StationInfoModel(String stationName) throws SQLException, JSONException {
		String ApiResponse=ServiceClient.serviceCall("http://api.bart.gov/api/stn.aspx?cmd=stninfo&orig="+stationName+"&key=MW9S-E7SL-26DU-VV8V&json=y");
		station=apiResponseParse_stationList(ApiResponse);
	}
	
    public List<StationInfoVO> apiResponseParse_stationList(String responseData) throws JSONException {
    	 List<StationInfoVO> list= new ArrayList<StationInfoVO>(); 
    	 JSONObject scheduleobj = new JSONObject(new JSONObject(responseData).getString("root"));
		 String schedule = scheduleobj.getString("stations");
		 JSONObject requestobj = new JSONObject(schedule);
		 String station = requestobj.getString("station");
		 JSONObject requestobj1 = new JSONObject(station);

		 StationInfoVO vo= new StationInfoVO();
            vo.setStation_name(requestobj1.getString("name"));
            vo.setAbbr(requestobj1.getString("abbr"));
            vo.setCity(requestobj1.getString("city"));
            vo.setCounty(requestobj1.getString("county"));
            vo.setZip_code(requestobj1.getString("zipcode"));
            vo.setAddress(requestobj1.getString("address"));
            String intro=requestobj1.getString("intro");
            JSONObject requestobj2 = new JSONObject(intro);
            vo.setIntro(requestobj2.getString("#cdata-section"));
            list.add(vo);
                      
         return list;	
}
}
