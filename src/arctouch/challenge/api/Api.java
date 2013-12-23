package arctouch.challenge.api;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import arctouch.challenge.model.ApiResponse;
import arctouch.challenge.model.Departure;
import arctouch.challenge.model.Route;
import arctouch.challenge.model.Stop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Api implements IApi {
	
	private ObjectMapper _objectMapper;
	
	public Api() {
		_objectMapper = new ObjectMapper();
	}

	@Override
	public List<Route> findRoutesByStopName(String stopName) {
		String formatedStopName = String.format("%%%s%%", stopName);
		String parameters = getJsonParameter("stopName", formatedStopName);
		
		String jsonResponse = executeRequest("findRoutesByStopName", parameters);
		
		ApiResponse<Route> result = null;
		try
		{
			result = _objectMapper.readValue(jsonResponse, new TypeReference<ApiResponse<Route>>() { });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result.getRows();
	}

	@Override
	public List<Stop> findStopsByRouteId(int routeId) {
		String parameters = getJsonParameter("routeId", Integer.toString(routeId));
		
		String jsonResponse = executeRequest("findStopsByRouteId", parameters);

		ApiResponse<Stop> result = null;
		try
		{
			result = _objectMapper.readValue(jsonResponse, new TypeReference<ApiResponse<Stop>>() { });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result.getRows();
	}

	@Override
	public List<Departure> findDeparturesByRouteId(int routeId) {
		String parameters = getJsonParameter("routeId", Integer.toString(routeId));
		
		String jsonResponse = executeRequest("findDeparturesByRouteId", parameters);

		ApiResponse<Departure> result = null;
		try
		{
			result = _objectMapper.readValue(jsonResponse, new TypeReference<ApiResponse<Departure>>() { });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result.getRows();
	}
	
	private String getJsonParameter(String paramName, String paramValue) {
		//in the future there can be an overload of this function that receives a key-value pair list as parameter,
		//for the cases where the JSON parameter returned must have more than only one property.
		JSONObject result = new JSONObject();
		JSONObject inner = new JSONObject();
		try
		{
			inner.put(paramName, paramValue);
			result.put("params", inner);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	private String executeRequest(String resource, String jsonParameters) {
		String endpoint = String.format("https://dashboard.appglu.com/v1/queries/%s/run", resource);
		
        HttpPost post = new HttpPost(endpoint);
        post.setHeader("Authorization", "Basic V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==");
        post.setHeader("X-AppGlu-Environment", "staging");
        post.setHeader("Content-Type", "application/json");
        
        String result = null;
        try
        {
			post.setEntity(new StringEntity(jsonParameters, "UTF-8"));
			
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			
			result = EntityUtils.toString(entity, "UTF-8");
		}
        catch (Exception e)
        {
			e.printStackTrace();
		}
        
        return result;
	}
}
