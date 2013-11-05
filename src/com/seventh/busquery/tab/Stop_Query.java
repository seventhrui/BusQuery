package com.seventh.busquery.tab;
/**
 * 站点查询
 * 查询经过该站点的线路
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.seventh.busquery.R;
import com.seventh.busquery.dao.BusDao;
import com.seventh.busquery.dao.impl.BusDaoImpl;
import com.seventh.busquery.result.StopResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Stop_Query extends Activity {
	private EditText txt_stop;
	private Button bt_stop_query;
	
	private static String stop="";
	public static List<HashMap<String, String>> routes=null;//存储经过站点线路
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stop_query_layout);
		
		txt_stop=(EditText) findViewById(R.id.txt_stop);
		routes=new ArrayList<HashMap<String, String>>();
		bt_stop_query=(Button) findViewById(R.id.bt_stop_query);
		bt_stop_query.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				stop=txt_stop.getText().toString();
				if(stop.equals("")||stop==null)
					stop="东圃镇";
				BusDao bd=new BusDaoImpl(getApplicationContext());
				routes = bd.searchStop(stop);
				/*for(String s:routes){
					Log.v("路线q", s);
				}*/
				Intent intent=new Intent(Stop_Query.this, StopResult.class);
				startActivity(intent);
			}
		});
	}
}
