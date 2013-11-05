package com.seventh.busquery.tab;
/**
 * 换乘查询
 */

import com.seventh.busquery.R;
import com.seventh.busquery.dao.BusDao;
import com.seventh.busquery.dao.impl.BusDaoImpl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Change_Query extends Activity {
	
	
	private EditText txt_begin_stop;
	private EditText txt_end_stop;
	private Button bt_change_query;//换乘查询按钮
	private Button bt_swap_begin_end;//起点终点交换按钮
	
	public static String startstop="";
	public static String endstop="";
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_query_layout);
		
		txt_begin_stop=(EditText) findViewById(R.id.txt_begin_stop);
		txt_end_stop=(EditText) findViewById(R.id.txt_end_stop);
		
		bt_change_query=(Button) findViewById(R.id.bt_change_query);
		bt_swap_begin_end=(Button) findViewById(R.id.bt_swap_begin_end);
		
		//if(txt_begin_stop.)
		
		//换乘查询
		bt_change_query.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				startstop=txt_begin_stop.getText().toString();
				endstop=txt_end_stop.getText().toString();
				BusDao bd=new BusDaoImpl(getApplicationContext());
				String ttt=bd.searchStop("aaa").toString();
				Toast.makeText(getApplicationContext(), ttt, 1).show();
			}
		});
		//交换起点和终点
		bt_swap_begin_end.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startstop=txt_begin_stop.getText().toString();
				endstop=txt_end_stop.getText().toString();
				String temp=startstop;
				startstop=endstop;
				endstop=temp;
				txt_begin_stop.setText(startstop);
				txt_end_stop.setText(endstop);
			}
		});
	}
}
