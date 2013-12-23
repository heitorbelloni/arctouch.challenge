package arctouch.challenge.app;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import arctouch.challenge.model.MessageConstants;
import arctouch.challenge.model.Route;

public class RouteListAdapter extends ArrayAdapter<Route> {
	private final Context context;
	private final List<Route> values;
    
    public RouteListAdapter(Context context, List<Route> values) {
    	super(context, R.layout.list_routes_row, values);
    	this.context = context;
    	this.values = values;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	View rowView = inflater.inflate(R.layout.list_routes_row, parent, false);
    	
    	Route route = values.get(position);
    	
    	TextView routeId = (TextView) rowView.findViewById(R.id.text_route_id);
    	routeId.setText(Integer.toString(route.getId()));
    	
    	TextView routeName = (TextView) rowView.findViewById(R.id.text_route_name);
    	routeName.setText(route.getShortName() + " - " + route.getLongName());
    	
    	routeName.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View view) {
    			LinearLayout parent = (LinearLayout) view.getParent();
    			TextView routeIdTextView = (TextView) parent.findViewById(R.id.text_route_id);
    			TextView routeNameTextView = (TextView) parent.findViewById(R.id.text_route_name);
    			
    			String routeId = routeIdTextView.getText().toString();
    			String routeName = routeNameTextView.getText().toString();
    			
    			Intent intent = new Intent(view.getContext(), DetailsActivity.class);
    			
    	    	intent.putExtra(MessageConstants.ROUTE_ID_MESSAGE, routeId);
    	    	intent.putExtra(MessageConstants.ROUTE_NAME_MESSAGE, routeName);
    	    	view.getContext().startActivity(intent);
    		}
    	});
    	
    	return rowView;
    }
}
