package arctouch.challenge.api;

import java.util.List;

import arctouch.challenge.model.Departure;
import arctouch.challenge.model.Route;
import arctouch.challenge.model.Stop;

public interface IApi {
	List<Route> findRoutesByStopName(String stopName);
    List<Stop> findStopsByRouteId(int routeId);
    List<Departure> findDeparturesByRouteId(int routeId);
}
