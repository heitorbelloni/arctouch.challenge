package arctouch.challenge.api.test;

import java.util.List;

import junit.framework.TestCase;
import arctouch.challenge.api.Api;
import arctouch.challenge.model.Departure;
import arctouch.challenge.model.Route;
import arctouch.challenge.model.Stop;

public class ApiTest extends TestCase {

	public ApiTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testFindRoutesByStopNameExistingRoute() {
		Api api = new Api();
		List<Route> result = api.findRoutesByStopName("lauro linhares");
		assertNotNull(result);
		assertTrue(!result.isEmpty());
	}
	
	public void testFindRoutesByStopNameNonExistingRoute() {
		Api api = new Api();
		List<Route> result = api.findRoutesByStopName("não existe");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	public void testFindDeparturesByRouteIdExisting() {
		Api api = new Api();
		List<Departure> result = api.findDeparturesByRouteId(22);
		assertNotNull(result);
		assertTrue(!result.isEmpty());
	}
	
	public void testFindDeparturesByRouteIdNonExisting() {
		Api api = new Api();
		List<Departure> result = api.findDeparturesByRouteId(-1);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	public void testFindStopsByRouteIdExisting() {
		Api api = new Api();
		List<Stop> result = api.findStopsByRouteId(22);
		assertNotNull(result);
		assertTrue(!result.isEmpty());
	}
	
	public void testFindStopsByRouteIdNonExisting() {
		Api api = new Api();
		List<Stop> result = api.findStopsByRouteId(-1);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
}
