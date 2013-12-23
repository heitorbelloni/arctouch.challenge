package arctouch.challenge.app;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import arctouch.challenge.api.Api;
import arctouch.challenge.api.IApi;
import arctouch.challenge.model.Route;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void searchRoute(View view) {
		EditText streetEdit = (EditText)findViewById(R.id.edit_street);
		String streetName = streetEdit.getText().toString();
		
		new FindRoutesByStopNameTask(this).execute(streetName);
	}
	
	public void setRoutesListView(List<Route> routeList)
	{
		ListView listView = (ListView) findViewById(R.id.list_routes);
		TextView listRoutesEmpty = (TextView) findViewById(R.id.list_routes_empty);
		
		if(routeList.isEmpty())
		{
			listView.setAdapter(null); //clear the listview;
			listRoutesEmpty.setVisibility(View.VISIBLE); //show no results message;
		}
		else
		{
			RouteListAdapter adapter = new RouteListAdapter(this, routeList);
			listView.setAdapter(adapter);
			listRoutesEmpty.setVisibility(View.GONE); //hide no results message
		}
	}
	
	private class FindRoutesByStopNameTask extends AsyncTask<String, Void, List<Route>> {
		private MainActivity activity;
		
		public FindRoutesByStopNameTask(MainActivity activity) {
			this.activity = activity;
		}

		@Override
		protected List<Route> doInBackground(String... params) {
			IApi api = new Api();
			return api.findRoutesByStopName(params[0]);
		}
		
		@Override
		protected void onPostExecute(List<Route> routeList) {
			activity.setRoutesListView(routeList);
		}
	}
}
