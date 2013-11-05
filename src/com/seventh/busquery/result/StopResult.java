package com.seventh.busquery.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.seventh.busquery.R;
import com.seventh.busquery.tab.Stop_Query;
import com.seventh.busquery.util.MyAdapter;


/**
 * 站点查询结果
 * 显示经过该站点的线路
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class StopResult extends Activity {
	private ListView routeslistview;
	MyAdapter adapter;
	List<HashMap<String, String>> route=null;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stop_result);
		
		routeslistview=(ListView) findViewById(R.id.routes_listview);
		route=new ArrayList<HashMap<String, String>>();
		route=Stop_Query.routes;
		
		adapter=new MyAdapter(this,route);
		routeslistview.setAdapter(adapter);
	}
}
