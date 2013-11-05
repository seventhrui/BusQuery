package com.seventh.busquery.dao.impl;
/**
 * BusDao 实现类(进行数据操作)
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.seventh.busquery.dao.BusDao;
import com.seventh.busquery.db.busSQLiteOpenHelper;

public class BusDaoImpl implements BusDao {
	private busSQLiteOpenHelper bussqlhelper;
	public BusDaoImpl(Context context){
		bussqlhelper=new busSQLiteOpenHelper(context);
	}
	@Override
	public Object searchChange(String beginstop, String endstop) {
		SQLiteDatabase db=bussqlhelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("SELECT * FROM Stop WHERE StopName=?", new String[]{beginstop});
		boolean rs=cursor.moveToNext();
		cursor.close();
		db.close();
		return rs;
	}

	@Override
	public List<HashMap<String, String>> searchStop(String stop) {
		SQLiteDatabase db=bussqlhelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("SELECT s.[StopName],r.[RouteName] FROM Stop s,RouteT0 rt,Route r WHERE s.[StopName]=? AND s.[StopKey]=rt.[StartStopKey] AND r.[RouteKey]=rt.[RouteKey] AND r.[RouteName] NOT LIKE '%[下行]' GROUP BY r.[RouteName] ORDER BY r.[RouteKey]",new String[]{stop});
		List<HashMap<String, String>> result=new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap;
		while(cursor.moveToNext()){
			hashMap=new HashMap<String, String>();
			String s1=cursor.getString(0);
			String s2=cursor.getString(1);
			hashMap.put("testItem", s1);
			hashMap.put("testStatus", s2);
			result.add(hashMap);
		}
		/*for(String s:result){
			Log.v("路线", s);
		}*/
		cursor.close();
		db.close();
		return result;
	}

	@Override
	//List<HashMap<String, String>>
	public List<HashMap<String, String>> searchRoute(String route) {
		SQLiteDatabase db=bussqlhelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("SELECT r.[RouteName],s.[StopName] FROM Route r,RouteT0 rt,Stop s WHERE r.[RouteName]=? AND rt.[RouteKey]=r.[RouteKey] AND s.[StopKey]=rt.[StartStopKey] GROUP BY s.[StopName] ORDER BY s.[StopKey]",new String[]{route});
		List<HashMap<String, String>> result=new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap;
		while(cursor.moveToNext()){
			hashMap=new HashMap<String, String>();
			String s1=cursor.getString(0);
			String s2=cursor.getString(1);
			hashMap.put("testItem", s1);
			hashMap.put("testStatus", s2);
			result.add(hashMap);
		}
		/*for(String s:result){
			Log.v("站点", s);
		}*/
		cursor.close();
		db.close();
		return result;
	}
}
