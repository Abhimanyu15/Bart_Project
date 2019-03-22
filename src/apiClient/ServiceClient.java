package apiClient;



import java.util.concurrent.TimeUnit;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class ServiceClient {
	
	public static void main(String [] args) throws JSONException {
		serviceCall("");
	}
	
	public static String status = "";
	static int recursive_counter=0;
	public static String serviceCall(String URL) throws JSONException {	
		 String responseData = "";
		// URL="http://api.bart.gov/api/sched.aspx?cmd=depart&orig=12TH&dest=24TH&date=now&key=MW9S-E7SL-26DU-VV8V&b=0&a=3&l=1&json=y";
		 HttpClient client = new HttpClient();
		 try {
		      // Execute the method.
			 GetMethod method = new GetMethod(URL);
			 method.setRequestHeader("Content-Type", "application/json");
			 method.setRequestHeader("Accept", "application/json");
			 
		      int statusCode = client.executeMethod(method);
		      if (statusCode == 201 || statusCode == 200) {
		        //System.out.println("Method Successful: " + method.getStatusLine());
		      }
		      else  {
			        System.err.println("Method failed: " + method.getStatusLine());
			        status = method.getResponseBodyAsString();
			   }
		      // Read the response body.
		      // byte[] responseBody = method.getResponseBody();
		    
		      responseData  = method.getResponseBodyAsString();

		    } catch (Exception e) {
		      System.err.println("Fatal protocol violation: " + e.getMessage());
		      e.printStackTrace();
		      status = e.getMessage();
		      recursive_counter++;
		      if(recursive_counter <= 10) {
		    	  System.out.println(" sleeping for 5 second before next recursive call \n counter="+recursive_counter+" \n for api call = "+URL );
		    	  try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	  responseData=serviceCall(URL);
		      }else
		    	  return "Resource Not Found";
		    }
		
	
         return responseData;
	}
}

