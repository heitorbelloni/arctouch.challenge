package arctouch.challenge.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import arctouch.challenge.api.Api;
import arctouch.challenge.api.IApi;
import arctouch.challenge.model.Departure;
import arctouch.challenge.model.MessageConstants;
import arctouch.challenge.model.Stop;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		//get messages from intent
		Intent intent = getIntent();
		String routeIdMessage = intent.getStringExtra(MessageConstants.ROUTE_ID_MESSAGE);
		String routeName = intent.getStringExtra(MessageConstants.ROUTE_NAME_MESSAGE);
	    int routeId = Integer.parseInt(routeIdMessage);
		
	    //set route name label
		TextView textView =  (TextView) findViewById(R.id.route_name);
	    textView.setText(routeName);
	    
	    new FindStopsByRouteIdTask(this).execute(routeId);
	    new FindDeparturesByRouteIdTask(this).execute(routeId);
	}

	private void setStopsListView(List<Stop> stopList) {
		Collections.sort(stopList);
		
		List<String> stopsResource = new ArrayList<String>();
		for (Stop stop : stopList) {
			stopsResource.add(stop.getName());
		}
		ListView listViewStops = (ListView) findViewById(R.id.list_stops);
		ArrayAdapter<String> stopsAdapter = new ArrayAdapter<String>(this, R.layout.list_simple_row, stopsResource);
		listViewStops.setAdapter(stopsAdapter);
	}

	private void setTimetableListView(List<Departure> departureList) {
		Collections.sort(departureList);
		
		String weekdays = "WEEKDAYS\n";
		String saturdays = "SATURDAYS\n";
		String sundays = "SUNDAYS\n";
		for (Departure departure : departureList) {
			switch (departure.getCalendar()) {
			case WEEKDAY:
				weekdays = weekdays.concat(departure.getTime() + " ");
				break;
			case SATURDAY:
				saturdays = saturdays.concat(departure.getTime() + " ");
				break;
			case SUNDAY:
				sundays = sundays.concat(departure.getTime() + " ");
				break;
			default:
				break;
			}
		}
		
		List<String> departuresResource = new ArrayList<String>();
		departuresResource.add(weekdays);
		departuresResource.add(saturdays);
		departuresResource.add(sundays);
		
		ListView listViewTimetable = (ListView) findViewById(R.id.list_timetable);
		ArrayAdapter<String> departuresAdapter = new ArrayAdapter<String>(this, R.layout.list_simple_row, departuresResource);
		listViewTimetable.setAdapter(departuresAdapter);
	}

	private class FindStopsByRouteIdTask extends AsyncTask<Integer, Void, List<Stop>> {
		private DetailsActivity activity;
		
		public FindStopsByRouteIdTask(DetailsActivity activity) {
			this.activity = activity;
		}

		@Override
		protected List<Stop> doInBackground(Integer... params) {
			IApi api = new Api();
			return api.findStopsByRouteId(params[0]);
		}
		
		@Override
		protected void onPostExecute(List<Stop> stopList) {
			activity.setStopsListView(stopList);
		}
	}

	private class FindDeparturesByRouteIdTask extends AsyncTask<Integer, Void, List<Departure>> {
		private DetailsActivity activity;
		
		public FindDeparturesByRouteIdTask(DetailsActivity activity) {
			this.activity = activity;
		}

		@Override
		protected List<Departure> doInBackground(Integer... params) {
			IApi api = new Api();
			return api.findDeparturesByRouteId(params[0]);
		}
		
		@Override
		protected void onPostExecute(List<Departure> departureList) {
			activity.setTimetableListView(departureList);
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
