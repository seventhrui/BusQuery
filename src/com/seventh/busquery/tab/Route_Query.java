package com.seventh.busquery.tab;
/**
 * 线路查询
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.seventh.busquery.R;
import com.seventh.busquery.dao.BusDao;
import com.seventh.busquery.dao.impl.BusDaoImpl;
import com.seventh.busquery.result.RouteResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Route_Query extends Activity {
	private EditText txt_route;
	private Button bt_route_query;
	
	private static String route="";
	public static List<HashMap<String, String>> stops=null;//存储路线经过站点
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route_query_layout);
		
		txt_route=(EditText) findViewById(R.id.txt_route);
		stops=new ArrayList<HashMap<String, String>>();
		bt_route_query=(Button) findViewById(R.id.bt_route_query);
		bt_route_query.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				route=txt_route.getText().toString();
				if(route.equals("")||route==null)
					route="139[下行]";
				BusDao bd=new BusDaoImpl(getApplicationContext());
				stops=bd.searchRoute(route);
				/*for(String s:stops){
					Log.v("站点q", s);
				}*/
				Intent intent=new Intent(Route_Query.this, RouteResult.class);
				startActivity(intent);
			}
		});
	}
}
