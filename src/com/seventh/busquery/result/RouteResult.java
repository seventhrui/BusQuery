package com.seventh.busquery.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.seventh.busquery.R;
import com.seventh.busquery.tab.Route_Query;
import com.seventh.busquery.util.MyAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class RouteResult extends Activity {
	private ListView stopslistview;
	MyAdapter adapter;
	List<HashMap<String, String>> stop=null;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route_result);
		
		stopslistview=(ListView) findViewById(R.id.stops_listview);
		stop=new ArrayList<HashMap<String, String>>();
		stop=Route_Query.stops;
		
		adapter=new MyAdapter(this,stop);
		stopslistview.setAdapter(adapter);
	}
}
